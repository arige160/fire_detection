package com.fireDetection.cot.mosquitto;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.fireDetection.cot.repositories.SmokesensorRepository;
import com.fireDetection.cot.repositories.FlamesensorRepository;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;
import com.fireDetection.cot.entities.sensorSmoke;
import com.fireDetection.cot.entities.sensorFlame;
import static java.lang.Float.parseFloat;

@Singleton
@Startup
public class mqtt {
    @Inject
    private SmokesensorRepository SmokesensorRepository;
    private FlamesensorRepository FlamesensorRepository;


    private static final Config config = ConfigProvider.getConfig();
    private final String uri = config.getValue("mqtt.uri", String.class);
    private final String username = config.getValue("mqtt.username", String.class);
    private final String password = config.getValue("mqtt.password", String.class);

    //Fire Detected Msg
    public void sendMessage(MqttClient client, String msg, String topic) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        client.publish(topic,message);
    }

    @PostConstruct
    public void start() {
        try {
            System.out.println("MQTT CONNECTED SUCCESSFULLY");
            MqttClient client = new MqttClient(
                    uri,
                    MqttClient.generateClientId(),
                    new MemoryPersistence());

            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            mqttConnectOptions.setConnectionTimeout(0);
            mqttConnectOptions.setSocketFactory(SSLSocketFactory.getDefault());
            client.connect(mqttConnectOptions);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable error) {
                    System.out.println("MQTT LOST CONNECTION + error");
                }

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) {
                    System.out.println("MESSAGE RECEIVED SUCCESSFULLY");
                    System.out.println(topic);

                    if (topic.equals("kitchen/smoke")) {
                        System.out.println("Smoke detected = " + mqttMessage);
                        sensorSmoke sensorSmoke =new sensorSmoke();
                        sensorSmoke.setSmoke(parseFloat(mqttMessage.toString()));
                    }
                    if(topic.equals("kitchen/infrared")){
                        System.out.println("Infrared detected = " + mqttMessage);
                        sensorFlame sensorFlame =new sensorFlame();
                        sensorFlame.setFlame(parseFloat(mqttMessage.toString()));

                    }
                    if (topic.equals("kitchen/fire")) {
                        System.out.println(mqttMessage);

                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("delivery complete " + iMqttDeliveryToken);
                }
            });
            //Subscribe to kitchen/smoke and kitchen/infrared
            client.subscribe("kitchen/smoke");
            client.subscribe("kitchen/infrared");
            client.subscribe("kitchen/fire");

        }
        catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }
}