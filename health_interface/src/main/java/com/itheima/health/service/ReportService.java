package com.itheima.health.service;

import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/8/1 20:25
 */
public interface ReportService {
    /**
     * 运营统计数据
     * @return
     */
    Map<String, Object> getBusinessReportData();
}
