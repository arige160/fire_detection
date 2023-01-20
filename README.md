<h1 align="center">
  <br>
Fire Detection Applicaiot
</h1>
<h3 align="center">
Cloud of Things Project
</h3>
<div align="center">
  <h4>
    <a href="#Context">Context</a> |
    <a href="#Installation-Guide">Installation Guide</a> |
    <a href="#Technologies">Technologies</a> |
   <a href="#Screenshots">Screenshots</a> |
    <a href="#Further-Readings">Further Readings</a> |
    <a href="#Contribution">Contribution</a>
  </h4>
</div>
<br>

## Context
Today, the security of spaces (closed and open) and of individuals has become unavoid-
able, as they are exposed to many dangers. Among these dangers, we can mention the
fires, which cause material losses and damage the human life.
To minimize these losses of this danger, we must be able to detect the fires at an early
stage using the technologies of the Internet of Things, taking into account several factors
that can facilitate the detection phase.
In the case of a kitchen, we can see that gas and flame are important factors that can
identify the fire.
We propose a system which must answer the following use cases:
- Smoke detection: this task requires a smoke gas sensor with a well determined threshold.
- flame detection: this task requires a flame sensor with a well determined threshold.
- Alert in case of fire detection (either by gas or by flame) by a buzzer and a notification on
the progressive web application (PWA).
## Live Demo
As for now, you can test the application by downloading the `.apk` file from the releases.
Also, you can test the application directly from your browser at https://homeautomationcot.me/ or https://api.homeautomationcot.me/. (note that some features are mobile-specific, and not all can be tested via the web version.)
## Installation Guide
We made sure that the architecture of the repository was well organized for users to test the project locally or build on it.
If you want to run the application locally, please follow the following steps: 
- Clone the repo: `git clone https://github.com/GhaziXX/home-automation-using-cot.git`
- Install node RED on your Raspberry pi and then load the content of [flows.json](./iot/flows.JSON) into a new flow. Feel free to change the sensors and actuators pins, the MQTT broker, and the API link for getting a list of installed sensors.
- Move into the [api](./api/) directory and run `npm install` to install the required dependencies. (Please ensure that you have **node.js** installed in your machine).
- Open [env.config.js](./api/main/env.config.js) and set your settings (certificate path, MQTT broker settings, and you Mongodb link). 
- Run `npm start` to start the server locally.
- Move into the [frontend](./frontend/) directory - make sure that you have flutter installed on your machine - run `flutter pub get` Then select whether you want to run on your mobile, emulator, or web and run `flutter pub run`.
## Technologies
Multiple technologies, plugins, packages and hardware sensors and actuators were used while developing this project, the technologies are diverse and used for backend and frontend development.
- Backend:
  - Wildfly : wildfly-preview-27.0.0.Final
- IoT:
  - Node RED
- Frontend
  -Vanilla JS
- Server
  - Mosquitto Broker
- Hardware
  - Raspberry Pi 3 - Model B - ARMv8 With 1G RAM
  - Smoke Gas sensor MQ2
  - IR sensor
  - LED
  -Buzzer
## Screenshots
![Alt text](./imgs/application.png)
## Further Readings
You can find more information and take a look at the architecture design with the following documents:
-  [User Guide](docs/scope%20statement.pdf)
-  [Design Document](docs/Design%20Document.pdf)
## Contribution
This project was developed by Marwa Sadkaoui and Arij Flihi as part of a Cloud of Things project at Sup'Com.


