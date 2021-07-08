package com.example.demo.src.eatdeal;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.eatdeal.model.GetEatDealDetailRes;
import com.example.demo.src.eatdeal.model.GetEatDealRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.FAILED_TO_EAT_DEAL;
import static com.example.demo.config.BaseResponseStatus.NOT_FOUND_EATDEALID;

@Repository
public class EatDealDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetEatDealRes> getEatdeal() {
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
                (rs, rowNum) -> new GetEatDealRes(
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
        try {
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
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new BaseException(NOT_FOUND_EATDEALID);
        }

    }


}
