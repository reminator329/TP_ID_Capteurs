package listener;

import data.DistanceData;
import data.MeteoData;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Map;
import java.util.UUID;

public class DistanceListener implements MqttCallback, Listener {

    private final DistanceData data;

    public DistanceListener() {
        this.data = new DistanceData();
        System.out.println("RUN");
        try {
            runClient();
        } catch (MqttException ignored) {

        }
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
        MqttTopic topic = mqttClient.getTopic("/distance");
        mqttClient.subscribe(String.valueOf(topic));

    }
    @Override
    public Map<String, String> getDatas() {
        return data.getDatas();
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        data.setData(DistanceData.idDistance, new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
