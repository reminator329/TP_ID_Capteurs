/*
* Ultrasonic Sensor HC-SR04 and Arduino Tutorial
*
* by Dejan Nedelkovski,
* www.HowToMechatronics.com
*
*/

// front sensor
int trigPin = 2;    // TRIG pin
int echoPin = 3;    // ECHO pin
// defines variables
int dist;
float duration;
void setup() {
pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT); // Sets the echoPin as an Input
Serial.begin(115200); // Starts the serial communication
}

void distance(){
  
 // Clears the trigPin
digitalWrite(trigPin, LOW);
delayMicroseconds(2);
// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin, HIGH);
// Calculating the distance
dist= duration*0.034/2;
// Prints the distance on the Serial Monitor

Serial.print(dist);
Serial.print(";");
delay(300);

}
void loop() {
distance();
}
