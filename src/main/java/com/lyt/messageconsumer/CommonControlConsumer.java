package com.lyt.messageconsumer;

import com.lyt.dto.BaseMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 通用控制
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
public class CommonControlConsumer extends BaseMessageConsumer {
    @Override
    public void run(String cabinetCode, BaseMessage baseMessage) {
    }
}
