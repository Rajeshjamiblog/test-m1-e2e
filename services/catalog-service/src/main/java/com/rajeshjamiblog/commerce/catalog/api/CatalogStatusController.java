package com.rajeshjamiblog.commerce.catalog.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog")
@Tag(name = "Catalog", description = "Catalogue API")
class CatalogStatusController {

    @GetMapping("/status")
    @Operation(summary = "Get the catalogue service status")
    ResponseEntity<Map<String, String>> status() {
        return ResponseEntity.ok(Map.of("service", "catalog-service", "status", "available"));
    }
}
