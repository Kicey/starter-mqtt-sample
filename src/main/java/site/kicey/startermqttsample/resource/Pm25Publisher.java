package site.kicey.startermqttsample.resource;

import org.springframework.integration.annotation.Gateway;
import site.kicey.springbootstartermqtt.MqttPublisher;
import site.kicey.springbootstartermqtt.MqttTopic;

/**
 * @author Kicey
 * 
 * pay attention that what we defined is an interface spring GatewayProxyFactoryBean will create a proxy bean for us.
 */
@MqttPublisher(clientId = "starter-server")
public interface Pm25Publisher {
    
    @MqttTopic(topics = "spring-out", requestChannel = "pm25PublisherSendChannel")
    @Gateway(requestChannel = "pm25PublisherSendChannel")
    // keep the channel name in the two annotations the same, util @Gateway support @AliasFor
    public void send(String str);
}
