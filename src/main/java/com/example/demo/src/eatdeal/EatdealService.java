package com.example.demo.src.eatdeal;

import com.example.demo.config.BaseException;
import com.example.demo.src.restaurants.RestaurantsDao;
import com.example.demo.src.restaurants.RestaurantsProvider;
import com.example.demo.src.restaurants.model.PatchRestaurantReq;
import com.example.demo.src.restaurants.model.PostRestaurantReq;
import com.example.demo.src.restaurants.model.PostRestaurantRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_USERNAME;

@Service
public class EatdealService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestaurantsDao restaurantsDao;
    private final RestaurantsProvider restaurantsProvider;
    private final JwtService jwtService;

    @Autowired
    public EatdealService(RestaurantsDao restaurantsDao, RestaurantsProvider restaurantsProvider, JwtService jwtService) {
        this.restaurantsDao = restaurantsDao;
        this.restaurantsProvider = restaurantsProvider;
        this.jwtService = jwtService;
    }

    public void patchRestaurantLike(PatchRestaurantReq patchRestaurantReq) throws BaseException {
        try{
            int result = restaurantsDao.patchRestaurantLike(patchRestaurantReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //POST
    public PostRestaurantRes createRestaurant(PostRestaurantReq postRestaurantReq) throws BaseException {

        try{
            System.out.println("2");
            int id = restaurantsDao.createRestaurant(postRestaurantReq);
            return new PostRestaurantRes(id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
