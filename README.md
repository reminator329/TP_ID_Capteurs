# TP_ID_Capteurs

## Rapport
Notre raport pour ce projet est disponible à la racine.
Ci-dessous, le lien vers notre vidéo de démonstration. <\br>
https://youtu.be/Ozm47bwWMQE

## Guide d'utilisation

Ouvrir shiftr.io pour lancer MQTT</br>
Ouvrir IntelliJ </br>
Ouvrir les programmes dans des fenêtres différentes de IntelliJ
* (MeteoSender ; DistanceSender) = Envoient les données des capteurs sur des topic MQTT
* (CapteursReveiver) = Serveur qui récupère les données sur les topics MQTT et met à disposition une interface RMI
* (ClientCapteursDiscord) = Client qui utilise les données du serveur pour permettre aux utilisateurs de faire des requêtes depuis Discord grâce à un bot

Lancer les programmes grâce aux Main.java.</br>
Il y a une particularité pour ClientCapteursDiscord qui a besoin du token du bot Discord en argument pour pouvoir l'utiliser, que vous pouvez recuperer dans le serveur discord.

Ouvrir distance/distance.ino sur Arduino
Brancher l'Arduino avec le capteur ultrason, en série avec le pc et téléverser le programme (ne pas ouvrir le moniteur série)
![327752304_1681626788918522_5555186326133574391_n](https://user-images.githubusercontent.com/74671671/216063938-cfdce09e-c0fe-43fb-9807-6f5724e68764.jpg)

Ouvrir Discord et rejoindre le serveur https://discord.gg/p6hqBsCfzr pour utiliser les commandes du bot Discord.</br>
Le token du bot est disponible sur ce serveur Discord sur demande.</br>


Note : 
Pour IntelliJ, il faut mettre une version 16 de gradle JVM pour chaque programme
![image](https://user-images.githubusercontent.com/63303367/213483867-2a500cdb-a4e0-43b4-a2dd-552a13e7045a.png)

## Commandes discord
![image](https://user-images.githubusercontent.com/74671671/215339958-a75debcf-93d8-429a-ba9f-e11211ee53bf.png)
