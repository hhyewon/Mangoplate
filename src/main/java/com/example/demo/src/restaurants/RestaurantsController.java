package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurants.model.*;
import com.example.demo.src.user.model.PatchUserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private final RestaurantsProvider restaurantsProvider;
    @Autowired
    private final RestaurantsService restaurantsService;

    public RestaurantsController(RestaurantsProvider restaurantsProvider, RestaurantsService restaurantsService){
        this.restaurantsProvider = restaurantsProvider;
        this.restaurantsService = restaurantsService;
    }

    /**
     * 식당 조회 API
     * [GET] /restaurants
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /restaurants? RestaurantId=
     * @return BaseResponse<List<GetUserRes>>
     */

    @GetMapping("") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetRestaurantsRes>> getRestaurants() {
        try{

                List<GetRestaurantsRes> getRestaurantsRes = restaurantsProvider.getRestaurants();
                return new BaseResponse<>(getRestaurantsRes);

            // Get Users
//            List<GetRestaurantRes> getRestaurantRes = restaurantsProvider.getRestaurantByRestaurantName(restaurantId);
//            return new BaseResponse<>(getRestaurantRes);
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
    @GetMapping("/{restaurantid}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetRestaurantRes> getRestaurant(@PathVariable("restaurantid") int id) {
        // Get Users
        try{
            if (id == 0){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_RESTAURANTID);
            }
            if (id> 4 || id<1){
                return new BaseResponse<>(POST_REVIEWS_INVALID_RESTAURANTID_RANGE);
            }

            GetRestaurantRes getRestaurantRes = restaurantsProvider.getRestaurant(id);

//            if(!isRegexId(getRestaurantRes.getId())){
//                return new BaseResponse<>(POST_REVIEWS_INVALID_RESTAURANTID);
//            }
            return new BaseResponse<>(getRestaurantRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 메뉴 더보기 API
     * [GET] /restaurants/:restaurantid/menu
     */

    @GetMapping("/{restaurantid}/menu") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<GetRestaurantMenuRes> getRestaurantMenu(@PathVariable("restaurantid") int restaurantId) {
        try{
                System.out.println("1");
            GetRestaurantMenuRes getRestaurantMenuRes = restaurantsProvider.getRestaurantMenu(restaurantId);
            return new BaseResponse<>(getRestaurantMenuRes);
        } catch(BaseException exception){
            System.out.println("10");
            return new BaseResponse<>((exception.getStatus()));
        }
    }



    /**
     * 가고싶다 설정 API
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
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



}
