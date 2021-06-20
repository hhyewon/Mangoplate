package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.src.restaurants.model.GetRestaurantRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RestaurantsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetRestaurantRes> getRestaurants(){
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
                (rs,rowNum) -> new GetRestaurantRes(
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

//    public List<GetRestaurantRes> getRestaurantByRestaurantName(String restaurantName){
//        String getRestaurantByRestaurantNameQuery = "select * from Restaurant where restaurantName =?";
//        String getRestaurantByRestaurantNameParams = restaurantName;
//        return this.jdbcTemplate.query(getRestaurantByRestaurantNameQuery,
//                (rs, rowNum) -> new GetRestaurantRes(
//                        rs.getInt("restaurantId"),
//                        rs.getString("restaurantName"),
//                        rs.getString("restaurantLocation")),
//                     //   rs.getString("views")),
//                getRestaurantByRestaurantNameParams);
//    }

//    public GetRestaurantRes getRestaurant(int id) throws BaseException {
//        String getRestaurantQuery = "select id, restaurantName, reviewUrl, restaurantLocation\n" +
//                "from Restaurant\n" +
//                "         left outer join Review on Review.restaurantId = Restaurant.id\n" +
//                "         left outer join (select ReviewImage.reviewId, reviewUrl, min(id) as firstImageId\n" +
//                "                          from ReviewImage\n" +
//                "                          group by reviewId) RestaurantImage on RestaurantImage.reviewId = Review.id\n" +
//                " where Restaurant.id=? \n";
//        int getRestaurantParams = id;
//        return this.jdbcTemplate.queryForObject(getRestaurantQuery,
//                (rs, rowNum) -> new GetRestaurantRes(
//                        rs.getInt("id"),
//                        rs.getString("restaurantName"),
//                        rs.getString("reviewUrl"),
//                        rs.getString("location")),
//                getRestaurantParams);
//
//    }
//
}
