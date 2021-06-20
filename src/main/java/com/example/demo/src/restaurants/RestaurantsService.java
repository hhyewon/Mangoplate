package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class RestaurantsService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestaurantsDao restaurantsDao;
    private final RestaurantsProvider restaurantsProvider;
    private final JwtService jwtService;

    @Autowired
    public RestaurantsService(RestaurantsDao restaurantsDao, RestaurantsProvider restaurantsProvider, JwtService jwtService) {
        this.restaurantsDao = restaurantsDao;
        this.restaurantsProvider = restaurantsProvider;
        this.jwtService = jwtService;
    }

}
