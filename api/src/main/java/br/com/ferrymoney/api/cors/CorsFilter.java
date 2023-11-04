package br.com.ferrymoney.api.cors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    private final String origemPermitida = "http://localhost:8080";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Access-Control-Allow-Origin", origemPermitida);
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod()) && origemPermitida.equals(req.getHeader("Origin"))) {
            resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            resp.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            resp.addHeader("Access-Control-Max-Age", "3600");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
