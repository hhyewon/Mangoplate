package com.example.demo.src.eatdeal;


import com.example.demo.config.BaseException;
import com.example.demo.src.eatdeal.model.GetEatDealDetailRes;
import com.example.demo.src.eatdeal.model.GetEatDealRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


@Service
public class EatDealProvider {


    private final EatDealDao eatDealDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public EatDealProvider(EatDealDao eatDealDao) {
        this.eatDealDao = eatDealDao;
    }


    public List<GetEatDealRes> getEatdeal() throws BaseException {
        try {
            List<GetEatDealRes> getEatDealRes = eatDealDao.getEatdeal();
            return getEatDealRes;
        } catch (Exception exception) {

            throw new BaseException(FAILED_TO_EAT_DEAL);
        }
    }



        public GetEatDealDetailRes getEatdealDetail(int id) throws BaseException{
            try{
                GetEatDealDetailRes getEatDealDetailRes = eatDealDao.getEatdealDetail(id);
                return getEatDealDetailRes;
            }
            catch (Exception exception) {
                System.out.println(exception);
                throw new BaseException(NOT_FOUND_EATDEALID);
            }
        }
//
//
//    public GetRestaurantMenuRes getRestaurantMenu(int restaurantId) throws BaseException {
//        try {
//            System.out.println("2");
//            GetRestaurantMenuRes getRestaurantMenuRes = restaurantsDao.getRestaurantMenu(restaurantId);
//            return getRestaurantMenuRes;
//        } catch (Exception exception) {
//            System.out.println("20");
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public GetRestaurantInfoRes getRestaurantInfo(int id) throws BaseException{
//        try{
//            GetRestaurantInfoRes getRestaurantInfoRes = restaurantsDao.getRestaurantInfo(id);
//            return getRestaurantInfoRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
