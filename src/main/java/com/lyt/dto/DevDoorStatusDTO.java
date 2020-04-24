package com.lyt.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 舱位状态
 * @author: lyt
 * @create: 2020-03-25 14:34
 **/
@Data
public class DevDoorStatusDTO {
    /*
     * 舱位编号
     */
    private int door;
    /*
     * 充电状态: [un: 未充电, ing:充电中, full: 已充满]
     */
    private String chargingState;
    /*
     * 舱门状态: [0:异常, 1: 正常]
     */
    private Integer doorState;
    /*
     * 租赁状态: [0: 未租, 1: 已租]
     */
    private Integer driverState;
    /*
     * 启用: [0:禁用, 1: 启用]
     */
    private Integer dateState;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static List<DevDoorStatusDTO> getList() {
        List<DevDoorStatusDTO> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            DevDoorStatusDTO dto = new DevDoorStatusDTO();
            dto.setDoor(i);
            dto.setChargingState("un");
            dto.setDoorState(1);
            dto.setDriverState(0);
            dto.setDateState(1);
            list.add(dto);
        }
        return list;
    }
}
