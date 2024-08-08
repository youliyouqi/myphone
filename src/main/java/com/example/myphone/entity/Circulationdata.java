package com.example.myphone.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 循环数据表(Circulationdata)实体类
 *
 * @author makejava
 * @since 2024-08-08 09:41:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Circulationdata implements Serializable {
    private static final long serialVersionUID = -58258855091787471L;
    /**
     * 主键ID
     */
    @TableId
    private Integer loopNumber;
    /**
     * 项目名
     */
    private String projectName;
    /**
     * 隧道
     */
    private String tunnel;
    /**
     * 工区
     */
    private String workArea;
    /**
     * 掌子面
     */
    private String palmFace;
    /**
     * 工法
     */
    private String process;
    /**
     * 施工里程起点
     */
    private String startMileage;
    /**
     * 施工里程终点
     */
    private String endMileage;
    /**
     * 长度（米）
     */
    private String length;
    /**
     * 围岩级别
     */
    private String surroundingRockLevel;
    /**
     * 衬砌类型
     */
    private String liningType;
    /**
     * 开挖工法
     */
    private String excavationMethod;
    /**
     * 循环开始时间
     */
    private Date startTime;
    /**
     * 循环结束时间
     */
    private Date endTime;
    /**
     * 循环用时（分钟）
     */
    private Integer totalTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 开挖轮廓线放线开始时间
     */
    private Date outlineStartTime;
    /**
     * 开挖轮廓线放线开始图片路径
     */
    private String outlineStartUrl;
    /**
     * 开挖轮廓线放线结束时间
     */
    private Date outlineEndTime;
    /**
     * 开挖轮廓线放线结束时图片路径
     */
    private String outlineEndUrl;
    /**
     * 开挖轮廓线放线用时（分钟）
     */
    private Integer outlineDuration;
    /**
     * 开挖轮廓线放线备注
     */
    private String outlineRemarks;
    /**
     * 钻孔、装药、爆破开始时间
     */
    private Date drillingStartTime;
    /**
     * 钻孔、装药、爆破开始图片路径
     */
    private String drillingStartUrl;
    /**
     * 钻孔、装药、爆破结束时间
     */
    private Date drillingEndTime;
    /**
     * 钻孔、装药、爆破结束图片路径
     */
    private String drillingEndUrl;
    /**
     * 钻孔、装药、爆破用时（分钟）
     */
    private Integer drillingDuration;
    /**
     * 钻孔、装药、爆破备注
     */
    private String drillingRemarks;
    /**
     * 通风开始时间
     */
    private Date ventilationStartTime;
    /**
     * 通风开始图片路径
     */
    private String ventilationStartUrl;
    /**
     * 通风结束时间
     */
    private Date ventilationEndTime;
    /**
     * 通风结束图片路径
     */
    private String ventilationEndUrl;
    /**
     * 通风用时（分钟）
     */
    private Integer ventilationDuration;
    /**
     * 通风备注
     */
    private String ventilationRemarks;
    /**
     * 装碴、运输开始时间
     */
    private Date transportStartTime;
    /**
     * 装碴、运输开始图片路径
     */
    private String transportStartUrl;
    /**
     * 装碴、运输结束时间
     */
    private Date transportEndTime;
    /**
     * 装碴、运输结束图片路径
     */
    private String transportEndUrl;
    /**
     * 装碴、运输用时（分钟）
     */
    private Integer transportDuration;
    /**
     * 装碴、运输备注
     */
    private String transportRemarks;
    /**
     * 排险、找顶、扒齐头开始时间
     */
    private Date hazardClearanceStartTime;
    /**
     * 排险、找顶、扒齐头开始图片路径
     */
    private String hazardClearancetStartUrl;
    /**
     * 排险、找顶、扒齐头结束时间
     */
    private Date hazardClearanceEndTime;
    /**
     * 排险、找顶、扒齐头结束图片路径
     */
    private String hazardClearanceEndUrl;
    /**
     * 排险、找顶、扒齐头用时（分钟）
     */
    private Integer hazardClearanceDuration;
    /**
     * 排险、找顶、扒齐头备注
     */
    private String hazardClearanceRemarks;
    /**
     * 立架放线开始时间
     */
    private Date frameLayingStartTime;
    /**
     * 立架放线开始图片路径
     */
    private String frameLayingStartUrl;
    /**
     * 立架放线结束时间
     */
    private Date frameLayingEndTime;
    /**
     * 立架放线结束图片路径
     */
    private String frameLayingEndUrl;
    /**
     * 立架放线用时（分钟）
     */
    private Integer frameLayingDuration;
    /**
     * 立架放线备注
     */
    private String frameLayingRemarks;
    /**
     * 网片、立钢架开始时间
     */
    private Date meshInstallationStartTime;
    /**
     * 网片、立钢架开始图片路径
     */
    private String meshInstallationStartUrl;
    /**
     * 网片、立钢架结束时间
     */
    private Date meshInstallationEndTime;
    /**
     * 网片、立钢架结束图片路径
     */
    private String meshInstallationEndUrl;
    /**
     * 网片、立钢架用时（分钟）
     */
    private Integer meshInstallationDuration;
    /**
     * 网片、立钢架备注
     */
    private String meshInstallationRemarks;
    /**
     * 锁脚、系统锚杆、超前小导管施作开始时间
     */
    private Date anchoringStartTime;
    /**
     * 锁脚、系统锚杆、超前小导管施作开始图片路径
     */
    private String anchoringStartUrl;
    /**
     * 锁脚、系统锚杆、超前小导管施作结束时间
     */
    private Date anchoringEndTime;
    /**
     * 锁脚、系统锚杆、超前小导管施作结束图片路径
     */
    private String anchoringEndUrl;
    /**
     * 锁脚、系统锚杆、超前小导管施作用时（分钟）
     */
    private Integer anchoringDuration;
    /**
     * 锁脚、系统锚杆、超前小导管施作备注
     */
    private String anchoringRemarks;
    /**
     * 喷射砼开始时间
     */
    private Date concreteSprayingStartTime;
    /**
     * 喷射砼开始图片路径
     */
    private String concreteSprayingStartUrl;
    /**
     * 喷射砼结束时间
     */
    private Date concreteSprayingEndTime;
    /**
     * 喷射砼结束图片路径
     */
    private String concreteSprayingEndUrl;
    /**
     * 喷射砼用时（分钟）
     */
    private Integer concreteSprayingDuration;
    /**
     * 喷射砼备注
     */
    private String concreteSprayingRemarks;
    /**
     * 扒齐头开始时间
     */
    private Date cleanupStartTime;
    /**
     * 扒齐头开始图片路径
     */
    private String cleanupStartUrl;
    /**
     * 扒齐头结束时间
     */
    private Date cleanupEndTime;
    /**
     * 扒齐头结束图片路径
     */
    private String cleanupEndUrl;
    /**
     * 扒齐头用时（分钟）
     */
    private Integer cleanupDuration;
    /**
     * 扒齐头备注
     */
    private String cleanupRemarks;
    /**
     * 版本号
     */
    @Version
    private Integer version;


}

