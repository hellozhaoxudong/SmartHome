var smokestatue = 0;
// smokestatue = smokes;
var shockstatue = 1;
// shockstatue = shocks;
var infraredstatue = 1;
// infraredstatue = infray;
var smoke = document.querySelector(".smoke");
var shock = document.querySelector(".shock");
var infrared = document.querySelector(".infrared")
function smokedecide(){
    if(smokestatue == 0){
        smoke.setAttribute('class','smoke smoke__safe');
    }else{
        smoke.setAttribute('class','smoke smoke__warn');
    }
}
function shockdecide(){
    if(shockstatue == 0){
        shock.setAttribute('class','shock shock__safe');
    }else{
        shock.setAttribute('class','shock shock__warn');
    }
}
function infrareddecide(){
    if(infraredstatue == 0){
        infrared.setAttribute('class','infrared infrared__safe');
    }else{
        infrared.setAttribute('class','infrared infrared__warn');
    }
}
var smoketimer = setInterval(smokedecide,500);
var shocktimer = setInterval(shockdecide,500);
var shocktimer = setInterval(infrareddecide,500);