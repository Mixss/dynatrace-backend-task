package me.mixss.dynatracebackendtask.restclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mixss.dynatracebackendtask.exceptions.ApiBadResponseException;
import me.mixss.dynatracebackendtask.exceptions.ApiNotFoundException;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public abstract class ApiClient {
    public JsonNode makeCall(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.getBody());
        }
        catch (HttpClientErrorException e){
            HttpStatus status = (HttpStatus) e.getStatusCode();
            if(status == HttpStatus.NOT_FOUND){
                throw new ApiNotFoundException();
            }
            throw new ApiBadResponseException(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ApiResponseBadFormatException();
        }
    }
}
