package org.sky_pro.team_work.controller;

import lombok.RequiredArgsConstructor;
import org.sky_pro.team_work.domain.BuildInfo;
import org.sky_pro.team_work.service.ManagementService;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {
    private final ManagementService managementService;
    private final BuildProperties buildProperties;

    @GetMapping("/info")
    public BuildInfo getServiceInfo() {
        return new BuildInfo(buildProperties.getName(), buildProperties.getVersion());
    }

    @PostMapping("/clear-caches")
    public void clearCaches() {
        managementService.clearCaches();
    }
}