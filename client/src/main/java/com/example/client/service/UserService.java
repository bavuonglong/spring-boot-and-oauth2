package com.example.client.service;

import com.example.client.dto.ServiceResponse;
import com.example.client.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Value("${resources-server.host}")
    private String host;

    @Value("${resources-server.user-history-endpoint}")
    private String userHistoryEndpoint;

    @Value("${resources-server.create-user-endpoint}")
    private String createUserEndpoint;

    @Value("${resources-server.user-logout-endpoint}")
    private String userLogoutEndpoint;

    private OAuth2RestTemplate oAuth2RestTemplate;

    private RestTemplate restTemplate;

    public UserService(OAuth2RestTemplate oAuth2RestTemplate, RestTemplate restTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
        this.restTemplate = restTemplate;
    }

    public List<Date> getUserHistory() {
        ServiceResponse response = oAuth2RestTemplate.getForObject(host + userHistoryEndpoint, ServiceResponse.class);
        return (List<Date>) response.getData();
    }

    public void createUser(UserDTO userDTO) {
        restTemplate.postForObject(host + createUserEndpoint, userDTO, ServiceResponse.class);
    }

    public void logout() {
        oAuth2RestTemplate.getForObject(host + userLogoutEndpoint + "?access_token=" + oAuth2RestTemplate.getAccessToken().getValue(), ServiceResponse.class);
    }
}
