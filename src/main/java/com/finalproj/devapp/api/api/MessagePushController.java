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

    @GetMapping("publishMessage")
    public void testPublishMessage() {
        mqttPushClient.publish("android_subscribe","这是一条测试消息");
    }

    @GetMapping("randomPublishMessage")
    public void randomPublishMessage() {
        mqttPushClient.publish("android_subscribe","这是一条测试消息");
    }


    @GetMapping("publishMessage")
    public void testPublishMessage2(String message){
        mqttPushClient.publish("android_publish",message);
    }

}
