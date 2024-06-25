package org.choongang.global.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.choongang.global.config.annotations.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Getter
@Service // 이 클래스가 서비스 클래스임을 나타내는 애노테이션
public class ApiRequestService {

    private final ObjectMapper om; // JSON 처리를 위한 ObjectMapper 객체

    // 생성자
    public ApiRequestService() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
    }

    /**
     * REST 요청 처리
     *
     * @param url : 요청 URL
     * @param method : 요청 메서드 - GET, POST, PUT, PATCH, DELETE
     * @param headers : 요청 헤더
     * @param data : POST, PUT, PATCH의 경우 BODY 데이터
     */
    public HttpResponse<String> request(String url, String method, Map<String, String> headers, Map<String, String> data) {

        // HttpClient 객체 생성
        HttpClient client = HttpClient.newHttpClient();
        // 요청 빌더 생성
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url));

        // 메서드를 대문자로 변환
        method = method.toUpperCase();

        /* 요청 헤더 추가 S */
        if (headers != null && !headers.isEmpty()) {
            headers.entrySet().stream().forEach(h -> builder.setHeader(h.getKey(), h.getValue()));
        }
        /* 요청 헤더 추가 E */

        /* 요청 바디 추가 S - 요청 바디는 POST, PUT, PATCH일때 적용 */
        if (List.of("POST", "PUT", "PATCH").contains(method)) {
            String defaultContentType = "application/x-www-form-urlencoded";
            String contentType = headers == null ? defaultContentType : headers.getOrDefault("content-type", defaultContentType);

            builder.setHeader("content-type", contentType);

            HttpRequest.BodyPublisher bodyPublisher = data == null ? HttpRequest.BodyPublishers.noBody() : getBodyString(data, contentType);

            if (method.equals("PUT")) {
                builder.PUT(bodyPublisher);
            } else {
                builder.POST(bodyPublisher);
            }
        }
        /* 요청 바디 추가 E */


        try {
            // 요청 보내고 응답 받기
            HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8")));
            return response;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 메서드 오버로딩: 헤더 없이 요청
    public HttpResponse<String> request(String url, String method, Map<String, String> data) {
        return request(url, method, null, data);
    }

    // 메서드 오버로딩: 헤더와 데이터 없이 요청
    public HttpResponse<String> request(String url, String method) {
        return request(url, method, null);
    }

    // 메서드 오버로딩: 기본 GET 요청
    public HttpResponse<String> request(String url) {
        return request(url, "GET");
    }

    /**
     * POST, PUT, PATCH 요청 데이터 처리
     *
     * @param data
     * @param contentType
     * @return
     */

    private HttpRequest.BodyPublisher getBodyString(Map<String, String> data, String contentType) {
        String res = null;

        // contentType이 application/json인 경우
        if (contentType.toLowerCase().contains("application/json")) {

            try {
                res = om.writeValueAsString(data);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } else { // contentType이 application/x-www-form-urlencoded 인 경우
            res = data.entrySet().stream().map(d -> d.getKey() + "=" + d.getValue()).toString();
            res = res == null?"": URLEncoder.encode(res, Charset.forName("UTF-8"));
        }

        return HttpRequest.BodyPublishers.ofString(res, Charset.forName("UTF-8"));
    }

    /**
     * 응답 데이터를 Map으로 변환
     *
     * @param response : 응답 객체
     * @return : Map 형태의 데이터
     */
    public Map<String, Object> toMap(HttpResponse<String> response) {
        return toMap(response.body());
    }

    // 응답 바디를 Map으로 변환
    public Map<String, Object> toMap(String body) {
        try {
            Map<String, Object> data = om.readValue(body, new TypeReference<>() {});
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 응답 데이터를 String으로 변환
     *
     * @param response
     * @return
     */
    public String toString(HttpResponse<String> response) {
        return response.body();
    }
}