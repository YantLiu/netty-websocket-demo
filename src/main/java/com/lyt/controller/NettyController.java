package com.lyt.controller;

import com.lyt.dto.BaseMessage;
import com.lyt.enums.MessageWork;
import com.lyt.messageconsumer.BaseMessageConsumer;
import com.lyt.messageconsumer.DoorOpenConsumer;
import com.lyt.utils.CabinetControlUtil;
import com.lyt.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NettyController {
    @GetMapping("/doorOpen")
    public void doorOpen(String clientid, String id, int door) {
        CabinetControlUtil.doorOpen(clientid, id, door);
    }

    @GetMapping("/doorRecharge")
    public void doorRecharge(String clientid, String id, int door, boolean recharge) {
        CabinetControlUtil.doorRecharge(clientid, id, door, recharge);
    }
}
