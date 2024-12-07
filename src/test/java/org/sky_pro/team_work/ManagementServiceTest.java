package org.sky_pro.team_work;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sky_pro.team_work.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableCaching
public class ManagementServiceTest {
    @Autowired
    private ManagementService managementService;
    @MockBean
    private CacheManager cacheManager;
    @MockBean
    private BuildProperties buildProperties;
    @Mock
    private Cache mockCache;

    @Test
    public void testClearCaches() {
        Mockito.when(cacheManager.getCache("rules")).thenReturn(mockCache);
        managementService.clearCaches();
        Mockito.verify(mockCache, Mockito.times(1)).clear();
    }
}