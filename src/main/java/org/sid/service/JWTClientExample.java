package org.sid.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class JWTClientExample {

    static final String URL_LOGIN = "http://localhost:8448/login";
    static final String URL_COMMANDES = "http://localhost:8444";

    // POST Login
    // @return "Authorization string".
    public static String postLogin(String username, String password) {

        // Request Header
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        // Request Body
     /*   MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
        parametersMap.add("username", username);
        parametersMap.add("password", password);*/

        // Request Entity
        HttpEntity<User> requestEntity = new HttpEntity<User>(new User(username,password), headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // POST Login
        ResponseEntity<String> response = restTemplate.exchange(URL_LOGIN, //
                HttpMethod.POST, requestEntity, String.class);

        HttpHeaders responseHeaders = response.getHeaders();

        List<String> list = responseHeaders.get("Authorization");
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public static void editUsernameClients(String lastUserName,String newUserName, String authorizationString) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        //
        // Authorization string (JWT)
        //
        headers.set("Authorization", "Bearer "+authorizationString);
        //
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        // Request to return JSON format
        //headers.setContentType(MediaType.APPLICATION_JSON);

        FormEditUser formEditUser = new FormEditUser(lastUserName,newUserName);
        // HttpEntity<String>: To get result as String.
        HttpEntity<FormEditUser> entity = new HttpEntity<FormEditUser>(formEditUser,headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<Void> response = restTemplate.exchange(URL_COMMANDES+"/editUserOrders", //
                HttpMethod.POST, entity, Void.class);

       // String result = response.getBody();

        System.out.println("all goood");
    }

    /*public static void main(String[] args) {
        String username = "tom";
        String password = "123";

        String authorizationString = postLogin(username, password);

        System.out.println("Authorization String=" + authorizationString);

        // Call REST API:
        callRESTApi(URL_EMPLOYEES, authorizationString);
    }*/

}

@Data
class FormEditUser{

    private String lastUserName;
    private String newUserName;


    public FormEditUser(String lastUserName,String newUserName){

        this.lastUserName = lastUserName;
        this.newUserName = newUserName;
    }
}

@Data
class User{

    private String username;
    private String password;


    public User(String username,String password){

        this.username = username;
        this.password = password;
    }
}