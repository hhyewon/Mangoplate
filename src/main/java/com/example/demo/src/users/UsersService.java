package com.example.demo.src.users;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.users.model.PatchUserReq;
import com.example.demo.src.users.model.PostUserReq;
import com.example.demo.src.users.model.PostUserRes;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        System.out.println("2");
        if(usersProvider.checkEmail(postUserReq.getEmail()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        String pwd;
        try{
            //암호화
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int id = usersDao.createUser(postUserReq);
            System.out.println("3");
            //jwt 발급.
            String jwt = jwtService.createJwt(id);
            return new PostUserRes(jwt,id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(FAILED_TO_EMAIL_SIGN_UP);
        }
    }

    public void modifyNickname(PatchUserReq patchUserReq) throws BaseException {
        try{
            int result = usersDao.modifyNickname(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            System.out.println(exception);
            throw new BaseException(FAILED_TO_MODIFY_USER);
        }
    }
}
