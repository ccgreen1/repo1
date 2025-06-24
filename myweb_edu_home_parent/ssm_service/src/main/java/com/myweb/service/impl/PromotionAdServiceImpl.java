package com.myweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myweb.dao.PromotionAdMapper;
import com.myweb.domain.PromotionAd;
import com.myweb.domain.PromotionAdVO;
import com.myweb.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;



    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {


//        调用分页插件(CurrentPage:当前页 getPageSize：每页显示条数)
        PageHelper.startPage(promotionAdVO.getCurrentPage(), promotionAdVO.getPageSize());

        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllPromotionAdByPage();
//        用分页对象来封装实体类内放回数据来分页显示
        PageInfo<PromotionAd> PageInfo = new PageInfo<>(allPromotionAdByPage);

        return PageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id, Integer status) {
//        将参数封装进promotionAd实体类
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

//        调用Mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }

    @Override
    public PromotionAd findPromotionAdByIdForService(int id) {
        PromotionAd promotionAdById = promotionAdMapper.findPromotionAdById(id);
        return promotionAdById;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.updatePromotionAd(promotionAd);
    }


}
