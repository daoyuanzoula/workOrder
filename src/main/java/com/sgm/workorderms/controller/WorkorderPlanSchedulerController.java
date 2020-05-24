package com.sgm.workorderms.controller;

import com.sgm.workorderms.service.WorkOrderService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling //1.开启调度器
public class WorkorderPlanSchedulerController{
    WorkOrderService workOrderService=new WorkOrderService();
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date=sdf.format(new Date());

    //2.每天凌晨0点启动工单计划调度器
    //@Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 14 11 * * ?")
    private void workOrderScheduler()throws ParseException {
        System.out.println(date+":工单计划调度器启动");
        workOrderService.produceWorkOrder();
        System.out.println(date+":工单计划调度器结束");
    }
}