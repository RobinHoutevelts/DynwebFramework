package framework.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Container;
import framework.event.Bag;
import framework.event.Event;
import framework.event.EventEmitter;
import framework.http.Request;
import framework.http.Response;
import framework.http.Session;
import framework.http.router.Resolver;
import framework.http.router.Route;
import framework.http.router.Router;

public class RouteFilter implements Filter {
    
    protected Container app;
    protected Router router;
    
    protected Route     route;
    protected Resolver  resolver;
    protected Request   request;
    protected Response  response;

    @Override
    public void init(FilterConfig config) throws ServletException {}
    
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        
        if (request instanceof HttpServletRequest){
            this.processRequest((HttpServletRequest) request, (HttpServletResponse) response);
        }
        
        if(this.resolver != null){
            this.resolver.resolve(this.request, this.response);
            
            // Stoppen met de ketting want we hebben output gestuurd
            return;
        }

        filterChain.doFilter(request, response);
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
        Container app = (Container) req.getServletContext().getAttribute("app");
        Router router = (Router) app.make("Router");
        EventEmitter emitter = (EventEmitter) app.make("EventEmitter");
        
        Session session = new Session(req.getSession(true));
        Request request = new Request(req);
                request.setSession(session);
        Response response = new Response(res);
        
        String URI = request.getRequestURI().replaceAll(request.getContextPath(), "");
        
        String requestMethod = request.getMethod();
        
        Route route = router.getRouteByURL(URI,requestMethod);
        
        if(route == null)
            return;
        
        Resolver resolver = router.getResolver(route);
        
        if(resolver == null)
            return;
        
        Bag eventBag = new Bag();
        eventBag.setAttribute("request", request);
        eventBag.setAttribute("response", response);
        eventBag.setAttribute("route", route);
        eventBag.setAttribute("resolver", resolver);
        
        emitter.fire(new Event("router.resolved", eventBag));
        
        this.route      = (Route)    eventBag.getAttribute("route");
        this.resolver   = (Resolver) eventBag.getAttribute("resolver");
        this.request    = (Request)  eventBag.getAttribute("request");
        this.response   = (Response) eventBag.getAttribute("response");
    }

}
