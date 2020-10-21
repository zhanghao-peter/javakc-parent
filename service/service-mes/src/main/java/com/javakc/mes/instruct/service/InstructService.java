package com.javakc.mes.instruct.service;


import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.mes.instruct.dao.InstructDao;
import com.javakc.mes.instruct.entity.Instruct;
import com.javakc.mes.instruct.vo.InstructQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class InstructService extends BaseService<InstructDao,Instruct> {

    @Autowired
    private InstructDao instructDao;

    public List<Instruct> findAll() {
        return instructDao.findAll();
    }

    public Page<Instruct> findPageInstruct(InstructQuery instructQuery, int pageNo, int pageSize){
        SimpleSpecificationBuilder<Instruct> simpleSpecificationBuilder = new SimpleSpecificationBuilder<Instruct>();
        if(!StringUtils.isEmpty(instructQuery.getDispatchInsNum())) {
            simpleSpecificationBuilder.and("dispatchInsNum",":",instructQuery.getDispatchInsNum());
        }
        Page page = dao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));
        return page;

    }




}
