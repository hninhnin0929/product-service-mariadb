package com.eservice.test.Controller;

import com.eservice.test.Entity.Response;
import com.eservice.test.Service.ExcelImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("excel")
public class ExcelImportController {

    @Autowired
    private ExcelImportService excelImportService;

    @GetMapping(value = "product")
    public Response importProductFile() {
        return excelImportService.importProduct();
    }

    @GetMapping(value = "hashcode")
    public Response importHashCodeFile() {
        System.out.println("Excel hashcode import");
        return excelImportService.importHashCode();
    }

    @GetMapping(value = "category")
    public Response importCategory() { return excelImportService.importCategory(); }

}
