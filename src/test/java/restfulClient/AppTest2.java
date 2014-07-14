package restfulClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Unit test for simple App.
 */
public class AppTest2
{
    @org.junit.Test
    public void test() {
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    "http://merchant.qa.gep-api.stg-jpe1.rpaas.stg.jp.local/shipping-location/list?mallId=gb");
            getRequest.addHeader("accept", "application/json");
            getRequest.addHeader("X-Client-Id", "uiui");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
//                System.out.println(output);
                ObjectMapper mapper = new ObjectMapper();
//                mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
                Object json = mapper.readValue(output, Object.class);
                String indented = mapper.defaultPrettyPrintingWriter().writeValueAsString(json);
                System.out.println(indented);
            }
            httpClient.getConnectionManager().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
