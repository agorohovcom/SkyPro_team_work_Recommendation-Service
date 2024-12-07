package org.sky_pro.team_work.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagementService {
    private final CacheManager cacheManager;
    @CacheEvict(value = "rules", allEntries = true)
    public void clearCaches() {
    }
}