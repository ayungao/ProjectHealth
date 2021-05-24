package com.itheima.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/23 20:21
 */
@Component("cleanImgJob")
public class CleanImgJob {

    @Reference
    private SetmealService setmealService;

    /**
     * 清理垃圾图片
     */
    public void cleanImg(){
//        查询七牛上的图片
        List<String> imgIn7Niu = QiNiuUtils.listFile();
//        查询数据库中的图片
        List<String> imgInDb = setmealService.findImgs();

        imgIn7Niu.removeAll(imgInDb);

        for (String s : imgIn7Niu) {
            QiNiuUtils.removeFiles(s);
        }
    }
}
