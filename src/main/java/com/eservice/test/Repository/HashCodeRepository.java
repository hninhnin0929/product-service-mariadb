package com.eservice.test.Repository;


import com.eservice.test.Entity.EntityStatus;
import com.eservice.test.Entity.HashCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface HashCodeRepository extends JpaRepository<HashCode, Long>, JpaSpecificationExecutor<HashCode> {

    public HashCode findByHashcodeNoAndEntityStatus(String hashCodeNo, EntityStatus entityStatus);
}
