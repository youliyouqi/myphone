package com.example.myphone.controller;


import com.example.myphone.entity.Circulationdata;
import com.example.myphone.entity.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.example.myphone.service.CirculationdataService;


/**
 * 循环数据表(Circulationdata)表控制层
 *
 * @author makejava
 * @since 2024-08-07 19:46:32
 */
@RestController
@RequestMapping("circulationdata")
public class CirculationdataController {
    @Resource
    private CirculationdataService circulationdataService;

      @PostMapping("/submit")
     public ResponseResult submit(@RequestBody Circulationdata circulationdata){
          return   circulationdataService.submit(circulationdata);
     }
}

