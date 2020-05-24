package com.sgm.workorderms.service;

import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.dao.CountWorkOrderPlanDao;

import java.util.ArrayList;
import java.util.List;

public class CountWorkOrderPlanService {
    CountWorkOrderPlanDao countWorkOrderPlanDao = null;
    /**
     * 统计某个时间段内的工单计划数
     * @param workOrderPlan
     * @return
     */
    public List<WorkOrderPlan> queryWorkOrderPlanForTimeRegin(WorkOrderPlan workOrderPlan){
        countWorkOrderPlanDao=new CountWorkOrderPlanDao();
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        //调用Dao层queryWorkOrderPlanForTimeRegin查询数据
        workOrderPlanList=countWorkOrderPlanDao.queryWorkOrderPlanForTimeRegin(workOrderPlan);
        return workOrderPlanList;
    }

    /**
     * 统计已生成工单的工单计划数
     * @return
     */
    public List<WorkOrderPlan> queryWorkOrderPlanComplete(){
        countWorkOrderPlanDao=new CountWorkOrderPlanDao();
        String sql="select * from  workorderms.workorder_plan where status=1";
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        //调用Dao层queryWorkOrderPlanForTimeRegin查询数据
        workOrderPlanList=countWorkOrderPlanDao.queryWorkOrderPlanComplete(sql);
        return workOrderPlanList;
    }

    /**
     * 统计未生成工单的工单计划数
     * @return
     */
    public List<WorkOrderPlan> queryWorkOrderPlanNotComplete(){
        countWorkOrderPlanDao=new CountWorkOrderPlanDao();
        String sql="select * from  workorderms.workorder_plan where status=0";
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        //调用Dao层queryWorkOrderPlanForTimeRegin查询数据
        workOrderPlanList=countWorkOrderPlanDao.queryWorkOrderPlanComplete(sql);
        return workOrderPlanList;
    }

    /**
     * 统计提交的所有工单计划数
     * @return
     */
    public List<WorkOrderPlan> queryWorkOrderPlanAll(){
        countWorkOrderPlanDao=new CountWorkOrderPlanDao();
        String sql="select * from  workorderms.workorder_plan";
        List<WorkOrderPlan> workOrderPlanList=new ArrayList<WorkOrderPlan>();
        //调用Dao层queryWorkOrderPlanForTimeRegin查询数据
        workOrderPlanList=countWorkOrderPlanDao.queryWorkOrderPlanComplete(sql);
        return workOrderPlanList;
    }
}