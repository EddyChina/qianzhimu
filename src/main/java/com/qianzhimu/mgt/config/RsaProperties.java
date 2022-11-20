package com.qianzhimu.mgt.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RsaProperties {

    public static String privateKey;

    public static String md5Key;

    @Value("${rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }

    @Value("${rsa.md5_key}")
    public void setMd5Key(String md5Key) {
        RsaProperties.md5Key = md5Key;
    }
}
