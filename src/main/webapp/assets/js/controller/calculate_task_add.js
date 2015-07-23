/**
 * Created by wei.shen on 2015/7/14.
 */

fanliApp.controller("taskAddCtrl",['$scope','$http','$modal','JobManageService',function($scope,$http,$modal,JobManageService) {
    $scope.showImportMsg = false;
    $scope.isLoading = false;
    $scope.developerOptions = [
        {"id":1 , "name":"沈伟", "bu":"数据部"},
        {"id":2 , "name":"张超", "bu":"数据部"},
        {"id":3 , "name":"唐宏頔", "bu":"数据部"},
        {"id":4 , "name":"王合理", "bu":"数据部"},
        {"id":5 , "name":"James", "bu":"前端"}
    ];
    $scope.hiveDatabases = [
        {"id":1,name:"dw",desc:""},
        {"id":2,name:"ods",desc:""},
        {"id":3,name:"load",desc:""}
    ];
    $scope.cycleOptions  = {
        mi: '分', H: '时', D: '日', W: '周', M: '月', Y: '年'
    };
    $scope.taskGroupOptions= [
            {ID: 1, Text: 'ods'},
            {ID: 2, Text: 'load'},
            {ID: 3, Text: 'dm'},
            {ID: 4, Text: 'dw'},
            {ID: 5, Text: 'rpt'},
            {ID: 6, Text: 'dim'}
    ];
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


    $scope.postDolInfo = function(){
        $scope.loadingMsg = "loading..."
        $scope.isLoading = true;

        $http.get("/fanli/dol/checkDol",{params:{dolPath:$scope.dolPath}})
            .success(function(response){
                $scope.isLoading = false;
                $scope.showImportMsg = true;
                $scope.importMsg = "dol导入成功";
                initConfUI();
                $scope.showConfig = true;
            }).error(function(response) {
                console.log("not success")
                $scope.isLoading = false;
            })
    }
    $scope.change = function() {
        console.log($scope.developer.name);
    }
    function initConfUI () {
        $scope.conf_taskName = "hive##" +$scope.db.name + "." + getTableName($scope.dolPath);
        $scope.conf_developer = $scope.developer.name;
        $scope.conf_frequency ='0 5 0 * * ?';
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

    }

    $scope.dependenceTasks = [];
    //$scope.addPreTask = function() {
    //    $scope.dependenceTasks.push({taskId:22,taskName:'hive##dw.taobao_order',cycle:'日',offset:0});
    //    $scope.dependenceTasks;
    //}

    function getTableName(path) {
        var arr = path.split("/");
        return arr[arr.length - 1];
    }

    $scope.submitTaskConfig = function() {
        JobManageService.addTask({},{
            //taskId:2,
            taskGroupId:3,
            taskName:"hive##dw.taobao_order",
            resource:"some",
            command:"canaan -dol ewew",
            cycle:"d",
            priority:2,
            ifRecall:1,
            ifWait:1,
            ifPre:1,
            ifEnable:1,
            freq:"wwwsdds",
            owner:"shenwei",
            waitCode:"222",
            recallCode:"qqww",
            successCode:"0",
            timeout:"1",
            recallInterval:2,
            logFile:"ee",
            addUser:"shenwei",
            addTime:"3232",
            updateUser:"shenwei",
            updateTime:"2015-06-12",
            type:2,
            offset:"d",
            recallLimit:3,
            concurrency:10
        });
    }


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
}]);
