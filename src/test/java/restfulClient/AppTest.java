package restfulClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @org.junit.Test
    public void test() {
        try {

            Client client = Client.create();
            WebResource webResource = client
                    .resource("http://merchant.qa.gep-api.stg-jpe1.rpaas.stg.jp.local/shipping-status/list?mallId=my");


            webResource.header("X-Client-Id", "ui");
            webResource.setProperty("X-Client-Id", "ui");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                System.out.println(response.getEntity(String.class));
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
