var app=angular.module('login',[]);
app.controller('loginController',function ($scope,$location,$interval,$http) {
    // $scope.login;
    $scope.appUrl=$location.absUrl();
    $scope.currTime;
    $scope.loginUrl;
    $scope.asdUrl;
    $scope.loginObject=new Object();
    $interval(function () {
        currTime=new Date().toLocaleTimeString();//获取当前时间
    },1000);

    //获取登陆地址
    $http.get("/control/json/Url.json").success(function (res) {
        loginUrl=res.logUrl;//获取登陆地址
        asdUrl = res.asd;//获取登陆地址
    });

    //登陆
    $scope.blogLogin=function () {
        //获取属性封装成对象
        if ($scope.username != null && $scope.username != "" && $scope.username != undefined) {
            $scope.loginObject.username = $scope.username;//username
        } else {
            alert("请输入用户名！");
            return;
        }

        if ($scope.password != null && $scope.password != "" && $scope.password != undefined) {
            $scope.loginObject.password = $scope.password;//username
        } else {
            alert("请输入密码！");
            return;
        }

        //建立HTTP请求
        var request = {
            method: 'POST',
            url:  asdUrl + "asd/login",//请求的地址
            params:{
                object: $scope.loginObject
            }

        };

        //提交表单结果
        debugger
        $http(request).success(function (response) {
            debugger
            if (response.code==200 && response.data==='登陆成功'){
                //跳转到登录页
                /*$http.get($scope.loginUrl+'login').success(function (res) {
                });*/
                alert("登陆成功！")
                // location.href='views/login.html';
            }else {
                alert("登陆失败！")
            }

        });


    }




});