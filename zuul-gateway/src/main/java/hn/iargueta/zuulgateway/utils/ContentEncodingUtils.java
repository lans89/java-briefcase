package hn.iargueta.zuulgateway.utils;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.brotli.BrotliCompressorInputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Component
@Slf4j
public class ContentEncodingUtils {

    public String uncompressBody(RequestContext context) throws Exception{
        String body="";
        final InputStream responseDataStream = context.getResponseDataStream();

        if(responseDataStream == null) {
            log.debug("BODY: {}", "");
            return null;
        }

        var isCompressedContent = context.getZuulResponseHeaders().stream()
                .filter(pair ->
                        pair.first().equals(HttpHeaders.CONTENT_ENCODING)).findFirst();
        log.info("is gzipped? {}", isCompressedContent);
        if(isCompressedContent.isPresent()){
            if(isCompressedContent.get().second().equals("gzip"))
                body = StreamUtils.copyToString(new GZIPInputStream(responseDataStream),
                        StandardCharsets.UTF_8);
            else if (isCompressedContent.get().second().equals("deflate"))
                body = StreamUtils.copyToString(new DeflaterInputStream(responseDataStream)
                        , StandardCharsets.UTF_8);
            else
                body = StreamUtils.copyToString(new BrotliCompressorInputStream(responseDataStream)
                        , StandardCharsets.UTF_8);
        }else{
            // normal
            body =  StreamUtils.copyToString(responseDataStream,StandardCharsets.UTF_8);
        }

        return body;
    }

    public void compressBody(RequestContext context, String body) throws Exception{
        var isCompressedContent = context.getZuulResponseHeaders().stream()
                .filter(pair ->
                        pair.first().equals(HttpHeaders.CONTENT_ENCODING)).findFirst();

        if(isCompressedContent.isPresent()){
            context.put("zuulResponseHeaders", context.getZuulResponseHeaders().stream()
                    .filter(pair -> !pair.first().equals(HttpHeaders.CONTENT_ENCODING))
                    .collect(Collectors.toList()));
            context.addZuulResponseHeader(HttpHeaders.CONTENT_ENCODING, "gzip");
            //Compress again
            ByteArrayOutputStream bos = new ByteArrayOutputStream(body.length());
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(body.getBytes(StandardCharsets.UTF_8));
            gzip.close();
            byte[] compressed = bos.toByteArray();
            bos.close();
            context.setResponseDataStream(new ByteArrayInputStream(compressed));
        }else{
            context.setResponseBody(body);
        }
    }
}
