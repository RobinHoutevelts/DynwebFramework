package framework.event;

import java.util.Collection;
import java.util.HashMap;

public class Bag {

    protected HashMap<String, Object> attributes = new HashMap<String, Object>();

    public Object getAttribute(String name) {
        Object attribute = null;
        if (this.attributes.containsKey(name))
            attribute = this.attributes.get(name);

        return attribute;
    }

    public Collection<String> getAttributeNames() {
        return this.attributes.keySet();
    }

    public void setAttribute(String name, Object object) {
        this.attributes.put(name, object);
    }

}
