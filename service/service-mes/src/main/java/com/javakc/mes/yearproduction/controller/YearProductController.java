package com.javakc.mes.yearproduction.controller;

import com.javakc.commonutils.api.APICODE;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.mes.yearproduction.entity.YearProduct;
import com.javakc.mes.yearproduction.service.YearProductService;
import com.javakc.mes.yearproduction.vo.YearProductQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年度生产计划管理 - 控制层")
@RestController
@RequestMapping("/mes/yearproduct")
public class YearProductController {

    @Autowired
    private YearProductService yearProductService;
    @ApiOperation(value = "查询全部年度计划")
    @GetMapping
    public APICODE findAll() {
        List<YearProduct> yearProductList = yearProductService.findAll();
        return APICODE.OK().data("items",yearProductList);
    }

    @ApiOperation("带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageYearProduct (@RequestBody(required = false)YearProductQuery yearProductQuery,@PathVariable(name = "pageNo") int pageNo,@PathVariable(name = "pageSize") int pageSize) {

        Page<YearProduct> page = yearProductService.findPageYearProduct(yearProductQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<YearProduct> yearProductList =page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",yearProductList);

    }

    @ApiOperation(value = "创建年度计划")
    @PostMapping("saveYearProduct")
    public APICODE saveYearProduct(@RequestBody YearProduct yearProduct){
        yearProductService.saveOrUpdate(yearProduct);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取年度指令库数据")
    @GetMapping("{yearProductId}")
    public APICODE getYearProductById(@PathVariable(name = "yearProductId")int yearProductId){
        YearProduct yearProduct = yearProductService.getById(yearProductId);
        return APICODE.OK().data("yearProduct",yearProduct);
    }

    @ApiOperation(value = "根据ID修改年度指令库数据")
    @PutMapping
    public APICODE updateYearProduct(@RequestBody YearProduct yearProduct){
         yearProductService.saveOrUpdate(yearProduct);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除年度指令库数据")
    @DeleteMapping("{yearProductId}")
    public APICODE deleteYearProduct(@PathVariable(name = "yearProductId")int yearProductId){
        yearProductService.removeById(yearProductId);
        return APICODE.OK();
    }
}
