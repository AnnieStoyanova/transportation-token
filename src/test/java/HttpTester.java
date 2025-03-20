import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpTester {

    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {
        HttpClient client = HttpClients.createDefault();

        try {

            HttpPost post = new HttpPost("http://localhost:8080/get-token");
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            StringEntity postEntity = new StringEntity("grant_type=client_credentials&client_id=sb-interview_demo_transport_app!b923597&client_secret=e5a58e12-6849-4833-8800-5eee585f347c$H0EkGqSXYJXVJTOMOocIcfufzbPmpeastGoMvrbKfIQ=&scope=interview_demo_transport_app!b923597.transportread");
            post.setEntity(postEntity);
            HttpResponse postResponse = client.execute(post);
            String jsonResponse = EntityUtils.toString(postResponse.getEntity());

           JsonNode rootNode = mapper.readTree(jsonResponse);
           String accessToken = rootNode.path("access_token").asText();  // Extracting access token

            System.out.println("Access Token: " + accessToken);

            HttpGet get = new HttpGet("http://localhost:8080/transports");
            get.setHeader("Authorization", "Bearer " + accessToken);  // Modify this if needed to extract just the token part
            HttpResponse getResponse = client.execute(get);
            String responseString = EntityUtils.toString(getResponse.getEntity());
            System.out.println("Transports: " + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
