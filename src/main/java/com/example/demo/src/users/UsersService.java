package com.example.demo.src.users;


import com.example.demo.config.BaseException;
import com.example.demo.src.users.model.PostUserReq;
import com.example.demo.src.users.model.PostUserRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UsersService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UsersDao usersDao;
    private final UsersProvider usersProvider;
    private final JwtService jwtService;

    public UsersService(UsersDao usersDao, UsersProvider usersProvider, JwtService jwtService) {
        this.usersDao = usersDao;
        this.usersProvider = usersProvider;
        this.jwtService = jwtService;
    }




    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        try{
            System.out.println("2");
            if(usersProvider.checkEmail(postUserReq.getEmail()) ==1){
                throw new BaseException(POST_USERS_EXISTS_EMAIL);
            }
            int id = usersDao.createUser(postUserReq);
            System.out.println("3");
            return new PostUserRes(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
//
//    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
//        try{
//            int result = userDao.modifyUserName(patchUserReq);
//            if(result == 0){
//                throw new BaseException(MODIFY_FAIL_USERNAME);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
}
