package com.javakc.mes.yearproduction.service;

import com.alibaba.excel.util.StringUtils;
import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.mes.yearproduction.dao.YearProductDao;
import com.javakc.mes.yearproduction.entity.YearProduct;
import com.javakc.mes.yearproduction.vo.YearProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearProductService extends BaseService<YearProductDao, YearProduct> {

    @Autowired
    private YearProductDao yearProductDao;

    public List<YearProduct> findAll() {
        return yearProductDao.findAll();
    }

    public Page<YearProduct> findPageYearProduct(YearProductQuery yearProductQuery,int pageNo,int pageSize ) {
        SimpleSpecificationBuilder<YearProduct> simpleSpecificationBuilder = new SimpleSpecificationBuilder<YearProduct>();

        if(!StringUtils.isEmpty(yearProductQuery.getPlanYear())&&yearProductQuery.getPlanYear()!=0){
            simpleSpecificationBuilder.and("planYear","=",yearProductQuery.getPlanYear());
        }
        if(!StringUtils.isEmpty(yearProductQuery.getPlanStatus())&&yearProductQuery.getPlanStatus() != 0){
            simpleSpecificationBuilder.and("planStatus","=",yearProductQuery.getPlanStatus());
        }

        Page page = yearProductDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));
        return page;

    }



}
