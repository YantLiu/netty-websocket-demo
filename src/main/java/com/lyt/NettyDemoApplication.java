package com.lyt;

import com.lyt.websocket.NettyWebsocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyDemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NettyDemoApplication.class, args);
        //启动NettyWebsocketServer
        new NettyWebsocketServer().run(16000);
    }

}
