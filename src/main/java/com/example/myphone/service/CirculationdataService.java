package com.example.myphone.service;

import com.example.myphone.entity.Circulationdata;
import com.example.myphone.entity.ResponseResult;

/**
 * 循环数据表(Circulationdata)表服务接口
 *
 * @author makejava
 * @since 2024-08-07 19:46:34
 */
public interface CirculationdataService {


    ResponseResult submit(Circulationdata circulationdata);
}
