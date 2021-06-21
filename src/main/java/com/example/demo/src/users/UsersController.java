package com.example.demo.src.users;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.users.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/users")
public class UsersController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UsersProvider usersProvider;
    @Autowired
    private final UsersService usersService;

    public UsersController(UsersProvider usersProvider, UsersService usersService) {
        this.usersProvider = usersProvider;
        this.usersService = usersService;
    }


    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     *
     * @return BaseResponse<List < GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers() {
        try {
            List<GetUserRes> getUserRes = usersProvider.getUsers();
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userIdx
     *
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userid}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetUserRes> getUser(@PathVariable("userid") int id) {
        // Get Users
        try {
            if( id == 0 ){
                return new BaseResponse<>(USERS_EMPTY_USER_ID);
            }
            GetUserRes getUserRes = usersProvider.getUser(id);
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        try{
            if(postUserReq.getEmail() == null){
                return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
            }
            if(!isRegexEmail(postUserReq.getEmail())){
                return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
            }
            if(postUserReq.getNickname() ==null){
                return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);
            }
            if(postUserReq.getPassword() ==null){
                return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
            }
            if(postUserReq.getPhoneNumber() ==null){
                return new BaseResponse<>(POST_USERS_EMPTY_PHONENUMBER);
            }
            if(postUserReq.getPassword().length() < 5){
                return new BaseResponse<>(POST_USERS_INVALID_PASSWORD);
            }
            PostUserRes postUserRes = usersService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @GetMapping("/{userid}/likelist") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<List<GetRestaurantLikeRes>> getRestaurantLike(@PathVariable("userid") int userId) {
        // Get Users
        try {
            if(userId == 0){
                return new BaseResponse<>(POST_REVIEWS_EMPTY_USERID);
            }
            System.out.println("1");
//            GetRestaurantLikeRes getRestaurantLikeRes = usersProvider.getRestaurantLike(userId);
            List<GetRestaurantLikeRes> getRestaurantLikeRes = usersProvider.getRestaurantLike(userId);
            return new BaseResponse<>(getRestaurantLikeRes);

//            return new BaseResponse<>(getRestaurantLikeRes);
        } catch (BaseException exception) {
            System.out.println(exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //팔로워 보기
    @ResponseBody
    @GetMapping("/{userid}/follower") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetFollowerRes>> getfollower(@PathVariable("userid") int userId) {
        try {
            List<GetFollowerRes> getFollowerRes = usersProvider.getfollower(userId);
            return new BaseResponse<>(getFollowerRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }



//    /**
//     * 로그인 API
//     * [POST] /users/logIn
//     * @return BaseResponse<PostLoginRes>
//     */
//    @ResponseBody
//    @PostMapping("/logIn")
//    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
//        try{
//            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
//            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
//            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
//            return new BaseResponse<>(postLoginRes);
//        } catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 유저정보변경 API
//     * [PATCH] /users/:userIdx
//     * @return BaseResponse<String>
//     */
//    @ResponseBody
//    @PatchMapping("/{userIdx}")
//    public BaseResponse<String> modifyUserName(@PathVariable("userIdx") int userIdx, @RequestBody User user){
//        try {
//            //jwt에서 idx 추출.
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            //같다면 유저네임 변경
//            PatchUserReq patchUserReq = new PatchUserReq(userIdx,user.getUserName());
//            userService.modifyUserName(patchUserReq);
//
//            String result = "";
//        return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//

    }

