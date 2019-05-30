var app=angular.module('login',[]);
app.controller('loginController',function ($scope,$location,$interval,$http) {
    $scope.login;
    $scope.appUrl=$location.absUrl();
    $scope.currTime;
    $scope.loginUrl;
    $interval(function () {
        currTime=new Date().toLocaleTimeString();//获取当前时间
    },1000);

    //获取登陆地址
    $http.get("/control/json/Url.json").success(function (res) {
        loginUrl=res.logUrl;//获取登陆地址
    });

    //登陆
    $scope.blogLogin=function () {
        $http.post(loginUrl+"login").then(function (successRess) {
        },function (errorRes) {
            alert(errorRes);
        });

    }

    //注册
    $scope.blogRegister=function () {

    }





});