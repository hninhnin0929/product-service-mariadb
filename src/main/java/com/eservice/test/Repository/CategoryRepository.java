package com.eservice.test.Repository;


import com.eservice.test.Entity.Category;
import com.eservice.test.Entity.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    public Category findByNameAndEntityStatus(String name, EntityStatus entityStatus);

    public Category findByNameContainingAndEntityStatus(String name, EntityStatus entityStatus);
}
