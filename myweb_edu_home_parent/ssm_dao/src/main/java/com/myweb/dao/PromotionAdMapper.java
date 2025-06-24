package com.myweb.dao;

import com.myweb.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

//    分页查询广告信息
    public List<PromotionAd> findAllPromotionAdByPage();

//    广告动态上下线（动态控制status的值）
    public void updatePromotionAdStatus(PromotionAd promotionAd);

    public PromotionAd findPromotionAdById(int id);

    public void savePromotionAd(PromotionAd promotionAd);

    void updatePromotionAd(PromotionAd promotionAd);

}
