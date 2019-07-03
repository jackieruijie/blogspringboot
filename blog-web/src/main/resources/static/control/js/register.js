var app = angular.module('register', []);
app.controller('registerController', function ($scope, $location, $interval, $http) {
    $scope.appUrl = $location.absUrl();
    $scope.asdUrl;
    $scope.userRegister = new Object();
    $interval(function () {
        currTime = new Date().toLocaleTimeString();//获取当前时间
    }, 1000);

    //获取注册地址
    $http.get("/control/json/Url.json").success(function (res) {
        $scope.asdUrl = res.asd;//获取登陆地址
    });


    //注册
    $scope.blogRegister = function () {
        //获取属性封装成对象
        if ($scope.username != null && $scope.username != "" && $scope.username != undefined) {
            $scope.userRegister.username = $scope.username;//username
        } else {
            alert("请输入用户名！");
            return;
        }

        if ($scope.password != null && $scope.password != "" && $scope.password != undefined) {

            if ($scope.repassword != null && $scope.repassword != "" && $scope.repassword != undefined) {
                if ($scope.repassword === $scope.password) {
                    $scope.userRegister.password = $scope.password;
                } else {
                    alert("密码与确认密码不不相同，请重新输入！");
                    return;
                }
            } else {
                alert("确认密码不能为空");
                return;
            }
        } else {
            alert("请输入密码！");
            return;
        }

        if ($scope.realname != null && $scope.realname != "" && $scope.realname != undefined) {
            $scope.userRegister.realname = $scope.realname;//username
        } else {
            alert("请输入真实姓名！");
            return;
        }

        if ($scope.mobile != null && $scope.mobile != "" && $scope.mobile != undefined) {
            $scope.userRegister.mobile = $scope.mobile;//username
        } else {
            alert("请输入手机号！");
            return;
        }

        if ($scope.email != null && $scope.email != "" && $scope.email != undefined) {
            $scope.userRegister.email = $scope.email;//username
        } else {
            alert("请输入邮箱！");
            return;
        }


        //建立HTTP请求
        var request = {
            method: 'POST',//请求的方式
            url: $scope.asdUrl + "asd/register",//请求的地址
            //请求的头，如果默认可以不写
            /*headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                "Access-Control-Allow-Origin": "*",
                'Accept': 'application/json'
            },*/
            timeout: 1000,//超时时间，还没有测试
            data: $scope.userRegister //message 必须是a=b&c=d的格式
        };

        console.log($scope.userRegister)
        debugger
        //提交表单结果
        $http(request).then(function (successRess) {

        }, function (errorRes) {
            alert(errorRes);
        });



 /*       $.ajax({
            url:  $scope.asdUrl + "asd/register",
            data: { o: $scope.userRegister },
            type: "post",
            xhrFields: {withCredentials: true},
            headers: {Accept: "application/json; charset=utf-8"},
            dataType: "json",
            async: false,
            success: function (res) {
                if (res!=null){
                    alert("注册成功！！！！")
                }
            },
            error: function () {
            }
        });
*/

    }


});