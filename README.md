# TP_ID_Capteurs
## Guide d'utilisation

Ouvrir IntelliJ
Ouvrir les programmes dans des fenêtres différentes de IntelliJ
* (MeteoSender ; DistanceSender) = Envoient les données des capteurs sur des topic MQTT
* (CapteursReveiver) = Serveur qui récupère les données sur les topics MQTT et met à disposition une interface RMI
* (ClientCapteursDiscord) = Client qui utilise les données du serveur pour permettre aux utilisateurs de faire des requêtes depuis Discord grâce à un bot

Ouvrir distance/distance.ino sur Arduino
Brancher l'Arduino avec le capteur ultrason, en série avec le pc
et téléverser le programme (ne pas ouvrir le moniteur série) 

Ouvrir Discord et rejoindre le serveur https://discord.gg/p6hqBsCfzr pour utiliser les commandes du bot Discord
Ouvrir shiftr.io pour lancer MQTT

Note : 
Pour IntelliJ, il faut mettre une version 16 de gradle JVM pour chaque programme
![image](https://user-images.githubusercontent.com/63303367/213483867-2a500cdb-a4e0-43b4-a2dd-552a13e7045a.png)

