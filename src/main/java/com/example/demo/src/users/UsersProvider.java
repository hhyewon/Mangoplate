package com.example.demo.src.users;


import com.example.demo.config.BaseException;
import com.example.demo.src.users.model.GetFollowerRes;
import com.example.demo.src.users.model.GetRestaurantLikeRes;
import com.example.demo.src.users.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UsersProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UsersDao usersDao;

    public UsersProvider(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    public List<GetUserRes> getUsers() throws BaseException{
        try{
            List<GetUserRes> getUserRes = usersDao.getUsers();
            return getUserRes;
        }
        catch (Exception exception) {
            System.out.println("51");
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetUserRes getUser(int id) throws BaseException {
        try {
            System.out.println("4");
            GetUserRes getUserRes = usersDao.getUser(id);
            return getUserRes;
        } catch (Exception exception) {
            System.out.println("40");
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            System.out.println("5");
            return usersDao.checkEmail(email);
        } catch (Exception exception){
            System.out.println("50");
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public List<GetRestaurantLikeRes> getRestaurantLike(int userId) throws BaseException {
        try {
            System.out.println("4");
            List<GetRestaurantLikeRes> getRestaurantLikeRes = usersDao.getRestaurantLike(userId);
            return getRestaurantLikeRes;
        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("40");
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetFollowerRes> getfollower(int userId) throws BaseException{
        try{
            List<GetFollowerRes> getFollowerRes = usersDao.getfollower(userId);
            return getFollowerRes;
        }
        catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
//        User user = userDao.getPwd(postLoginReq);
//        String password;
//        try {
//            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPassword());
//        } catch (Exception ignored) {
//            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
//        }
//
//        if(postLoginReq.getPassword().equals(password)){
//            int userIdx = userDao.getPwd(postLoginReq).getUserIdx();
//            String jwt = jwtService.createJwt(userIdx);
//            return new PostLoginRes(userIdx,jwt);
//        }
//        else{
//            throw new BaseException(FAILED_TO_LOGIN);
//        }
//
//    }

}
