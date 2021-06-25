package com.example.demo.src.reviews;


import com.example.demo.src.restaurants.model.GetRestaurantsRes;
import com.example.demo.src.restaurants.model.PostRestaurantReq;
import com.example.demo.src.reviews.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetReviewRes getReviews(int id) {
        String getReviewsQuery = "select Review.id,\n" +
                "       Review.restaurantId,\n" +
                "       userUrl,\n" +
                "       nickname,\n" +
                "       ifnull(totalReviewCount, 0)         as totalReviewCount,\n" +
                "       ifnull(followCount, 0)              as followCount,\n" +
                "       score,\n" +
                "       case\n" +
                "           when score = 3 then '맛있다'\n" +
                "           when score = 2 then '그저그렇다'\n" +
                "           when score = 1 then '별로'\n" +
                "           end                             as scoreIcon,\n" +
                "       restaurantName,\n" +
                "       substring(restaurantLocation, 7, 3) as restaurantLocation,\n" +
                "       comment,\n" +
                "       Review.updatedAt                    as reviewUpdatedAt,\n" +
                "       reviewUrl,\n" +
                "       reply,\n" +
                "       ReviewReply.updatedAt               as replyUpdatedAt,\n" +
                "       ifnull(replytotal, 0)               as replytotal\n" +
                "from Review\n" +
                "         inner join ReviewScore on ReviewScore.reviewId = Review.id\n" +
                "         INNER JOIN User on User.id = Review.userId\n" +
                "         inner Join Restaurant on Review.restaurantId = Restaurant.id\n" +
                "         left outer join ReviewImage on Review.id = ReviewImage.reviewId\n" +
                "         left outer join ReviewReply on Review.id = ReviewReply.reviewId\n" +
                "         left outer join (select ReviewReply.reviewId, count(*) as replytotal\n" +
                "                          from ReviewReply\n" +
                "                          group by reviewId) Reply\n" +
                "                         on Review.id = Reply.reviewId\n" +
                "         left outer join Follow on Follow.userId = User.id\n" +
                "         left outer join (select Follow.id, count(*) as followCount\n" +
                "                          from Follow\n" +
                "                          group by userId) FollowCount\n" +
                "                         on User.id = Follow.userId\n" +
                "         left outer join (select userId, count(*) as totalReviewCount\n" +
                "                          from Review\n" +
                "                          where status = 'ACTIVATE'\n" +
                "                          group by userId) ReviewCount\n" +
                "                         on ReviewCount.userId = User.id\n" +
                "where Review.id=?\n" +
                "group by Review.userId";
        int getReviewsParams = id;
        return this.jdbcTemplate.queryForObject(getReviewsQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("id"),
                        rs.getInt("restaurantId"),
                        rs.getString("userUrl"),
                        rs.getString("nickname"),
                        rs.getInt("totalReviewCount"),
                        rs.getInt("followCount"),
                        rs.getFloat("score"),
                        rs.getString("scoreIcon"),
                        rs.getString("restaurantName"),
                        rs.getString("restaurantLocation"),
                        rs.getString("comment"),
                        rs.getString("reviewUpdatedAt"),
                        rs.getString("reviewUrl"),
                        rs.getString("reply"),
                        rs.getString("replyUpdatedAt"),
                        rs.getInt("replyTotal")),
                        getReviewsParams);
    }

   public int createReview(PostReviewReq postReviewReq){
        System.out.println("1");
        String createReviewQuery = "insert into Review(userId,restaurantId,comment) values (?,?,?)\n";
;
        System.out.println("2");
        Object[] createReviewParams = new Object[]{postReviewReq.getUserId(), postReviewReq.getRestaurantId(), postReviewReq.getComment()};
        System.out.println("3");
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);
        System.out.println("4");

        String lastInserIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);

    }


    public int modifyReview(PatchReviewReq patchReviewReq){
        String modifyReviewQuery = "update Review \n" +
                "left outer join ReviewImage on ReviewImage.reviewId = Review.id\n" +
                "left outer join ReviewScore on ReviewScore.reviewId = Review.id\n" +
                "set userId =?," +
                "   ReviewScore.score    = ?,\n" +
                "    ReviewImage.reviewUrl=?,\n" +
                "    Review.comment=?\n" +
                "where Review.id =?;";
        Object[] modifyReviewParams = new Object[]{ patchReviewReq.getUserId(), patchReviewReq.getScore(),   patchReviewReq.getReviewUrl(),patchReviewReq.getComment(),patchReviewReq.getId() };

        return this.jdbcTemplate.update(modifyReviewQuery,modifyReviewParams);

    }

    public int createReply(int reviewId,PostReplyReq postReplyReq){
        System.out.println("3");
        String createReplyQuery = "insert into ReviewReply (reviewId, userId, reply) VALUES (?, ?,?)";
        Object[] createReplyParams = new Object[]{reviewId,postReplyReq.getUserId(),postReplyReq.getReply()};
        this.jdbcTemplate.update(createReplyQuery, createReplyParams);
        System.out.println("4");
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int modifyReviewDel(PatchReviewDelReq patchReviewDelReq){
        String modifyReviewDelQuery = "update Review set status=? where Review.id =?";
        Object[] modifyReviewDelParams = new Object[]{ patchReviewDelReq.getStatus(),patchReviewDelReq.getId() };

        return this.jdbcTemplate.update(modifyReviewDelQuery,modifyReviewDelParams);

    }
}

