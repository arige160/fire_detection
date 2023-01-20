import VanillaRouter from './router.js';
import {startWatch, stopWatch} from './util.js';

let currentContext = {};
const router = new VanillaRouter({type: history, routes: {
        '/': 'main',
        '/dashboard': 'dashboard',
        '/login': 'login',
    }}).listen().on('route', async e => {
    fetch('pages/' + e.detail.route + '.html')
        .then(response => response.text())
        .then(htmlData => {
            document.getElementById('main-content').innerHTML = htmlData;

            if (e.detail.route === 'dashboard' && navigator.geolocation) {
                navigator.geolocation.getCurrentPosition( // success callback, error callback, options
                    (position) => {
                        //Latitude
                        if (position.coords.altitude == null) {
                            document.getElementById("latitude").innerHTML = "Latitude not available";
                        } else {
                            document.getElementById("latitude").innerHTML = "Latitude: "+position.coords.altitude;
                        }
                        //Altitude
                        if (position.coords.altitude == null) {
                            document.getElementById("altitude").innerHTML = "Altitude not available";
                        } else {
                            document.getElementById("altitude").innerHTML = "Altitude: "+position.coords.altitude;
                        }
                        //Longitude
                        if (position.coords.longitude == null) {
                            document.getElementById("longitude").innerHTML = "Longitude not available";
                        } else {
                            document.getElementById("longitude").innerHTML = "Longitude:"+position.coords.longitude;
                        }
                        //Send a sms to the emergency that contains longitude,latitude and altiltude



                    },
                    (err) => {
                        switch (err.code) {
                            case err.PERMISSION_DENIED:
                                console.log("User denied access to geolocation");
                                break;
                            case err.POSITION_UNAVAILABLE:
                                console.log("Location information is unavailable");
                                break;
                            case err.TIMEOUT:
                                console.log("Request to get user location timed out");
                                break;
                            case err.UNKNOWN_ERROR:
                                console.log("An unknown error occured.");
                                break;
                        }

                    },
                    {
                        enableHightAccuracy: false,
                        timeout: 15000,
                        maximumAge: 0
                    }
                );
            }
        })
        .catch(e => console.log(e));
});

if("serviceWorker" in navigator){
    window.addEventListener("load", () => {
        navigator.serviceWorker.register("/scripts/serviceWorker.js")
            .then(res => console.log("serviceWorker registered"))
            .catch(err => console.log("serviceWorker not registered", err));
    });
}
