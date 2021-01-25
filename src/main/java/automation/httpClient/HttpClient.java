package automation.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClient {

    public final String response(final String url) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet request = new HttpGet(url);
        request.addHeader(HttpHeaders.AUTHORIZATION,"Bearer ");

        final CloseableHttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new RuntimeException(response.getEntity().toString());
        }
        final HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity);
    }

}
