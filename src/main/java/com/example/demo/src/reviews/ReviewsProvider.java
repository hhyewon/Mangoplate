package com.example.demo.src.reviews;


import com.example.demo.config.BaseException;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

//Provider : Read의 비즈니스 로직 처리
@Service
public class ReviewsProvider {

    private final ReviewsDao reviewsDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewsProvider(ReviewsDao reviewsDao) {
        this.reviewsDao = reviewsDao;

    }


}
