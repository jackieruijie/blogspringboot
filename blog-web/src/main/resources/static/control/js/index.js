var app = angular.module('blogIndex', []);
app.controller('blogIndexController', function ($scope, $location, $interval, $http,$model) {
    $scope.login;
    $scope.appUrl = $location.absUrl();
    $scope.currTime;
    $scope.loginUrl;
    $interval(function () {
        currTime = new Date().toLocaleTimeString();//获取当前时间
    }, 1000);

    //获取登陆地址
    $http.get("control/json/Url.json").success(function (res) {
        loginUrl = res.logUrl;//获取登陆地址
    });

    //打开登陆弹窗
    $scope.loginClick = function () {
        debugger
        var modalInstance=$model.open({
            templateUrl : 'myModalContent.html',
            controller : ModalInstanceCtrl,
            resolve : {
                items : function() {
                    return $scope.items;
                }
            }
        });
        modalInstance.opened.then(function() {// 模态窗口打开之后执行的函数
            console.log('modal is opened');
        });
        modalInstance.result.then(function(result) {
            console.log(result);
        }, function(reason) {
            console.log(reason);// 点击空白区域，总会输出backdrop
            // click，点击取消，则会暑促cancel
            $log.info('Modal dismissed at: ' + new Date());
        });

        var ModalInstanceCtrl = function($scope, $modalInstance, items) {
            $scope.items = items;
            $scope.selected = {
                item : $scope.items[0]
            };
            $scope.ok = function() {
                $modalInstance.close($scope.selected);
            };
            $scope.cancel = function() {
                $modalInstance.dismiss('cancel');
            };
        };


    }

    //注册
    $scope.blogRegister = function () {

    }


});