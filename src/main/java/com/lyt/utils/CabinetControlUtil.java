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
public class CabinetControlUtil {

    private CabinetControlUtil() {
    }

    /**
     * @Description: 舱位开门
     * @Param: [clientid, id, door]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/23
     */
    public static void doorOpen(String clientid, String id, int door) {
        ChannelHandlerPool.sendMessageToClient(clientid, new BaseMessage(MessageWork.DOOR_OPEN, id, door));
    }

    public static final String DOOR_RECHARGE_RECHARGE = "recharge";
    /**
     * @Description: 舱位通电/断电
     * @Param: [clientid, id, door, recharge]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/23
     */
    public static void doorRecharge(String clientid, String id, int door, boolean recharge) {
        JSONObject data = new JSONObject();
        data.put(DOOR_RECHARGE_RECHARGE, recharge);
        ChannelHandlerPool.sendMessageToClient(clientid, new BaseMessage(MessageWork.DOOR_RECHARGE, id, door, data));
    }
}
