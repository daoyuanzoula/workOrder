package com.sgm.workorderms.service;

import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.dao.WorkOrderPlanDao;
import com.sgm.workorderms.util.Utils;

public class WorkOrderPlanService {

    /**
     * 创建工单业务逻辑处理
     * @param workOrderPlan
     * @return
     */
    public Integer CreateWorkOrderPlan(WorkOrderPlan workOrderPlan){
        WorkOrderPlanDao workOrderPlanDao=new WorkOrderPlanDao();
        Utils utils=new Utils();
        //1 根据科室代码生成工单号
        String number =  utils.createWorkOrderNumber(workOrderPlan.getNumber());
        //2 根据周期生成对应的cron
        String cycle= utils.analysisCycle(workOrderPlan.getCycle());
        //3 调用Dao层入库
        workOrderPlan.setNumber(number);
        workOrderPlan.setCycle(cycle);
        Integer result=0;
        result=workOrderPlanDao.insert(workOrderPlan);
        return result;
    }
}