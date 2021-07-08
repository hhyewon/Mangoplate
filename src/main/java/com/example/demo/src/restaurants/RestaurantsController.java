package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.restaurants.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
//import static com.example.demo.utils.ValidationRegex.isRegexId;
import static com.example.demo.utils.ValidationRegex.isRegexPhoneNumber;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private final RestaurantsProvider restaurantsProvider;
    @Autowired
    private final RestaurantsService restaurantsService;
    @Autowired
    private final JwtService jwtService;

    public RestaurantsController(RestaurantsProvider restaurantsProvider, RestaurantsService restaurantsService, JwtService jwtService){
        this.restaurantsProvider = restaurantsProvider;
        this.restaurantsService = restaurantsService;
        this.jwtService = jwtService;
    }

    /**
     * 식당 조회 API
     * [GET] /restaurants
     * @RequestParam(required = false) String Email
     *     private float userLatitude;
     *     private float userLongitude;
     *     @RequestParam(required = false) float userLatitude,
     *                                                                 @RequestParam(required = false) float userLongitude
     */

    @GetMapping("") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetRestaurantsRes>> getRestaurants() {
        try{
                List<GetRestaurantsRes> getRestaurantsRes = restaurantsProvider.getRestaurants();
                return new BaseResponse<>(getRestaurantsRes);
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

            if (id==0){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_RESTAURANTID);
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
    @GetMapping("/{restaurantid}/convenience") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetRestaurantInfoRes> getRestaurantInfo(@PathVariable("restaurantid") int id) {
        // Get Users
        try{
//            if (id == 0){
//                return new BaseResponse<>(POST_REVIEWS_EMPTY_RESTAURANTID);
//            }
//            if (id> 4 || id<1){
//                return new BaseResponse<>(POST_REVIEWS_INVALID_RESTAURANTID_RANGE);
//            }
            GetRestaurantInfoRes getRestaurantInfoRes = restaurantsProvider.getRestaurantInfo(id);

            return new BaseResponse<>(getRestaurantInfoRes);
        } catch(BaseException exception){
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
                        //jwt에서 idx 추출.
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (postRestaurantReq.getUserId() != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
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
            if (!isRegexPhoneNumber(postRestaurantReq.getRestaurantNumber())) {
                return new BaseResponse<>(POST_USERS_INVALID_PHONENUMBER);
            }
//            if(!isRegexId(postRestaurantReq.getUserId())){
//                return new BaseResponse<>(USERS_INVALID_USER_ID);
//            }

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
    public BaseResponse<PostRestaurantVisitedRes> createRestaurantVisited(@PathVariable("restaurantid") int restaurantId, @PathVariable("userid") int userId, @RequestBody PostRestaurantVisitedReq postRestaurantVisitedReq) {
        try{
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (userId != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            System.out.println("1");
            PostRestaurantVisitedRes postRestaurantVisitedRes = restaurantsService.createRestaurantVisited(restaurantId,userId, postRestaurantVisitedReq);
            return new BaseResponse<>(postRestaurantVisitedRes);
        } catch(BaseException exception){
            System.out.println(exception);
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
    public BaseResponse<String> patchLike(@RequestParam(required = false) String status, @PathVariable("userid") int userId , @PathVariable("restaurantid") int restaurantId) {
        try{
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (userId != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            if(!status.equals("ACTIVATE") && !status.equals("INACTIVATE")){
                return new BaseResponse<>(INVALID_STATUS);
            }
            if(status==null){
                return new BaseResponse<>(PATCH_EMPTY_STATUS);
            }
            // Get Users
//            PatchRestaurantRes patchRestaurantRes = new PatchRestaurantRes(status, userId, restaurantId);
            restaurantsService.patchLike(status, userId, restaurantId);
//            return new BaseResponse<>(patchRestaurantRes);
            String result = "";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }



    /**
     * 식당 정보 변경 API
     * [PATCH] /restaurants/:restaurantid/convenience
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{restaurantid}/convenience")
    public BaseResponse<String> patchConvenience(@PathVariable("restaurantid") int id, @RequestBody PatchRestaurantConvenienceReq patchRestaurantConvenienceReq) {
        try{
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (patchRestaurantConvenienceReq.getUserId() != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            restaurantsService.patchConvenience(id, patchRestaurantConvenienceReq);
            String result = "";
            return new BaseResponse<>(result);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 식당 메뉴 변경 API
     * [PATCH] /restaurants/:restaurantid/convenience
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{restaurantid}/menu")
    public BaseResponse<String> patchMenu(@PathVariable("restaurantid") int restaurantId, @RequestBody PatchRestaurantMenuReq patchRestaurantMenuReq) {
        try{
            int userIdByJwt = jwtService.getId();
            //userIdx와 접근한 유저가 같은지 확인
            if (patchRestaurantMenuReq.getUserId() != userIdByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            restaurantsService.patchMenu(restaurantId, patchRestaurantMenuReq);
            String result = "";
            return new BaseResponse<>(result);

        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
