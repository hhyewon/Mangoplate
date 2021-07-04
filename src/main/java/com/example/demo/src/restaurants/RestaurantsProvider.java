package com.example.demo.src.restaurants;


import com.example.demo.config.BaseException;
import com.example.demo.src.restaurants.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


@Service
public class RestaurantsProvider {


    private final RestaurantsDao restaurantsDao;
    private final JwtService jwtService;
    private  GetRestaurantsRes getRestaurantsRes;

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public RestaurantsProvider(RestaurantsDao restaurantsDao, JwtService jwtService) {
        this.restaurantsDao = restaurantsDao;
        this.jwtService = jwtService;
    }

    public List<GetRestaurantsRes> getRestaurants() throws BaseException {
        try {
            List<GetRestaurantsRes> getRestaurantsRes = restaurantsDao.getRestaurants();
            return getRestaurantsRes;
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(FAILED_TO_RESTAURANTS);
        }
    }

        public GetRestaurantRes getRestaurant(int id) throws BaseException{
            try{
                GetRestaurantRes getRestaurantRes = restaurantsDao.getRestaurant(id);
                return getRestaurantRes;
            }
            catch (Exception exception) {

                throw new BaseException(FAILED_TO_RESTAURANT);
            }
        }


    public List<GetRestaurantMenuRes> getRestaurantMenu(int restaurantId) throws BaseException {
        try {
            System.out.println("2");
            List<GetRestaurantMenuRes> getRestaurantMenuRes = restaurantsDao.getRestaurantMenu(restaurantId);
            return getRestaurantMenuRes;
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(FAILED_TO_RESTAURANT_MENU);
        }
    }

    public GetRestaurantInfoRes getRestaurantInfo(int id) throws BaseException{
        try{
            GetRestaurantInfoRes getRestaurantInfoRes = restaurantsDao.getRestaurantInfo(id);
            return getRestaurantInfoRes;
        }
        catch (Exception exception) {
            throw new BaseException(FAILED_TO_RESTAURANT_CONVENIENCE);
        }
    }




}
