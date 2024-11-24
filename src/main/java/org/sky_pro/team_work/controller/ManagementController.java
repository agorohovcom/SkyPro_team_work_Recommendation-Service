package org.sky_pro.team_work.controller;

import org.sky_pro.team_work.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class ManagementController {
    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping("/clear-caches")
    public ResponseEntity<Void> clearCaches() {
        managementService.clearCaches();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/info")
    public String info() {
       return managementService.getVersion();
    }
}

