package framework.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

@SuppressWarnings({"rawtypes","unchecked"})
public class Session extends HttpServletSessionWrapper {
    
    HttpSession session;
    
    public Session(HttpSession session){
        super(session);
    }
    
    public boolean has(String name)
    {
        return this.get(name) != null;
    }
    
    public Object get(String name)
    {
        return this.get(name, null);
    }
    
    public Object get(String name, Object defaultValue)
    {
        Object value = this.getAttribute(name);
        if(value == null)
            value = defaultValue;
        
        return value;
    }
    
    public Object pull(String key)
    {
        return this.pull(key, null);
    }
    
    public Object pull(String key, String defaultValue)
    {
        Object value = this.get(key, defaultValue);
        this.removeAttribute(key);
        
        return value;
    }
    
    public boolean hasOldInput()
    {
        return this.hasOldInput(null);
    }
    
    
    public boolean hasOldInput(String key)
    {
        Object oldInput = this.getOldInput(key);
        if(oldInput == null)
            return false;
        
        if(oldInput instanceof HashMap<?, ?> ){
            return ((HashMap) oldInput).size() > 0;
        }
        
        return true;
    }
    
    public Object getOldInput()
    {
        return this.getOldInput(null, null);
    }
    
    public Object getOldInput(String key)
    {
        return this.getOldInput(key, null);
    }
    
    public Object getOldInput(String key, Object defaultValue)
    {
        Map<String,Object> input = (Map<String, Object>) this.get("_old_input", new HashMap<String, Object>());
        
        if(key == null)
            return input;
        
        Object value = input.get(key);
        if(value == null)
            value = defaultValue;
        
        return value;
    }
    
    public void set(String name, Object value)
    {
        this.setAttribute(name, value);
    }
    
    public void put(List<Map<String, List<Object>>> keys)
    {
        for(Map<String, List<Object>> map : keys){
            for(String key : map.keySet()){
                Object value = map.get(key);
                this.put(key,value);
            }
        }
    }
    
    public void put(String key, Object value){
        this.set(key,value);
    }
    
    public void push(String key, Object value)
    {
        List<Object> list = (List<Object>) this.get(key,new ArrayList<Object>());
        
        list.add(value);
        
        this.set(key,list);
    }
    
    public void flash(String key, Object value)
    {
        this.put(key, value);
        
        this.push("flash.new", value);
        
        this.removeFromOldFlashData(key);
    }
    
    public void flashInput(Map<String, Object> value)
    {
        this.flash("_old_input", value);
    }
    
    public void reflash()
    {
        this.mergeNewFlashes((Set<String>) this.get("flash.old", new HashSet<String>()));
        
        this.put("flash.old", new HashSet<String>());
    }
    
    public void keep(Set<String> keys)
    {
        this.mergeNewFlashes(keys);
        
        this.removeFromOldFlashData(keys);
    }
    
    public void keep(String key)
    {
        Set<String> keys = new HashSet<String>();
        keys.add(key);
        
        this.keep(keys);
    }
    
    public void ageFlashData()
    {
        Set<String> keys = (Set<String>) this.get("flash.old", new HashSet<String>());
        for(String key : keys){
            this.forget(key);
        }
    }
    
    protected void mergeNewFlashes(Set<String> keys)
    {
        Set<String> newKeys = (Set<String>) this.get("flash.new", new HashSet<String>());
        newKeys.addAll(keys);
        
        this.put("flash.new",newKeys);
    }
    
    protected void removeFromOldFlashData(Set<String> keys)
    {
        Set<String> oldKeys = (Set<String>) this.get("flash.old", new HashSet<String>());
        oldKeys.removeAll(keys);
        
        this.put("flash.old",oldKeys);
    }
    
    protected void removeFromOldFlashData(String key)
    {
        Set<String> oldKeys = (Set<String>) this.get("flash.old", new HashSet<String>());
        oldKeys.remove(key);
        
        this.put("flash.old",oldKeys);
    }
    
    public void forget(String key)
    {
        this.removeAttribute(key);
    }
}
