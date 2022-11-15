package com.finalproj.devapp.api.api;

import cn.hutool.json.JSONUtil;
import com.finalproj.devapp.api.model.SendMessageObject;
import com.finalproj.devapp.api.mqtt.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messagePush")
public class MessagePushController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping("publishMessage")
    public void testPublishMessage(@RequestParam String title, @RequestParam String content, @RequestParam String param, @RequestParam String state) {
        boolean currentState = false;
        if(state.equals("1")){
            currentState = true;
        }
        SendMessageObject  s = new SendMessageObject(title, content, param, currentState);
        mqttPushClient.publish("android_subscribe", JSONUtil.toJsonStr(s));
    }

    @GetMapping("randomPublishMessage")
    public void randomPublishMessage() {
        mqttPushClient.publish("android_subscribe","这是一条测试消息");
    }


    @GetMapping("publishMessage2")
    public void testPublishMessage2(String message){
        mqttPushClient.publish("android_publish",message);
    }

}
