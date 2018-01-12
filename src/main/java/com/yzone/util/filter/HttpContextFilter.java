package com.yzone.util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpContextFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
    }

    /**
     * 允许ajax跨域
     *
     * @param request
     * @param response
     * @param chain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept");

        chain.doFilter(request, httpServletResponse);
    }

    @Override
    public void destroy() {
    }
}
