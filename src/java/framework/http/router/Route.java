package framework.http.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("rawtypes")
public class Route {
    
    protected String name;
    protected String regex;
    
    protected Class controllerClass;
    protected String methodName;
    
    protected List<String> parameterNames;
    protected Resolver resolver;
    
    public Route(String name, String regex, Class controllerClass, String methodName){
        this.setName(name);
        this.setRegex(regex);
        this.setControllerClass(controllerClass);
        this.setMethodName(methodName);
        this.parameterNames = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
    
    public Class getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class controllerClass) {
        this.controllerClass = controllerClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }

    public void addParameter(String parameterName) {
        this.parameterNames.add(parameterName);
    }
    
    public HashMap<String, String> extractParameters(String url){
        HashMap<String, String> parameters = new HashMap<String, String>();
        
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(url);

        int groups = matcher.groupCount();

        int i = 0;
        if (matcher.matches()) {
            while (groups > i) {
                String key = this.parameterNames.get(i);
                String val = matcher.group(i + 1);

                parameters.put(key, val);
                i++;
            }
        }
        
        return parameters;
    }
    
    public String injectParameters(HashMap<String, String> parameters){
        String url = this.regex;
        
        for(String parameter : parameters.values()){
            url = url.replaceFirst("(\\(\\[\\^/\\]\\+\\))", parameter);
        }
        
        return url;
    }
    
    public boolean urlMatches(String url){
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(url);
        
        return matcher.matches();
    }
    
}
