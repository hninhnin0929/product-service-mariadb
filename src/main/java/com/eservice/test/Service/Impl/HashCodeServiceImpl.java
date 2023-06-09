package com.eservice.test.Service.Impl;

import com.eservice.test.Repository.HashCodeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eservice.test.Service.HashCodeService;
import com.eservice.test.Entity.HashCode;

@Service("hashCodeService")
public class HashCodeServiceImpl implements HashCodeService {
    private Logger logger = Logger.getLogger(HashCodeServiceImpl.class);

    @Autowired
    private HashCodeRepository hashCodeRepository;

    public void save(HashCode hashCode) {
        try {
            if (hashCode.isBoIdRequired(hashCode.getBoId()))
                hashCode.setBoId("HASH" + hashCodeRepository.count());
            hashCodeRepository.save(hashCode);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
