var app = angular.module('blogIndex', []);
app.controller('blogIndexController', function ($scope, $location, $interval, $http) {
    $scope.login;
    $scope.appUrl = $location.absUrl();
    $scope.currTime;
    $scope.loginUrl;
    $interval(function () {
        currTime = new Date().toLocaleTimeString();//获取当前时间
    }, 1000);

    //获取登陆地址
    $http.get("/control/json/Url.json").success(function (res) {
        loginUrl = res.logUrl;//获取登陆地址
    });

    //登陆
    $scope.blogLogin = function () {
       /* $http({
                method: 'get',
                url: loginUrl + 'login',
                headers: {Accept: "application/json; charset=utf-8"},
            }
        ).then(
            function (successRes) {
                alert(successRes)
            }, function (errorRes) {
                debugger
                alert(errorRes);
            }
        );*/
       $.ajax({
               url:loginUrl+'login',

           }

       )

    }

    //注册
    $scope.blogRegister = function () {

    }


});