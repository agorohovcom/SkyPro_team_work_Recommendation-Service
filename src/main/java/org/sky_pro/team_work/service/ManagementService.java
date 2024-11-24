package org.sky_pro.team_work.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class ManagementService {

    @CacheEvict(value = "rules", allEntries = true)
    public void clearCaches() {
    }

    @Value("${application.version}")
    private String version="1.0.0";

    public String getVersion() {
        return version;
    }
}