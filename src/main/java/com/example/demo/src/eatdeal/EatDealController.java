package com.example.demo.src.eatdeal;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.eatdeal.model.GetEatDealDetailRes;
import com.example.demo.src.eatdeal.model.GetEatDealRes;
import com.example.demo.src.restaurants.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/eat-deal")
public class EatDealController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EatDealProvider eatDealProvider;
    @Autowired
    private final EatdealService eatdealService;

    public EatDealController(EatDealProvider eatDealProvider, EatdealService eatdealService) {
        this.eatDealProvider = eatDealProvider;
        this.eatdealService = eatdealService;
    }

    /**
     * 식당 조회 API
     * [GET] /eat-deal
     */

    @GetMapping("") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetEatDealRes>> getEatdeal() {
        try{
                List<GetEatDealRes> getEatdealRes = eatDealProvider.getEatdeal();
                return new BaseResponse<>(getEatdealRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 식당 상세 조회 API
     * [GET] /restaurants/:restaurantId
     * @return BaseResponse<GetUserRes>
     */
 //    Path-variable
    @ResponseBody
    @GetMapping("/{eat-dealid}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetEatDealDetailRes> getEatdealDetail(@PathVariable("eat-dealid") int id) {
        try{
            GetEatDealDetailRes getEatDealDetailRes = eatDealProvider.getEatdealDetail(id);
            return new BaseResponse<>(getEatDealDetailRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }
//
//    /**
//     * 메뉴 더보기 API
//     * [GET] /restaurants/:restaurantid/menu
//     */
//    @ResponseBody
//    @GetMapping("/{restaurantid}/menu") // (GET) 127.0.0.1:9000/restaurants
//    public BaseResponse<GetRestaurantMenuRes> getRestaurantMenu(@PathVariable("restaurantid") int restaurantId) {
//        try{
//                System.out.println("1");
//            GetRestaurantMenuRes getRestaurantMenuRes = restaurantsProvider.getRestaurantMenu(restaurantId);
//            return new BaseResponse<>(getRestaurantMenuRes);
//        } catch(BaseException exception){
//            System.out.println("10");
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 식당 정보 조회 API
//     * [GET] /restaurants/:restaurantId
//     * @return BaseResponse<GetUserRes>
//     */
//    //    Path-variable
//    @ResponseBody
//    @GetMapping("/{restaurantid}/info") // (GET) 127.0.0.1:9000/app/users/:userIdx
//    public BaseResponse<GetRestaurantInfoRes> getRestaurantInfo(@PathVariable("restaurantid") int id) {
//        // Get Users
//        try{
//            if (id == 0){
//                return new BaseResponse<>(POST_REVIEWS_EMPTY_RESTAURANTID);
//            }
//            if (id> 4 || id<1){
//                return new BaseResponse<>(POST_REVIEWS_INVALID_RESTAURANTID_RANGE);
//            }
//            GetRestaurantInfoRes getRestaurantInfoRes = restaurantsProvider.getRestaurantInfo(id);
//
//            return new BaseResponse<>(getRestaurantInfoRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//
//    }
//
//
//
//
//    /**
//     * 가고싶다 설정 API
//     * [PATCH] /users/:userIdx
//     * @return BaseResponse<String>
//     */
//    @ResponseBody
//    @PatchMapping("/{/{restaurantid}/{userid}/like}")
//    public BaseResponse<String> patchRestaurantLike(@PathVariable("restaurantid") int restaurantId, @PathVariable("userid") int userId, @RequestBody PatchRestaurantRes patchRestaurantRes){
//        try {
//
//            //같다면 유저네임 변경
//            PatchRestaurantReq patchRestaurantReq = new PatchRestaurantReq(restaurantId,userId, patchRestaurantRes.getStatus());
//            restaurantsService.patchRestaurantLike(patchRestaurantReq);
//
//            String result = "";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//
//    /**
//     * 식당 등록하기 API
//     * [POST] /restaurants
//     * @return BaseResponse<PostUserRes>
//     */
//    // Body
//    @ResponseBody
//    @PostMapping("")
//    public BaseResponse<PostRestaurantRes> createRestaurant(@RequestBody PostRestaurantReq postRestaurantReq) {
//        try{
//            if(postRestaurantReq.getRestaurantName() == null){
//                return new BaseResponse<>(POST_REVIEWS_EMPTY_RST_NAME);
//            }
//            if(postRestaurantReq.getRestaurantLocation() == null){
//                return new BaseResponse<>(POST_REVIEWS_EMPTY_RST_LOCATION);
//            }
//            if(postRestaurantReq.getVariety() == null){
//                return new BaseResponse<>(POST_REVIEWS_EMPTY_RST_VARIETY);
//            }
//            if(postRestaurantReq.getUserId() == 0){
//                return new BaseResponse<>(POST_REVIEWS_EMPTY_USERID);
//            }
//
//            System.out.println("1");
//            PostRestaurantRes postRestaurantRes = restaurantsService.createRestaurant(postRestaurantReq);
//            return new BaseResponse<>(postRestaurantRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }



}
