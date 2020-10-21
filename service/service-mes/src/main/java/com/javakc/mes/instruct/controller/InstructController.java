package com.javakc.mes.instruct.controller;

import com.javakc.commonutils.api.APICODE;

import com.javakc.mes.instruct.entity.Instruct;
import com.javakc.mes.instruct.service.InstructService;
import com.javakc.mes.instruct.vo.InstructQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "煤矿调度指令执行 - 控制层")
@RestController
@RequestMapping("/mes/instruct")

public class InstructController {

    @Autowired
    private InstructService instructService;

    @ApiOperation(value = "查询全部调度")
    @GetMapping
    public APICODE findAll() {
        List<Instruct> InstructList = instructService.findAll();
        return APICODE.OK().data("items",InstructList);
    }

    @ApiOperation("带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageInstruct(@RequestBody(required = false) InstructQuery instructQuery, @PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        Page<Instruct> page = instructService.findPageInstruct(instructQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<Instruct> instructList = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",instructList);
    }


    @ApiOperation("接收下达集团调度指令信息")
    @PostMapping("SaveReleaseInstruct")
    public APICODE SaveReleaseInstruct(@RequestBody Instruct instruct) {
        instructService.saveOrUpdate(instruct);
        return APICODE.OK();
    }
}
