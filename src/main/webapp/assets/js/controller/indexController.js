/**
 * Created by wei.shen on 2015/7/11.
 */

fanliApp.controller("indexCtrl",function($scope) {
    $scope.tabs = [
        { title:'page1',page:'/assert'},
        { title:'page2'},
        { title:'page3'},
        { title:'page4'}
    ];
});