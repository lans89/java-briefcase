package hn.iargueta.zuulgateway.filters;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import hn.iargueta.zuulgateway.enums.ZuulFilterType;
import hn.iargueta.zuulgateway.utils.ContentEncodingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.brotli.BrotliCompressorInputStream;
import org.apache.commons.compress.compressors.brotli.BrotliUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
@Slf4j
public class PostBodyEditor extends ZuulFilter {

    @Autowired
    private ContentEncodingUtils contentEncodingUtils;

    @Override
    public String filterType() {
        return ZuulFilterType.POST.getName();
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("filtro: {}", this.getClass().getSimpleName());
        RequestContext context = RequestContext.getCurrentContext();
        context.getZuulResponseHeaders().stream().forEach(s -> {
            log.debug("header: {} -> {}", s.first(), s.second());
        });
        try {
            String body=contentEncodingUtils.uncompressBody(context);
            log.info("BODY: {}", body);
            //Si no hay body retorna
            if(body.isBlank())
                return null;
            //Modify
            body += "<testing/>";
            contentEncodingUtils.compressBody(context, body);

        }
        catch (Exception e) {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return null;
    }
}
