package com.javakc.pms.dispord.service;

import com.javakc.commonutils.api.APICODE;
import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.pms.dispord.dao.DispOrdDao;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DispOrdService extends BaseService<DispOrdDao,DispOrd> {

    @Autowired
    private DispOrdDao dispOrdDao;

    public List<DispOrd> findAll() {
        return dispOrdDao.findAll();
    }

    public Page<DispOrd> findPageDispOrd(DispOrdQuery dispOrdQuery,int pageNo,int pageSize) {
        SimpleSpecificationBuilder<DispOrd> simpleSpecificationBuilder = new SimpleSpecificationBuilder<DispOrd>();
        if(!StringUtils.isEmpty(dispOrdQuery.getOrderName())){
            /**
             * 可用操作符
             * = 等值、!= 不等值 (字符串、数字)
             * >=、<=、>、< (数字)
             * ge，le，gt，lt(字符串)
             * :表示like %v%
             * l:表示 v%
             * :l表示 %v
             * null表示 is null
             * !null表示 is not null
             */
            simpleSpecificationBuilder.and("orderName",":",dispOrdQuery.getOrderName());
        }
        Page page = dispOrdDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo-1, pageSize));
        return page;
    }
}
