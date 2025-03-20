package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenController {

    @Value("${oauth.token.url}")
    private String tokenUrl;

    @Value("${oauth.client.id}")
    private String clientId;

    @Value("${oauth.client.secret}")
    private String clientSecret;

    @Value("${oauth.scope}")
    private String scope;

    // For reference:

    // curl --request POST \
    // --url https://mbn-provider.authentication.eu12.hana.ondemand.com/oauth/token \
    // --header 'Content-Type: application/x-www-form-urlencoded' \
    // --data grant_type=client_credentials \
    // --data response_type=token \
    // --data 'client_id=sb-interview_demo_transport_app!b923597' \
    // --data 'client_secret=e5a58e12-6849-4833-88005eee585f347c$H0EkGqSXYJXVJTOMOocIcfufzbPmpeastGoMvrbKfIQ=' \
    // --data 'scope=interview_demo_transport_app!b923597.transportread'

    @PostMapping("/get-token")
    public ResponseEntity<String> getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("scope", scope);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(tokenUrl, entity, String.class);
    }
}
