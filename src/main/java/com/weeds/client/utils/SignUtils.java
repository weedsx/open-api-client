package com.weeds.client.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 生成签名
 * @author weeds
 */
public class SignUtils {
    /**
     * 根据content和secretKey进行SHA384生成摘要
     * @param content
     * @param secretKey
     * @return
     */
    public static String genSign(String content,String secretKey){
        Digester digester = new Digester(DigestAlgorithm.SHA384);
        String originalText=content+"."+secretKey;
        return digester.digestHex(originalText);
    }
}
