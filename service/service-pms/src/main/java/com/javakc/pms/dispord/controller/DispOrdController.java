package com.javakc.pms.dispord.controller;

import com.alibaba.excel.EasyExcel;
import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.listener.ExcelListener;
import com.javakc.pms.dispord.service.DispOrdService;
import com.javakc.pms.dispord.vo.DispOrdData;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
    public APICODE findPageDispOrd(@RequestBody(required = false) DispOrdQuery dispOrdQuery, @PathVariable(name = "pageNo") int pageNo,@PathVariable(name = "pageSize") int pageSize){

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

    @ApiOperation(value = "列表导出", notes = "使用阿里EasyExcel导出Excel格式的列表数据")
    @GetMapping("exportEasyExcel")
    public void exportEasyExcel(HttpServletResponse response) {
        // ## 查询调度指令库
        List<DispOrd> dispOrdList = dispOrdService.findAll();
        // ## 定义导出列表集合
        List<DispOrdData> dispOrdDataList = new ArrayList<>();

        for (DispOrd dispOrd : dispOrdList) {
            DispOrdData dispOrdData = new DispOrdData();
            BeanUtils.copyProperties(dispOrd, dispOrdData);//属性全部复制
            dispOrdDataList.add(dispOrdData);
        }

        String fileName = "dispOrdList";

        try {
            // ## 设置响应信息
            response.reset();
            response.setContentType("application/vnd.ms-excel; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".xlsx");
            EasyExcel.write(response.getOutputStream(), DispOrdData.class).sheet("指令列表").doWrite(dispOrdDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "列表导入", notes = "使用阿里EasyExcel导入Excel格式的列表数据")
    @PostMapping("importEasyExcel")
    public void importEasyExcel(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), DispOrdData.class, new ExcelListener(dispOrdService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "列表导出", notes = "使用POI导出Excel格式的列表数据")
    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        dispOrdService.exportExcel(response);
    }

    @ApiOperation(value = "列表导入", notes = "使用POI导入Excel格式的列表数据")
    @PostMapping("importExcel")
    public void importExcel(MultipartFile file) {
        dispOrdService.importExcel(file);
    }
}
