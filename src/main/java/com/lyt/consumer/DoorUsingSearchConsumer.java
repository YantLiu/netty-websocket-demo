package com.lyt.consumer;

import com.lyt.dto.BaseMessage;
import com.lyt.dto.DevDoorStatusDTO;
import com.lyt.enums.MessageWork;
import com.lyt.websocket.ChannelHandlerPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description: 报文消费者-柜位状态查询
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
@Slf4j
public class DoorUsingSearchConsumer extends BaseMessageConsumer {
    @Override
    public void run(String devCode, BaseMessage baseMessage) {
//        String id = UUID.randomUUID().toString().replaceAll("-", "");
//        BaseMessage ret = new BaseMessage(MessageWork.DOORS_USING_SEARCH, id, DevDoorStatusDTO.getList());
//        ChannelHandlerPool.sendMessageToClient(devCode, ret);
    }
}
