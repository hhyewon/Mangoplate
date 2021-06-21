package com.example.demo.src.users;



import com.example.demo.src.users.model.GetFollowerRes;
import com.example.demo.src.users.model.GetRestaurantLikeRes;
import com.example.demo.src.users.model.GetUserRes;
import com.example.demo.src.users.model.PostUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UsersDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
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
    public GetUserRes getUser(int id){
        String getUserQuery = "select * from User where id = ?";
        int getUserParams = id;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phoneNumber"),
                        rs.getString("userUrl"),
                        rs.getString("nickname")),
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
//    public int modifyUserName(PatchUserReq patchUserReq){
//        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
//        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};
//
//        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
//    }
//
//    public User getPwd(PostLoginReq postLoginReq){
//        String getPwdQuery = "select userIdx, password,email,userName,ID from UserInfo where ID = ?";
//        String getPwdParams = postLoginReq.getId();
//
//        return this.jdbcTemplate.queryForObject(getPwdQuery,
//                (rs,rowNum)-> new User(
//                        rs.getInt("userIdx"),
//                        rs.getString("ID"),
//                        rs.getString("userName"),
//                        rs.getString("password"),
//                        rs.getString("email")
//                ),
//                getPwdParams
//                );
//
//    }
//
//

}
