/**
 * Created by wei.shen on 2015/7/11.
 */
var fanliApp = angular.module("fanliApp",[
    'ngRoute',
    'ngResource',
    'common.service',
    'job_manage.service',
    'component.service',
    'ui.bootstrap',
    'constant.service',
    'fanli.filter',
    'table.service'
]);

fanliApp.config(function($routeProvider){
    $routeProvider.
        when('/',{
            templateUrl : 'monitor.html',
            controller:'MonitorCtrl'
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
            controller : 'transportTaskAddCtrl'
        })
        .when('/job_log/:instanceId',{
            templateUrl : 'job_log.html',
            controller:'jobLogCtrl'
        }).when('/calculate_task_edit/:taskid',{
            templateUrl:'calculate_task_edit.html',
            controller:'taskEditCtrl'
        }).when('/transfer_task_edit/:taskid',{
            templateUrl:'transport_edit.html',
            controller:'transferEditCtrl'
        });

});