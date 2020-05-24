package com.sgm.workorderms.dao;

import com.sgm.workorderms.bean.WorkOrder;
import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.util.UtilJDBC;
import com.sgm.workorderms.util.Utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Timestamp;

/**
 * 封装工单计划表相关的非共有数据库操作
 */

public class WorkOrderPlanDao {

    /**
     * 向工单计划表中插入数据
     * @param workOrderPlan
     * @return
     */
    public Integer insert(WorkOrderPlan workOrderPlan){
        Integer result=0;
        //根据数据库表, 连接数据库执行插入操作
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs =null;
        PreparedStatement ps = null;
        try {
            conn = UtilJDBC.getConnection();
            //stmt=conn.createStatement();
            String number = workOrderPlan.getNumber();

            String sql= "insert into workorder_plan(number,name,content,cycle,plan_start_time,plan_end_time,role,executor,status) " +
                    "values(?,?,?,?,?,?,?,?,?)";
            //rs=stmt.executeQuery(sql);
            ps = conn.prepareStatement(sql);
            ps.setString(1,workOrderPlan.getNumber());
            ps.setString(2,workOrderPlan.getName());
            ps.setString(3,workOrderPlan.getContent());
            ps.setString(4,workOrderPlan.getCycle());
            java.sql.Timestamp date = new java.sql.Timestamp(workOrderPlan.getPlan_start_time().getTime());
            System.out.println(date);
            ps.setObject(5, date);
            java.sql.Timestamp date1 = new java.sql.Timestamp(workOrderPlan.getPlan_end_time().getTime());
            System.out.println(date1);
            ps.setObject(6, date1);
            ps.setString(7,workOrderPlan.getRole());
            ps.setString(8,workOrderPlan.getExecutor());
            ps.setString(9,"0");

            ps.addBatch();
            ps.executeBatch();
            result = 1;
        }  catch (SQLException e) {//执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {
            UtilJDBC.close(rs,stmt,ps,conn);
        }
        return result;
    }

    /**
     * 根据SQL查询一个字段值
     * @param sql
     * @return
     */
    public String queryField(String sql,String field){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs =null;
        String result="";
        try {
            conn = UtilJDBC.getConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getString(field)!=null){
                    result = rs.getString(field);
                }
            }
        }  catch (SQLException e) {//执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {
            UtilJDBC.close(rs,stmt,null,conn);
        }
        return result;
    }

    public void updateWorkOrder(List<WorkOrderPlan> workOrderPlanList) {
        Connection conn=null;
        PreparedStatement psmt=null;
        String sql="UPDATE workorderms.workorder_plan SET status = ? WHERE id = ?";
        boolean result=false;
        try {
            conn = UtilJDBC.getConnection();
            psmt = conn.prepareStatement(sql);
            for (int i=0;i<workOrderPlanList.size();i++){
                //连接数据库
                psmt.setString(1, "1");
                psmt.setInt(2,workOrderPlanList.get(i).getId());
                psmt.addBatch();
                //psmt.executeUpdate();

            }
            //1 提交SQL命令
            //psmt.executeUpdate();
            psmt.executeBatch();
            result=true;
        }  catch (SQLException e) {//2 执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {//3 释放资源
            UtilJDBC.close(null,null,psmt,conn);
        }

    }
}
