package com.myweb.service;

import com.github.pagehelper.PageInfo;
import com.myweb.domain.PromotionAd;
import com.myweb.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

//    分页查询广告信息
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);


    public void updatePromotionAdStatus(int id,Integer status);

//    回显广告信息
    PromotionAd findPromotionAdByIdForService(int id);

//    新建广告
    void savePromotionAd(PromotionAd promotionAd);

//    更新修改广告
    void updatePromotionAd(PromotionAd promotionAd);
}
