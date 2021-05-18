package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/18 4:05
 */
@RequestMapping("/setmeal")
@RestController
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        setmealService.add(setmeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 上传图片
     *
     * @param imgFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
//        获取名字，截取后缀，拼接随机文件名
        String originalFilename = imgFile.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + fileType;

//        调七牛云api上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), fileName);
            Map<String, String> map = new HashMap<>();
            map.put("domain", QiNiuUtils.DOMAIN);
            map.put("imgName", fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    /**
     * 分页查询套餐
     *
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Setmeal> list = setmealService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, list);
    }

    /**
     * 回显套餐
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(int id) {
        Setmeal setmeal = setmealService.findById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("setmeal",setmeal);
        map.put("domain",QiNiuUtils.DOMAIN);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,map);
    }

    /**
     * 回显与套餐绑定的检查组
     * @param id
     * @return
     */
    @RequestMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(int id){
        List<Integer> list = setmealService.findCheckgroupIdsBySetmealId(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    }

    /**
     * 提交套餐编辑
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        setmealService.update(setmeal,checkgroupIds);
        return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    /**
     * 删除套餐
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(int id){
        setmealService.deleteById(id);
        return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
}
