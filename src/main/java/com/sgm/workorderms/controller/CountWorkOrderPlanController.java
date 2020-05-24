package com.sgm.workorderms.controller;

import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.bean.WorkOrderPlanChange;
import com.sgm.workorderms.service.CountWorkOrderPlanService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountWorkOrderPlanController {
    CountWorkOrderPlanService countWorkOrderPlanService=null;

    /**
     * 统计某个时间段内的工单计划数
     * @param workOrderPlanChange
     * @return
     */
    @RequestMapping("/queryWorkOrderPlanForTimeRegin")
    public List<WorkOrderPlan> queryWorkOrderPlanForTimeRegin(@RequestBody WorkOrderPlanChange workOrderPlanChange) throws ParseException {
        WorkOrderPlan workOrderPlan = new WorkOrderPlan();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        workOrderPlan.setPlan_start_time(sdf.parse(workOrderPlanChange.getPlan_start_time()));
        workOrderPlan.setPlan_end_time(sdf.parse(workOrderPlanChange.getPlan_end_time()));

        countWorkOrderPlanService=new CountWorkOrderPlanService();
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        //1 校验时间参数是否为空，且开始时间不能大于结束时间
        if((workOrderPlan.getPlan_start_time()==null)&&(workOrderPlan.getPlan_end_time()==null)&&(workOrderPlan.getPlan_start_time().getTime()>workOrderPlan.getPlan_end_time().getTime())){
            return null;
        }else {
            //2 调用业务逻辑层queryWorkOrderPlanForTimeRegin方法查询数据
            workOrderPlanList=countWorkOrderPlanService.queryWorkOrderPlanForTimeRegin(workOrderPlan);
        }
        return workOrderPlanList;
    }

    /**
     * 统计已生成工单的工单计划数
     * @return
     */
    @RequestMapping("/queryWorkOrderPlanComplete")
    public List<WorkOrderPlan> queryWorkOrderPlanComplete(){
        countWorkOrderPlanService=new CountWorkOrderPlanService();
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        workOrderPlanList=countWorkOrderPlanService.queryWorkOrderPlanComplete();
        return workOrderPlanList;
    }

    /**
     * 统计未生成工单的工单计划数
     * @return
     */
    @RequestMapping("/queryWorkOrderPlanNotComplete")
    public List<WorkOrderPlan> queryWorkOrderPlanNotComplete(){
        countWorkOrderPlanService=new CountWorkOrderPlanService();
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        workOrderPlanList=countWorkOrderPlanService.queryWorkOrderPlanNotComplete();
        return workOrderPlanList;
    }

    /**
     * 统计提交的工单计划数
     * @return
     */
    @RequestMapping("/queryWorkOrderPlanAll")
    public List<WorkOrderPlan> queryWorkOrderPlanAll(){
        countWorkOrderPlanService=new CountWorkOrderPlanService();
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        workOrderPlanList=countWorkOrderPlanService.queryWorkOrderPlanAll();
        return workOrderPlanList;
    }
}