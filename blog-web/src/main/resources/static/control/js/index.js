var app = angular.module('blogIndex', ['ui.bootstrap']);
// var app = angular.module('blogIndex');
app.controller('blogIndexController', function ($scope, $location, $interval, $http,$modal) {
    // $scope.login;
    $scope.appUrl = $location.absUrl();
    $scope.currTime;
    $scope.loginUrl;
    $scope.asdUrl;
    $interval(function () {
        currTime = new Date().toLocaleTimeString();//获取当前时间
    }, 1000);

    //获取登陆地址
    $http.get("control/json/Url.json").success(function (res) {
        loginUrl = res.logUrl;//获取登陆地址
        asdUrl = res.asd;//获取登陆地址
    });
    
    
    //实现登陆模块的弹出
    $scope.loginClick=function () {
        var modalInstance = $modal.open({
            templateUrl: "loginUI.html",
            size: 'md',
            backdrop: 'static',//有背景却不能关闭弹窗
            keyboard: true,//不允许Esc关闭弹窗
            controller: function ($scope, $modalInstance) {
                $scope.loginObject=new Object();//登陆对象存储

                /**
                 * 登陆实现
                 */
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
                            alert("登陆成功！")
                        }else {
                            alert("登陆失败！"+response.data)
                        }

                    });
                }


                $scope.cancel = function () {
                    $modalInstance.close('cancle');
                    // $scope.cleanSeach();
                }

            }
        });
    }


});