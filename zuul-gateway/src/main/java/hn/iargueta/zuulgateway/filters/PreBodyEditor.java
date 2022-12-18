package hn.iargueta.zuulgateway.filters;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import hn.iargueta.zuulgateway.enums.ZuulFilterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
@Slf4j
public class PreBodyEditor extends ZuulFilter {
    @Override
    public String filterType() {
        return ZuulFilterType.PRE.getName();
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("filtro: {}", this.getClass().getSimpleName());
        RequestContext context = RequestContext.getCurrentContext();
        try {
            RequestContext.getCurrentContext().getResponseBody();
        }
        catch (Exception e) {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return null;
    }
}
