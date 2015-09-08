/**
 * Created by wei.shen on 2015/8/6.
 */

fanliApp.controller('transferEditCtrl',function($scope,$routeParams,$modal,JobManageService,ConstantService,DimService) {
    initUI();
    function initUI() {
        //getDevelopers();
        $scope.taskGroupOptions = ConstantService.getTaskGroupOption();
        $scope.cycleOptions = ConstantService.getCycleOptions();
        $scope.priorityOptions = ConstantService.getPriorityOption();
        $scope.ifRecallOptions = ConstantService.getIfRecallOption();
        $scope.recallLimitOptions = ConstantService.getRecallLimitOption();
        $scope.recallIntervalOptions = ConstantService.getRecallIntervalOption();
        $scope.offsetOptions = ConstantService.getOffsetOption();
        $scope.timeoutOptions = ConstantService.getTimeOutOption();
    }

    $scope.submitTaskCfg = function() {
        setLoading(true,'正在修改传输任务......');
        $scope.showSaveSucess = false;
        var result = JobManageService.updateTransferTask({},{
            taskId:$routeParams.taskid,
            taskGroupId:$scope.conf_taskGroup,
            taskName:$scope.conf_taskName,
            command:$scope.conf_para1,
            cycle:$scope.conf_cycle,
            priority:$scope.conf_priority,
            ifRecall:$scope.conf_ifRecall,
            freq:$scope.conf_frequency,
            owner:$scope.conf_developer.chName,
            waitCode:$scope.conf_waitCode,
            successCode:$scope.conf_successCode,
            timeout:$scope.conf_timeout,
            recallInterval:$scope.conf_recallInterval,
            updateUser:$scope.conf_developer.chName,
            offset:$scope.conf_offset,
            recallLimit:$scope.conf_recallLimit
        });

        result.$promise.then(function(data) {
            if(data.isSuccess) {
                updatePres();


            }
        },function() {})

    }
    var updatePres = function() {
        var ret = JobManageService.updatePre({},{
            taskId: $routeParams.taskid,
            preId:preTasks()
        });

        ret.$promise.then(function(data) {
            if(data.isSuccess) {
                setLoading(false,'');
                showAlert('修改成功')
            }
        },function() {

        })

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

    $scope.setTaskName = function() {
        $scope.conf_taskName = $scope.conf_src + '2' +$scope.conf_target + '##' + $scope.conf_targetTable;
    }

    //function getDevelopers (){
    //    var rep = DimService.queryAllDevelopers({},{});
    //    rep.$promise.then(function(data) {
    //        if(data.isSuccess) {
    //            $scope.developerOptions = data.results;
    //        }
    //
    //    },function() {
    //
    //    })
    //}

    function getTransferConfigById(id) {
        setLoading(true,'正在获取传输配置......')
        JobManageService.getTransferTaskById({
            taskid:id
        }).$promise.then(function(data) {
            setValues(data);
                setLoading(false,'');
        },function() {
                $scope.errorShow = true;
                $scope.cfgMsg = '获取传输配置信息失败，请联系开发';
            })
    }

    function setValues(data) {
        var name = data.taskName;
        var arr = name.split('##');
        var sp = arr[0].split(2);
        $scope.conf_src = sp[0];
        $scope.conf_target = sp[1];
        $scope.conf_targetTable = arr[1];
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

    getTransferConfigById($routeParams.taskid);

    var getPreTasks = function(id) {
        var pres = JobManageService.queryPreTaskById({
            taskid: id
        });
        pres.$promise.then(function(data) {
            $scope.dependenceTasks = data.results;
        },function() {
            $scope.errorShow = true;
            $scope.cfgMsg = '获取前置信息失败，请联系开发';
        })
    }
    getPreTasks($routeParams.taskid);

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

    function showAlert(msg){
        $scope.showSaveSucess = true;
        $scope.saveSuccessMsg = msg;
    }

    function setLoading(a,b){
        $scope.Loading = a;
        $scope.LoadingMsg = b;
    }
})