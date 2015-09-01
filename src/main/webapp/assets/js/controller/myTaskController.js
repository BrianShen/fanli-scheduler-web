/**
 * Created by wei.shen on 2015/7/16.
 */

fanliApp.controller("myTaskCtrl",['$scope','$filter','$modal','JobManageService','component',function($scope,$filter,$modal,JobManageService,component) {
    $scope.taskGroupOptions= [
        {ID: 1, Text: 'ods'},
        {ID: 2, Text: 'load'},
        {ID: 3, Text: 'dm'},
        {ID: 4, Text: 'dw'},
        {ID: 5, Text: 'rpt'},
        {ID: 6, Text: 'dim'}
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
        $scope.isValid = true;
        //是否隐藏查询结果
        $scope.hideTable = true;
        //页面是否正在加载
        $scope.isLoading = false;

    }

    //关闭提示信息
    $scope.closeAlert = function () {
        $scope.alert.isShow = false;
    };

    $scope.showAlert = function(a,b) {
        $scope.alert.isShow = true;
        $scope.alert.type = a;
        $scope.alert.msg = b;
    }

    $scope.submitQuery = function() {
        showLoading("正在查询中...");
        $scope.closeAlert();

        var tasks = JobManageService.queryTasks({},{
            group: $scope.jobGroup,
            owner: $scope.jobDeveloper,
            id: $scope.jobID,
            isValid:$scope.isValid==true?1:0
        });
        processQueryResult(tasks);
    };

    $scope.handleValid = function() {
        console.log($scope.isValid);
        $scope.submitQuery();
    }






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

    function setLoading(a,b) {
        $scope.isLoading = a;
        $scope.loadingMsg = b;
    }

    /**
     * *******************************************************任务操作***************************************************************
     * @param index
     */
    //编辑任务
    $scope.editJob = function (index) {
        var job = getJobByIndex(index);
        if (job.type == 2)
            window.open("#/calculate_task_edit/" + job.taskId);
        else
            window.open("#/transfer_task_edit/" + job.taskId);
    };

    //预跑任务
    $scope.preRunJob = function (index) {
        var job = getJobByIndex(index);
        $scope.msg = {
            headerText: '设置预跑任务:' + job.taskId + ' 时间',
            actionButtonStyle: 'btn-primary',
            jobExecCycle: job.cycle
        };
        var modalInstance = $modal.open({
            templateUrl: '/assets/pages/dialog/preRunDialog.html',
            controller: PreRunTaskCtrl,
            resolve: {
                msg: function () {
                    return $scope.msg;
                }
            }
        });
        modalInstance.result.then(function (time) {
            var result = JobManageService.preRunTask({
                'startTime': time.startDate,
                'endTime': time.endDate,
                'taskId': job.taskId
            });
            $scope.isLoading = true;
            $scope.closeAlert();
            process(result);
        }, function () {
        });
    };
    function process(result) {
        result.$promise.then(function(data) {
            if(data.isSuccess) {
                if(data.result < 10) {
                    var ids = data.results;
                    var idStr = '';
                    for(var i = 0;i < ids.length;i ++) {
                        idStr  = idStr + ids[i] + ',';
                    }
                    idStr = idStr.substring(0,idStr.length - 1);
                    $scope.showAlert('alert-success','成功预跑' + data.result + '个任务,taskid为：' + idStr);
                } else {
                    $scope.showAlert('alert-success','成功预跑' + data.result + '个任务');
                }
                setLoading(false,'');

            } else {
                $scope.showAlert('alert-danger','生成实例失败，如有必要请联系开发人员！');
                setLoading(false,'');
            }
        },function(){$scope.showAlert('alert-danger','生成实例失败，如有必要请联系开发人员！');
            setLoading(false,'');})
    }

    //失效任务
    $scope.invalidJob = function (index) {
        var job = getJobByIndex(index);
        $scope.message = {
            headerText: '警告: 以下任务将会受到影响，请确认是否失效任务' + job.taskId + '?',
            actionButtonStyle: 'btn-danger',
            taskId: job.taskId
        };
        var modalInstance = $modal.open({
            templateUrl: '/assets/pages/dialog/influencedTasksDialog.html',
            controller: InfluencedTaskCtrl,
            windowClass: 'taskQueryDialog',
            resolve: {
                msg: function () {
                    return $scope.message;
                }
            }
        });
        modalInstance.result.then(function (data) {
            var result = JobManageService.invalidTask({
                'taskId': job.taskId,
                'type': job.type == 2 ? 'calculate' : 'transfer'
            });
            $scope.isLoading = true;
            $scope.closeAlert();
            processValid(result, 0);
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };


    // * *******************************************************任务操作***************************************************************


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

