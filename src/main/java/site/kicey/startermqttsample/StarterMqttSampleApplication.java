package site.kicey.startermqttsample;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import site.kicey.startermqttsample.resource.Pm25Publisher;
import site.kicey.startermqttsample.resource.Pm25Resource;

@SpringBootApplication(scanBasePackageClasses = {Pm25Resource.class})
public class StarterMqttSampleApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StarterMqttSampleApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner(ConfigurableBeanFactory beanFactory, Pm25Publisher pm25Publisher){
        return (args) -> {
            System.out.println("Hello Mqtt!");
            
            // we use a thread to send mqtt message circularly
            Thread sender = new Thread(() -> {
                int cur = 0;
                while(true){
                    beanFactory.getBean(Pm25Publisher.class);
                    pm25Publisher.send("what we count now is " + cur);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ++cur;
                }
            });
            sender.start();
        };
    }
}
