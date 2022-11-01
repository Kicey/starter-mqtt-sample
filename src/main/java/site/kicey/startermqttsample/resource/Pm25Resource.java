package site.kicey.startermqttsample.resource;

import org.springframework.messaging.Message;
import site.kicey.springbootstartermqtt.MqttSubscriber;
import site.kicey.springbootstartermqtt.MqttTopic;

/**
 * @author Kicey
 */
@MqttSubscriber(clientId = "starter-server")
public class Pm25Resource {
    
    // remember, every method should have a unique inputChannel name
    @MqttTopic(topics = "/pm25Resource", inputChannel = "pm25ResourceInputChannel")
    public void commit(Message<?> message){
        System.out.println("Get the message!");
        System.out.println(message.getPayload());
    }
    
    @MqttTopic(topics = "/pm25Resource2", inputChannel = "pm25ResourceInputChannel2")
    public void commit2(Message<?> message){
        System.out.println("Another type message!");
    }
}
