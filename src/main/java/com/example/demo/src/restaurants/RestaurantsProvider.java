package com.example.demo.src.restaurants;


import com.example.demo.config.BaseException;
import com.example.demo.src.restaurants.model.GetRestaurantInfoRes;
import com.example.demo.src.restaurants.model.GetRestaurantMenuRes;
import com.example.demo.src.restaurants.model.GetRestaurantRes;
import com.example.demo.src.restaurants.model.GetRestaurantsRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;


@Service
public class RestaurantsProvider {


    private final RestaurantsDao restaurantsDao;
    private final JwtService jwtService;

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
            throw new BaseException(DATABASE_ERROR);
        }
    }

        public GetRestaurantRes getRestaurant(int id) throws BaseException{
            try{
                GetRestaurantRes getRestaurantRes = restaurantsDao.getRestaurant(id);
                return getRestaurantRes;
            }
            catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
        }


    public GetRestaurantMenuRes getRestaurantMenu(int restaurantId) throws BaseException {
        try {
            System.out.println("2");
            GetRestaurantMenuRes getRestaurantMenuRes = restaurantsDao.getRestaurantMenu(restaurantId);
            return getRestaurantMenuRes;
        } catch (Exception exception) {
            System.out.println("20");
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetRestaurantInfoRes getRestaurantInfo(int id) throws BaseException{
        try{
            GetRestaurantInfoRes getRestaurantInfoRes = restaurantsDao.getRestaurantInfo(id);
            return getRestaurantInfoRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
