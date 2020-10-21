package com.javakc.pms.instruct.controller;

import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.instruct.client.MesInstructClient;
import com.javakc.pms.instruct.entity.Instruct;
import com.javakc.pms.instruct.service.InstructService;
import com.javakc.pms.instruct.vo.InstructQuery;
import com.javakc.servicebase.hanler.HctfException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "调度指令管理 - 控制层")
@RestController
@RequestMapping("/pms/instruct")
public class InstructController {

    @Autowired
    private InstructService instructService;
    @Autowired
    private MesInstructClient mesInstructClient;

    @ApiOperation(value = "查询全部调度")
    @GetMapping
    public APICODE findAll() {
        List<Instruct> InstructList = instructService.findAll();
        return APICODE.OK().data("items",InstructList);
    }

    @ApiOperation("带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageInstruct(@RequestBody(required = false)InstructQuery instructQuery, @PathVariable(name = "pageNo") int pageNo, @PathVariable(name = "pageSize") int pageSize){
        Page<Instruct> page = instructService.findPageInstruct(instructQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<Instruct> instructList = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",instructList);
    }

    @ApiOperation("根据id获取调度指令管理信息")
    @GetMapping("{instructId}")
    public APICODE getInstructById(@PathVariable(name="instructId") int id){

        Instruct instruct = instructService.getById(id);
        return APICODE.OK().data("instruct",instruct);
    }
    @ApiOperation("下达集团调度指令")
    @PutMapping("updateRelease/{instructId}")
    public APICODE updateRelease(@PathVariable(name="instructId") int id) {

        Instruct instruct = instructService.getById(id);
        instruct.setInsRelTime(new Date());
        instruct.setIsRelease(1);

        //调用mes服务接口
        APICODE apicode = mesInstructClient.SaveReleaseInstruct(instruct);
        if(apicode.getCode()==20001){
            throw new HctfException(20001,apicode.getMessage());
        }
        instructService.saveOrUpdate(instruct);
        return APICODE.OK();
    }
}
