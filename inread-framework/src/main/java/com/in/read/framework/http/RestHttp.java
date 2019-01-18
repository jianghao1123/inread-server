package com.in.read.framework.http;

import com.in.read.framework.config.WXMappingFastJsonHttpMessageConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by luyun on 2019/1/9.
 */
public class RestHttp {

    public static <T> T get(Class<T> clz, String url, Map<String, String> queryMap) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WXMappingFastJsonHttpMessageConverter());
        T entity = restTemplate.getForObject(url
                , clz, queryMap);
        return entity;
    }

    public static <T> T postBody(Class<T> clz, String url, String body) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        T entity = restTemplate.postForObject(url
                , request, clz);
        return entity;
    }

    public static <T> T postForm(Class<T> clz, String url, String form) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>(form, headers);
        T entity = restTemplate.postForObject(url
                , request, clz);
        return entity;
    }
}
