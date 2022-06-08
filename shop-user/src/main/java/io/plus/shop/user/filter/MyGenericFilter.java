package io.plus.shop.user.filter;

import com.alibaba.nacos.shaded.io.opencensus.trace.Span;
import com.alibaba.nacos.shaded.io.opencensus.trace.Tracer;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Description: 链路过滤器
 *
 * @author Super_light
 * @date 6/8/22 11:25 PM
 */
@Component
@Order( Ordered.HIGHEST_PRECEDENCE + 6)
public class MyGenericFilter extends GenericFilterBean {

    private Pattern skipPattern = Pattern.compile(SleuthWebProperties.DEFAULT_SKIP_PATTERN);

    private final Tracer tracer;
    public MyGenericFilter(Tracer tracer){
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)){
            throw new ServletException("只支持HTTP访问");
        }
        Span currentSpan = this.tracer.getCurrentSpan();
        if (currentSpan == null) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        boolean skipFlag = skipPattern.matcher(httpServletRequest.getRequestURI()).matches();
        if (!skipFlag){
            String traceId = currentSpan.getContext().getTraceId().toString();
            httpServletRequest.setAttribute("traceId", traceId);
            httpServletResponse.addHeader("SLEUTH-HEADER", traceId);
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}

