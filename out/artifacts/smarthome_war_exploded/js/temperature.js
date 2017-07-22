var temperature = document.querySelector(".temp");
var humidity = document.querySelector(".humi");
var bright = document.querySelector(".bright");
function setenvironmentdate(){
    var temp = temperatures+"℃";
    var humi = humid+"％";
    var bri = brightness+"lux";
    temperature.innerHTML="<b>" + temp + "</b>";
    humidity.innerHTML="<b>" + humi + "</b>";
    bright.innerHTML="<b>" + bri + "</b>";
}
var datetimer = setInterval(setenvironmentdate,2000);
