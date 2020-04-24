package com.lyt.controller;

import com.lyt.utils.DevControlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "柜机控制")
public class DoorController {
    @GetMapping("/reboot")
    @ApiOperation("重启")
    public void reboot(@ApiParam(value = "设备号", required = true) @RequestParam("userCode") String devCode,
                       @ApiParam(value = "账号", required = true) @RequestParam("userCode") String id) {
        DevControlUtil.reboot(devCode, id);
    }

    @GetMapping("/doorOpen")
    @ApiOperation("开门")
    public void doorOpen(String devCode, String id, int door) {
        DevControlUtil.doorOpen(devCode, id, door);
    }

    @GetMapping("/doorRecharge")
    @ApiOperation("充电")
    public void doorRecharge(String devCode, String id, int door, boolean recharge) {
        DevControlUtil.doorRecharge(devCode, id, door, recharge);
    }
}
