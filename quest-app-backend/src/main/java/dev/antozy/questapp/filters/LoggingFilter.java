package dev.antozy.questapp.filters;

import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ThreadContext.clearAll();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Map<String,String> httpHeaders = new HashMap<String, String>();

        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            httpHeaders.put(key.toLowerCase(), value);
        }

        if(!httpHeaders.isEmpty()){
            httpHeaders.get("Host");
            ThreadContext.put("HOST", httpHeaders.get("host"));
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
