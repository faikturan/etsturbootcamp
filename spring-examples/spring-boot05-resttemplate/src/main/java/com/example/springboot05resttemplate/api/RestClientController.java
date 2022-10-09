package com.example.springboot05resttemplate.api;

import com.example.springboot05resttemplate.model.KisiDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/test")
public class RestClientController {

    private static final String WEB_URL = "http://localhost:8081/kisi";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<KisiDto>> getKisiList(){
        ResponseEntity<List> result = restTemplate.getForEntity(WEB_URL, List.class);
        List<KisiDto> responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<KisiDto> kaydet(@RequestBody KisiDto kisiDto){
        ResponseEntity<KisiDto> result = restTemplate.postForEntity(WEB_URL, kisiDto, KisiDto.class);
        KisiDto responseBody = result.getBody();
        return ResponseEntity.ok(responseBody);
    }




}
