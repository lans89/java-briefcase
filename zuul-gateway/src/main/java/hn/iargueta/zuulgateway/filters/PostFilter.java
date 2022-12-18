package hn.iargueta.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import hn.iargueta.zuulgateway.enums.ZuulFilterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return ZuulFilterType.POST.getName();
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("filtro: {}", this.getClass().getSimpleName());
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulResponseHeader("X-test", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        ctx.getZuulResponseHeaders().stream().forEach(s -> {
            log.debug("header: {} -> {}", s.first(), s.second());
        });
        return null;
    }
}
