package com.example.demo.src.users;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.users.model.GetRestaurantVisitedRes;
import com.example.demo.src.users.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
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
    private final JwtService jwtService;

    public UsersProvider(UsersDao usersDao, JwtService jwtService) {
        this.usersDao = usersDao;
        this.jwtService = jwtService;
    }


    public List<GetUsersRes> getUsers() throws BaseException{
        try{
            List<GetUsersRes> getUserRes = usersDao.getUsers();
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


    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
        Users users = usersDao.getPwd(postLoginReq);
        String password;
        try {
            //암호화
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(users.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(postLoginReq.getPassword().equals(password)){
            int id = usersDao.getPwd(postLoginReq).getId();
            //jwt 발급
            String jwt = jwtService.createJwt(id);
            return new PostLoginRes(id,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

    public List<GetRestaurantVisitedRes> getRestaurantVisited(int id) throws BaseException {
        try {
            List<GetRestaurantVisitedRes> getRestaurantVisitedRes = usersDao.getRestaurantVisited(id);
            return getRestaurantVisitedRes;
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
