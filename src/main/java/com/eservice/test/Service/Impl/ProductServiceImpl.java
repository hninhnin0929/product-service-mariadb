package com.eservice.test.Service.Impl;


import com.eservice.test.Entity.*;
import com.eservice.test.Repository.ProductRepository;
import com.eservice.test.Service.ProductService;
import com.eservice.test.dto.SearchDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("productService")
public class ProductServiceImpl implements ProductService {
    private Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {
        try {
            if (product.isBoIdRequired(product.getBoId()))
                product.setBoId("PRODUCT" + productRepository.count());
            productRepository.save(product);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    public Response byNameAndType(SearchDto dto) {
        Response response = new Response(true, "success");
        List<Product> productList = productRepository.findByNameContainingAndEntityStatus(dto.getName(), EntityStatus.ACTIVE);
        switch (dto.getType()) {
            case Product:
                response.setDataList(productList);
                break;
            case Review:
                productList.stream().forEach((product) -> {
                    response.getDataList().addAll(product.getReviewList());
                });
                break;
            case Category:
                List<Category> categoryList = new ArrayList<Category>();
                productList.stream().forEach((product) -> {
                    categoryList.addAll(product.getCategoryList()
                            .stream()
                            .filter(c -> !categoryList.contains(c))
                            .collect(Collectors.toList()));
                });
                response.setDataList(categoryList);
                break;
            case HashCode:
                List<HashCode> hashCodeList = productList.stream()
                        .map(product -> product.getHashcode())
                        .collect(Collectors.toList());
                response.setDataList(hashCodeList);
                break;
            default:
                break;
        }
        return response;
    }

    @Override
    public Response findByFullTextSearch(SearchDto dto) {
        Response response = new Response(true, "success");
        List<Product> productList = productRepository.findByFullTextSearch(dto.getName());
        switch (dto.getType()) {
            case Product:
                response.setDataList(productList);
                break;
            case Review:
                productList.stream().forEach((product) -> {
                    response.getDataList().addAll(product.getReviewList());
                });
                break;
            case Category:
                List<Category> categoryList = new ArrayList<Category>();
                productList.stream().forEach((product) -> {
                    categoryList.addAll(product.getCategoryList()
                            .stream()
                            .filter(c -> !categoryList.contains(c))
                            .collect(Collectors.toList()));
                });
                response.setDataList(categoryList);
                break;
            case HashCode:
                List<HashCode> hashCodeList = productList.stream()
                        .map(product ->
                                product.getHashcode())
                        .collect(Collectors.toList());
                response.setDataList(hashCodeList);
                break;
            default:
                break;
        }
        System.out.println("full text = " + response.getDataList().size());
        return response;
    }
}
