package com.example.demo.src.reviews;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.restaurants.model.PostRestaurantReq;
import com.example.demo.src.restaurants.model.PostRestaurantRes;
import com.example.demo.src.reviews.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Transactional
@Service
public class ReviewsService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewsDao reviewsDao;
    private final ReviewsProvider reviewsProvider;
    private final JwtService jwtService;


    @Autowired
    public ReviewsService(ReviewsDao reviewsDao, ReviewsProvider reviewsProvider, JwtService jwtService) {
        this.reviewsDao = reviewsDao;
        this.reviewsProvider = reviewsProvider;
        this.jwtService = jwtService;
    }


    //POST
    @Transactional
    public PostReviewRes createReview(PostReviewReq postReviewReq) throws BaseException {
    try {
        int id = reviewsDao.createReview(postReviewReq);
        return new PostReviewRes(id);

//        return new PostReviewRes(reviewsDao.createReview(postReviewReq));
    }catch (Exception exception){
        System.out.println(exception);
        throw new BaseException(FAILED_TO_POST_REVIEW);
    }
    }

    //POST
    public PostReviewRes createReply(int reviewId, PostReplyReq postReplyReq) throws BaseException {

        try{
            System.out.println("2");
            int id = reviewsDao.createReply(reviewId, postReplyReq);
            return new PostReviewRes(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(FAILED_TO_CREATE_REPLY);
        }
    }

    //Patch
    public void modifyReview(PatchReviewReq patchReviewReq) throws BaseException {
        try{
            int result = reviewsDao.modifyReview(patchReviewReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_EMPTY);
            }

        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(FAILED_TO_MODIFY_REVIEW);
        }
    }
    public void modifyReviewDel(PatchReviewDelReq patchReviewDelReq) throws BaseException {
        try{
            int result = reviewsDao.modifyReviewDel(patchReviewDelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_EMPTY);
            }
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
