var xmlHttp;
var results;
var arr;
var temperature;
var humid;
var brightness;
var smokes;
var infray;
var shocks;
function datarequest(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=10110";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=datarequestcallback;
    xmlHttp.send(null);
    // results = "1-1-1-1-1-1";
}
function datarequestcallback(){
    if(xmlHttp.readyState==4) {
        if (xmlHttp.status == 200) {
            results = xmlHttp.responseText;//响应成功
        }
    }
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

function recv() {
    datarequest();
    arr = results.split("-");
    temperatures = arr[0];
    humid = arr[1];
    brightness = arr[2];
    smokes = arr[3];
    infray = arr[4];
    shocks = arr[5];
    console.log(temperature);
    console.log(humid);
    console.log(smokes);
    console.log(brightness);
    console.log(infray);
    console.log(shocks);
}
var daterecvtimer = setInterval(recv,5000);