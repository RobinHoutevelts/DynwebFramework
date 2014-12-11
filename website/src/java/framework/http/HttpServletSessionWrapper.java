package framework.http;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

abstract class HttpServletSessionWrapper implements HttpSession{
    
    private HttpSession session;
    
    public HttpServletSessionWrapper(HttpSession session){
        this.session = session;
    }

    @Override
    public Object getAttribute(String arg0) {
        return this.session.getAttribute(arg0);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return this.session.getAttributeNames();
    }

    @Override
    public long getCreationTime() {
        return this.session.getCreationTime();
    }

    @Override
    public String getId() {
        return this.session.getId();
    }

    @Override
    public long getLastAccessedTime() {
        return this.session.getLastAccessedTime();
    }

    @Override
    public int getMaxInactiveInterval() {
        return this.session.getMaxInactiveInterval();
    }

    @Override
    public ServletContext getServletContext() {
        return this.session.getServletContext();
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return this.session.getSessionContext();
    }

    @Override
    public Object getValue(String arg0) {
        return this.session.getValue(arg0);
    }

    @Override
    public String[] getValueNames() {
        return this.session.getValueNames();
    }

    @Override
    public void invalidate() {
        this.session.invalidate();
    }

    @Override
    public boolean isNew() {
        return this.session.isNew();
    }

    @Override
    public void putValue(String arg0, Object arg1) {
        this.session.putValue(arg0, arg1);
    }

    @Override
    public void removeAttribute(String arg0) {
        this.session.removeAttribute(arg0);
    }

    @Override
    public void removeValue(String arg0) {
        this.session.removeValue(arg0);
    }

    @Override
    public void setAttribute(String arg0, Object arg1) {
        this.session.setAttribute(arg0, arg1);
    }

    @Override
    public void setMaxInactiveInterval(int arg0) {
        this.session.setMaxInactiveInterval(arg0);
    }
}
