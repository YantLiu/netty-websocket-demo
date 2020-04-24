package com.lyt.consumer;

import com.lyt.dto.BaseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 报文消费者-开门
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
@Slf4j
public class DoorOpenConsumer extends BaseMessageConsumer{
    @Override
    public void run(String devCode, BaseMessage baseMessage) {
    }
}
