package com.qianzhimu.website.config;

import com.qianzhimu.website.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "dokit")
public class DokitPermissionConfig {

    public Boolean check(String ...permissions){
        List<String> dokitPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return dokitPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(dokitPermissions::contains);
    }

}
