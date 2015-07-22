/**
 * Created by wei.shen on 2015/7/16.
 */

fanliApp.controller("myTaskCtrl",['$scope','$filter','JobManageService','component',function($scope,$filter,JobManageService,component) {
    $scope.taskGroupOptions= [
        {ID: 1, Text: 'ods'},
        {ID: 2, Text: 'load'},
        {ID: 3, Text: 'dm'},
        {ID: 4, Text: 'dw'},
        {ID: 5, Text: 'dim'},
        {ID: 6, Text:'knn'}
    ];

    initPageParams();

    function initPageParams() {
        //提示信息
        $scope.alert = {
            type: '',
            msg: '',
            isShow: false
        };
        //是否生效过滤项
        $scope.isValid = 'yes';
        //是否隐藏查询结果
        $scope.hideTable = true;
        //页面是否正在加载
        $scope.isLoading = false;
    }

    //关闭提示信息
    $scope.closeAlert = function () {
        $scope.alert.isShow = false;
    };

    $scope.submitQuery = function() {
        showLoading("正在查询中...");

        var tasks = JobManageService.queryTasks({},{
            group: $scope.jobGroup,
            owner: $scope.jobDeveloper,
            id: $scope.jobID
        });
        processQueryResult(tasks);
    };


    function processQueryResult(tasks) {
        tasks.$promise.then(function(data) {
            if(data.isSuccess) {
                $scope.allTaskList = data.results;
                $scope.table = component.getCustomizedTable($scope, $filter);
                $scope.isLoading = false;
                $scope.hideTable = false;
            } else{
                console.log("没有成功");
            }
        });
    };

    //显示加载信息
    function showLoading(msg) {
        $scope.isLoading = true;
        $scope.loadingMsg = msg;
    }

    $scope.editJob = function (index) {
        var job = getJobByIndex(index);
        if (job.type == 2)
            window.open("#/calculateJobConfig/" + job.taskId);
        else
            window.open("#/transferJobConfig/" + job.taskId);
    };

    //根据实际的index获得job
    function getJobByIndex(index) {
        var selectIndex = getSelectedIndex(index);
        return $scope.table.displayedDataList[selectIndex]; //需要设置的任务
    }

    //根据当前页的index获得实际的index
    function getSelectedIndex(index) {
        return  index + ($scope.table.startIndex - 1);
    }

}]);

