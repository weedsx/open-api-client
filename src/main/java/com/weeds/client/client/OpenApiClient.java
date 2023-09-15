package com.weeds.client.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.weeds.client.constant.OpenApiReqHeaderConstant;
import com.weeds.client.model.Param;
import com.weeds.client.utils.SignUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.weeds.client.constant.ApiRemoteServeConstant.GATEWAY_HOST;

/**
 * SDK 调用接口的客户端
 *
 * @author weeds
 */
public class OpenApiClient {
    private final String accessKey;

    private final String secretKey;


    public OpenApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * get请求
     *
     * @param paramsMap 请求参数
     * @param urlStr    请求 url
     * @return
     */
    public String getByMap(Map<String, String> paramsMap, String urlStr) {
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try (HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + url.getPath())
                .addHeaders(getHeaderMap(paramsMap.toString(), urlStr, Method.GET.name()))
                .formStr(paramsMap)
                .execute()) {
            return httpResponse.body();
        }
    }

    /**
     * get请求
     *
     * @param paramList list 参数列表
     * @param urlStr    请求 url
     * @return
     */
    public String getByList(List<Param> paramList, String urlStr) {
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // 将 List 参数转为 map
        HashMap<String, String> paramMap = new HashMap<>(paramList.size());
        paramList.forEach(param -> paramMap.put(param.getName(), param.getValue()));
        try (HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + url.getPath())
                .addHeaders(getHeaderMap(paramMap.toString(), urlStr, Method.GET.name()))
                .formStr(paramMap)
                .execute()) {
            return httpResponse.body();
        }
    }

    /**
     * post 请求
     *
     * @param body   请求体
     * @param urlStr 请求 url
     * @return
     */
    public String post(String body, String urlStr) {
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try (HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + url.getPath())
                .addHeaders(getHeaderMap(body, urlStr, Method.POST.name()))
                .body(body)
                .execute()) {
            return httpResponse.body();
        }
    }

    /**
     * 根据content和秘钥生成单向加密的摘要
     *
     * @param content
     * @return
     */
    private Map<String, String> getHeaderMap(String content, String url, String method) {
        return new HashMap<String, String>(7) {{
            put(OpenApiReqHeaderConstant.API_URL, url);
            put(OpenApiReqHeaderConstant.METHOD_TYPE, method);
            put(OpenApiReqHeaderConstant.ACCESS_KEY, accessKey);
            // 一定不能直接发送 secretKey
            put(OpenApiReqHeaderConstant.NONCE, RandomUtil.randomNumbers(4));
            put(OpenApiReqHeaderConstant.BODY, content);
            put(OpenApiReqHeaderConstant.TIME_STAMP, String.valueOf(System.currentTimeMillis() / 1000));
            // 根据用户信息和秘钥生成单向加密的摘要
            put(OpenApiReqHeaderConstant.SIGN, SignUtils.genSign(content, secretKey));
        }};
    }
}
