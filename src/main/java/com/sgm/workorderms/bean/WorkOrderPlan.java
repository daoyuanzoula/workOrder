package com.sgm.workorderms.bean;


import java.math.BigDecimal;
import java.util.Date;

public class WorkOrderPlan {
    private int id ;
    private String number;
    private String name;
    private String content;
    private String cycle;
    private Date plan_start_time;
    private Date plan_end_time;
    private String role;
    private String executor;
    private String status;

    public WorkOrderPlan(int id, String number, String name, String content, String cycle, Date plan_start_time, Date plan_end_time, String role, String executor, String status) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.content = content;
        this.cycle = cycle;
        this.plan_start_time = plan_start_time;
        this.plan_end_time = plan_end_time;
        this.role = role;
        this.executor = executor;
        this.status = status;
    }

    public WorkOrderPlan() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Date getPlan_start_time() {
        return plan_start_time;
    }

    public void setPlan_start_time(Date plan_start_time) {
        this.plan_start_time = plan_start_time;
    }

    public Date getPlan_end_time() {
        return plan_end_time;
    }

    public void setPlan_end_time(Date plan_end_time) {
        this.plan_end_time = plan_end_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
