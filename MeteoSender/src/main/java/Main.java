import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws MqttException, InterruptedException {


        while (true) {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=8739106db67b65d2ee403c932278b5cd&lang=fr&units=metric"; // FAKE REST API pour le test -> users : 10 items r�cup�r�s

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // Ajout d'une ent�te
            request.addHeader("User-Agent", org.apache.http.params.CoreProtocolPNames.USER_AGENT);
            HttpResponse response = null;
            try {
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = null;
            try {
                rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // lecture de la r�ponse
            StringBuffer result = new StringBuffer();
            String line = "";
            while (true) {
                try {
                    if (!((line = rd.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.append(line);
            }

            JSONObject res_json = new JSONObject(result.toString());
            JSONObject main = res_json.getJSONObject("main");
            float temps_min = main.getFloat("temp_min");
            float temps_max = main.getFloat("temp_max");

            // Affichage non structur�
            // System.out.println("Temps à " + ville + " : min = " + temps_min + " ; max = " + temps_max);
            System.out.println("Temps à " + (temps_min + temps_max) / 2);

            /*
                MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                mqttConnectOptions.setUserName("upssitech");
                mqttConnectOptions.setPassword("2011".toCharArray());
            */

            MqttClient mqttClient = new MqttClient("tcp://localhost:1883", UUID.randomUUID().toString());
            mqttClient.connect();

            MqttMessage mqttMessage = new MqttMessage(String.valueOf((temps_min + temps_max) / 2 + Math.random()*3).getBytes());
            mqttClient.publish("/meteo/toulouse/temperature", mqttMessage);
            System.out.println("Message envoyé");

            Thread.sleep(1000);
        }
    }
}
