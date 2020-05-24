package com.sgm.workorderms.dao;

import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.util.UtilJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountWorkOrderPlanDao {

    /**
     * 根据时间区间查询工单计划表
     * @param workOrderPlan
     * @return
     */
    public List<WorkOrderPlan> queryWorkOrderPlanForTimeRegin(WorkOrderPlan workOrderPlan){
        String sql="select * from  workorderms.workorder_plan m where m.plan_start_time >= ? and m.plan_end_time <=?";
        List<WorkOrderPlan> workOrdersPlanList=new ArrayList<>();
        Connection conn=null;
        PreparedStatement ptmt=null;
        ResultSet rs =null;
        String result="";
        try {
            conn = UtilJDBC.getConnection();
            ptmt=conn.prepareStatement(sql);
            ptmt.setObject(1,workOrderPlan.getPlan_start_time());
            ptmt.setObject(2,workOrderPlan.getPlan_end_time());
            rs=ptmt.executeQuery();
            while(rs.next()){
                WorkOrderPlan workOrderPlanTemp=new WorkOrderPlan();
                workOrderPlanTemp.setId(rs.getInt("id"));
                workOrderPlanTemp.setNumber(rs.getString("number"));
                workOrderPlanTemp.setName(rs.getString("name"));
                workOrderPlanTemp.setContent(rs.getString("content"));
                workOrderPlanTemp.setCycle(rs.getString("cycle"));
                workOrderPlanTemp.setPlan_start_time(rs.getDate("plan_start_time"));
                workOrderPlanTemp.setPlan_end_time(rs.getDate("plan_end_time"));
                workOrderPlanTemp.setRole(rs.getString("role"));
                workOrderPlanTemp.setExecutor(rs.getString("executor"));
                workOrderPlanTemp.setStatus(rs.getString("status"));
                workOrdersPlanList.add(workOrderPlanTemp);
            }
        }  catch (SQLException e) {//执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {
            UtilJDBC.close(rs,null,ptmt,conn);
        }
        return workOrdersPlanList;
    }

    /**
     * 根据SQL语句查询工单计划表所有字段
     * @param sql
     * @return
     */
    public List<WorkOrderPlan> queryWorkOrderPlanComplete(String sql){
        List<WorkOrderPlan> workOrdersPlanList=new ArrayList<>();
        Connection conn=null;
        PreparedStatement ptmt=null;
        ResultSet rs =null;
        String result="";
        try {
            conn = UtilJDBC.getConnection();
            ptmt=conn.prepareStatement(sql);
            rs=ptmt.executeQuery();
            while(rs.next()){
                WorkOrderPlan workOrderPlanTemp=new WorkOrderPlan();
                workOrderPlanTemp.setId(rs.getInt("id"));
                workOrderPlanTemp.setNumber(rs.getString("number"));
                workOrderPlanTemp.setName(rs.getString("name"));
                workOrderPlanTemp.setContent(rs.getString("content"));
                workOrderPlanTemp.setCycle(rs.getString("cycle"));
                workOrderPlanTemp.setPlan_start_time(rs.getDate("plan_start_time"));
                workOrderPlanTemp.setPlan_end_time(rs.getDate("plan_end_time"));
                workOrderPlanTemp.setRole(rs.getString("role"));
                workOrderPlanTemp.setExecutor(rs.getString("executor"));
                workOrderPlanTemp.setStatus(rs.getString("status"));
                workOrdersPlanList.add(workOrderPlanTemp);
            }
        }  catch (SQLException e) {//执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {
            UtilJDBC.close(rs,null,ptmt,conn);
        }
        return workOrdersPlanList;
    }
}