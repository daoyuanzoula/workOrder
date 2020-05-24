package com.sgm.workorderms.controller;

import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.bean.WorkOrderPlanChange;
import com.sgm.workorderms.service.WorkOrderPlanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WorkOrderPlanController {

    @RequestMapping("/createWorkOrderPlan")
    public String CreateWorkOrderPlan(@RequestBody WorkOrderPlanChange workOrderPlanChange) throws ParseException {
        //JSON String转换日期格式
        WorkOrderPlan workOrderPlan = new WorkOrderPlan();
        //workOrderPlan.setId(workOrderPlanChange.getId());
        workOrderPlan.setNumber(workOrderPlanChange.getNumber());
        workOrderPlan.setName(workOrderPlanChange.getName());
        workOrderPlan.setContent(workOrderPlanChange.getContent());
        workOrderPlan.setCycle(workOrderPlanChange.getCycle());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        workOrderPlan.setPlan_start_time(sdf.parse(workOrderPlanChange.getPlan_start_time()));
        workOrderPlan.setPlan_end_time(sdf.parse(workOrderPlanChange.getPlan_end_time()));
        workOrderPlan.setRole(workOrderPlanChange.getRole());
        workOrderPlan.setExecutor(workOrderPlanChange.getExecutor());
        workOrderPlan.setStatus(workOrderPlanChange.getStatus());

        WorkOrderPlanService workOrderPlanService = new WorkOrderPlanService();
        //System.out.println(workOrderPlan);
        String resDate=sdf.format(new Date());
        int result=0;   //用于接收SQL执行返回码
        //1、判断前端传过来的数据是否满足要求
        //需要处理字段Number、Name、Content、Cycle、Role、Executor
        //...
        if((workOrderPlan.getPlan_start_time()==null)&&(workOrderPlan.getPlan_end_time()==null)&&(workOrderPlan.getPlan_start_time().getTime()>=workOrderPlan.getPlan_end_time().getTime())){
            return "参数不能为空或开始时间大于等于结束时间";
        }

        result=workOrderPlanService.CreateWorkOrderPlan(workOrderPlan);
        //System.out.println(result);
        //根据返回结果值,完成工单计划成功或失败逻辑处理
        //...
        if  (result == 1) {
            return resDate+":创建工单计划成功";
        } else {
            return resDate+":创建工单计划失败";
        }

    }
}