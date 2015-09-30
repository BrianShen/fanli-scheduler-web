/**
 * Created by wei.shen on 2015/8/4.
 */

fanliApp.controller('taskEditCtrl',function($scope,$resource,$modal,$routeParams,JobManageService,ConstantService){
    $scope.taskid = $routeParams.taskid;
    $scope.Loading = true;
    $scope.LoadingMsg = '加载中...';
    $scope.taskGroupOptions= [
        {ID: 1, Text: 'ods'},
        {ID: 2, Text: 'load'},
        {ID: 3, Text: 'dm'},
        {ID: 4, Text: 'dw'},
        {ID: 5, Text: 'rpt'},
        {ID: 6, Text: 'dim'}
    ];
    $scope.cycleOptions  = {
        mi: '分', H: '时', D: '日', W: '周', M: '月', Y: '年'
    };
    $scope.priorityOptions = [
        {ID: 1, Text: '高'},
        {ID: 2, Text: '中'},
        {ID: 3, Text: '低'}
    ];
    $scope.ifRecallOptions =[
        {ID: 1, Text: '是'},
        {ID: 0, Text: '否'}
    ];
    $scope.recallLimitOptions = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    $scope.recallIntervalOptions = [
        {ID: 1, Text: '1分钟'},
        {ID: 2, Text: '2分钟'},
        {ID: 3, Text: '3分钟'},
        {ID: 4, Text: '4分钟'},
        {ID: 5, Text: '5分钟'},
        {ID: 6, Text: '6分钟'},
        {ID: 7, Text: '7分钟'},
        {ID: 8, Text: '8分钟'},
        {ID: 9, Text: '9分钟'},
        {ID: 10, Text: '10分钟'}
    ];
    $scope.offsetOptions = ['D0', 'D1', 'D2', 'D3', 'D4', 'D5', 'D6', 'D7', 'D8', 'D9', 'D10',
        'M0', 'M1', 'M2', 'M3', 'M4', 'M5', 'M6', 'M7', 'M8', 'M9', 'M10'];
    $scope.timeoutOptions = [
        {ID: 30, Text: '30分钟'},
        {ID: 60, Text: '1小时'},
        {ID: 90, Text: '1.5小时'},
        {ID: 120, Text: '2小时'},
        {ID: 150, Text: '2.5小时'},
        {ID: 180, Text: '3小时'},
        {ID: 210, Text: '3.5小时'},
        {ID: 240, Text: '4小时'}
    ];

    var getTaskById = function(id) {
        var task = JobManageService.queryTaskById({
            taskid:id
        });
        task.$promise.then(function(data) {
            if(data.isSuccess) {
                setValues(data.result);
                $scope.Loading = false;
            }
        },function() {

        })
    }

    var setValues = function(data) {
        $scope.conf_taskName = data.taskName;
        $scope.conf_developer = data.owner;
        $scope.conf_frequency = data.freq;
        $scope.conf_taskGroup = data.taskGroupId;
        $scope.conf_cycle = data.cycle;
        $scope.conf_priority = data.priority;
        $scope.conf_ifRecall = data.ifRecall;
        $scope.conf_recallLimit = data.recallLimit;
        $scope.conf_recallInterval = data.recallInterval;
        $scope.conf_successCode = data.successCode;
        $scope.conf_waitCode = data.waitCode;
        $scope.conf_offset = data.offset;
        $scope.conf_timeout = data.timeout;
        $scope.conf_para1 = data.command;
    }

    var getPreTasks = function(id) {
        var pres = JobManageService.queryPreTaskById({
           taskid: id
        });
        pres.$promise.then(function(data) {

            getTaskById($routeParams.taskid);
            $scope.dependenceTasks = data.results;
        },function() {

        })
    }


    getPreTasks($routeParams.taskid);

    $scope.updateTaskCfg = function() {
        $scope.showSaveSucess = false;
        $scope.saveSuccessMsg = '';
        var updateResult = JobManageService.updateTask({},{
            taskId:$routeParams.taskid,
            taskName:$scope.conf_taskName,
            owner:$scope.conf_developer,
            freq:$scope.conf_frequency,
            taskGroupId:$scope.conf_taskGroup,
            cycle:$scope.conf_cycle,
            priority:$scope.conf_priority,
            ifRecall:$scope.conf_ifRecall,
            recallLimit:$scope.conf_recallLimit,
            recallInterval:$scope.conf_recallInterval,
            successCode:$scope.conf_successCode,
            waitCode:$scope.conf_waitCode,
            offset: $scope.conf_offset,
            timeout: $scope.conf_timeout,
            command: $scope.conf_para1,
            ifPre:hasPre()
        });

        updateResult.$promise.then(function(data) {
            if(data.isSuccess) {
                updatePres();
            }
        },function() {

        })

    };

    function hasPre() {
        if($scope.dependenceTasks.length > 0) {
            return 1;
        } else return 0;
    }

    function getPreList() {
        var list = [];
        if($scope.dependenceTasks.length > 0) {
            for(var i = 0;i < $scope.dependenceTasks.length;i ++) {
                list.push({
                    taskId:$routeParams.taskid,
                    preId:$scope.dependenceTasks[i].taskId,
                    offset:parseInt($scope.dependenceTasks[i].offset.substring(1))
                })
            }
        }
        console.log(list);
        return list;
    }

    var updatePres = function() {
        var ret = JobManageService.updatePre({},JSON.stringify(getPreList()));

        ret.$promise.then(function(data) {
            if(data.isSuccess) {
                showMsg('保存成功')
            }
        },function() {

        })

    }



    var showMsg = function(msg) {
        $scope.showSaveSucess = true;
        $scope.saveSuccessMsg = msg;
    }

    var preTasks = function() {
        var pre = '';
        if($scope.dependenceTasks.length > 0){
            var pres = $scope.dependenceTasks;
            for(var i = 0;i < pres.length - 1;i ++) {
                pre = pre + pres[i].taskId + ","
            }
            pre = pre + pres[pres.length - 1].taskId;
        }
        return pre;
    }

    $scope.getOffsetOptions = function(cycle) {
        return ConstantService.getOffsetsByCycle(cycle);
    }

    $scope.getCycleText = function(cycle) {
        return ConstantService.cycleToText(cycle);
    }

    $scope.getExecutionCycleLabel = function(cycle) {
        return ConstantService.getCycleCss(cycle);
    }


    //删除依赖
    $scope.deleteDependenceTask = function (index) {
        $scope.message = {
            headerText: '提示',
            bodyText: '是否删除任务前驱: ' + $scope.dependenceTasks[index].taskId + ' ?',
            actionButtonStyle: 'btn-danger',
            showCancel: true
        };
        var modalInstance = $modal.open({
            templateUrl: 'dialog/message.html',
            controller: MessageCtrl,
            resolve: {
                msg: function () {
                    return $scope.message;
                }
            }
        });
        modalInstance.result.then(function (data) {
            $scope.dependenceTasks.splice(index, 1);
        }, function () {
        });
    };

    //配置依赖
    $scope.showDependenceDialog = function () {
        $scope.message = {
            headerText: '请选择依赖任务',
            data: $scope.dependenceTasks // 传入数据，dialog的controller进行修改
        };

        var modalInstance = $modal.open({
            templateUrl: 'dialog/taskDependencyDialog.html',
            controller: 'TaskDependencyCtrl',
            windowClass: 'taskQueryDialog',
            resolve: {
                msg: function () {
                    return $scope.message;
                }
            }
        });
        modalInstance.result.then(function (data) {
            $scope.dependenceTasks = data;
        }, function () {
        });
    };
})