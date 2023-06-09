package com.eservice.test.Service;


import com.eservice.test.Entity.Product;
import com.eservice.test.Entity.Response;
import com.eservice.test.dto.SearchDto;

public interface ProductService {

    public void save(Product product);

    public Response byNameAndType(SearchDto dto);

    public Response findByFullTextSearch(SearchDto dto);


    }
