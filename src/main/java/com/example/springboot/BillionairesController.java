package com.example.springboot;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillionairesController {

    private final BillionairesService service;

    BillionairesController(BillionairesService service) {
        this.service = service;
    }

    @GetMapping(value = "/billionaires/csv")
    public ResponseEntity<Resource> getCsvFile() {
        String filename = "billionaires.csv";
        InputStreamResource file = new InputStreamResource(service.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping(value = "/billionaires/xlsx")
    public ResponseEntity<Resource> getXlsxFile() {
        String filename = "billionaires.xlsx";
        InputStreamResource file = new InputStreamResource(service.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .body(file);
    }
}
