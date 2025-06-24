package com.myweb.service;

import com.myweb.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    List<PromotionSpace> findAllPromotionSpace();

    void savePromotionSpace(PromotionSpace promotionSpace);

    void updatePromotionSpace(PromotionSpace promotionSpace);

   PromotionSpace findPromotionSpaceById(int id);
}
