package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
//    SUCCESS_GET_RESTAURANTS(true, 1001, "식당 전체보기에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    EMPTY_JWT_LOGIN(false,2001,"로그인이 필요한 서비스입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    USERS_INVALID_USER_ID(false,2004,"유저 ID는 숫자로 입력해주세요"),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2020, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2021, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2022,"중복된 이메일입니다."),
    POST_USERS_EMPTY_NICKNAME(false, 2023, "닉네입을 입력해주세요."),
    POST_USERS_EMPTY_PASSWORD(false, 2024, "비밀번호를 입력해주세요."),
    POST_USERS_EMPTY_PHONENUMBER(false, 2025, "전화번호를 입력해주세요."),
    POST_USERS_INVALID_PASSWORD(false, 2026, "최소 6~12자, 영문, 숫자 포함해서 입력해주세요"),
    PATCH_USERS_INVALID_NICKNAME(false,2027,"닉네임은 2자 이상이어야 합니다."),
    POST_USERS_INVALID_PHONENUMBER(false,2028,"전화번호를 올바른 형식으로 입력해주세요 ex)01012345678"),
    POST_USERS_INVALID_NICKNAME(false,2029,"20자 이하로 입력해주세요"),

    // [POST] /reviews
    POST_REVIEWS_EMPTY_COMMENT(false, 2040, "comment에 내용을 입력해주세요."),
    POST_REVIEWS_EMPTY_SCORE(false, 2041, "score에 평점을 입력해주세요."),
    POST_REVIEWS_INVALID_COMMENT(false, 2042, "comment는 10000자 이하로 입력해주세요."),
    POST_REVIEWS_INVALID_SCORE(false, 2043, "score에 1과 3사이의 점수를 입력해주세요."),
    POST_REVIEWS_INVALID_RESTAURANTID(false, 2044, "Id에 정수를 입력해주세요."),
    POST_REVIEWS_INVALID_REVIEWURL(false, 2045, "reviewUrl에 url형식으로 입력해주세요."),

    POST_REVIEWS_EMPTY_RST_NAME(false, 2046, "식당이름을 입력해주세요."),
    POST_REVIEWS_EMPTY_RST_LOCATION(false, 2047, "식당 위치를 입력해주세요."),
    POST_REVIEWS_EMPTY_RST_VARIETY(false, 2048, "식당 종류를 입력해주세요."),
    POST_REVIEWS_EMPTY_USERID(false, 2049, "유저 아이디를 입력해주세요."),

    POST_REVIEWS_EMPTY_REPLY(false,2050,"댓글 내용을 입력해주세요"),
    // [GET] /restaurants/:restaurnatId
    POST_REVIEWS_EMPTY_RESTAURANTID(false, 2060, "식당 ID를 입력해주세요 "),
    POST_REVIEWS_INVALID_RESTAURANTID_RANGE(false, 2061, "값이 존재하지 않습니다. 식당 ID에 1부터 4까지의 숫자로 입력해주세요."),


    INVALID_STATUS(false, 2300,"Status 값을 ACTIVATE 혹은 INACTIVATE로 입력해주세요"),
    PATCH_EMPTY_STATUS(false, 2301,"Status 값을 입력해주세요"),

    //POST// Rest/like
    POST_RESTAURANTS_EMPTY_USERID(false, 2400,"유저 ID 값을 입력해주세요"),
    POST_RESTAURANTS_EMPTY_RESTAURANTID(false, 2401,"식당 ID 값을 입력해주세요"),
    POST_RESTAURANTS_EMPTY(false, 2301,"Status 값을 입력해주세요"),

    //POST /Restaurant

    //PATCH
    PATCH_RESTAURANTS_INVALID_TIME(false, 2500, "00:00~24:00의 숫자로 입력해주세요."),
    PATCH_RESTAURANTS_INVALID_DAYS(false, 2501, "월부터 금까지의 요일을 입력해주세요"),
    PATCH_RESTAURANTS_INVALID_ISPARKING(false, 2502, "Yes 혹은 No를 입력해주세요."),

    //GET //REST



    // [POST]


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    //실패 /user
    FAILED_TO_EMAIL_SIGN_UP(false,3000,"이메일로 회원가입에 실패하였습니다."),
    FAILED_TO_EMAIL_LOGIN(false,3000,"이메일로 로그인에 실패하였습니다."),
    FAILED_TO_MODIFY_USER(false,3000,"회원정보 변경에 실패하였습니다."),
    FAILED_TO_USER(false,3000,"유저 프로필 조회에 실패하였습니다."),
    FAILED_TO_RESTAURANT_LIKE(false,3000,"가고싶다에 실패하였습니다."),
    FAILED_TO_RESTAURANT_VISITED(false,3000,"가봤어요에 실패하였습니다."),
    FAILED_TO_FOLLOWERS(false,3000,"팔로워 조회에 실패하였습니다."),
    FAILED_TO_VISITED_LIST(false,3000,"가봤어요 조회에 실패하였습니다."),
    FAILED_TO_LIKED_LIST(false,3000,"가고싶다 조회에 실패하였습니다."),

    //실패 /restaurants
    FAILED_TO_RESTAURANTS(false,3000,"식당 전체 조회에 실패하였습니다."),
    FAILED_TO_RESTAURANT(false,3000,"식당 상세 조회에 실패하였습니다."),
    FAILED_TO_CREATE_RESTAURANT(false,3000,"식당 상세 조회에 실패하였습니다."),
    FAILED_TO_RESTAURANT_CONVENIENCE(false,3000,"식당 편의정보 조회에 실패하였습니다."),
    FAILED_TO_MODIFY_RESTAURANT_MENU_(false,3000,"식당 메뉴 수정에 실패하였습니다."),
    FAILED_TO_MODIFY_RESTAURANT_CONVENIENCE(false,3000,"식당 편의정보 수정에 실패하였습니다."),
    FAILED_TO_RESTAURANT_MENU(false,3000,"식당 메뉴 조회에 실패하였습니다."),


    //실패 /review
    FAILED_TO_REVIEWS(false,3000,"리뷰 조회에 실패하였습니다."),
    FAILED_TO_POST_REVIEW(false,3000,"리뷰 작성에 실패하였습니다."),
    FAILED_TO_MODIFY_REVIEW(false,3000,"리뷰 수정에 실패하였습니다."),
    FAILED_TO_CREATE_REPLY(false,3000,"리뷰 댓글 달기에 실패하였습니다."),

    //실패 /eat-deal
    FAILED_TO_EAT_DEAL(false,3000,"잇딜 조회에 실패하였습니다."),
    FAILED_TO_EAT_DEAL_DETAIL(false,3000,"잇딜 상세 조회에 실패하였습니다."),


    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),


    NOT_FOUND_RESTAURANTID(false, 3021, "존재하지 않는 식당 입니다."),
    NOT_FOUND_REVIEWID(false, 3022, "존재하지 않는 리뷰 입니다."),
    NOT_FOUND_EATDEALID(false, 3024, "존재하지 않는 잇딜 입니다."),
    NOT_FOUND_USERID(false, 3023, "존재하지 않는 유저 입니다."),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),

    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),
    ERROR(false, 4002, "ff"),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    //[PATCH] /reviews/{reviewId}
    MODIFY_FAIL_COMMENT(false,4015,"리뷰 내용 수정 실패"),
    MODIFY_FAIL_SCORE(false,4016,"리뷰 점수 수정 실패"),
    MODIFY_FAIL_REVIEWURL(false,4017,"리뷰 사진 수정 실패"),
    MODIFY_FAIL_EMPTY(false,4018,"입력안된 값이 있습니다"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),

    //[PATCH] /restaruant/like
    MODIFY_FAIL_ISLIKE(false,4040,"가고싶다 설정에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

     BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
