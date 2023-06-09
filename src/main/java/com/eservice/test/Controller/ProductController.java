package com.eservice.test.Controller;

import com.eservice.test.Entity.Response;
import com.eservice.test.Entity.Views;
import com.eservice.test.Service.ProductService;
import com.eservice.test.dto.SearchDto;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*api - filter by name, filter by name and type (Category, review, hashcode, product )*/
    @ResponseBody
    @JsonView(Views.Thin.class)
    @PostMapping(value = "bynameandtype")
    public Response byNameAndType(@RequestBody @Valid SearchDto dto) {
        return productService.byNameAndType(dto);
    }

    @ResponseBody
    @JsonView(Views.Thin.class)
    @PostMapping(value = "searchProductByFullText")
    public Response findByFullTextSearch(@RequestBody @Valid SearchDto dto){
        return productService.findByFullTextSearch(dto);
    }
}
