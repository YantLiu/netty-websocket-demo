package com.lyt.messageconsumer;

import com.lyt.dto.BaseMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description: 开舱门
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
public class CabinetSearchConsumer extends BaseMessageConsumer{
    @Override
    public void run(String clientid, BaseMessage baseMessage) {
    }
}
