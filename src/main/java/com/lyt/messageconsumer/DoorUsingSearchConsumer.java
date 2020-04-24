package com.lyt.messageconsumer;

import com.lyt.dto.BaseMessage;
import com.lyt.dto.CabinetDoorStatusDTO;
import com.lyt.enums.MessageWork;
import com.lyt.websocket.ChannelHandlerPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @description: 开舱门
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
@Slf4j
public class DoorUsingSearchConsumer extends BaseMessageConsumer {
    @Override
    public void run(String clientid, BaseMessage baseMessage) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        BaseMessage ret = new BaseMessage(MessageWork.DOORS_USING_SEARCH, id, 0, CabinetDoorStatusDTO.getList());
        ChannelHandlerPool.sendMessageToClient(clientid, ret);
    }
}
