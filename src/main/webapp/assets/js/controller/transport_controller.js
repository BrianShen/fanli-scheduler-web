'use strict'
/**
 * Created by wei.shen on 2015/8/5.
 */

fanliApp.controller('transportTaskAddCtrl',function($scope,DimService,ConstantService,JobManageService) {

    initUI();

    function getDevelopers (){
        var rep = DimService.queryAllDevelopers({},{});
        rep.$promise.then(function(data) {
            if(data.isSuccess) {
                $scope.developerOptions = data.results;
            }

        },function() {

        })
    }

    function initUI() {
        $scope.conf_src = '';
        $scope.conf_target = '';
        $scope.conf_targetTable = ''
        $scope.setTaskName = function() {
            $scope.conf_taskName = $scope.conf_src + '2' +$scope.conf_target + '##' + $scope.conf_targetTable;
        }
        getDevelopers();
        $scope.taskGroupOptions = ConstantService.getTaskGroupOption();
        $scope.cycleOptions = ConstantService.getCycleOptions();
        $scope.priorityOptions = ConstantService.getPriorityOption();
        $scope.ifRecallOptions = ConstantService.getIfRecallOption();
        $scope.recallLimitOptions = ConstantService.getRecallLimitOption();
        $scope.recallIntervalOptions = ConstantService.getRecallIntervalOption();
        $scope.offsetOptions = ConstantService.getOffsetOption();
        $scope.timeoutOptions = ConstantService.getTimeOutOption();

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


        $scope.submitTaskCfg = function() {
            setLoading(true,'正在新增传输任务......');
            var req = JobManageService.addTransferTask({},{
                taskGroupId:$scope.conf_taskGroup,
                taskName:$scope.conf_taskName,
                resource:"mysql",
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
                logFile:"/home/hadoop/applog",
                addUser:$scope.conf_developer.chName,
                updateUser:$scope.conf_developer.chName,
                type:1,
                offset:$scope.conf_offset,
                recallLimit:$scope.conf_recallLimit,
                concurrency:1
            });

            req.$promise.then(function(data) {
                if(data.isSuccess) {
                    showAlert('新增传输成功');
                    setLoading(false,'')
                }
            },function() {

            })
        }
    }

    function hasPre() {
        if($scope.conf_src == 'hive') {
            return 1;
        } else {
            return 0;
        }
    }

    function showAlert(msg){
        $scope.showSaveSucess = true;
        $scope.saveSuccessMsg = msg;
    }

    function setLoading(a,b){
        $scope.Loading = a;
        $scope.LoadingMsg = b;
    }


})
