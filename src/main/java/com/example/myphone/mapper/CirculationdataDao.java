package com.example.myphone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myphone.entity.Circulationdata;
import org.apache.ibatis.annotations.Mapper;

/**
 * 循环数据表(Circulationdata)表数据库访问层
 *
 * @author makejava
 * @since 2024-08-07 19:46:33
 */
@Mapper
public interface CirculationdataDao extends BaseMapper<Circulationdata> {
}

