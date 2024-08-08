package com.example.myphone.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.myphone.entity.Circulationdata;
import com.example.myphone.entity.ResponseResult;
import com.example.myphone.enums.AppHttpCodeEnum;
import com.example.myphone.mapper.CirculationdataDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.myphone.service.CirculationdataService;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


/**
 * 循环数据表(Circulationdata)表服务实现类
 *
 * @author makejava
 * @since 2024-08-07 19:46:35
 */
@Service("circulationdataService")
public class CirculationdataServiceImpl implements CirculationdataService {
@Resource
private CirculationdataDao circulationdataDao;
    @Transactional
    @Override
    public ResponseResult<?> submit(Circulationdata circulationdata) {
        try {
            LambdaQueryWrapper<Circulationdata> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Circulationdata::getProjectName, circulationdata.getProjectName())
                    .eq(Circulationdata::getTunnel, circulationdata.getTunnel())
                    .eq(Circulationdata::getWorkArea, circulationdata.getWorkArea())
                    .eq(Circulationdata::getPalmFace, circulationdata.getPalmFace())
                    .eq(Circulationdata::getStartMileage, circulationdata.getStartMileage())
                    .eq(Circulationdata::getEndMileage, circulationdata.getEndMileage())
                    .eq(Circulationdata::getSurroundingRockLevel, circulationdata.getSurroundingRockLevel())
                    .eq(Circulationdata::getLiningType, circulationdata.getLiningType())
                    .eq(Circulationdata::getExcavationMethod, circulationdata.getExcavationMethod());

            Long dataExist = circulationdataDao.selectCount(lambdaQueryWrapper);
            if (dataExist <= 0) {
                // 工法第一次添加
                // 计算循环用时
                Date outlineStartTime = circulationdata.getOutlineStartTime();
                Date outlineEndTime = circulationdata.getOutlineEndTime();
                long minutes = calculateMinutesDifference(outlineStartTime, outlineEndTime);
                // 设置循环用时
                circulationdata.setTotalTime((int) minutes);
                // 设置第一次开挖放线的用时
                circulationdata.setOutlineDuration((int) minutes);
                // 添加循环开始时间
                circulationdata.setStartTime(circulationdata.getOutlineStartTime());
                // 添加循环结束时间
                circulationdata.setEndTime(circulationdata.getOutlineEndTime());
                circulationdata.setVersion(0);
                //长度计算
                try {
                    double positiveDifference = calculateLengthDifference(circulationdata.getStartMileage(), circulationdata.getEndMileage());
                    circulationdata.setLength(String.valueOf(positiveDifference));
                } catch (NumberFormatException e) {
                    System.err.println("长度格式错误: " + e.getMessage());
                    return ResponseResult.errorResult(AppHttpCodeEnum.MILEAGE_ERROR);
                }

                int insert = circulationdataDao.insert(circulationdata);
                if (insert <= 0) {
                    return ResponseResult.errorResult(AppHttpCodeEnum.ADD_ERROR);
                } else {
                    return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
                }
            } else {
                Circulationdata circulationdataNew = circulationdataDao.selectOne(lambdaQueryWrapper);
                String process = circulationdata.getProcess();
                Integer currentVersion = circulationdataNew.getVersion();

                switch (process) {
                    case "①开挖轮廓线放线（min）":
                        Date outlineStartTime = circulationdata.getOutlineStartTime();
                        Date outlineEndTime = circulationdata.getOutlineEndTime();
                        long minutes = calculateMinutesDifference(outlineStartTime, outlineEndTime);
                        circulationdataNew.setOutlineStartTime(outlineStartTime);
                        circulationdataNew.setOutlineEndTime(outlineEndTime);
                        circulationdataNew.setOutlineDuration((int) minutes);
                        circulationdataNew.setEndTime(outlineEndTime);
                        break;
                    case "②钻孔、装药、爆破（min）":
                        Date drillingStartTime = circulationdata.getDrillingStartTime();
                        Date drillingEndTime = circulationdata.getDrillingEndTime();
                        long drillingMinutes = calculateMinutesDifference(drillingStartTime, drillingEndTime);
                        circulationdataNew.setDrillingStartTime(drillingStartTime);
                        circulationdataNew.setDrillingEndTime(drillingEndTime);
                        circulationdataNew.setDrillingDuration((int) drillingMinutes);
                        circulationdataNew.setDrillingStartUrl(circulationdata.getDrillingStartUrl());
                        circulationdataNew.setDrillingEndUrl(circulationdata.getDrillingEndUrl());
                        circulationdataNew.setDrillingRemarks(circulationdata.getDrillingRemarks());
                        circulationdataNew.setEndTime(drillingEndTime);
                        break;
                    case "③通风（min）":
                        Date ventilationStartTime = circulationdata.getVentilationStartTime();
                        Date ventilationEndTime = circulationdata.getVentilationEndTime();
                        long ventilationMinutes = calculateMinutesDifference(ventilationStartTime, ventilationEndTime);
                        circulationdataNew.setVentilationStartTime(ventilationStartTime);
                        circulationdataNew.setVentilationEndTime(ventilationEndTime);
                        circulationdataNew.setVentilationDuration((int) ventilationMinutes);
                        circulationdataNew.setVentilationStartUrl(circulationdata.getVentilationStartUrl());
                        circulationdataNew.setVentilationEndUrl(circulationdata.getVentilationEndUrl());
                        circulationdataNew.setVentilationRemarks(circulationdata.getVentilationRemarks());
                        circulationdataNew.setEndTime(ventilationEndTime);
                        break;
                    case "④装碴、运输（min）":
                        Date transportStartTime = circulationdata.getTransportStartTime();
                        Date transportEndTime = circulationdata.getTransportEndTime();
                        long transportMinutes = calculateMinutesDifference(transportStartTime, transportEndTime);
                        circulationdataNew.setTransportStartTime(transportStartTime);
                        circulationdataNew.setTransportEndTime(transportEndTime);
                        circulationdataNew.setTransportDuration((int) transportMinutes);
                        circulationdataNew.setTransportStartUrl(circulationdata.getTransportStartUrl());
                        circulationdataNew.setTransportEndUrl(circulationdata.getTransportEndUrl());
                        circulationdataNew.setTransportRemarks(circulationdata.getTransportRemarks());
                        circulationdataNew.setEndTime(transportEndTime);
                        break;
                    case "⑤排险、找顶、扒齐头（min）":
                        Date hazardClearanceStartTime = circulationdata.getHazardClearanceStartTime();
                        Date hazardClearanceEndTime = circulationdata.getHazardClearanceEndTime();
                        long hazardClearanceMinutes = calculateMinutesDifference(hazardClearanceStartTime, hazardClearanceEndTime);
                        circulationdataNew.setHazardClearanceStartTime(hazardClearanceStartTime);
                        circulationdataNew.setHazardClearanceEndTime(hazardClearanceEndTime);
                        circulationdataNew.setHazardClearanceDuration((int) hazardClearanceMinutes);
                        circulationdataNew.setHazardClearancetStartUrl(circulationdata.getHazardClearancetStartUrl());
                        circulationdataNew.setHazardClearanceEndUrl(circulationdata.getHazardClearanceEndUrl());
                        circulationdataNew.setHazardClearanceRemarks(circulationdata.getHazardClearanceRemarks());
                        circulationdataNew.setEndTime(hazardClearanceEndTime);
                        break;
                    case "⑥立架放线（min）":
                        Date frameLayingStartTime = circulationdata.getFrameLayingStartTime();
                        Date frameLayingEndTime = circulationdata.getFrameLayingEndTime();
                        long frameLayingMinutes = calculateMinutesDifference(frameLayingStartTime, frameLayingEndTime);
                        circulationdataNew.setFrameLayingStartTime(frameLayingStartTime);
                        circulationdataNew.setFrameLayingEndTime(frameLayingEndTime);
                        circulationdataNew.setFrameLayingDuration((int) frameLayingMinutes);
                        circulationdataNew.setFrameLayingStartUrl(circulationdata.getFrameLayingStartUrl());
                        circulationdataNew.setFrameLayingEndUrl(circulationdata.getFrameLayingEndUrl());
                        circulationdataNew.setFrameLayingRemarks(circulationdata.getFrameLayingRemarks());
                        circulationdataNew.setEndTime(frameLayingEndTime);
                        break;
                    case "⑦网片、立钢架（min）":
                        Date meshInstallationStartTime = circulationdata.getMeshInstallationStartTime();
                        Date meshInstallationEndTime = circulationdata.getMeshInstallationEndTime();
                        long meshInstallationMinutes = calculateMinutesDifference(meshInstallationStartTime, meshInstallationEndTime);
                        circulationdataNew.setMeshInstallationStartTime(meshInstallationStartTime);
                        circulationdataNew.setMeshInstallationEndTime(meshInstallationEndTime);
                        circulationdataNew.setMeshInstallationDuration((int) meshInstallationMinutes);
                        circulationdataNew.setMeshInstallationStartUrl(circulationdata.getMeshInstallationStartUrl());
                        circulationdataNew.setMeshInstallationEndUrl(circulationdata.getMeshInstallationEndUrl());
                        circulationdataNew.setMeshInstallationRemarks(circulationdata.getMeshInstallationRemarks());
                        circulationdataNew.setEndTime(meshInstallationEndTime);
                        break;
                    case "⑧锁脚、系统锚杆、超前小导管施作（min）":
                        Date anchoringStartTime = circulationdata.getAnchoringStartTime();
                        Date anchoringEndTime = circulationdata.getAnchoringEndTime();
                        long anchoringMinutes = calculateMinutesDifference(anchoringStartTime, anchoringEndTime);
                        circulationdataNew.setAnchoringStartTime(anchoringStartTime);
                        circulationdataNew.setAnchoringEndTime(anchoringEndTime);
                        circulationdataNew.setAnchoringDuration((int) anchoringMinutes);
                        circulationdataNew.setAnchoringStartUrl(circulationdata.getAnchoringStartUrl());
                        circulationdataNew.setAnchoringEndUrl(circulationdata.getAnchoringEndUrl());
                        circulationdataNew.setAnchoringRemarks(circulationdata.getAnchoringRemarks());
                        circulationdataNew.setEndTime(anchoringEndTime);
                        break;
                    case "⑨喷射砼（min）":
                        Date concreteSprayingStartTime = circulationdata.getConcreteSprayingStartTime();
                        Date concreteSprayingEndTime = circulationdata.getConcreteSprayingEndTime();
                        long concreteSprayingMinutes = calculateMinutesDifference(concreteSprayingStartTime, concreteSprayingEndTime);
                        circulationdataNew.setConcreteSprayingStartTime(concreteSprayingStartTime);
                        circulationdataNew.setConcreteSprayingEndTime(concreteSprayingEndTime);
                        circulationdataNew.setConcreteSprayingDuration((int) concreteSprayingMinutes);
                        circulationdataNew.setConcreteSprayingStartUrl(circulationdata.getConcreteSprayingStartUrl());
                        circulationdataNew.setConcreteSprayingEndUrl(circulationdata.getConcreteSprayingEndUrl());
                        circulationdataNew.setConcreteSprayingRemarks(circulationdata.getConcreteSprayingRemarks());
                        circulationdataNew.setEndTime(concreteSprayingEndTime);
                        break;
                    case "⑩扒齐头（min）":
                        Date cleanupStartTime = circulationdata.getCleanupStartTime();
                        Date cleanupEndTime = circulationdata.getCleanupEndTime();
                        long cleanupMinutes = calculateMinutesDifference(cleanupStartTime, cleanupEndTime);
                        circulationdataNew.setCleanupStartTime(cleanupStartTime);
                        circulationdataNew.setCleanupEndTime(cleanupEndTime);
                        circulationdataNew.setCleanupDuration((int) cleanupMinutes);
                        circulationdataNew.setCleanupStartUrl(circulationdata.getCleanupStartUrl());
                        circulationdataNew.setCleanupEndUrl(circulationdata.getCleanupEndUrl());
                        circulationdataNew.setCleanupRemarks(circulationdata.getCleanupRemarks());
                        circulationdataNew.setEndTime(cleanupEndTime);
                        break;
                    default:
                        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "未处理的过程类型");
                }

                circulationdataNew.setVersion(currentVersion + 1);
                int update = circulationdataDao.update(circulationdataNew,lambdaQueryWrapper);
                if (update <= 0) {
                    return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
                } else {
                    return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "操作失败，发生异常：" + e.getMessage());
        }
    }

    /**
     * 计算两个 Date 对象之间的时间差，以分钟为单位。
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 时间差（分钟）
     * @throws IllegalArgumentException 如果任一时间为 null
     */
    public static long calculateMinutesDifference(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("时间不能为 null");
        }

        // 将 Date 转换为 ZonedDateTime
        ZonedDateTime startDateTime = startTime.toInstant().atZone(ZoneId.systemDefault());
        ZonedDateTime endDateTime = endTime.toInstant().atZone(ZoneId.systemDefault());

        // 计算时间差
        Duration duration = Duration.between(startDateTime, endDateTime);
        return duration.toMinutes();
    }
    /**
     * 计算两个长度点之间的差值。
     *
     * @param startPoint 起点字符串（如 "PDK+27.4"）
     * @param endPoint 终点字符串（如 "PDK+24.85"）
     * @return 长度差值的绝对值
     * @throws NumberFormatException 如果字符串格式不正确
     */
    public static double calculateLengthDifference(String startPoint, String endPoint) throws NumberFormatException {
        double startLength = extractLength(startPoint);
        System.out.println("截取的起始里程"+startLength);
        double endLength = extractLength(endPoint);
        System.out.println("截取的终点里程"+endLength);
        double difference = startLength - endLength;
        System.out.println("两者之差"+difference);
        // 返回绝对值
        return Math.abs(difference);
    }

    /**
     * 提取字符串中的长度部分。
     *
     * @param point 包含长度的字符串（如 "PDK+27.4"）
     * @return 提取的长度值
     */
    public static double extractLength(String point) {
        // 找到 "+" 的位置
        int plusIndex = point.indexOf('+');
        int plusIndexCN = point.indexOf('\uFF0B');
        if (plusIndex == -1&& plusIndexCN == -1) {
            throw new NumberFormatException("字符串格式不正确，没有 '+' 符号");
        }
        if (plusIndex>0){
            // 提取 "+" 后面的部分，并将其转换为浮点数
            String lengthString = point.substring(plusIndex + 1);
            return Double.parseDouble(lengthString);
        }
        // 提取 "+" 后面的部分，并将其转换为浮点数
        String lengthString = point.substring(plusIndexCN + 1);
        return Double.parseDouble(lengthString);
    }
}
