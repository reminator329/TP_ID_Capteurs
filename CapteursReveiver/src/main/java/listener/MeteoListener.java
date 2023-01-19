package listener;

import data.MeteoData;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class MeteoListener implements MqttCallback, Listener {

    private final MeteoData data;

    public MeteoListener() {
        this.data = new MeteoData();
        System.out.println("RUN METEO");
        try {
            runClient();
        } catch (MqttException ignored) {

        }
    }

    @Override
    public Map<String, String> getDatas() {
        return data.getDatas();
    }

    public void runClient() throws MqttException {
        /*
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName("upssitech");
        mqttConnectOptions.setPassword("2011".toCharArray());

         */

        MqttClient mqttClient = new MqttClient("tcp://localhost:1883", UUID.randomUUID().toString());
        mqttClient.connect();
        mqttClient.setCallback(this);
        MqttTopic topic = mqttClient.getTopic("/meteo/toulouse/temperature");

        mqttClient.subscribe(String.valueOf(topic));

    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        data.setData(MeteoData.idTemperature, new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
