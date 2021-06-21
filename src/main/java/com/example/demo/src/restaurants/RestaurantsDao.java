package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.src.restaurants.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_USERNAME;

@Repository
public class RestaurantsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetRestaurantsRes> getRestaurants(){
        String getRestaurantsQuery = "select Restaurant.id\n" +
                "       ,restaurantName\n" +
                "       ,firstUrl\n" +
                "       ,substring(restaurantLocation, 7, 3) as restaurantLocation\n"+
                "       ,round(6371 *\n" +
                "             acos(cos(radians(userLatitude)) * cos(radians(restaurantLatitude)) * cos(radians(restaurantLongitude)\n" +
                "                 - radians(userLongitude)) + sin(radians(userLatitude)) * sin(radians(restaurantLatitude))), 2)\n" +
                "                                 as distance"+
                "       ,ifnull(totalReviews,0) as totalReviews        " +
                "       ,ifnull(totalViews,0) as totalViews\n" +
                "       ,ifnull(rating,0) as rating \n" +
                "       ,ifnull(isLike, 0) as isLike" +
                "   from Restaurant" +
                "   inner join UserLocation" +
                "   left outer join (select ReviewImage.reviewId, reviewUrl as firstUrl\n" +
                "                          from ReviewImage\n" +
                "                                   inner join (select reviewId, min(id) as firstId\n" +
                "                                               from ReviewImage\n" +
                "                                               group by reviewId) firstImage\n" +
                "                                              on ReviewImage.id = firstImage.firstId) MainImage\n" +
                "                         on Restaurant.id = MainImage.reviewId" +
                "   left outer join (select Review.restaurantId, count(*) as totalReviews\n" +
                "                          from Review\n" +
                "                          group by restaurantId) Reviewc\n" +
                "                         on Restaurant.id = Reviewc.restaurantId" +
                "         left outer join (select RestaurantViews.restaurantId, count(*) as totalViews\n" +
                "                          from RestaurantViews\n" +
                "                          group by restaurantId) View\n" +
                "                         on Restaurant.id = View.restaurantId" +
                "                left outer join (select Review.restaurantId, AVG(score) as rating\n" +
                "                          from Review\n" +
                "                                   inner join ReviewScore on ReviewScore.reviewId = Review.id\n" +
                "                          group by Review.restaurantId) Score\n" +
                "                         on Score.restaurantId = Restaurant.id" +
                "         left outer join (select RestaurantLike.restaurantId, RestaurantLike.userId, count(*) as isLike\n" +
                "                          from RestaurantLike\n" +
                "                          where userId = '1'\n" +
                "                          group by restaurantId) IsLike\n" +
                "                         on Restaurant.id = IsLike.restaurantId";
        return this.jdbcTemplate.query(getRestaurantsQuery,
                (rs,rowNum) -> new GetRestaurantsRes(
                        rs.getInt("id"),
                        rs.getString("restaurantName"),
                        rs.getString("firstUrl"),
                        rs.getString("restaurantLocation"),
                        rs.getFloat("distance"),
                        rs.getInt("totalViews"),
                        rs.getInt("totalReviews"),
                        rs.getFloat("rating"),
                        rs.getInt("isLike"))
        );



    }


    public GetRestaurantRes getRestaurant(int id) throws BaseException {
        String getRestaurantQuery = "select Restaurant.id\n" +
                "     , firstUrl\n" +
                "     , restaurantName\n" +
                "     , restaurantLocation\n" +
                "     , ifnull(totalViews, 0)   as totalViews\n" +
                "     , ifnull(totalReviews, 0) as totalReviews\n" +
                "     , round(rating, 2)        as rating\n" +
                "     , ifnull(totalLike, 0)    as totalLike\n" +
                "     , ifnull(isLike, 0)       as isLike\n" +
                "     , ifnull(isVisited, 0)    as isVisited\n" +
                "\n" +
                "from Restaurant\n" +
                "         left outer join (select ReviewImage.reviewId, reviewUrl as firstUrl\n" +
                "                          from ReviewImage\n" +
                "                                   inner join (select reviewId, min(id) as firstId\n" +
                "                                               from ReviewImage\n" +
                "                                               group by reviewId) firstImage\n" +
                "                                              on ReviewImage.id = firstImage.firstId) MainImage\n" +
                "                         on Restaurant.id = MainImage.reviewId\n" +
                "         left outer join (select Review.restaurantId, count(*) as totalReviews\n" +
                "                          from Review\n" +
                "                          group by restaurantId) Reviewc\n" +
                "                         on Restaurant.id = Reviewc.restaurantId\n" +
                "         left outer join (select RestaurantViews.restaurantId, count(*) as totalViews\n" +
                "                          from RestaurantViews\n" +
                "                          group by restaurantId) View\n" +
                "                         on Restaurant.id = View.restaurantId\n" +
                "         left outer join (select RestaurantLike.restaurantId, count(*) as totalLike\n" +
                "                          from RestaurantLike\n" +
                "                          group by restaurantId) Liketable\n" +
                "                         on Restaurant.id = Liketable.restaurantId\n" +
                "         left outer join (select Review.restaurantId, AVG(score) as rating\n" +
                "                          from Review\n" +
                "                                   inner join ReviewScore on ReviewScore.reviewId = Review.id\n" +
                "                          group by Review.restaurantId) Score\n" +
                "                         on Score.restaurantId = Restaurant.id\n" +
                "         left outer join (select RestaurantLike.restaurantId, RestaurantLike.userId, count(*) as isLike\n" +
                "                          from RestaurantLike\n" +
                "                          where userId = '1'\n" +
                "                          group by restaurantId) IsLike\n" +
                "                         on Restaurant.id = IsLike.restaurantId\n" +
                "         left outer join (select RestaurantVisited.restaurantId, RestaurantVisited.userId, count(*) as isVisited\n" +
                "                          from RestaurantVisited\n" +
                "                          where userId = '1'\n" +
                "                          group by restaurantId) IsVisited\n" +
                "                         on Restaurant.id = IsVisited.restaurantId\n" +
                "where Restaurant.id=?";
        int getRestaurantParams = id;
        return this.jdbcTemplate.queryForObject(getRestaurantQuery,
                (rs, rowNum) -> new GetRestaurantRes(
                        rs.getInt("id"),
                        rs.getString("restaurantName"),
                        rs.getString("firstUrl"),
                        rs.getString("restaurantLocation"),
                        rs.getInt("totalViews"),
                        rs.getInt("totalReviews"),
                        rs.getFloat("rating"),
                        rs.getInt("isLike"),
                        rs.getInt("isVisited")),
                getRestaurantParams);

    }

    public GetRestaurantMenuRes getRestaurantMenu(int restaurantId){
        String getRestaurantMenuQuery = "select * from RestaurantMenu where restaurantId = ?";
        int getRestaurantMenuParams = restaurantId;
        System.out.println("3");
        return this.jdbcTemplate.queryForObject(getRestaurantMenuQuery,
                (rs, rowNum) -> new GetRestaurantMenuRes(
                        rs.getInt("restaurantId"),
                        rs.getString("menuName"),
                        rs.getInt("price"),
                        rs.getString("nickName")),
                getRestaurantMenuParams);
    }

    public GetRestaurantInfoRes getRestaurantInfo(int id){
        String getRestaurantInfoQuery = "" +
                "select Restaurant.id" +
                "     , concat('마지막 업데이트: ', date_format(Restaurant.updatedAt, '%Y-%m-%d')) as updatedAt\n" +
                "     , CONCAT(openHour, ' ~ ', closeHour) as businessHours\n" +
                "     , CONCAT(breakTimeStart, ' ~ ', breakTimeEnd) as breakTime\n" +
                "     , offDays\n" +
                "     , variety\n" +
                "     , isParking\n" +
                "     , Concat(nickname, '님의 소중한 발견으로 등록한 장소예요!') as writer\n" +
                "from Restaurant\n" +
                "         left outer join User on Restaurant.userId = User.id\n" +
                "where Restaurant.id = ?";
        int getRestaurantInfoParams = id;
        return this.jdbcTemplate.queryForObject(getRestaurantInfoQuery,
                (rs, rowNum) -> new GetRestaurantInfoRes(
                        rs.getInt("id"),
                        rs.getString("updatedAt"),
                        rs.getString("businessHours"),
                        rs.getString("breakTime"),
                        rs.getString("offDays"),
                        rs.getString("variety"),
                        rs.getString("isParking"),
                        rs.getString("writer")
                        ),
                getRestaurantInfoParams);
    }




    public int patchRestaurantLike(PatchRestaurantReq patchRestaurantReq){
        String modifyRestaurantLikeQuery = "update RestaurantLike set status =? where userId =? AND restaurantId = ?; ";
        Object[] modifyRestaurantLikeParams = new Object[]{patchRestaurantReq.getRestaurantId(), patchRestaurantReq.getUserId(),patchRestaurantReq.getStatus()};

        return this.jdbcTemplate.update(modifyRestaurantLikeQuery,modifyRestaurantLikeParams);
    }

    public int createRestaurant(PostRestaurantReq postRestaurantReq){
        System.out.println("3");
        String createRestaurantQuery = "insert into Restaurant (restaurantName, restaurantLocation, restaurantNumber, variety, userId) VALUES (?,?,?,?,?)";
        Object[] createRestaurantParams = new Object[]{postRestaurantReq.getRestaurantName(), postRestaurantReq.getRestaurantLocation(), postRestaurantReq.getRestaurantNumber(), postRestaurantReq.getVariety(),postRestaurantReq.getUserId()};
        this.jdbcTemplate.update(createRestaurantQuery, createRestaurantParams);
        System.out.println("4");
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }


}
