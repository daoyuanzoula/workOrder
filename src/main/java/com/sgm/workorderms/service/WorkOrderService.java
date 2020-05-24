package com.sgm.workorderms.service;

import com.sgm.workorderms.dao.WorkOrderDao;
import com.sgm.workorderms.dao.WorkOrderPlanDao;
import com.sgm.workorderms.bean.WorkOrder;
import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkOrderService {

    /**
     * 根据工单计划生成工单
     * @throws ParseException
     */
    public void produceWorkOrder() throws ParseException {
        Utils utils=new Utils();
        WorkOrderDao workOrderDao=new WorkOrderDao();
        WorkOrderPlanDao workOrderPlanDao=new WorkOrderPlanDao();

        List<WorkOrderPlan> workOrderPlanList=new ArrayList<>();
        List<WorkOrder> workOrderList=new ArrayList<>();
        Map<String ,Object> map =new HashMap<String,Object>();
        //1.获取当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date())+" 00:00:00";
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate=utils.dateAdd(sdf.parse(date),5,1);//date参数加1天

        //2.写出满足条件的SQL
        String sql="select * from workorderms.workorder_plan where status=0 and plan_start_time>=date_format('"+date
                +"','%Y-%m-%d %H:%i:%s') and plan_start_time < date_format('"+sdf.format(newDate)+"','%Y-%m-%d %H:%i:%s')";

        //3.调用查询数据的方法
        workOrderPlanList=workOrderDao.queryWorkOrder(sql);

        //4.更新时间值，作为打印时间戳
        String strDate=sdf.format(new Date());

        //5.调用工单生成方法
        if(workOrderPlanList.size()<0){
            //当日无工单计划
            System.out.println(strDate+":当日无工单计划");
            return;
        }

        map=utils.produceWorkOrder(workOrderPlanList);
        workOrderList=(List<WorkOrder>) map.get("workOrder");
        workOrderPlanList=(List<WorkOrderPlan>)map.get("workOrderPlan");

        //5.1 工单数据入库
        boolean flag = workOrderDao.insertWorkOrder(workOrderList);

        if(!flag){
            //根据工单号生成工单入库失败
            return;
        }

        System.out.println(strDate+":根据工单号生成工单入库成功");
        //5.2 更新工单计划数据：工单生成状态置为1-已生成
        //...
        workOrderPlanDao.updateWorkOrder(workOrderPlanList);
        System.out.println(strDate+":更改工单状态成功");


    }
}