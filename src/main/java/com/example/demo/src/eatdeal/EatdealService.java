package com.example.demo.src.eatdeal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EatdealService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EatDealDao eatDealDao;
    private final EatDealProvider eatDealProvider;

    public EatdealService(EatDealDao eatDealDao, EatDealProvider eatDealProvider) {
        this.eatDealDao = eatDealDao;
        this.eatDealProvider = eatDealProvider;
    }
}
