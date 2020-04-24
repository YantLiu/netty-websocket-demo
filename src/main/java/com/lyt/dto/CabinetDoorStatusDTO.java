package com.lyt.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 舱位状态
 * @author: lyt
 * @create: 2020-03-25 14:34
 **/
public class CabinetDoorStatusDTO {
    /*
     * 经舱位编号
     */
    private int door;
    /*
     * 充电状态: [un: 未充电, ing:充电中, full: 已充满]
     */
    private String chargingState;
    /*
     * 舱门状态: [0:异常, 1: 正常]
     */
    private String cabinetState;
    /*
     * 租赁状态: [0: 未租, 1: 已租]
     */
    private String driverState;
    /*
     * 启用: [0:禁用, 1: 启用]
     */
    private String dateState;


    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public String getChargingState() {
        return chargingState;
    }

    public void setChargingState(String chargingState) {
        this.chargingState = chargingState;
    }

    public String getCabinetState() {
        return cabinetState;
    }

    public void setCabinetState(String cabinetState) {
        this.cabinetState = cabinetState;
    }

    public String getDriverState() {
        return driverState;
    }

    public void setDriverState(String driverState) {
        this.driverState = driverState;
    }

    public String getDateState() {
        return dateState;
    }

    public void setDateState(String dateState) {
        this.dateState = dateState;
    }

    public static List<CabinetDoorStatusDTO> getList() {
        List<CabinetDoorStatusDTO> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            CabinetDoorStatusDTO dto = new CabinetDoorStatusDTO();
            dto.setDoor(i);
            dto.setChargingState("un");
            dto.setCabinetState("1");
            dto.setDriverState("0");
            dto.setDateState("1");
            list.add(dto);
        }
        return list;
    }
}
