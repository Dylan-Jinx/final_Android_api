package com.finalproj.devapp.api.api;

import com.finalproj.devapp.api.mqtt.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messagePush")
public class MessagePushController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping("testPublishMessage1")
    public void testPublishMessage() {
        mqttPushClient.publish("android_subscribe","这是一条测试消息");
    }

    @GetMapping("testPublishMessage2")
    public void testPublishMessage2(String message){
        mqttPushClient.publish("android_publish",message);
    }

}
