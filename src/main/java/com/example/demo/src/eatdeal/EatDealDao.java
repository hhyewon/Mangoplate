package com.example.demo.src.eatdeal;

import com.example.demo.config.BaseException;
import com.example.demo.src.eatdeal.model.GetEatDealDetailRes;
import com.example.demo.src.eatdeal.model.GetEatDealRes;
import com.example.demo.src.restaurants.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EatDealDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetEatDealRes> getEatdeal(){
        String getEatDealQuery = "select eatDealUrl\n" +
                "     , eatDealPrice\n" +
                "     , eatDealDiscount\n" +
                "     , round(eatDealPrice - (eatDealPrice * eatDealDiscount) / 100, -3) as discountPrice\n" +
                "     , restaurantName\n" +
                "     , substring(RestaurantLocation, 7, 3)                                        as RestaurantLocation\n" +
                "     , eatDealInfo\n" +
                "     , isPickUp\n" +
                "from EatDeal\n" +
                "         inner join EatDealImage on EatDeal.id = EatDealImage.eatdealId\n" +
                "         inner join Restaurant on Restaurant.id = EatDeal.id";
        return this.jdbcTemplate.query(getEatDealQuery,
                (rs,rowNum) -> new GetEatDealRes(
                        rs.getString("eatDealUrl"),
                        rs.getInt("eatDealPrice"),
                        rs.getInt("eatDealDiscount"),
                        rs.getInt("discountPrice"),
                        rs.getString("restaurantName"),
                        rs.getString("restaurantLocation"),
                        rs.getString("eatDealInfo"),
                        rs.getString("isPickUp")));
    }


    public GetEatDealDetailRes getEatdealDetail(int id) throws BaseException {
        String getEatDealDetailQuery = "select eatDealUrl\n" +
                "     , substring(restaurantLocation, 7, 3) as restaurantLocation\n" +
                "     , restaurantName\n" +
                "     , eatDealPrice\n" +
                "     , eatDealDiscount\n" +
                "     , round(eatDealPrice - (eatDealPrice * eatDealDiscount) / 100, -3) as discountPrice\n" +
                "     , eatDealInfo\n" +
                "     , isPickUp\n" +
                "     , menuInfo\n" +
                "     , howToUse\n" +
                "     , refundPolicy\n" +
                "from EatDeal\n" +
                "         inner join EatDealImage on EatDeal.id = EatDealImage.eatdealId\n" +
                "         inner join Restaurant on Restaurant.id = EatDeal.id\n" +
                "where EatDeal.id =?";
        int getEatDealDetailParams = id;
        return this.jdbcTemplate.queryForObject(getEatDealDetailQuery,
                (rs, rowNum) -> new GetEatDealDetailRes(
                        rs.getString("eatDealUrl"),
                        rs.getInt("eatDealPrice"),
                        rs.getInt("eatDealDiscount"),
                        rs.getInt("discountPrice"),
                        rs.getString("restaurantName"),
                        rs.getString("restaurantLocation"),
                        rs.getString("eatDealInfo"),
                        rs.getString("isPickUp"),
                        rs.getString("menuInfo"),
                        rs.getString("howToUse"),
                        rs.getString("refundPolicy")),
                getEatDealDetailParams);

    }
//
//    public GetRestaurantMenuRes getRestaurantMenu(int restaurantId){
//        String getRestaurantMenuQuery = "select * from RestaurantMenu where restaurantId = ?";
//        int getRestaurantMenuParams = restaurantId;
//        System.out.println("3");
//        return this.jdbcTemplate.queryForObject(getRestaurantMenuQuery,
//                (rs, rowNum) -> new GetRestaurantMenuRes(
//                        rs.getInt("restaurantId"),
//                        rs.getString("menuName"),
//                        rs.getInt("price"),
//                        rs.getString("nickName")),
//                getRestaurantMenuParams);
//    }
//
//    public GetRestaurantInfoRes getRestaurantInfo(int id){
//        String getRestaurantInfoQuery = "" +
//                "select Restaurant.id" +
//                "     , concat('마지막 업데이트: ', date_format(Restaurant.updatedAt, '%Y-%m-%d')) as updatedAt\n" +
//                "     , CONCAT(openHour, ' ~ ', closeHour) as businessHours\n" +
//                "     , CONCAT(breakTimeStart, ' ~ ', breakTimeEnd) as breakTime\n" +
//                "     , offDays\n" +
//                "     , variety\n" +
//                "     , isParking\n" +
//                "     , Concat(nickname, '님의 소중한 발견으로 등록한 장소예요!') as writer\n" +
//                "from Restaurant\n" +
//                "         left outer join User on Restaurant.userId = User.id\n" +
//                "where Restaurant.id = ?";
//        int getRestaurantInfoParams = id;
//        return this.jdbcTemplate.queryForObject(getRestaurantInfoQuery,
//                (rs, rowNum) -> new GetRestaurantInfoRes(
//                        rs.getInt("id"),
//                        rs.getString("updatedAt"),
//                        rs.getString("businessHours"),
//                        rs.getString("breakTime"),
//                        rs.getString("offDays"),
//                        rs.getString("variety"),
//                        rs.getString("isParking"),
//                        rs.getString("writer")
//                        ),
//                getRestaurantInfoParams);
//    }
//
//
//
//
//    public int patchRestaurantLike(PatchRestaurantReq patchRestaurantReq){
//        String modifyRestaurantLikeQuery = "update RestaurantLike set status =? where userId =? AND restaurantId = ?; ";
//        Object[] modifyRestaurantLikeParams = new Object[]{patchRestaurantReq.getRestaurantId(), patchRestaurantReq.getUserId(),patchRestaurantReq.getStatus()};
//
//        return this.jdbcTemplate.update(modifyRestaurantLikeQuery,modifyRestaurantLikeParams);
//    }
//
//    public int createRestaurant(PostRestaurantReq postRestaurantReq){
//        System.out.println("3");
//        String createRestaurantQuery = "insert into Restaurant (restaurantName, restaurantLocation, restaurantNumber, variety, userId) VALUES (?,?,?,?,?)";
//        Object[] createRestaurantParams = new Object[]{postRestaurantReq.getRestaurantName(), postRestaurantReq.getRestaurantLocation(), postRestaurantReq.getRestaurantNumber(), postRestaurantReq.getVariety(),postRestaurantReq.getUserId()};
//        this.jdbcTemplate.update(createRestaurantQuery, createRestaurantParams);
//        System.out.println("4");
//        String lastInserIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
//    }


}
