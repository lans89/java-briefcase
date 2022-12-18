package hn.iargueta.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import hn.iargueta.zuulgateway.enums.ZuulFilterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("prefilter")
@Slf4j
public class Prefilter extends ZuulFilter {

    @Override
    public String filterType() {
        return ZuulFilterType.PRE.getName();
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("Filtro: {}", this.getClass().getSimpleName());
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("Test", "TestSample");
        var headers = ctx.getRequest().getHeaderNames();
        headers.asIterator().forEachRemaining(s -> {
            log.info("header: {}, value: {}",s,ctx.getRequest().getHeader(s));
        });
        log.info("path: {}, method: {}", ctx.getRequest().getRequestURI(), ctx.getRequest().getMethod());
        return null;
    }
}