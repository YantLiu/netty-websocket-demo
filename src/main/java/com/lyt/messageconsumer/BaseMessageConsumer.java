package com.lyt.messageconsumer;

import com.lyt.dto.BaseMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 业务办理接口
 * @author: lyt
 * @create: 2020-03-23 17:18
 **/
@Slf4j
public abstract class BaseMessageConsumer {
    /**
     * @Description: 业务处理方法
     * @Param: [clientid, baseMessage]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/24
     */
    public abstract void run(String clientid, BaseMessage baseMessage);
}
