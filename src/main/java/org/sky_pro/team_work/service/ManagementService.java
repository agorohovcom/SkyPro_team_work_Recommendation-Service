package org.sky_pro.team_work.service;



import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


@Service
public class ManagementService {
    @CacheEvict(value = "rules", allEntries = true)
    public void clearCaches() {
    }
}