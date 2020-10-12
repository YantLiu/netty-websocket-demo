package com.lyt.utils;

import com.alibaba.fastjson.JSONObject;
import com.lyt.dto.BaseMessage;
import com.lyt.enums.MessageWork;
import com.lyt.websocket.ChannelHandlerPool;

/**
 * @description: 柜机控制类
 * @author: lyt
 * @create: 2020-03-23 17:17
 **/
public class DevControlUtil {

    private DevControlUtil() {
    }

    /**
     * @Description: 柜位开门
     * @Param: [devCode, id, door]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/23
     */
    public static void reboot(String devCode, String id) {
        //ChannelHandlerPool.sendMessageToClient(devCode, new BaseMessage(MessageWork.DOOR_OPEN, id, null));
    }

    /**
     * @Description: 柜位开门
     * @Param: [devCode, id, door]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/23
     */
    public static void doorOpen(String devCode, String id, int door) {
        JSONObject data = new JSONObject();
        data.put("door", door);
        //ChannelHandlerPool.sendMessageToClient(devCode, new BaseMessage(MessageWork.DOOR_OPEN, id, data));
    }

    public static final String DOOR_RECHARGE_RECHARGE = "recharge";
    /**
     * @Description: 柜位通电/断电
     * @Param: [devCode, id, door, recharge]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/23
     */
    public static void doorRecharge(String devCode, String id, int door, boolean recharge) {
        JSONObject data = new JSONObject();
        data.put("door", door);
        data.put(DOOR_RECHARGE_RECHARGE, recharge);
        //ChannelHandlerPool.sendMessageToClient(devCode, new BaseMessage(MessageWork.DOOR_RECHARGE, id, data));
    }
}
