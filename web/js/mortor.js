var airstatue = 0;//空调开关状态
// airstatue =
function airopen(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=110";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=airopencallback;
    xmlHttp.send(null);
}
function airopencallback(){

}
function airclose(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=111";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=airclosecallback;
    xmlHttp.send(null);
}
function airclosecallback(){

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
function changeair(a){
    if(airstatue == 0){
        a.setAttribute('class','air air__animat');
        airopen();
        airstatue=1;
    }else{
        a.setAttribute('class','air');
        airclose();
        airstatue=0;
    }
}