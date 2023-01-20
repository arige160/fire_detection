function login(){
    fetch('https://sub.example.ltn:8443/cot-1.0-SNAPSHOT/api/user/marwa.marwa@supcom.tn/123456789')
        .then((response) => response.json())
        .then((data) => console.log(data));
}