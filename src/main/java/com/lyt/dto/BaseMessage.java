package com.lyt.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyt.enums.MessageWork;
import com.lyt.utils.ValidateUtils;

/**
 * @description: 基础报文
 * @author: lyt
 * @create: 2020-03-23 17:18
 **/
public class BaseMessage<T> {
    //类型
    private String type;
    //报文编号
    private String work;
    //请求id
    private String id;
    //是否成功
    private boolean success;
    //消息
    private String message;
    //舱位编号
    private int door;
    //告警类型
    private String warnType;
    //数据对象
    private T data;

    public BaseMessage() {
    }

    public BaseMessage(MessageWork work, String id, int door) {
        this.type = work.getType().name();
        this.work = work.getWork();
        this.id = id;
        this.door = door;
    }

    public BaseMessage(MessageWork work, String id, int door, T data) {
        this.type = work.getType().name();
        this.work = work.getWork();
        this.id = id;
        this.door = door;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        System.out.println(new BaseMessage<>(MessageWork.DOORS_USING_SEARCH, "Tank-756FG_1", 1));
    }
}
