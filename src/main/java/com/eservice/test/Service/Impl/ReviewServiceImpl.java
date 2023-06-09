package com.eservice.test.Service.Impl;


import com.eservice.test.Entity.Review;
import com.eservice.test.Repository.ReviewRepository;
import com.eservice.test.Service.ReviewService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
    private Logger logger = Logger.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        try {
            if (review.isBoIdRequired(review.getBoId()))
                review.setBoId("REVIEW" + reviewRepository.count());
            reviewRepository.save(review);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
    }


}
