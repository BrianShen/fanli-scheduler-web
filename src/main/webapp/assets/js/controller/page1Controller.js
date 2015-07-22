/**
 * Created by wei.shen on 2015/7/12.
 */

fanliApp.controller('page1Ctrl',function($scope,$modal) {
    $scope.open = function () {

        var modalInstance = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'test.html',
            controller: 'ModalInstanceCtrl',
            windowClass: 'taskQueryDialog'

        });

    };


});

fanliApp.controller("ModalInstanceCtrl",function($scope,$modalInstance) {
    $scope.shuru = '123';
    $scope.show = function(){
        console.log($scope.shuru);
    }
});