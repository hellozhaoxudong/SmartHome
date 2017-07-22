function test() {
    return 1;
}
var a;
var timer = setInterval(a = test(),1000);
alert(a);