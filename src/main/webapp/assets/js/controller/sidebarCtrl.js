/**
 * Created by wei.shen on 2015/7/16.
 */

fanliApp.controller("sidebarCtrl",function($scope,$location) {
    $scope.isActive = function (route) {
        //if ($location.path().indexOf('/dropdown') == 0) {
        //    return  route === '/dropdown';
        //}
        console.log('route:' + route + ', $location.path:' + $location.path());
        return route === $location.path();
    }
});
