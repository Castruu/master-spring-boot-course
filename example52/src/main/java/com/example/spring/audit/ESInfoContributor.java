package com.example.spring.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ESInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("App Name", "ESchool");
        infoMap.put("Description", "School management app for Admin and Students");
        infoMap.put("Version", "BETA");
        infoMap.put("Contact Email", "admin@email.com");
        infoMap.put("Contact Mobile", "123456789");
        builder.withDetails(Map.of("eschool-info", infoMap));
    }
}
