package com.javakc.pms.dispord.controller;

import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.service.DispOrdService;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "调度指令库管理 - 控制层")
@RestController
@RequestMapping("/pms/dispord")
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;
    /**
     * 查询所有调度指令库
     * @return
     */
    @ApiOperation(value = "查询全部调度")
    @GetMapping
    public APICODE findAll() {

        List<DispOrd> dispOrdList = dispOrdService.findAll();
//        int i=1/0;
        return APICODE.OK().data("items", dispOrdList);

    }


    @ApiOperation("带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrd(DispOrdQuery dispOrdQuery, @PathVariable(name = "pageNo") int pageNo,@PathVariable(name = "pageSize") int pageSize){

        Page<DispOrd> page = dispOrdService.findPageDispOrd(dispOrdQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<DispOrd> dispOrdList = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",dispOrdList);
    }

    @ApiOperation(value = "创建调度指令库")
    @PostMapping("saveDispOrd")
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd){
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取调度指令库数据")
    @GetMapping("{dispOrdId}")
    public APICODE getDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId){
        DispOrd dispOrd =dispOrdService.getById(dispOrdId);
        return APICODE.OK().data("dispOrd",dispOrd);

    }

    @ApiOperation(value = "根据ID修改调度指令库管理")
    @PutMapping
    public APICODE updateDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除调度指令库管理")
    @DeleteMapping("{dispOrdId}")
    public APICODE deleteDispOrd(@PathVariable(name = "dispOrdId") String dispOrdId) {
        dispOrdService.removeById(dispOrdId);
        return APICODE.OK();
    }
}
