package framework.http.router;

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
import framework.http.Request;
import framework.http.Response;

public class RouteFilter implements Filter {

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

        filterChain.doFilter(request, response);
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
        Container app = (Container) req.getServletContext().getAttribute("app");
        Router router = (Router) app.make("Router");
        
        Request request = new Request(req);
        Response response = new Response(res);
        
        String URI = request.getRequestURI().replaceAll(request.getContextPath(), "");
        
        String requestMethod = request.getMethod();

        Resolver resolver = router.getResolver(URI,requestMethod);
        
        if(resolver != null)
            resolver.resolve(request, response);
    }

}
