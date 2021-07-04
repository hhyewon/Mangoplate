package com.example.demo.src.eatdeal;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.eatdeal.model.GetEatDealDetailRes;
import com.example.demo.src.eatdeal.model.GetEatDealRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.NOT_FOUND_EATDEALID;

@RestController
@RequestMapping("/eat-deal")
public class EatDealController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final EatDealProvider eatDealProvider;
    @Autowired
    private final EatdealService eatdealService;

    public EatDealController(EatDealProvider eatDealProvider, EatdealService eatdealService) {
        this.eatDealProvider = eatDealProvider;
        this.eatdealService = eatdealService;
    }

    /**
     * 식당 조회 API
     * [GET] /eat-deal
     */

    @GetMapping("") // (GET) 127.0.0.1:9000/restaurants
    public BaseResponse<List<GetEatDealRes>> getEatdeal() {
        try{
                List<GetEatDealRes> getEatdealRes = eatDealProvider.getEatdeal();
                return new BaseResponse<>(getEatdealRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 식당 상세 조회 API
     * [GET] /restaurants/:restaurantId
     * @return BaseResponse<GetUserRes>
     */
 //    Path-variable
    @ResponseBody
    @GetMapping("/{eat-dealid}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetEatDealDetailRes> getEatdealDetail(@PathVariable("eat-dealid") int id) {
        try{
            GetEatDealDetailRes getEatDealDetailRes = eatDealProvider.getEatdealDetail(id);
            return new BaseResponse<>(getEatDealDetailRes);

//            if(!getEatDealDetailRes.existsById(id)){
//                return new BaseResponse<>(NOT_FOUND_EATDEALID);
//            }

        } catch(BaseException exception){

            return new BaseResponse<>((exception.getStatus()));
        }

    }


}
