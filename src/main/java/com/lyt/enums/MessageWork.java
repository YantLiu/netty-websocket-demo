package com.lyt.enums;

import com.lyt.consumer.*;

import java.util.Arrays;

/**
 * @author liuyanting
 * @description 报文类型
 * @date: 2019/9/4
 */
public enum MessageWork {
    //控制
    DEV_REBOOT("DEV_REBOOT", MessageType.CONTROL, "柜机重启", CommonControlConsumer.class),
    DOOR_OPEN("DOOR_OPEN", MessageType.CONTROL, "舱位开门", DoorOpenConsumer.class),
    DOOR_RECHARGE("DOOR_RECHARGE", MessageType.CONTROL, "舱位通电/断电", CommonControlConsumer.class),

    //查询
    DOORS_USING_SEARCH("DOORS_USING_SEARCH", MessageType.SEARCH, "查询舱位占用情况", DoorUsingSearchConsumer.class),
    ;

    private final String work;
    private final MessageType type;
    private final String message;
    private Class<? extends BaseMessageConsumer> clz;

    MessageWork(String work, MessageType type, String message, Class<? extends BaseMessageConsumer> clz) {
        this.work = work;
        this.type = type;
        this.message = message;
        this.clz = clz;
    }

    public String getWork() {
        return work;
    }

    public MessageType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Class<? extends BaseMessageConsumer> getClz() {
        return clz;
    }

    public boolean isEqual(int work, String type) {
        return this.work.equals(work) && this.type.isEqual(type);
    }

    public static MessageWork get(String work) {
        return Arrays.asList(MessageWork.values()).stream()
                .filter(a -> a.work.equals(work))
                .findFirst().orElse(null);
    }

    public static MessageWork getByName(String name) {
        return Arrays.asList(MessageWork.values()).stream()
                .filter(a -> a.name().equals(name))
                .findFirst().orElse(null);
    }

    /**
     * @Description: 消息类型
     * @Param:
     * @return:
     * @Author: lyt
     * @Date: 2020/4/24
     */
    public enum MessageType {
        CONTROL,//控制
        SEARCH,//查询
        REPORT,//上报
        WARN,//告警
        ;

        public boolean isEqual(String name) {
            return this.name().equals(name);
        }

        public static MessageType getByName(String name) {
            return Arrays.asList(MessageType.values()).stream()
                    .filter(a -> a.name().equals(name))
                    .findFirst().orElse(null);
        }
    }
}
