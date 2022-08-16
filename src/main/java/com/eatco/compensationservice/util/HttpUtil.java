package com.eatco.compensationservice.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class HttpUtil {

    @Autowired
    private RestTemplate restTemplate;

    public <Req, Res> Res hitRequest(String url, Req requestObj, Class<Req> classObj, Map<String, String> headers, HttpMethod method)  {
        Object resultObj = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> entity = null;

        if(headers != null && !headers.isEmpty())  {
            httpHeaders = new HttpHeaders();
            Set<String> headerKeySet = headers.keySet();
            for(String key : headerKeySet) httpHeaders.add(key, headers.get(key));
        }

        if(requestObj != null) entity = new HttpEntity<>(requestObj, httpHeaders);
        else 	entity = new HttpEntity<>(httpHeaders);

        try {
            // Send request with GET method, and Headers.
            StopWatch stopWatch = new StopWatch();
            log.info("Going to hit Third Party API");
            stopWatch.start();
            ResponseEntity<?> response = restTemplate.exchange(url, method, entity, classObj);
            stopWatch.stop();
            log.info("After hitting Third Party API Time Taken: {} ms.",stopWatch.getTotalTimeMillis());
            resultObj = response.getBody();
        } catch (RestClientException e) {
            String errorResponse = null;
            if(e instanceof HttpClientErrorException){
                HttpClientErrorException exception =  (HttpClientErrorException)e;
                errorResponse = exception.getResponseBodyAsString();
            }
            else errorResponse = e.getMessage();
            log.error("error with http connect : {}", ExceptionUtils.getStackTrace(e) );
            //if(!StringUtils.isEmpty(errorResponse)) resultObj = objectMapper.readValue(errorResponse, classObj);
        }catch(Exception e) {
            log.error("error with http connect : {}", ExceptionUtils.getStackTrace(e) );
        }
        return (Res) resultObj;
    }
}
