package com.javakc.mes.instruct.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mes_disp_ins")
@EntityListeners(AuditingEntityListener.class)
public class Instruct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private int id;

    @ApiModelProperty(value = "调度指令编号")
    @Column(name = "dispatch_ins_num")
    private String dispatchInsNum;

    @ApiModelProperty(value = "指令传达人")
    @Column(name = "ins_com")
    private int insCom;

    @ApiModelProperty(value = "指令下达人")
    @Column(name = "order_rel_per")
    private int orderRelPer;

    @ApiModelProperty(value = "指令接收方")
    @Column(name = "ins_rec")
    private String insRec;

    @ApiModelProperty(value = "指令描述")
    @Column(name = "ins_des")
    private String insDes;

    @ApiModelProperty(value = "指令库外键")
    @Column(name = "pms_disp_ord_id")
    private String pmsDispOrdId;

    @ApiModelProperty(value = "指令受理表外键")
    @Column(name = "pms_order_acc_id")
    private int pmsOrderAccId;

    @ApiModelProperty(value = "指令执行表外键")
    @Column(name = "pms_ins_exe_id")
    private int pmsInsExeId;



    @ApiModelProperty(value = "下达时间")
    @Column(name = "ins_rel_time")
    private Date insRelTime;

    @ApiModelProperty(value = "指令执行状态")
    @Column(name = "is_release")
    private int isRelease;

    @ApiModelProperty(value = "指令名称")
    @Column(name = "order_name")
    private int orderName;

    @ApiModelProperty(value = "优先级")
    @Column(name = "priority")
    private int priority;

    @ApiModelProperty(value = "专业类型")
    @Column(name = "spec_type")
    private int specType;



}
