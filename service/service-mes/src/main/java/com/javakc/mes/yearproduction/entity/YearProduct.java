package com.javakc.mes.yearproduction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "production_plan_year")
@EntityListeners(AuditingEntityListener.class)
public class YearProduct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "年度计划编号")
    @Column(name = "plan_num")
    private String planNum;

    @ApiModelProperty(value = "计划单位")
    @Column(name = "plan_unit")
    private String planUnit;

    @ApiModelProperty(value = "计划年份")
    @Column(name = "plan_year")
    private int planYear;

    @ApiModelProperty(value = "总产量")
    @Column(name = "total_output")
    private double totalOutput;

    @ApiModelProperty(value = "总进尺")
    @Column(name = "total_footage")
    private double totalFootage;

    @ApiModelProperty(value = "计划状态1，0")
    @Column(name = "plan_status")
    private int planStatus;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value = "编制日期")
    @Column(name = "preparation_date",updatable = false)
    private Date preparationDate;

    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;


    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    @Column(name = "gmt_modified",insertable = false)
    private Date gmtModified;


}
