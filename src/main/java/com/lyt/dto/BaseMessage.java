package com.lyt.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyt.enums.MessageWork;
import com.lyt.utils.ValidateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 基础报文
 * @author: lyt
 * @create: 2020-03-23 17:18
 **/
@Data
public class BaseMessage<T> {
    @ApiModelProperty("报文类型")
    private String work;

    @ApiModelProperty("业务id")
    private String transactionId;

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("错误消息")
    private String errorMessage;

    @ApiModelProperty("报文体")
    private T data;

    public BaseMessage() {
    }

    public BaseMessage(MessageWork work, String transactionId) {
        this.work = work.getWork();
        this.transactionId = transactionId;
    }

    public BaseMessage(MessageWork work, String transactionId, T data) {
        this.work = work.getWork();
        this.transactionId = transactionId;
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
