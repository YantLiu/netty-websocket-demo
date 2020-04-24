package com.lyt.consumer;

import com.lyt.dto.BaseMessage;
import org.springframework.stereotype.Component;

/**
 * @description: 报文消费者-通用控制
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
public class CommonControlConsumer extends BaseMessageConsumer {
    @Override
    public void run(String devCode, BaseMessage baseMessage) {
    }
}
