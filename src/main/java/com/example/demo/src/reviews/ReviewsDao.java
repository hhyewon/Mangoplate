package com.example.demo.src.reviews;


import com.example.demo.src.reviews.model.PatchReviewReq;
import com.example.demo.src.reviews.model.PostReviewReq;
import com.example.demo.src.user.model.*;
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

   public int createReview(PostReviewReq postReviewReq){

        System.out.println("1");
        String createReviewQuery = "insert into Review (userId,restaurantId, comment) VALUES (?,?,?)";
        System.out.println("2");
        Object[] createReviewParams = new Object[]{postReviewReq.getUserId(), postReviewReq.getRestaurantId(), postReviewReq.getComment()};
        System.out.println("3");
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);
        System.out.println("4");

        String lastInserIdQuery = "select last_insert_id()";

        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);

    }


    public int modifyReview(PatchReviewReq patchReviewReq){
        String modifyReviewQuery = "update ReviewScore as ReviewScore,\n" +
                "         ReviewImage as ReviewImage,\n" +
                "    Review as Review\n" +
                "set\n" +
                "   ReviewScore.score    = ?,\n" +
                "    ReviewImage.reviewUrl=?,\n" +
                "    Review.comment=?\n" +
                "where  Review.id =ReviewImage.reviewId and Review.id=ReviewScore.reviewID and Review.id=?;";
        Object[] modifyReviewParams = new Object[]{patchReviewReq.getId(), patchReviewReq.getReviewUrl(), patchReviewReq.getComment(),patchReviewReq.getScore()};

        return this.jdbcTemplate.update(modifyReviewQuery,modifyReviewParams);
    }
}
