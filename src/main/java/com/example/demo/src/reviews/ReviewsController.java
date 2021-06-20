package com.example.demo.src.reviews;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.reviews.model.PatchReviewReq;
import com.example.demo.src.reviews.model.PostReviewReq;
import com.example.demo.src.reviews.model.PostReviewRes;
import com.example.demo.src.reviews.model.Reviews;
import com.example.demo.src.user.model.*;
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
     * 회원가입 API
     * [POST] /users
     *
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
//        if (postReviewReq.getComment() == null) {
//            return new BaseResponse<>(POST_REVIEWS_EMPTY_COMMENT);
//        }
//        if (postReviewReq.getScore() == null) {
//            return new BaseResponse<>(POST_REVIEWS_EMPTY_SCORE);
//        }
        try {
            System.out.println("6");
            PostReviewRes PostReviewRes = reviewsService.createReview(postReviewReq);
            return new BaseResponse<>(PostReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


        /**
         * 리뷰 변경 API
         * [PATCH] /users/:userIdx
         * @return BaseResponse<String>
         */
        @ResponseBody
        @PatchMapping("/{reviewId}")
        public BaseResponse<String> modifyReview(@PathVariable("reviewId") int id, @RequestBody Reviews reviews){
            try {
                //같다면 리뷰 내용 변경
                PatchReviewReq patchReviewReq = new PatchReviewReq(id, reviews.getReviewUrl(), reviews.getScore(), reviews.getComment());
                reviewsService.modifyReview(patchReviewReq);

                String result = "";
                return new BaseResponse<>(result);
            } catch (BaseException exception) {
                return new BaseResponse<>((exception.getStatus()));
            }
        }


    }
