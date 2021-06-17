package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 레스토랑 전체보기 API
     * [GET] /restaurants
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /restaurants? RestaurantId=
     * @return BaseResponse<List<GetUserRes>>
     */

    @GetMapping("") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);
            }
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}
