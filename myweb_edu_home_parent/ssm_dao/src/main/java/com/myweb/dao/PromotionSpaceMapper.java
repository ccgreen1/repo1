package com.myweb.dao;


import com.myweb.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

//    查询所有的广告位
    public List<PromotionSpace> findAllPromotionSpace();

//    新建广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    public void updatePromotionSpace(PromotionSpace promotionSpace);

    public PromotionSpace findPromotionSpaceById(int id);

}
