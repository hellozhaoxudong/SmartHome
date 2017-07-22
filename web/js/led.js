var xmlHttp;
var ledonstatue = 1;//led总开关状态
var curtainstatue = 0;//窗帘状态
var led = document.querySelector(".led");
var buttonaddb = document.querySelector(".buttonadd");
var buttonsubb = document.querySelector(".buttonsub");
var curtainleft = document.querySelector(".curtainleft");
var curtainright = document.querySelector(".curtainright");
//获取led状态
ledstatue=false;
function ledinit(){
    led.statue = ledstatue;
    if(ledstatue==true){
        led.setAttribute('class','led ledon');
    }else{
        led.setAttribute('class','led ledoff');
    }
}
function ledchange(a) {
    //    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp = createXMLHttp();
    if (a.statue == true) {
        a.setAttribute('class', 'led ledoff');
        a.statue = false;

        //要给服务器发送数据
        var url = "order" + a.index + "?sign=1";
        xmlHttp.open("GET", url, true);
        //4.绑定回调方法
        xmlHttp.onreadystatechange = ledchangecallback;
        xmlHttp.send(null);
    } else {
        a.setAttribute('class', 'led ledon');
        a.statue = true;
    }
}
function ledchangecallback(){

  }

  function createXMLHttp(){
    //创建xmlHttp对象
    //对于大多数浏览器
    var xmlHttp;
    if(window.XMLHttpRequest){
      xmlHttp=new XMLHttpRequest();
    }//要考虑浏览器的兼容性
    if(window.ActiveXObject){
      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      if(!xmlHttp){
        xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
      }
    }
    return xmlHttp;
}
function ledchangeall(){
    if(ledonstatue==0){
            led.setAttribute('class','led ledoff');
            led.statue=false;
    }else{
            led.setAttribute('class','led ledon');
            led.statue=true;
    }

}
function changeall(a){
    if(ledonstatue==1){
        a.setAttribute('class','buttonalloff');
        a.text = "open all"
        ledonstatue = 0;
        ledchangeall();
    }else{
        a.setAttribute('class','buttonallon');
        a.text = "close all"
        ledonstatue=1;
        ledchangeall();
    }
}
ledinit();

//调节
function ledadd(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=210";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=ledaddcallback;
    xmlHttp.send(null);
}
function ledaddcallback(){

}
function ledsub(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=211";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=ledsubcallback;
    xmlHttp.send(null);
}
function ledsubcallback(){

}


//窗帘
function curtaininit(){
    if(curtainstatue == 0){
        curtainleft.setAttribute('class','curtainleft curtainleft__off');
        curtainright.setAttribute('class','curtainright curtainright__off');
    }else{
        curtainleft.setAttribute('class','curtainleft curtainleft__on');
        curtainright.setAttribute('class','curtainright curtainright__on');
    }
}
curtaininit();

//窗帘开
function curtainopen(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=120";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=curtainopencallback;
    xmlHttp.send(null);
}
function curtainopencallback(){

}

function createXMLHttp(){
    //创建xmlHttp对象
    //对于大多数浏览器
    var xmlHttp;
    if(window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }//要考虑浏览器的兼容性
    if(window.ActiveXObject){
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        if(!xmlHttp){
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    return xmlHttp;
}

//窗帘关
function curtainclose(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=121";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=curtainclosecallback;
    xmlHttp.send(null);
}
function curtainclosecallback(){

}

function createXMLHttp(){
    //创建xmlHttp对象
    //对于大多数浏览器
    var xmlHttp;
    if(window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }//要考虑浏览器的兼容性
    if(window.ActiveXObject){
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        if(!xmlHttp){
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    return xmlHttp;
}
function curtainchange() {
    if(curtainstatue == 1){
        curtainleft.setAttribute('class','curtainleft curtainleft__off');
        curtainright.setAttribute('class','curtainright curtainright__off');
        curtainclose();
        curtainstatue=0;
    }else{
        curtainleft.setAttribute('class','curtainleft curtainleft__on');
        curtainright.setAttribute('class','curtainright curtainright__on');
        curtainopen();
        curtainstatue=1;
    }
}