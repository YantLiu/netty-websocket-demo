package com.lyt.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyt.enums.MessageWork;
import com.lyt.utils.ValidateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 基础报文
 * @author: lyt
 * @create: 2020-03-23 17:18
 **/
@Data
@Builder
@AllArgsConstructor
public class BaseMessage<T> {
    @ApiModelProperty("事件对象[cabinet: 柜机, door: 柜位, server: 服务器]")
    private String object;

    @ApiModelProperty("报文类型[control: 柜机控制, report: 上报, warn: 告警]")
    private String type;

    @ApiModelProperty("事件名")
    private String work;

    @ApiModelProperty("对象数据")
    private T data;

    public BaseMessage() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        System.out.println(BaseMessage.builder().object("cabinet").type("control").work("cabinetDoorOpen").data(new Object()).build());
    }
}
