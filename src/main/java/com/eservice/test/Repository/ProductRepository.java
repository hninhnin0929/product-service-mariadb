package com.eservice.test.Repository;



import com.eservice.test.Entity.EntityStatus;
import com.eservice.test.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    public List<Product> findByNameContainingAndEntityStatus(String name, EntityStatus entityStatus);

    @Query(value = "SELECT * FROM Product WHERE " +
            "entityStatus='ACTIVE' AND " +
            "MATCH (name, description) AGAINST (?1)", nativeQuery = true)
    List<Product> findByFullTextSearch(String keyword);

}
