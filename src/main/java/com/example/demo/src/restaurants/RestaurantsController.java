package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurants.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

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
    @ResponseBody
    @GetMapping("/{restaurantid}/menu") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetRestaurantMenuRes>> getRestaurantMenu(@PathVariable("restaurantid") int restaurantId) {
        try{
                System.out.println("1");
            List<GetRestaurantMenuRes> getRestaurantMenuRes = restaurantsProvider.getRestaurantMenu(restaurantId);
            return new BaseResponse<>(getRestaurantMenuRes);
        } catch(BaseException exception){
            System.out.println("10");
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 식당 정보 조회 API
     * [GET] /restaurants/:restaurantId
     * @return BaseResponse<GetUserRes>
     */
    //    Path-variable
    @ResponseBody
    @GetMapping("/{restaurantid}/info") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetRestaurantInfoRes> getRestaurantInfo(@PathVariable("restaurantid") int id) {
        // Get Users
        try{
            if (id == 0){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_RESTAURANTID);
            }
            if (id> 4 || id<1){
                return new BaseResponse<>(POST_REVIEWS_INVALID_RESTAURANTID_RANGE);
            }
            GetRestaurantInfoRes getRestaurantInfoRes = restaurantsProvider.getRestaurantInfo(id);

            return new BaseResponse<>(getRestaurantInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 가고싶다 설정 API
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{restaurantid}/{userid}/like")
    public BaseResponse<String> patchRestaurantLike(@PathVariable("restaurantid") int restaurantId, @PathVariable("userid") int userId, @RequestBody PatchRestaurantReq patchRestaurantReq){
        try {
            if(!patchRestaurantReq.getStatus().equals("ACTIVATE") || !patchRestaurantReq.getStatus().equals("INACTIVATE")){
                return new BaseResponse<>(INVALID_STATUS);
            }
            if(patchRestaurantReq.getStatus() ==null){
                return new BaseResponse<>(PATCH_EMPTY_STATUS);
            }
            PatchRestaurantReq patchUserReq = new PatchRestaurantReq(patchRestaurantReq.getStatus(), restaurantId,userId);
            restaurantsService.patchRestaurantLike(patchUserReq);

            String result = "";
            return new BaseResponse<>(result);


        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 식당 등록하기 API
     * [POST] /restaurants
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostRestaurantRes> createRestaurant(@RequestBody PostRestaurantReq postRestaurantReq) {
        try{
            if(postRestaurantReq.getRestaurantName() == null){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_RST_NAME);
            }
            if(postRestaurantReq.getRestaurantLocation() == null){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_RST_LOCATION);
            }
            if(postRestaurantReq.getVariety() == null){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_RST_VARIETY);
            }
            if(postRestaurantReq.getUserId() == 0){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_USERID);
            }

            System.out.println("1");
            PostRestaurantRes postRestaurantRes = restaurantsService.createRestaurant(postRestaurantReq);
            return new BaseResponse<>(postRestaurantRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 식당 가봤어요 API
     * [POST] /restaurants
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/{restaurantid}/{userid}/visited")
    public BaseResponse<PostRestaurantVisitedRes> createRestaurantVisited(@PathVariable("restaurantid") int restaurantId,@PathVariable("userid") int userId) {
        try{
            if(restaurantId==0){
                return new BaseResponse<>(POST_RESTAURANTS_EMPTY_RESTAURANTID);
            }
            if (userId ==0){
                return new BaseResponse<>(POST_RESTAURANTS_EMPTY_USERID);
            }
            System.out.println("1");
            PostRestaurantVisitedRes postRestaurantVisitedRes = restaurantsService.createRestaurantVisited(restaurantId,userId);
            return new BaseResponse<>(postRestaurantVisitedRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 가봤어요 조회 API
     * [GET] /restaurants
     */

    @GetMapping("/{restaurantid}/{userid}/visited-list") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetRestaurantVisitedRes>> getRestaurantVisited(@PathVariable("restaurantid") int restaurantId, @PathVariable("userid") int userId) {
        try{

            List<GetRestaurantVisitedRes> getRestaurantVisitedRes = restaurantsProvider.getRestaurantVisited();
            return new BaseResponse<>(getRestaurantVisitedRes);

            // Get Users
//            List<GetRestaurantRes> getRestaurantRes = restaurantsProvider.getRestaurantByRestaurantName(restaurantId);
//            return new BaseResponse<>(getRestaurantRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



}
