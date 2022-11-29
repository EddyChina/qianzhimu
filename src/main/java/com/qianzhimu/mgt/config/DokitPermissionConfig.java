package com.qianzhimu.mgt.config;

import com.qianzhimu.api.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "dokit")
public class DokitPermissionConfig {

    public Boolean check(String ...permissions){
        Set<String> dokitPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return dokitPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(dokitPermissions::contains);
    }

}
