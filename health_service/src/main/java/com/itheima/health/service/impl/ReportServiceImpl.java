package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.dao.OrderDao;
import com.itheima.health.service.ReportService;
import com.itheima.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/8/1 20:25
 */
@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    /**
     * 运营统计数据
     * @return
     */
    @Override
    public Map<String, Object> getBusinessReportData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //reportDate
        String today = sdf.format(new Date());
        // 周一
        String monday = sdf.format(DateUtils.getThisWeekMonday());
        // 周日
        String sunday = sdf.format(DateUtils.getSundayOfThisWeek());
        // 1号
        String firstDayOfThisMonth = sdf.format(DateUtils.getFirstDayOfThisMonth());
        // 这个月的最后一天
        String lastDayOfThisMonth = sdf.format(DateUtils.getLastDayOfThisMonth());

        // =================== 会员统计 ======================
        //todayNewMember
        int todayNewMember = memberDao.findMemberCountByDate(today);
        //totalMember
        int totalMember = memberDao.findMemberTotalCount();
        //thisWeekNewMember
        int thisWeekNewMember = memberDao.findMemberCountAfterDate(monday);
        //thisMonthNewMember
        int thisMonthNewMember = memberDao.findMemberCountAfterDate(firstDayOfThisMonth);

        // ===============  预约统计 ===================
        //todayOrderNumber
        int todayOrderNumber = orderDao.findOrderCountByDate(today);
        //todayVisitsNumber
        int todayVisitsNumber = orderDao.findVisitsCountByDate(today);
        //thisWeekOrderNumber
        int thisWeekOrderNumber = orderDao.findOrderCountBetweenDate(monday,sunday);
        //thisWeekVisitsNumber
        int thisWeekVisitsNumber = orderDao.findVisitsCountAfterDate(monday);
        //thisMonthOrderNumber
        int thisMonthOrderNumber = orderDao.findOrderCountBetweenDate(firstDayOfThisMonth, lastDayOfThisMonth);
        //thisMonthVisitsNumber
        int thisMonthVisitsNumber = orderDao.findVisitsCountAfterDate(firstDayOfThisMonth);

        // ============== 热门套餐 ==================
        //hotSetmeal
        List<Map<String,Object>> hotSetmeal = orderDao.findHotSetmeal();

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("reportDate",today);
        map.put("todayNewMember",todayNewMember);
        map.put("totalMember",totalMember);
        map.put("thisWeekNewMember",thisWeekNewMember);
        map.put("thisMonthNewMember",thisMonthNewMember);
        map.put("todayOrderNumber",todayOrderNumber);
        map.put("todayVisitsNumber",todayVisitsNumber);
        map.put("thisWeekOrderNumber",thisWeekOrderNumber);
        map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        map.put("thisMonthOrderNumber",thisMonthOrderNumber);
        map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        map.put("hotSetmeal",hotSetmeal);
        return map;
    }
}
