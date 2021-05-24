package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/24 19:34
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 批量导入预约设置
     * @param excelFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile) {
        try {
//        读文件，转化为OrderSetting的集合
            List<String[]> strings = POIUtils.readExcel(excelFile);
            List<OrderSetting> orderSettingList = new ArrayList<OrderSetting>();
            SimpleDateFormat sdf = new SimpleDateFormat(POIUtils.DATE_FORMAT);
            OrderSetting os = null;
            for (String[] stringArray : strings) {
                Date date = sdf.parse(stringArray[0]);
                int number = Integer.valueOf(stringArray[1]);
                os = new OrderSetting(date, number);
                orderSettingList.add(os);
            }
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    /**
     * 通过月份查询预约设置信息
     * @param month
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month){
        List<Map<String,Integer>> list = orderSettingService.getOrderSettingByMonth(month);
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }
}
