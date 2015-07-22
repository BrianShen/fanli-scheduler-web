/**
 * Created by wei.shen on 2015/7/11.
 */
var fanliApp = angular.module("fanliApp",[
    'ngRoute',
    'common.service',
    'job_manage.service',
    'component.service',
    'ui.bootstrap',
    'constant.service']);

fanliApp.config(function($routeProvider){
    $routeProvider.
        when('/',{
            templateUrl : 'page1.html',
            controller:'page1Ctrl'
        }).
        when('/mytask',{
            templateUrl : 'mytask.html',
            controller:'myTaskCtrl'
        }).
        when('/taskAdd',{
            templateUrl : 'CalculateTaskConfig.html',
            controller : 'taskAddCtrl'
        }).
        when('/transport',{
            templateUrl : 'transport.html',
            //controller : 'page4Ctrl'
        });

});