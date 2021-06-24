package com.example.demo.src.users;



import com.example.demo.src.users.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDao {

    private JdbcTemplate jdbcTemplate;
    private JwtService jwtService;

    @Autowired
    public UsersDao(JdbcTemplate jdbcTemplate, JwtService jwtService) {
        this.jdbcTemplate = jdbcTemplate;
        this.jwtService = jwtService;
    }



    public List<GetUsersRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUsersRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phoneNumber"),
                        rs.getString("userUrl"),
                        rs.getString("nickname"))
                );
    }
//
//    public List<GetUserRes> getUsersByEmail(String email){
//        String getUsersByEmailQuery = "select * from UserInfo where email =?";
//        String getUsersByEmailParams = email;
//        return this.jdbcTemplate.query(getUsersByEmailQuery,
//                (rs, rowNum) -> new GetUserRes(
//                        rs.getInt("userIdx"),
//                        rs.getString("userName"),
//                        rs.getString("ID"),
//                        rs.getString("Email"),
//                        rs.getString("password")),
//                getUsersByEmailParams);
//    }
//
    //특정 회원 조회
    public GetUserRes getUser(int id){
        String getUserQuery = "select User.id,\n" +
                "       userUrl,\n" +
                "       nickname,\n" +
                "       ifnull(totalFollowCount,0) as totalFollowCount,\n" +
                "       ifnull(totalReviewCount,0) as totalReviewCount,\n" +
                "       ifnull(totalVisited,0) as totalVisited,\n" +
                "       ifnull(totalLike,0) as totalLike\n" +
                "from User\n" +
                "         left outer join (select RestaurantLike.userId, count(*) as totalLike\n" +
                "                          from RestaurantLike\n" +
                "#                           where userId = ?\n" +
                "                          group by restaurantId) IsLike\n" +
                "                         on User.id = IsLike.userId\n" +
                "         left outer join (select RestaurantVisited.userId, count(*) as totalVisited\n" +
                "                          from RestaurantVisited\n" +
                "#                           where userId = ?\n" +
                "                          group by restaurantId) IsVisited\n" +
                "                         on User.id = IsVisited.userId\n" +
                "         left outer join Follow on Follow.userId = User.id\n" +
                "         left outer join (select Follow.id, count(*) as totalFollowCount\n" +
                "                     from Follow) FollowCount\n" +
                "                    on User.id = Follow.userId\n" +
                "         left outer join (select Review.userId, count(*) as totalReviewCount\n" +
                "                          from Review\n" +
                "                          group by userId) Reviews\n" +
                "                         on User.id = Reviews.userId\n" +
                "where User.id=?\n" +
                "group by User.id";
        int getUserParams = id;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("userUrl"),
                        rs.getString("nickname"),
                        rs.getInt("totalFollowCount"),
                        rs.getInt("totalReviewCount"),
                        rs.getInt("totalVisited"),
                        rs.getInt("totalLike")),
                getUserParams);
    }

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (email, password, phoneNumber, userUrl, nickname) VALUES (?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getEmail(), postUserReq.getPassword(), postUserReq.getPhoneNumber(), postUserReq.getUserUrl(), postUserReq.getNickname() };
        this.jdbcTemplate.update(createUserQuery, createUserParams);
        System.out.println("6");
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from User where email = ?)";
        String checkEmailParams = email;
        System.out.println("7");
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public List<GetRestaurantLikeRes> getRestaurantLike(int userId){
        String getRestaurantLikQuery = "select Restaurant.userId,\n" +
                "       reviewUrl                 ,\n" +
                "       substring(restaurantLocation, 7, 3)  as restaurantLocation,\n" +
                "       restaurantName            ,\n" +
                "       ifnull(views,0)                     as views,\n" +
                "       ifnull(reviewCount, 0)    as reviews,\n" +
                "       ifnull(IsLike.isLike, 0)  as isLike\n" +
                "from Restaurant\n" +
                "         left outer join (select Review.id, reviewUrl\n" +
                "                          from Review\n" +
                "                                   inner join ReviewImage on Review.id = ReviewImage.reviewId) MainImage\n" +
                "                         on MainImage.id = Restaurant.id\n" +
                "         left outer join (select Review.restaurantId, count(*) as reviewCount\n" +
                "                          from Review) ReviewCount\n" +
                "                         on ReviewCount.restaurantId = Restaurant.id\n" +
                "\n" +
                "         left outer join (select RestaurantLike.restaurantId, RestaurantLike.userId, count(*) as isLike\n" +
                "                          from RestaurantLike\n" +
                "                          where userId = ?\n" +
                "                          group by restaurantId) IsLike\n" +
                "                         on Restaurant.id = IsLike.restaurantId\n" +
                "         left outer join (select ReviewScore.reviewId, AVG(score) as rating\n" +
                "                          from ReviewScore\n" +
                "                          group by ReviewScore.id) Score\n" +
                "                         on Restaurant.id = Score.reviewId\n" +
                "where IsLike=1 \n";
        int getRestaurantLikeParams = userId;
        return this.jdbcTemplate.query(getRestaurantLikQuery,
                (rs, rowNum) -> new GetRestaurantLikeRes(
                        rs.getInt("userId"),
                        rs.getString("reviewUrl"),
                        rs.getString("restaurantLocation"),
                        rs.getString("restaurantName"),
                        rs.getInt("views"),
                        rs.getInt("reviews"),
                        rs.getInt("isLike")),
                getRestaurantLikeParams);
    }

    public List<GetFollowerRes> getfollower(int userId){
        String getFolloewerQuery = "select followId from Follow where userId=? order by followId asc ";
        int getFolloewerParams = userId;
        return this.jdbcTemplate.query(getFolloewerQuery,
                (rs, rowNum) -> new GetFollowerRes(
                        rs.getInt("followId")
                ),
                getFolloewerParams);
    }

    public Users getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select id,nickname, email, password from User where email = ?";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new Users(
                        rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getString("email"),
                        rs.getString("password")
                ),
                getPwdParams
                );
    }

    public int modifyNickname(PatchUserReq patchUserReq){
        String modifyNicknameQuery = "update User set nickname = ?, email =?, phoneNumber=?, userUrl=? where id = ? ";
        Object[] modifyNicknameParams = new Object[]{ patchUserReq.getNickname(),patchUserReq.getEmail(),patchUserReq.getPhoneNumber(),patchUserReq.getUserUrl(),patchUserReq.getId()};

        return this.jdbcTemplate.update(modifyNicknameQuery,modifyNicknameParams);
    }

}
