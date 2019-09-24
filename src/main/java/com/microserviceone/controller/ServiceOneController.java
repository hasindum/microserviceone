package com.microserviceone.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/")
public class ServiceOneController {

    @PostMapping(value = "/reverse")
    public String Doreverse(@RequestBody String payload) {
    	System.out.println(payload);
    	String[] words = payload.split(" ");
    	String reversedString = "";
    	for (int i = 0; i < words.length; i++)
            {
               String word = words[i]; 
               String reverseWord = "";
               for (int j = word.length()-1; j >= 0; j--) 
    	   {
    		reverseWord = reverseWord + word.charAt(j);
    	   }
    	   reversedString = reversedString + reverseWord + " ";
    	}
    	
        final String uri = "http://service-two-springboot-demoweb.default.svc.cluster.local:8080/random";
//        final String uri = "http://localhost:8080/random";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        String jsonStr = "{\"message\": \"'"+reversedString+"'\", \"random\": \"'"+result+"'\"}";
        Gson gson = new Gson();
        JsonElement jsonTree = gson.toJsonTree(jsonStr);
        System.out.println(jsonTree.toString());        
        
        
        return jsonTree.toString();
    }
}
