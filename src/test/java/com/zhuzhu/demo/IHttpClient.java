package com.zhuzhu.demo;

import java.util.Map;

public interface IHttpClient {
    /**
     * http/https get request invoke
     *
     * @param url        url
     * @param parameters parameters
     * @param headers    headers
     * @param clazz      response entity will be parse to clazz type
     * @param <T>        clazz type
     * @return T
     */
    <T> T get(String url, Map<String, String> parameters, Map<String, String> headers, Class<T> clazz);

    /**
     * http/https get request invoke
     *
     * @param url        url
     * @param parameters parameters
     * @param headers    headers
     * @param entity     request entity
     * @param clazz      response entity will be parse to clazz type
     * @param <T>        clazz type
     * @return T
     */
    <T> T put(String url, Map<String, String> parameters, Map<String, String> headers, Object entity, Class<T> clazz);


    /**
     * http/https post request invoke
     *
     * @param url        url
     * @param parameters parameters
     * @param headers    headers
     * @param entity     request entity
     * @param clazz      response entity will be parse to clazz type
     * @param <T         class type
     * @return T
     */
    <T> T post(String url, Map<String, String> parameters, Map<String, String> headers, Object entity, Class<T> clazz);
}

