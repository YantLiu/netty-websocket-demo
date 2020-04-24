package com.lyt.messageconsumer;

import com.lyt.dto.BaseMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description: 开舱门
 * @author: lyt
 * @create: 2020-03-24 16:39
 **/
@Component
@Slf4j
public class DoorOpenConsumer extends BaseMessageConsumer{
    @Override
    public void run(String clientid, BaseMessage baseMessage) {
    }
}
