// var app = angular.module('blogIndex', ['ui.bootstrap']);
var app = angular.module('blogIndex', ['ui.bootstrap']);
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
/*        var modalInstance=$model.open({
            templateUrl: "loginUI.html",
            size: 'lg',
            backdrop: 'static',//有背景却不能关闭弹窗
            keyboard: true,//不允许Esc关闭弹窗
//                scope: $scope,
            controller: function ($scope, $modalInstance) {

            }
        });*/
        var modalInstance = $modal.open({
            templateUrl: "loginUI.html",
            size: 'md',
            backdrop: 'static',//有背景却不能关闭弹窗
            keyboard: true,//不允许Esc关闭弹窗
//                scope: $scope,
            controller: function ($scope, $modalInstance) {
                $scope.loginObject;//登陆对象存储

                $scope.cancel = function () {
                    $modalInstance.close('cancle');
                    // $scope.cleanSeach();
                }

            }
        });
    }


});