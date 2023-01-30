import com.fazecast.jSerialComm.SerialPort;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws MqttException, InterruptedException, IOException {


        System.out.println("Program Started!!!");

        SerialPort sp = SerialPort.getCommPort("COM6"); // device name TODO: must be changed
        sp.setComPortParameters(115200, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0); // block until bytes can be written

        if (sp.openPort()) {
            System.out.println("Port is open :)");
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        while (true) {

            if (sp.bytesAvailable() > 0) {

                byte[] readBuffer = new byte[sp.bytesAvailable()];
                int numRead = sp.readBytes(readBuffer, readBuffer.length);

                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < readBuffer.length; i++)
                    buffer.append((char) readBuffer[i]);

                System.out.println(buffer.toString());
                String distance = buffer.toString().split(";")[0];

            /*
                MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                mqttConnectOptions.setUserName("upssitech");
                mqttConnectOptions.setPassword("2011".toCharArray());
            */

                MqttClient mqttClient = new MqttClient("tcp://localhost:1883", UUID.randomUUID().toString());
                mqttClient.connect();

                System.out.println(distance);
                MqttMessage mqttMessage = new MqttMessage(distance.getBytes());
                mqttClient.publish("/distance", mqttMessage);
                System.out.println("Message envoyé");

                Thread.sleep(1000);
            }
        }
    }
}
