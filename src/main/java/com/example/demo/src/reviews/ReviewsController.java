package com.example.demo.src.reviews;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurants.model.GetRestaurantsRes;
import com.example.demo.src.restaurants.model.PostRestaurantReq;
import com.example.demo.src.restaurants.model.PostRestaurantRes;
import com.example.demo.src.reviews.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewsProvider reviewsProvider;
    @Autowired
    private final ReviewsService reviewsService;
    @Autowired
    private final JwtService jwtService;

    public ReviewsController(ReviewsProvider reviewsProvider, ReviewsService reviewsService, JwtService jwtService) {
        this.reviewsProvider = reviewsProvider;
        this.reviewsService = reviewsService;
        this.jwtService = jwtService;
    }

    /**
     * 리뷰 상세 조회 API
     * [GET] /reviews/:reviewid
     */
    @GetMapping("/{reviewid}") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<GetReviewRes> getReviews(@PathVariable("reviewid") int id) {
        try{
            System.out.println("1");
           GetReviewRes getReviewRes = reviewsProvider.getReviews(id);
            return new BaseResponse<>(getReviewRes);

            // Get Users
//            List<GetRestaurantRes> getRestaurantRes = restaurantsProvider.getRestaurantByRestaurantName(restaurantId);
//            return new BaseResponse<>(getRestaurantRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



    /**
     * 리부쓰기 API
     * [POST] /reviews
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
//        if (postReviewReq.getScore() == null) {
//            return new BaseResponse<>(POST_REVIEWS_EMPTY_SCORE);
//        }
        try {
            //jwt에서 idx 추출.
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (postReviewReq.getUserId() != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if (postReviewReq.getComment() == null) {
                return new BaseResponse<>(POST_REVIEWS_EMPTY_COMMENT);
            }
            if(postReviewReq.getComment().length()>10000){
                return new BaseResponse<>(POST_REVIEWS_INVALID_COMMENT);
            }
//            if (postReviewReq.getScore)
            System.out.println("6");
            PostReviewRes PostReviewRes = reviewsService.createReview(postReviewReq);
            return new BaseResponse<>(PostReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    //리뷰 댓글달기
    @ResponseBody
    @PostMapping("/{reviewid}/reply")
    public BaseResponse<PostReviewRes> createReply(@PathVariable("reviewid") int reviewId, @RequestBody PostReplyReq postReplyReq) {
        try{
            //jwt에서 idx 추출.
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (postReplyReq.getUserId() != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if (postReplyReq.getReply().isEmpty()){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_REPLY);
            }
            System.out.println("1");
            PostReviewRes postReviewRes = reviewsService.createReply(reviewId, postReplyReq);
            return new BaseResponse<>(postReviewRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


        /**
         * 리뷰 변경 API >>  having Review.userId=User.id
         * [PATCH] /users/:userIdx
         * @return BaseResponse<String>
         */
        @ResponseBody
        @PatchMapping("/{reviewid}")
        public BaseResponse<String> modifyReview(@PathVariable("reviewid") int id, @RequestBody Reviews reviews){
            try {

                int userIdByJwt = jwtService.getId();
                //userIdx와 접근한 유저가 같은지 확인
                if (reviews.getUserId() != userIdByJwt) {
                    return new BaseResponse<>(INVALID_USER_JWT);
                }
                //같다면 리뷰 내용 변경
                PatchReviewReq patchReviewReq = new PatchReviewReq(id, reviews.getReviewId(), reviews.getUserId(), reviews.getScore(), reviews.getReviewUrl(), reviews.getComment());
                reviewsService.modifyReview(id, patchReviewReq);

                String result = "";
                return new BaseResponse<>(result);
            } catch (BaseException exception) {
                System.out.println(exception);
                return new BaseResponse<>((exception.getStatus()));
            }
        }

    /**
     * 리뷰 삭제 API >> DELETE로 해보기
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{reviewid}/delete")
    public BaseResponse<String> modifyReviewDel(@PathVariable("reviewid") int id, @RequestBody Reviews reviews){
        try {
            //같다면 리뷰 내용 변경
            PatchReviewDelReq patchReviewDelReq = new PatchReviewDelReq( reviews.getStatus(),id);
            reviewsService.modifyReviewDel(patchReviewDelReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            System.out.println(exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }




    }
