package com.sgm.workorderms.dao;

import com.sgm.workorderms.bean.WorkOrder;
import com.sgm.workorderms.bean.WorkOrderPlan;
import com.sgm.workorderms.util.UtilJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装工单表相关的非共有数据库操作
 */
public class WorkOrderDao {
    /**
     * 根据SQL查询workOrderPlan，返回结果封装在List中
     * @param sql
     * @return workOrdersPlanList
     */
    public List<WorkOrderPlan> queryWorkOrder(String sql){
        List<WorkOrderPlan> workOrdersPlanList=new ArrayList<>();
        //连接数据库处理....
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet rs = null;
        try {
            conn = UtilJDBC.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                WorkOrderPlan workOrdersPlan = new WorkOrderPlan(rs.getInt("id"),
                        rs.getString("number"),
                        rs.getString("name"),
                        rs.getString("content"),
                        rs.getString("cycle"),
                        rs.getDate("plan_start_time"),
                        rs.getDate("plan_end_time"),
                        rs.getString("role"),
                        rs.getString("executor"),
                        rs.getString("status")
                );
                workOrdersPlanList.add(workOrdersPlan);
            }
            System.out.println(workOrdersPlanList);
        }  catch (SQLException e) {//2 执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {//3 释放资源
            UtilJDBC.close(null,null,psmt,conn);
        }

        return workOrdersPlanList;
    }

    /**
     * 向数据库批量提交工单数据
     * @param workOrderList
     * @return result
     */
    public boolean insertWorkOrder(List<WorkOrder> workOrderList){
        Connection conn=null;
        PreparedStatement psmt=null;
        String sql="insert into workorderms.workorder(number,name,content,start_time,end_time,role,executor) values(?,?,?,?,?,?,?)";
        boolean result=false;
        try {
            conn = UtilJDBC.getConnection();
            psmt = conn.prepareStatement(sql);
            for (int i=0;i<workOrderList.size();i++){
                //连接数据库处理....
                psmt.setString(1,workOrderList.get(i).getNumber());
                psmt.setString(2,workOrderList.get(i).getName());
                psmt.setString(3,workOrderList.get(i).getContent());
                java.sql.Timestamp date = new java.sql.Timestamp(workOrderList.get(i).getStart_time().getTime());
                psmt.setTimestamp(4, date);
                java.sql.Timestamp date1 = new java.sql.Timestamp(workOrderList.get(i).getEnd_time().getTime());
                psmt.setTimestamp(5, date1);
                psmt.setString(6,workOrderList.get(i).getRole());
                psmt.setString(7,workOrderList.get(i).getExecutor());
                psmt.addBatch();
                //psmt.executeUpdate();

            }
            //1 提交SQL命令
            psmt.executeBatch();
            result=true;
        }  catch (SQLException e) {//2 执行与数据库建立连接需要抛出SQL异常
            e.printStackTrace();
        }   finally {//3 释放资源
            UtilJDBC.close(null,null,psmt,conn);
        }
        return result;
    }
}