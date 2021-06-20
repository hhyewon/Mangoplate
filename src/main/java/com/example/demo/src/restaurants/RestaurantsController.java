package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurants.model.GetRestaurantRes;
import com.example.demo.src.restaurants.model.PatchRestaurantRes;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseResponse<List<GetRestaurantRes>> getRestaurants() {
        try{

                List<GetRestaurantRes> getRestaurantRes = restaurantsProvider.getRestaurants();
                return new BaseResponse<>(getRestaurantRes);

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
    // Path-variable
//    @ResponseBody
//    @GetMapping("/{restaurantId}") // (GET) 127.0.0.1:9000/app/users/:userIdx
//    public BaseResponse<GetRestaurantRes> getRestaurant(@PathVariable("restaurantId") int id) {
//        // Get Users
//        try{
//            GetRestaurantRes getRestaurantRes = restaurantsProvider.getRestaurant(id);
//            return new BaseResponse<>(getRestaurantRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//
//    }

//    @ResponseBody
//    @RestController
//    public class hello{

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        logger.debug("/hello");
        return "h";
    }
//}



//    @PatchMapping("/{restaurantId}/like")
//    public BaseResponse<PatchRestaurantRes> patchRestaurantRes(@PathVariable int likeId) {
//        try {
//            return new BaseResponse<>(SUCCESS_PUT_LIKED_ON_ALBUM, albumService.createLikeOnAlbumById(albumId, request));
//        } catch (IllegalArgumentException e) {
//            return new BaseResponse<>(NOT_FOUND_ALBUM);
//        }
//    }




}
