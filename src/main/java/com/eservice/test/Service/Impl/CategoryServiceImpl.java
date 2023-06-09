package com.eservice.test.Service.Impl;


import com.eservice.test.Entity.Category;
import com.eservice.test.Repository.CategoryRepository;
import com.eservice.test.Service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        try {
            if (category.isBoIdRequired(category.getBoId()))
                category.setBoId("CATEGORY" + categoryRepository.count());
            categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
