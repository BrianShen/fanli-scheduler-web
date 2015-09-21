/**
 * Created by wei.shen on 2015/9/17.
 */

fanliApp.controller('OtherTaskCtrl',function($scope,$modal,$http,DimService,ConstantService,JobManageService) {

    $scope.taskGroupOptions = ConstantService.getTaskGroupOption();
    $scope.cycleOptions = ConstantService.getCycleOptions();
    $scope.priorityOptions = ConstantService.getPriorityOption();
    $scope.ifRecallOptions = ConstantService.getIfRecallOption();
    $scope.recallLimitOptions = ConstantService.getRecallLimitOption();
    $scope.recallIntervalOptions = ConstantService.getRecallIntervalOption();
    $scope.offsetOptions = ConstantService.getOffsetOption();
    $scope.timeoutOptions = ConstantService.getTimeOutOption();
    DimService.queryAllDevelopers().$promise.then(function(data) {
        $scope.developerOptions = data.results;
    });

    $scope.conf_frequency = '0 5 0 * * ?';
    $scope.conf_taskGroup = $scope.taskGroupOptions[1].ID;
    $scope.conf_cycle = 'D';
    $scope.conf_priority =2;
    $scope.conf_ifRecall = 1;
    $scope.conf_recallLimit = 3;
    $scope.conf_recallInterval = 9;
    $scope.conf_successCode = '0';
    $scope.conf_waitCode = '';
    $scope.conf_offset = 'D0';
    $scope.conf_timeout = 90;
    $scope.conf_para1 = '';

    $scope.dependenceTasks = [];

    $scope.conf_db_name = '';
    $scope.conf_table_name = '';
    $scope.conf_src = '';
    $scope.conf_target = '';


    $scope.$watch('[conf_table_name,conf_db_name]',function() {
        if($scope.conf_task_type == 'calculate') {
            $scope.conf_taskName = 'hive##'+ $scope.conf_db_name + '.' + $scope.conf_table_name ;
        } else if ($scope.conf_task_type == 'transfer') {
            $scope.conf_taskName = $scope.conf_src + '2' + $scope.conf_target + '##' + $scope.conf_table_name ;
        }

    });

    $scope.$watch('[conf_src,conf_target]',function() {
        $scope.conf_taskName = $scope.conf_src + '2' + $scope.conf_target + '##' + $scope.conf_table_name ;
    });

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

    $scope.submitTaskConfig = function() {
        setAlert(false,'','');
        setLoading(true,"保存中...")
        var result = JobManageService.addTask({},{
            taskGroupId:$scope.conf_taskGroup,
            taskName:$scope.conf_taskName,
            resource:$scope.conf_src,
            command:$scope.conf_para1,
            cycle:$scope.conf_cycle,
            priority:$scope.conf_priority,
            ifRecall:$scope.conf_ifRecall,
            ifWait:0,
            ifPre:hasPre(),
            ifEnable:1,
            freq:$scope.conf_frequency,
            owner:$scope.conf_developer.chName,
            waitCode:$scope.conf_waitCode,
            recallCode:"",
            successCode:$scope.conf_successCode,
            timeout:$scope.conf_timeout,
            recallInterval:$scope.conf_recallInterval,
            logFile:"/data1/log/applog",
            addUser:$scope.conf_developer.chName,
            updateUser:$scope.conf_developer.chName,
            type:$scope.conf_task_type == 'calculate'?2:1,
            offset:$scope.conf_offset,
            recallLimit:$scope.conf_recallLimit,
            concurrency:1
        });

        result.$promise.then(function(data) {
            if(data.isSuccess) {
                var taskid = data.result.taskId;
                console.log(taskid);
                addPreRelaTaskToDatabase(taskid);

            } else {
                setAlert(true,'alert-danger',"保存调度任务失败");
            }
        })
    };

    function addPreRelaTaskToDatabase(taskid) {
        $scope.generatedTaskId = taskid;
        if ($scope.dependenceTasks.length > 0) {
            $http.post("/fanli/taskManager/taskpreadd", {
                taskId: $scope.generatedTaskId,
                preId: getPreTasks()
            }).success(function (data) {
                if(data.isSuccess) {
                    setLoading(false,'');
                    setAlert(true,'alert-success',"新增任务成功");
                    $scope.taskConfSave = true;
                } else {
                    setLoading(false,'');
                    setAlert(true,'alert-danger',"保存调度任务，但保存依赖信息失败");
                }
                scrollToTop();
            }).error(function() {
                setAlert(true,'alert-danger',"保存调度任务，但保存依赖信息失败");
                setLoading(false,'');
            })
        } else {
            $scope.taskConfSave = true;
            setLoading(false,'');
            setAlert(true,'alert-success',"新增任务成功");
        }
    };

    var scrollToTop = function() {
        var x=document.body.scrollTop||document.documentElement.scrollTop;
        var timer=setInterval(function(){
            x=x-100;
            if(x<100)
            {
                x=0;
                window.scrollTo(x,x);
                clearInterval(timer);
            }
            window.scrollTo(x,x);
        },"250");
    }

    var getPreTasks = function() {
        var pre = '';
        if($scope.dependenceTasks.length > 0){
            var pres = $scope.dependenceTasks;
            for(var i = 0;i < pres.length - 1;i ++) {
                pre = pre + pres[i].taskId + ","
            }
            pre = pre + pres[pres.length - 1].taskId;
        }
        return pre;
    };

    function setAlert(a,b,c) {
        $scope.sucShow = a;
        $scope.alerttype = b;
        $scope.sucMsg = c;
    }

    var hasPre = function() {
        if($scope.dependenceTasks.length > 0) {
            return 1;
        }else {
            return 0;
        }
    };

    function setLoading(a,b) {
        $scope.Loading = a;
        $scope.LoadingMsg = b;
    }





})