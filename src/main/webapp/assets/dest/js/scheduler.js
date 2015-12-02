/**
 * Created by wei.shen on 2015/7/11. 1234567
 */
var fanliApp = angular.module("fanliApp", [
    'ngRoute',
    'ngResource',
    'common.service',
    'job_manage.service',
    'job_monitor.service',
    'component.service',
    'ui.bootstrap',
    'constant.service',
    'fanli.filter',
    'table.service'
]);

fanliApp.config(function ($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'monitor.html',
            controller: 'MonitorCtrl'
        }).
        when('/mytask', {
            templateUrl: 'mytask.html',
            controller: 'myTaskCtrl'
        }).
        when('/taskAdd', {
            templateUrl: 'CalculateTaskConfig.html',
            controller: 'taskAddCtrl'
        }).
        when('/transport', {
            templateUrl: 'transport.html',
            controller: 'transportTaskAddCtrl'
        })
        .when('/job_log/:instanceId', {
            templateUrl: 'job_log.html',
            controller: 'jobLogCtrl'
        }).when('/calculate_task_edit/:taskid', {
            templateUrl: 'calculate_task_edit.html',
            controller: 'taskEditCtrl'
        }).when('/transfer_task_edit/:taskid', {
            templateUrl: 'transport_edit.html',
            controller: 'transferEditCtrl'
        }).when('/feedback',{
            templateUrl: 'feedback.html',
            controller: 'feedbackCtrl'
        }).when('/compatible',{
            templateUrl: 'other_task.html',
            controller: 'OtherTaskCtrl'
        }).when('/instance_status_tree/:instanceId',{
            templateUrl: 'instance_status.html',
            controller: 'instanceStatusCtrl'
        }).when('/data_monitor/:op/:taskId',{
            templateUrl:'dataMonitor.html',
            controller:'dataMonitorCtrl'
        }).when('/tableAccess',{
            templateUrl:'tableAccess.html',
            controller:'tableAccessCtrl'
        }) ;

});
/**
 * Created by wei.shen on 2015/7/14.
 */

fanliApp.controller("taskAddCtrl", ['$scope', '$http', '$modal', '$filter', 'ConstantService', 'component', 'JobManageService', 'DimService', 'DolService',
    function ($scope, $http, $modal, $filter, ConstantService, component, JobManageService, DimService, DolService) {
        $scope.showImportMsg = false;
        $scope.isLoading = false;
        $scope.dolPath = '';
        var getDevelopers = function () {
            var rep = DimService.queryAllDevelopers({}, {});
            rep.$promise.then(function (data) {
                if (data.isSuccess) {
                    $scope.developerOptions = data.results;
                }

            }, function () {

            })
        }

        getDevelopers();

        $scope.hiveDatabases = [
            {"id": 1, name: "dw", desc: ""},
            {"id": 2, name: "ods", desc: ""},
            {"id": 3, name: "load", desc: ""},
            {"id": 4, name: "dm", desc: ""},
            {"id": 5, name: "rpt", desc: ""},
            {"id": 6, name: "dim", desc: ""}
        ];
        $scope.cycleOptions = {
            mi: '分', H: '时', D: '日', W: '周', M: '月', Y: '年'
        };
        //$scope.taskGroupOptions = [
        //    {ID: 1, Text: 'ods'},
        //    {ID: 2, Text: 'load'},
        //    {ID: 3, Text: 'dm'},
        //    {ID: 4, Text: 'dw'},
        //    {ID: 5, Text: 'rpt'},
        //    {ID: 6, Text: 'dim'}
        //];

        $scope.taskGroupOptions = ConstantService.getTaskGroupOption();
        $scope.priorityOptions = [
            {ID: 1, Text: '高'},
            {ID: 2, Text: '中'},
            {ID: 3, Text: '低'}
        ];
        $scope.ifRecallOptions = [
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

        $scope.tableRefreshTypes = ConstantService.getTableRefreshCycle();

        $scope.tableType = 'MANAGED_TABLE';

        var setButtonClickable = function (a, b, c) {
            $scope.taskConfPre = a;
            $scope.taskConfSave = b;
            $scope.taskConfNext = c;
        }
        $scope.postDolInfo = function () {
            $scope.showImportMsg = false;
            if (!checkDolPath()) {
                return;
            }
            $scope.loadingMsg = "loading...";
            $scope.isLoading = true;

            $http.get("/fanli/dol/importDol", {params: {dolPath: $scope.dolPath}})
                .success(function (response) {
                    if (response.isSuccess) {

                        parseTargetTable();

                    } else {
                        $scope.isLoading = false;
                        $scope.showImportMsg = true;
                        $scope.alertType = 'alert-danger';
                        $scope.importMsg = response.messages;
                    }

                }).error(function (response) {
                    console.log("not success")
                    $scope.isLoading = false;
                    $scope.showImportMsg = true;
                    $scope.alertType = 'alert-danger';
                    $scope.importMsg = response.messages;
                })
        }
        $scope.change = function () {
            console.log($scope.developer.cnName);
        }

        $scope.setDefaultFreq = function () {
            switch ($scope.conf_taskGroup) {
                case 1:$scope.conf_frequency = '0 30 0 * * ?';break;
                case 2:$scope.conf_frequency = '0 5 0 * * ?';break;
                case 3:$scope.conf_frequency = '0 0 3 * * ?';break;
                case 4:$scope.conf_frequency = '0 0 1 * * ?';break;
                case 5:$scope.conf_frequency = '0 0 4 * * ?';break;
                case 6:$scope.conf_frequency = '0 5 0 * * ?';break;
            }
        }

        function parseTargetTable() {
            console.log("dol name:" + getDolName());
            var table = DolService.parseDolToGetTable({
                dolName: getDolName()
            });
            table.$promise.then(function (data) {
                if (data.isSuccess) {
                    $scope.table_name = data.result;

                    initConfUI();
                    $scope.isLoading = false;
                    $scope.showImportMsg = true;
                    $scope.alertType = 'alert-success';
                    $scope.importMsg = "dol导入成功";
                    setButtonClickable(false, false, true);
                    $scope.showConfig = true;
                } else {
                    $scope.isLoading = false;
                    $scope.showImportMsg = true;
                    $scope.alertType = 'alert-danger';
                    $scope.importMsg = "dol导入成功，但表名解析失败";
                }
            }, function (data) {
                $scope.isLoading = false;
                $scope.showImportMsg = true;
                $scope.alertType = 'alert-danger';
                $scope.importMsg = "dol导入成功，但表名解析失败";
            })
        }

        function checkDolPath() {
            if ($scope.dolPath == '' || $scope.dolPath == undefined) {
                $scope.showImportMsg = true;
                $scope.alertType = 'alert-danger';
                $scope.importMsg = "dol路径不能为空";
                return false;
            }
            if ($scope.developer == '' || $scope.developer == undefined) {
                $scope.showImportMsg = true;
                $scope.alertType = 'alert-danger';
                $scope.importMsg = "开发者不能为空";
                return false;
            }
            if ($scope.db == '' || $scope.db == undefined) {
                $scope.showImportMsg = true;
                $scope.alertType = 'alert-danger';
                $scope.importMsg = "目标库不能为空";
                return false;
            }

            var arr = $scope.dolPath.split('.');
            if (arr[arr.length - 1] != 'dol') {
                $scope.showImportMsg = true;
                $scope.alertType = 'alert-danger';
                $scope.importMsg = "请检查dol路径，文件必须以.dol结尾";
                return false;
            }
            return true;
        }


        $scope.dependenceTasks = [];
        //$scope.addPreTask = function() {
        //    $scope.dependenceTasks.push({taskId:22,taskName:'hive##dw.taobao_order',cycle:'日',offset:0});
        //    $scope.dependenceTasks;
        //}

        //周期变化触发
        $scope.cycleChanged = function () {
            if ($scope.cycle == 'W') {
                $scope.conf_frequency = '0 5 0 * * MON';
            }
        };

        function getTableName(path) {
            var arr = path.split("/");
            var dol = arr[arr.length - 1];
            return dol.substring(0, dol.length - 4);
        }

        function initConfUI() {
            $scope.conf_taskName = "hive##" + $scope.db.name + "." + $scope.table_name;
            $scope.conf_developer = $scope.developer.cnName;
            $scope.conf_frequency = '0 5 0 * * ?';
            $scope.conf_taskGroup = $scope.taskGroupOptions[1].ID;
            $scope.conf_cycle = 'D';
            $scope.conf_priority = 2;
            $scope.conf_ifRecall = 1;
            $scope.conf_recallLimit = 3;
            $scope.conf_recallInterval = 9;
            $scope.conf_successCode = '0';
            $scope.conf_waitCode = '';
            $scope.conf_offset = 'D0';
            $scope.conf_timeout = 90;
            $scope.conf_para1 = '';
        }


        var checkFormValid = function () {
            //if($scope.conf_para1 === "") {
            //    $scope.showCfgMsg = true;
            //    $scope.cfgMsg = "执行命令不能为空";
            //    return false;
            //}
            if ($scope.successCode === "") {
                $scope.showCfgMsg = true;
                $scope.cfgMsg = "成功返回码不能为空";
                return false;
            }
            return true;
        }

        var alertPreCfg = function () {
            $scope.message = {
                headerText: '确认？',
                bodyText: '请确认此任务的前驱是如下任务,这将严重影响调度的稳定性：<br>' + getBloodRela(),
                actionButtonStyle: 'btn-danger',
                showCancel: true
            };
            var modalInstance = $modal.open({
                templateUrl: 'dialog/pretaskAlert.html',
                controller: MessageCtrl,
                resolve: {
                    msg: function () {
                        return $scope.message;
                    }
                }
            });

            return modalInstance;
        }

        $scope.submitTaskConfig = function () {
            $scope.showCfgMsg = false;
            $scope.cfgMsg = "";
            var ensure = false;
            var result = alertPreCfg();
            result.result.then(function (data) {
                ensure = true;
                var valid = checkFormValid();
                console.log(ensure);
                if (valid && ensure) {
                    $scope.cfgSaveLoadingMsg = "正在保存配置和依赖信息......";
                    $scope.cfgSaveIsLoading = true;
                    var result = JobManageService.addTask({}, {
                        //taskId:2,
                        taskGroupId: $scope.conf_taskGroup,
                        taskName: $scope.conf_taskName,
                        tableName:$scope.table_name,
                        resource: "hive",
                        command: getCalCommand(),
                        cycle: $scope.conf_cycle,
                        priority: $scope.conf_priority,
                        ifRecall: $scope.conf_ifRecall,
                        ifWait: 0,
                        ifPre: hasPre(),
                        ifEnable: 1,
                        freq: $scope.conf_frequency,
                        owner: $scope.conf_developer,
                        waitCode: $scope.conf_waitCode,
                        recallCode: "",
                        successCode: $scope.conf_successCode,
                        timeout: $scope.conf_timeout,
                        recallInterval: $scope.conf_recallInterval,
                        logFile: "/data1/log/applog",
                        addUser: $scope.conf_developer,
                        updateUser: $scope.conf_developer,
                        type: 2,
                        offset: $scope.conf_offset,
                        recallLimit: $scope.conf_recallLimit,
                        concurrency: 1
                    });
                    result.$promise.then(function (data) {
                        if (data.isSuccess) {
                            $scope.generatedTaskId = data.result.taskId;
                            if ($scope.dependenceTasks.length > 0) {
                                $http.post("/fanli/taskManager/taskpreadd", JSON.stringify(getPreList())).success(function (data) {
                                    $scope.saveSuccessMsg = "保存成功";
                                    $scope.showSaveSucess = true;
                                    setButtonClickable(true, true, false);
                                    $scope.cfgSaveLoadingMsg = "";
                                    $scope.cfgSaveIsLoading = false;
                                    console.log("add pre success")
                                })
                            } else {
                                $scope.saveSuccessMsg = "保存成功";
                                $scope.showSaveSucess = true;
                                setButtonClickable(true, true, false);
                                $scope.cfgSaveLoadingMsg = "";
                                $scope.cfgSaveIsLoading = false;
                            }
                        }
                    });
                }
            }, function () {
                ensure = false;
            });

        };

        $scope.getOffsetOptions = function(cycle) {
            return ConstantService.getOffsetsByCycle(cycle);
        }

        $scope.getCycleText = function(cycle) {
            return ConstantService.cycleToText(cycle);
        }

        $scope.getExecutionCycleLabel = function(cycle) {
            return ConstantService.getCycleCss(cycle);
        }

        function getPreList() {
            var list = [];
            if($scope.dependenceTasks.length > 0) {
                for(var i = 0;i < $scope.dependenceTasks.length;i ++) {
                    list.push({
                        taskId:$scope.generatedTaskId,
                        preId:$scope.dependenceTasks[i].taskId,
                        offset:parseInt($scope.dependenceTasks[i].offset.substring(1))
                    })
                }
            }
            console.log(list);
            return list;
        }

        function getCalCommand() {
            var command;
            var dol = getDolName();
            if ($scope.conf_cycle == 'H') {
                command = "canaan -dol " + dol + " -t " + "${unix_timestamp} ";
            } else {
                command = "canaan -dol " + dol + " -d " + "${date}";
            }
            return command;
        };
        function getDolName() {
            var arr = $scope.dolPath.split('/');
            return arr[arr.length - 1];
        }

        var getPreTasks = function () {
            var pre = '';
            if ($scope.dependenceTasks.length > 0) {
                var pres = $scope.dependenceTasks;
                for (var i = 0; i < pres.length - 1; i++) {
                    pre = pre + pres[i].taskId + ","
                }
                pre = pre + pres[pres.length - 1].taskId;
            }
            return pre;
        }
        var hasPre = function () {
            if ($scope.dependenceTasks.length > 0) {
                return 1;
            } else {
                return 0;
            }
        }

        //$scope.submitTaskConfig = function() {
        //    var result = JobManageService.addTask({},{
        //        //taskId:2,
        //        taskGroupId:3,
        //        taskName:"hive##dw.taobao_order",
        //        resource:"some",
        //        command:"canaan -dol ewew",
        //        cycle:"d",
        //        priority:2,
        //        ifRecall:1,
        //        ifWait:1,
        //        ifPre:1,
        //        ifEnable:1,
        //        freq:"wwwsdds",
        //        owner:"shenwei",
        //        waitCode:"222",
        //        recallCode:"qqww",
        //        successCode:"0",
        //        timeout:"1",
        //        recallInterval:2,
        //        logFile:"ee",
        //        addUser:"shenwei",
        //        addTime:"3232",
        //        updateUser:"shenwei",
        //        updateTime:"2015-06-12",
        //        type:2,
        //        offset:"d",
        //        recallLimit:3,
        //        concurrency:10
        //    });
        //    result.$promise.then(function(data) {
        //        if(data.isSuccess) {
        //            $scope.generatedTaskId = data.result.taskId;
        //        }
        //    });
        //};

        $scope.returnStep1 = function () {
            $scope.showConfig = false;
            $scope.showImportMsg = false;
            $scope.importMsg = "";
        };

        $scope.gotoStep3 = function () {
            setButtonClickable(true, true, true);
            getMetaData();
            setButtonState(false, false, true);
            $scope.showMetaConfig = true;
        };

        $scope.returnStep2 = function () {
            $scope.showMetaConfig = false;

        };

        $scope.saveMeta = function () {
            console.log($scope.tableComment);
            setButtonState(true, true, true);
            $scope.publishSuccess = true;
            $scope.publishLoadingMsg = "正在保存HIVE META信息......";
            $http.post("/fanli/mdm/saveMeta", {
                tableName: $scope.table_name,
                description: $scope.tableComment,
                createTableInfo: getBuildTableSql($scope.metatable),
                columnDescription: getColumnDesc($scope.showColumnTable),
                bloodRelation: getBloodRela(),
                tableType: $scope.refreshType
            }).success(function (data) {
                $scope.publishSuccess = false;
                $scope.publishLoadingMsg = "";
                setButtonState(true, true, false);
                console.log("save success");
            }).error(function (data) {
                setButtonState(false, false, true);
                $scope.publishSuccess = false;
                $scope.publishLoadingMsg = "";
            });
        };

        var setButtonState = function (a, b, c) {
            $scope.preThree = a;
            $scope.saveThree = b;
            $scope.publishThree = c;
        }

        $scope.changeComment = function (index) {
            //console.log("change " + (parseInt(index) + ($scope.showColumnTable.currentPage - 1)*($scope.showColumnTable.selectedRecordPerPage)) + "  " + $scope.showColumnTable.displayedDataList[index + ($scope.showColumnTable.currentPage - 1)*($scope.showColumnTable.selectedRecordPerPage)].comment)

        }

        var getBuildTableSql = function (data) {

            //$http.get("/fanli/table/buildTable",data).success(function(data) {
            //    sql = data.result;
            //});
            var sql = "use " + $scope.db.name + ";\n" + "drop table if exists " + data.table + ";\n" +
                "create external table " + data.table + "(\n";
            var columns = data.columns;
            var partitions = data.partitions;
            for (var i = 0; i < columns.length - 1; i++) {
                sql = sql + columns[i].name + " " + columns[i].type + ",\n";
            }
            sql = sql + columns[columns.length - 1].name + " " + columns[columns.length - 1].type + ")\n";
            if (partitions.length > 0) {
                sql = sql + "PARTITIONED BY(";
                for (var i = 0; i < partitions.length - 1; i++) {
                    sql = sql + partitions[i].name + " " + partitions[i].type + ","
                }
                sql = sql + partitions[partitions.length - 1].name + " " + partitions[partitions.length - 1].type + ")\n"
            }
            sql = sql + "STORED AS ORC;";
            return sql;
        };

        var getColumnDesc = function (data) {
            var col = [];
            var columns = data.displayedDataList;
            for (var i = 0; i < columns.length; i++) {
                var ele = columns[i];
                col.push({column: ele.name, info: ele.comment});
            }
            //col = col + columns[columns.length - 1].name + ":" + columns[columns.length - 1].comment + "}]";
            col = JSON.stringify(col);
            return col;
        };


        var getBloodRela = function () {
            var pre = '';
            var dp = $scope.dependenceTasks;
            if (dp.length > 0) {
                pre = dp[0].taskName;
                for (var i = 1; i < dp.length; i++) {
                    pre = pre + "," + dp[i].taskName;
                }
            }
            return pre;
        }

        $scope.publish = function () {
            $scope.message = {
                headerText: '提示',
                bodyText: '请确认是否执行如下建表语句，如有疑问请联系开发人员: <pre>' + getBuildTableSql($scope.metatable) + '</pre>',
                actionButtonStyle: 'btn-danger',
                showCancel: true
            };
            var modalInstance = $modal.open({
                templateUrl: 'dialog/publish.html',
                controller: MessageCtrl,
                resolve: {
                    msg: function () {
                        return $scope.message;
                    }
                }
            });
            modalInstance.result.then(function (data) {
                var sql = getBuildTableSql($scope.metatable);
                $scope.publishSuccess = true;
                $scope.publishLoadingMsg = "正在建表...";
                $http.post("/fanli/table/build", {sql: sql}).success(function (data) {
                    $scope.publishStatusClass = 'alert-success';
                    $scope.publishSuccess = false;
                    $scope.publishLoadingMsg = '';
                    $scope.showPublishResultMsg = true;
                    $scope.publishResultMsg = '建表已成功';
                    setButtonState(true, true, true);
                    console.log("建表成功");
                }).error(function (data) {
                    $scope.publishStatusClass = 'alert-danger';
                    $scope.showPublishResultMsg = true;
                    $scope.publishResultMsg = '建表失败，请联系调度开发人员';
                    $scope.publishSuccess = false;
                    $scope.publishLoadingMsg = '';
                });
            }, function () {
            });

        }

        var getMetaData = function () {
            $scope.metaIsLoading = true;
            $scope.metaLoadingMsg = "正在加载元数据信息......";
            $http.get("/fanli/domain/meta", {
                params: {
                    db: 'test',
                    table: $scope.table_name
                }
            }).success(function (data) {
                $scope.tableLocation = data.source;
                $scope.metatable = data;
                $scope.showColumnTable = component.getMultiHiveTableColumnTable($filter, data.columns);
                $scope.partitions = data.partitions;
                $scope.metaIsLoading = false;
                $scope.metaLoadingMsg = "";
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

/**
 * Created by wei.shen on 2015/11/25.
 */

fanliApp.controller('dataMonitorCtrl',function($scope,$resource,$routeParams,DimService) {
    //获取url里taskId
    $scope.taskId = $routeParams.taskId;
    //获取url中的操作选项 op='new'新增配置  op='edit' 修改配置
    $scope.op = $routeParams.op;

    $scope.options = [{src:'id',target:'id'},{src:'name',target:'name'}];

    //定义资源
    var monitorResource = $resource('/fanli/dataMonitor/:taskId',{},
        {
            queryMonitor:{
                 method:'GET',
                 params:{
                     taskId:'@taskId'
                 }
            },
            updateMonitor:{
                method:'PUT',
                params:{
                    taskId:'@taskId'
                }
            }
        });
    var newResource = $resource('/fanli/dataMonitor');
    //var updateResource = $resource('/fanli/dataMonitor/:taskId',{id:'@'},{update:{method:'PUT'}});
    var reader = $resource('/fanli/load/sql',{taskid:'@taskid'});
    var writer = $resource('/fanli/load/pre',{taskId:'@taskId'});

    if($scope.op == 'edit') {
        var ret = monitorResource.queryMonitor({taskId:$scope.taskId});
        ret.$promise.then(function(monitor) {
                $scope.detailId=monitor.detailId;
                $scope.owner = {cnName:monitor.ownerName};
                $scope.mail_suject = monitor.mailSubject;
                $('#mail-input-to').val(monitor.mailTo);
                $('#msg-input').val(monitor.mobile);
                $('#mail-input-cc').val(monitor.mailCc);
                $scope.developerOptions = DimService.queryAllDevelopers().$promise.then(function(data) {
                    $scope.developerOptions = data.results;
                    //生成邮件array
                    var mailList = [];
                    angular.forEach($scope.developerOptions,function(ele){
                        mailList.push(ele.ownerMailAddress)
                    });
                    var phoneList = [];
                    angular.forEach($scope.developerOptions,function(ele){
                        phoneList.push(ele.owneMobile);
                    });
                    //初始化tag input
                    initTagInput(mailList,phoneList);
                });
                $scope.mail_content = monitor.mailMessage;
                var contrast = JSON.parse(monitor.contrast);
                $scope.diff_type = contrast.diff_type;
                $scope.min_diff = contrast.min_diff;
                $scope.max_diff = contrast.max_diff;
        });
        //monitorResource.get({taskId:$scope.taskId},function(monitor) {
        //    $scope.ownerName = monitor.ownerName;
        //    $scope.mail_suject = monitor.mailSubject;
        //    $('#mail-input-to').val(monitor.mailTo);
        //    $('#msg-input').val(monitor.mobile);
        //    $('#mail-input-cc').val(monitor.mailCc);
        //    $scope.developerOptions = DimService.queryAllDevelopers().$promise.then(function(data) {
        //        $scope.developerOptions = data.results;
        //        //生成邮件array
        //        var mailList = [];
        //        angular.forEach($scope.developerOptions,function(ele){
        //            mailList.push(ele.ownerMailAddress)
        //        });
        //        var phoneList = [];
        //        angular.forEach($scope.developerOptions,function(ele){
        //            phoneList.push(ele.owneMobile);
        //        });
        //        //初始化tag input
        //        initTagInput(mailList,phoneList);
        //    });
        //    $scope.mail_content = monitor.mailMessage;
        //    var contrast = JSON.parse(monitor.contrast);
        //    $scope.diff_type = contrast.diff_type;
        //    $scope.min_diff = contrast.min_diff;
        //    $scope.max_diff = contrast.max_diff;
        //
        //},function(error) {
        //    console.log("error!")
        //})
    } else if($scope.op == 'new') {
        //生成默认邮件主题
        $scope.mail_suject = '任务' + $scope.taskId + '的告警';
        initDevelopers();
        generateMonitorInfoFromLoadCfg();
    }

    $scope.submitMonitorConfig = function() {
        if($scope.owner == undefined ||$scope.mail_suject == ''||$scope.mail_suject == undefined) {
            return;
        }
        if($('#mail-input-to').val() == ''||$('#mail-input-to').val() == undefined) {
            alert('Mail address can not be empty!');
            return;
        }

        if( $scope.min_diff == undefined||$scope.max_diff == undefined) {
            return;
        }

        if($scope.diff_type ==undefined) {
            alert('请选择差值类型');
            return;
        }

        if($scope.op == 'new') {
            newResource.save({
                ownerId:$scope.owner.ownerId,
                ownerName:$scope.owner.cnName,
                taskId:$scope.taskId,
                mobile:$('#msg-input').val(),
                mailTo:$('#mail-input-to').val(),
                mailCc:$('#mail-input-cc').val(),
                mailSubject:$scope.mail_suject,
                mailMessage:$scope.mail_content,
                sourceDatasource:$scope.src_connectProps,
                targetDatasource:$scope.target_connectProps,
                sourceSql:$scope.src_sql,
                targetSql:$scope.target_sql,
                contrast:JSON.stringify(getContrastItem())
            },function(data) {$scope.saveSuccess = true})
        } else{
            var ret =  monitorResource.updateMonitor({},{
                detailId:$scope.detailId,
                taskId:$scope.taskId,
                ownerName:$scope.owner.cnName,
                mobile:$('#msg-input').val(),
                mailTo:$('#mail-input-to').val(),
                mailCc:$('#mail-input-cc').val(),
                mailSubject:$scope.mail_suject,
                mailMessage:$scope.mail_content,
                contrast:JSON.stringify(getContrastItem())
            });

            ret.$promise.then(function(data) {alert('accepted')})
        }

    }


    function getContrastItem() {
        var ret = {};
        ret.diff_type = $scope.diff_type;
        ret.min_diff = $scope.min_diff;
        ret.max_diff = $scope.max_diff;
        return ret;
    }

    function initDevelopers() {
        //获取开发者名单
        $scope.developerOptions = DimService.queryAllDevelopers().$promise.then(function(data) {
            $scope.developerOptions = data.results;
            //生成邮件array
            var mailList = [];
            angular.forEach($scope.developerOptions,function(ele){
                mailList.push(ele.ownerMailAddress)
            });
            var phoneList = [];
            angular.forEach($scope.developerOptions,function(ele){
                phoneList.push(ele.owneMobile);
            });
            //初始化tag input
            initTagInput(mailList,phoneList);
        });
    }




    //setEvent();
    //function setEvent() {
    //    $("input[id^='mail-input']").on('removed', function (e, value) {
    //        // do something...
    //        alert("remove!")
    //    })
    //}

    function initTagInput(mailList,phoneList) {
        var tag_input_mail = $("input[id^='mail-input']");
        tag_input_mail.tag(
            {
                placeholder:tag_input_mail.attr('placeholder'),
                //enable typeahead by specifying the source array
                source: mailList//defined in ace.js >> ace.enable_search_ahead
            }
        );

        var tag_input_msg = $("#msg-input");
        tag_input_msg.tag({
            placeholder:tag_input_msg.attr('placeholder'),
            source:phoneList
            //enable typeahead by specifying the source array
            //source: mailList//defined in ace.js >> ace.enable_search_ahead
        });
    }





    function generateMonitorInfoFromLoadCfg() {
        reader.get({taskid:$scope.taskId},function(data) {
            var readParam = JSON.parse(data.result);
            var where = '';
            //console.log(readParam.sql);
            var sql = readParam.sql;
            if(sql.toLowerCase().indexOf('where') > 0) {
                if(readParam.where != ''&& readParam.where != undefined && readParam.where != null) {
                    where = readParam.where
                } else {
                    where = sql.split('where')[1].trim();
                }
            }

            var tableName = '';
            if(readParam.tableName != ''&& readParam.tableName != undefined && readParam.tableName != null) {
                tableName = readParam.tableName;
            } else {
                if(sql.indexOf('where') > 0) {
                    tableName = sql.match(/from(.+)where/)[1].trim();
                } else tableName = sql.match(/from(.+)/)[1].trim();
            }

            if(readParam.plugin == 'hivereader') {
                $scope.src_connectProps = 'hive172';
                $scope.src_sql = 'select count(1) as cnt from ' + tableName;
            } else {
                $scope.src_connectProps = readParam.connectProps;
                $scope.src_sql = 'select count(1) as cnt from ' + readParam.dbname + '.'  + tableName;
            }
            if(where != '') {
                $scope.src_sql += ' where ' + where;
            }
            console.log($scope.src_sql);
        });

        writer.get({taskId:$scope.taskId},function(data) {
            var writeParam = JSON.parse(data.result);
            var tableName = '';
            var dbName = '';
            var hive_table_add_partition_switch = writeParam.hive_table_add_partition_switch;
            var hive_table_add_partition_condition = writeParam.hive_table_add_partition_condition;
            var partionArray = [];
            if(hive_table_add_partition_switch) {
                var partitions = hive_table_add_partition_condition.split('@')[0];
                partionArray = partitions.split(',');
            }
            var sql = 'select count(1) as cnt from ';
            if(writeParam.plugin == 'hdfswriter') {
                tableName = writeParam.dir.match(/\.db\/(.+?)\//)[1].trim();
                var dbPath = writeParam.dir.match(/.*\/(.*)\.db\/.*/)[1].trim();
                if(dbPath == 'tmp') {
                    dbName = 'tmpdb'
                } else {
                    dbName = dbPath.toLowerCase();
                }
                sql += dbName + '.' + tableName;
                $scope.target_connectProps = 'hive172';
                if(hive_table_add_partition_switch) {
                    sql += ' where ';
                    sql += partionArray[0];
                    for(var i = 1;i <  partionArray.length;i ++) {
                        sql +=  ' and ' + partionArray[i];
                    }
                }
            } else if(writeParam.plugin == 'sqlserverwriter') {
                tableName = writeParam.tableName;
                dbName = writeParam.dbname;
                sql += dbName + '.' + tableName;

                if(writeParam.pre.toLowerCase().indexOf('where') > 0) {
                    var where = writeParam.pre.match(/where(.*)/i)[1].trim();
                    sql+= ' where ' + where;
                }
                $scope.target_connectProps = writeParam.connectProps;
            }

            $scope.target_sql = sql;
            console.log($scope.target_sql);
        })
    }




    //选中开发者 默认设置初始化邮件，电话等配置
    //$scope.generateInitConfig = function() {
    //
    //    var owner = $scope.owner;
    //    var mailTo = owner.ownerMailAddress;
    //    //邮件列表赋初值
    //    if(tagInput.val().split(',').length > 1) {
    //        tagInput.val(mailTo);//先这样吧 不作替换
    //    } else {
    //        tagInput.val(mailTo);
    //    }
    //
    //    //alert($scope.mailTo);
    //    //alert($("#form-field-tags1").val());
    //    //initBootStrapTag();
    //
    //}

    //function initBootStrapTag() {
    //    var tag_input = $("input[id^='form-field-tags']");
    //    if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) )
    //    {
    //        tag_input.tag(
    //            {
    //                placeholder:tag_input.attr('placeholder'),
    //                //enable typeahead by specifying the source array
    //                source: ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
    //            }
    //        );
    //    }
    //    else {
    //        //display a textarea for old IE, because it doesn't support this plugin or another one I tried!
    //        tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
    //        //$('#form-field-tags').autosize({append: "\n"});
    //    }
    //}
})

'use strict';

//任务依赖选择dialog
fanliApp.controller('TaskDependencyCtrl', function ($scope, $filter, $modalInstance, msg,component, JobManageService,ConstantService) {

    //initParams();
    //initScope();

        $scope.dependenceTasks = msg.data;
        $scope.taskGroups = ConstantService.getTaskGroupOption();
        $scope.alert = {
            type: '',
            msg: '',
            isShow: false
        };



        $scope.queryPre = function() {
            var task = this.taskid;
            var owner = this.taskOwner;
            var grp = this.taskGroup;
            var preTasks = JobManageService.queryTasks({},{
                group: grp,
                owner: owner,
                id: task,
                isValid:1
            });

            processResult(preTasks);
        };

    $scope.ok = function() {
        $modalInstance.close($scope.dependenceTasks);
    };

    //显示提示信息
    function showAlert(type, msg) {
        $scope.alert.msg = msg;
        $scope.alert.type = type;
        $scope.alert.isShow = true;
    }

    //点击依赖选择框的响应
    $scope.setDependence = function (index) {
        //检查依赖是否已经被添加
        var flag = 1;
        var selectRowIndex = index + ($scope.table.startIndex - 1);
        var job = $scope.table.displayedDataList[selectRowIndex];
        console.log(job.isSelected);
        if (job.isSelected) {
            var isExists = false;
            for(var i = 0;i < $scope.dependenceTasks.length;i ++) {
                if(job.taskId == $scope.dependenceTasks[i].taskId) {
                    isExists = true;
                }
            }
            if(isExists) {
                showAlert('warning','该依赖任务已存在，不能重复配置')
                $scope.table.displayedDataList[selectRowIndex].isSelected = false;
            } else{
                addDependency(job);
            }


        } else {
            deleteDependency(job);
        }
    };

    //删除依赖
    function deleteDependency(job) {
        var index = getDependenceIndex(job.taskId);
        if (index != -1) {
            $scope.dependenceTasks.splice(index, 1);
            showAlert('success', '成功删除依赖(taskId: ' + job.taskId + ', taskName: ' + job.taskName + ')');
        }
        else {
            showAlert('warning', '查找任务失败');
        }
    }

    //添加依赖
    function addDependency(job) {
        var taskRela = {
            taskId: job.taskId,
            taskName: job.taskName,
            cycle: job.cycle,
            owner: job.owner,
            offset: job.cycle + '0'
        };
        //var isExists = false;
        //for(var i = 0;i < $scope.dependenceTasks.length;i ++) {
        //    if(taskRela.taskId == $scope.dependenceTasks[i].taskId) {
        //        isExists = true;
        //    }
        //}
        //if(!isExists) {
        //    $scope.dependenceTasks.push(taskRela);
        //    showAlert('success', '成功添加依赖(taskId: ' + job.taskId + ', taskName: ' + job.taskName + ')');
        //} else {
        //    showAlert('warning','该依赖任务已存在，不能重复配置')
        //}

        $scope.dependenceTasks.push(taskRela);
        showAlert('success', '成功添加依赖(taskId: ' + job.taskId + ', taskName: ' + job.taskName + ')');

    }

    //根据taskId获得其依赖列表中的index
    var getDependenceIndex = function (taskId) {
        for (var i = 0; i < $scope.dependenceTasks.length; i++) {
            if ($scope.dependenceTasks[i].taskId == taskId) {
                return i;
            }
        }
        return -1;
    };

    //根据传入的依赖信息设置默认的选择状态
    //function setDefaultSelected() {
    //    //将所有的任务都设置为未选中
    //    angular.forEach($scope.table.displayedDataList, function (job) {
    //        job.isSelected = false;
    //    });
    //    for (var i = 0; i < $scope.dependenceTasks.length; i++) {
    //        var index = getQueryResultIndex($scope.dependenceTasks[i].taskId);
    //        if (index != -1)
    //            $scope.table.displayedDataList[index].isSelected = true;
    //    }
    //}


    function processResult(result){
        result.$promise.then(
            function(data) {
                if(data.isSuccess) {
                    $scope.allTaskList = data.results;
                    $scope.table = component.getCustomizedTable($scope, $filter);
                    //setDefaultSelected();
                    $scope.hideTable = false;
                }
            }
        );
    }

});

//受影响的任务列表的Dialog的controller
var InfluencedTaskCtrl = function ($scope, $filter, component, $modalInstance, msg, JobManageService, ConstantService) {
    /***********************执行开始***********************/
    initScope();
    //setUIElements();
    getInfluencedTasks();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
        //页面是否正在加载
        $scope.isLoading = false;
        //提示信息
        $scope.alert = {
            type: '',
            msg: '',
            isShow: false
        };
    }

    function initFunctions() {
        //确定按钮的响应
        $scope.ok = function () {
            $modalInstance.close('ok');
        };
        //取消按钮的响应
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        //获得cycle的文字描述
        $scope.getCycleText = function (cycle) {
            return ConstantService.cycleToText(cycle);
        };
        //获取cycle的css样式
        $scope.getExecutionCycleLabel = function (cycle) {
            return ConstantService.getCycleCss(cycle);
        };
        //获得开发者的中文名
        //$scope.getDevelopChineseName = function (pinyinName) {
        //    return UtilsService.getDeveloperRealName(pinyinName);
        //};
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.alert.isShow = false;
        };
    }

    //function setUIElements() {
    //    $scope.developers = UtilsService.getDevelopers();
    //}

    //获取受影响的任务列表
    function getInfluencedTasks() {
        var result = JobManageService.getInfluencedTasksByTaskId({
            'taskId': $scope.msg.taskId
        });
        $scope.isLoading = true;
        $scope.closeAlert();
        process(result);
    }

    //处理获得的受影响的任务
    function process(result) {
        result.$promise.then(
            function (data) {
                $scope.isLoading = false;
                if (data.isSuccess) {
                    $scope.allTaskList = data.results;
                    $scope.table = component.getCustomizedTable($scope, $filter);
                    $scope.table.predicate = 'owner';
                    $scope.table.reverse = !$scope.table.reverse;
                }
                else {
                    showAlert('warning', data.messages)
                }
            }
        );
    };

    //显示提示信息
    function showAlert(type, msg) {
        $scope.alert.msg = msg;
        $scope.alert.type = type;
        $scope.alert.isShow = true;
    }
};

//同源任务列表的Dialog的controller
var SameSourceTaskCtrl = function ($scope, $filter, component, $modalInstance, msg, JobManageService, ConstantService) {
    /***********************执行开始***********************/
    initScope();
    //setUIElements();
    getSameSourceTasks();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
        //页面是否正在加载
        $scope.isLoading = false;
        //提示信息
        $scope.alert = {
            type: '',
            msg: '',
            isShow: false
        };
    }

    function initFunctions() {
        //确定按钮的响应
        $scope.ok = function () {
            $modalInstance.close('ok');
        };
        //取消按钮的响应
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        //获得cycle的文字描述
        $scope.getCycleText = function (cycle) {
            return ConstantService.cycleToText(cycle);
        };
        //获取cycle的css样式
        $scope.getExecutionCycleLabel = function (cycle) {
            return ConstantService.getCycleCss(cycle);
        };
        //获得开发者的中文名
        //$scope.getDevelopChineseName = function (pinyinName) {
        //    return UtilsService.getDeveloperRealName(pinyinName);
        //};
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.alert.isShow = false;
        };
    }

    //function setUIElements() {
    //    $scope.developers = UtilsService.getDevelopers();
    //}

    //获取同源任务列表
    function getSameSourceTasks() {
        var result = JobManageService.getSameSourceTasksByTaskId({
            'taskId': $scope.msg.taskId
        });
        $scope.isLoading = true;
        $scope.closeAlert();
        process(result);
    }

    //处理获得的同源任务
    function process(result) {
        result.$promise.then(
            function (data) {
                $scope.isLoading = false;
                if (data.success) {
                    $scope.allTaskList = data.results;
                    $scope.table = component.getCustomizedTable($scope, $filter);
                    $scope.table.predicate = 'owner';
                    $scope.table.reverse = !$scope.table.reverse;
                }
                else {
                    showAlert('warning', data.messages)
                }
            }
        );
    };

    //显示提示信息
    function showAlert(type, msg) {
        $scope.alert.msg = msg;
        $scope.alert.type = type;
        $scope.alert.isShow = true;
    }
};


//受影响的任务实例列表的Dialog的controller
var DirectInfluencedInstancesDialog = function ($scope, $filter, component, $modalInstance, msg, JobMonitorService, ConstantService) {
    /***********************执行开始***********************/
    initScope();
    //setUIElements();
    getDirectInfluencedInstances();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
        //页面是否正在加载
        $scope.isLoading = false;
        //提示信息
        $scope.alert = {
            type: '',
            msg: '',
            isShow: false
        };
        //显示影响任务列表
        $scope.tableShow = false;
    }

    function initFunctions() {
        //确定按钮的响应
        $scope.ok = function () {
            $modalInstance.close('ok');
        };
        //取消按钮的响应
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        //获得cycle的文字描述
        $scope.getCycleText = function (cycle) {
            return ConstantService.cycleToText(cycle);
        };
        //获取cycle的css样式
        $scope.getExecutionCycleLabel = function (cycle) {
            return ConstantService.getCycleCss(cycle);
        };
        //获得开发者的中文名
        //$scope.getDevelopChineseName = function (pinyinName) {
        //    return UtilsService.getDeveloperRealName(pinyinName);
        //};
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.alert.isShow = false;
        };
    }

    //function setUIElements() {
    //    $scope.developers = UtilsService.getDevelopers();
    //}

    //获取受影响的任务列表
    function getDirectInfluencedInstances() {
        var result = JobMonitorService.getDirectInfluencedInstancesByInstanceId({
            'instanceId': $scope.msg.instanceId
        });
        $scope.isLoading = true;
        $scope.closeAlert();
        process(result);
    }

    //处理获得的受影响的任务
    function process(result) {
        result.$promise.then(
            function (data) {
                $scope.isLoading = false;
                if (data.isSuccess) {
                    $scope.allTaskList = data.results;
                    $scope.table = component.getCustomizedTable($scope, $filter);
                    $scope.table.predicate = 'owner';
                    $scope.table.reverse = !$scope.table.reverse;
                    $scope.tableShow = true;
                }
                else {
                    showAlert('warning', data.messages)
                }
            }
        );
    };

    //显示提示信息
    function showAlert(type, msg) {
        $scope.alert.msg = msg;
        $scope.alert.type = type;
        $scope.alert.isShow = true;
    }
};

//任务预跑Dialog的controller
var PreRunTaskCtrl = function ($scope, $filter, $modalInstance, msg) {
    /***********************执行开始***********************/
    initScope();
    setUIElements();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
        //提示信息
        $scope.alert = {
            type: "",
            msg: "",
            isShow: false
        };
        //页面是否正在加载
        $scope.isLoading = false;
        //返回给调用页面的值
        $scope.returnBackTime = {
            startDate: '',
            endDate: ''
        };
    }

    function initFunctions() {
        //取消按钮的响应
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.alert.isShow = false;
        };
        //确认按钮的响应
        $scope.ok = function () {
            var endDateSplit;
            var endTimeSplit;
            var endTimeForCalcu;
            switch ($scope.datePickerStyle) {
                case 'H':
                    //计算返回的时间值
                    endDateSplit = $scope.time.startDate.split("-");
                    endTimeSplit = $scope.time.startTime.split(":");
                    endTimeForCalcu = new Date(endDateSplit[0], endDateSplit[1] - 1, endDateSplit[2], endTimeSplit[0], endTimeSplit[1], endTimeSplit[2]);
                    endTimeForCalcu.setMilliseconds(endTimeForCalcu.getMilliseconds() - 3);
                    $scope.returnBackTime.startDate = $filter("dateFormat")(endTimeForCalcu, "yyyy-MM-dd hh:mm:ss", 1);
                    endDateSplit = $scope.time.endDate.split("-");
                    endTimeSplit = $scope.time.endTime.split(":");
                    endTimeForCalcu = new Date(endDateSplit[0], endDateSplit[1] - 1, endDateSplit[2], endTimeSplit[0], endTimeSplit[1], endTimeSplit[2]);
                    endTimeForCalcu.setHours(endTimeForCalcu.getHours() + 1);
                    $scope.returnBackTime.endDate = $filter("dateFormat")(endTimeForCalcu, "yyyy-MM-dd hh:mm:ss", 1);
                    break;
                case 'D':
                case 'W':
//                    $scope.returnBackTime.startDate = $scope.time.startDate;
                    endDateSplit = $scope.time.startDate.split("-");
                    endTimeForCalcu = new Date(endDateSplit[0], endDateSplit[1] - 1, endDateSplit[2], 0, 0, 0);
                    $scope.returnBackTime.startDate = $filter("dateFormat")(endTimeForCalcu, "yyyy-MM-dd hh:mm:ss", 1);
                    endDateSplit = $scope.time.endDate.split("-");
                    endTimeForCalcu = new Date(endDateSplit[0], endDateSplit[1] - 1, endDateSplit[2], 0, 0, 0);
                    endTimeForCalcu.setDate(endTimeForCalcu.getDate() + 1);
                    $scope.returnBackTime.endDate = $filter("dateFormat")(endTimeForCalcu, "yyyy-MM-dd hh:mm:ss", 1);
                    break;
                case 'M':
                    //计算返回的时间值
                    $scope.returnBackTime.startDate = $scope.time.startYear + '-' + $scope.time.startMonth + '-01' + ' 00:00:00';
                    endTimeForCalcu = new Date($scope.time.endYear, $scope.time.endMonth - 1, "01");
                    endTimeForCalcu.setMonth(endTimeForCalcu.getMonth() + 1);
                    $scope.returnBackTime.endDate = $filter("dateFormat")(endTimeForCalcu, "yyyy-MM-dd 00:00:00", 1);
                    break;
            }
            var starttime = new Date(
                Date.parse(($scope.returnBackTime.startDate).replace(/\-/g, "/")));
            var endtime = new Date(
                Date.parse(($scope.returnBackTime.endDate).replace(/\-/g, "/")));

            if (starttime > endtime) {
                $scope.alert.isShow = true;
                $scope.alert.type = "danger";
                $scope.alert.msg = "开始时间 大于 结束时间,请修改...";
            } else {
                $modalInstance.close($scope.returnBackTime);
            }
        };

    }

    function setUIElements() {
        $scope.datePickerStyle = $scope.msg.jobExecCycle;
        $scope.time = {
            startDate: '',
            startTime: '',
            endDate: '',
            endTime: '',
            startYear: '',
            startMonth: '',
            endYear: '',
            endMonth: '',
            months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10',
                '11', '12']
        };
        setTimeByDatePickerStyle();
    }

    function setTimeByDatePickerStyle() {
        var currentTime = new Date(new Date().getTime());
        switch ($scope.datePickerStyle) {
            case 'H':
                //初始化显示值
                $scope.time.startDate = $filter('dateFormat')(currentTime.getFullYear() + "-" + (currentTime.getMonth() + 1) + "-" + currentTime.getDate(), 'yyyy-MM-dd', 0);
                $scope.time.endDate = $filter('dateFormat')(currentTime.getFullYear() + "-" + (currentTime.getMonth() + 1) + "-" + currentTime.getDate(), 'yyyy-MM-dd', 0);
                $scope.time.startTime = currentTime.getHours() >= 1 && currentTime.getHours() <= 9 ? '0' + currentTime.getHours() + ':00:00' : currentTime.getHours() + ':00:00';
                $scope.time.endTime = currentTime.getHours() >= 1 && currentTime.getHours() <= 9 ? '0' + currentTime.getHours() + ':00:00' : currentTime.getHours() + ':00:00';
                break;
            case 'D':
            case 'W':
                $scope.time.startDate = $filter('dateFormat')(currentTime.getFullYear() + "-" + (currentTime.getMonth() + 1) + "-" + currentTime.getDate(), 'yyyy-MM-dd', 0);
                $scope.time.endDate = $filter('dateFormat')(currentTime.getFullYear() + "-" + (currentTime.getMonth() + 1) + "-" + currentTime.getDate(), 'yyyy-MM-dd', 0);
                break;
            case 'M':
                //初始化显示值
                $scope.time.startYear = currentTime.getFullYear();
                $scope.time.startMonth = (currentTime.getMonth() + 1) >= 1 && (currentTime.getMonth() + 1) <= 9 ? '0' + (currentTime.getMonth() + 1) : (currentTime.getMonth() + 1);
                $scope.time.endYear = currentTime.getFullYear();
                $scope.time.endMonth = (currentTime.getMonth() + 1) >= 1 && (currentTime.getMonth() + 1) <= 9 ? '0' + (currentTime.getMonth() + 1) : (currentTime.getMonth() + 1);
                break;
        }
    }
};

//级联重跑dialog的controller
var CascadeRecallTasksCtrl = function ($scope, $filter, $modalInstance, JobMonitorService, msg) {
    /***********************执行开始***********************/
    initScope();
    setUIElements();
    getRecallCascadeInstances();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
        //提示信息
        $scope.alert = {
            type: "",
            msg: "",
            isShow: false
        };
        //页面正在加载
        $scope.isLoading = true;
        //执行过滤 与 取消过滤
        $scope.filterTags = [];
    }

    function initFunctions() {
        //点击确认的响应
        $scope.ok = function () {
            if (new Date($scope.cascadeRecallData.startDate) > new Date($scope.cascadeRecallData.endDate)) {
                showAlert('warning', '开始时间大于结束时间,请修改');
            } else {
                //获取所有选中的instances
                getSelectedNodeBeforeSubmit($scope.treeData);
                if ($scope.cascadeRecallData.instanceIds.length == 0) {
                    showAlert('warning', '重跑实例列表为空');
                    return;
                }
                $modalInstance.close($scope.cascadeRecallData);
            }
        };
        //点击取消的响应
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.alert.isShow = false;
        };
    }

    function setUIElements() {
        //返回给调用页面的信息
        $scope.cascadeRecallData = {
            startDate: '',
            endDate: '',
            instanceIds: []
        };
        //与instance的timeId相同
        var d = new Date($scope.msg.time_id);
        $scope.cascadeRecallData.startDate = $filter("dateFormat")(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate(), 'yyyy-MM-dd', 0);
        $scope.cascadeRecallData.endDate = $filter("dateFormat")(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate(), 'yyyy-MM-dd', 0);
        $scope.treeData = {};
    }

    //获得所有需要重跑的instances
    function getRecallCascadeInstances() {
        var result = JobMonitorService.recallCascadeGetInstances(
            {
                'instanceId': $scope.msg.jobInstanceId,
                'date': $scope.msg.time_id
            }
        );
        $scope.isLoading = true;
        $scope.closeAlert();
        process(result);
    }

    //处理获得的受影响的任务
    function process(result) {
        result.$promise.then(
            function (data) {
                showAlert(data.success ? 'success' : 'warning', data.messages);
                $scope.isLoading = false;
                if (data.success) {
                    $scope.cascadeJobs = data.results;   //获取的 children 任务
                    angular.forEach($scope.cascadeJobs, function (item) {
                        item.filtered = false;
                        item.deleted = false;
                    });
                    $scope.treeData = getRoot($scope.cascadeJobs);
                }
            }
        );
    }

    //返回树形结构的顶节点
    function getRoot(data) {
        var preIdToInstanceRelas = {};
        for (var i = 0; i < data.length; i++) {
            preIdToInstanceRelas[data[i].preInstanceId] = [];
        }
        for (var i = 0; i < data.length; i++) {
            if (!contains(preIdToInstanceRelas, data[i])) //去重
                preIdToInstanceRelas[data[i].preInstanceId].push(data[i]);
        }
        var elements = [];
        elements.push(data[0]);
        while (elements.length != 0) {
            var element = elements.splice(0, 1)[0];
            var instanceId = element.instanceId;
            var children = preIdToInstanceRelas[instanceId];
            if (children) {
                for (var i = 0; i < children.length; i++) {
                    if (!element.children)
                        element.children = [];
                    var cloneObj = cloneInstanceRela(children[i]);
                    element.children.push(cloneObj);
                    elements.push(cloneObj);
                }
            }
        }
        return data[0];
    }

    //拷贝InstanceRela对象
    function cloneInstanceRela(obj) {
        var instanceRela = {};
        instanceRela.filtered = obj.filtered;
        instanceRela.taskName = obj.taskName;
        instanceRela.preInstanceId = obj.preInstanceId;
        instanceRela.instanceId = obj.instanceId;
        instanceRela.taskId = obj.taskId;
        return instanceRela;
    }

    //instanceRela是否在map<instanceRela>中存在
    function contains(map, instanceRela) {
        var children = map[instanceRela.preInstanceId];
        for (var i = 0; i < children.length; i++) {
            var child = children[i];
            if (child.instanceId == instanceRela.instanceId)
                return true;
        }
        return false;
    }

    //获取给定节点下所有选中的节点
    function getSelectedNodeBeforeSubmit(node) {
        if (!(node.filtered || node.deleted)) {
            if ($scope.cascadeRecallData.instanceIds.indexOf(node.instanceId) == -1) {
                $scope.cascadeRecallData.instanceIds.push(node.instanceId);
            }
        }
        if (node.children == undefined && node._children != undefined) {
            for (var i = 0; i < node._children.length; i++) {
                getSelectedNodeBeforeSubmit(node._children[i]);
            }
        }
        if (node.children != undefined && node._children == undefined) {
            for (var i = 0; i < node.children.length; i++) {
                getSelectedNodeBeforeSubmit(node.children[i]);
            }
        }
    };

    //显示提示信息
    function showAlert(type, msg) {
        $scope.alert.msg = msg;
        $scope.alert.type = type;
        $scope.alert.isShow = true;
    }
};

//级联预跑dialog的controller
var CascadePreRunTasksCtrl = function ($scope, $filter, $modalInstance, JobManageService, msg) {
    /***********************执行开始***********************/
    initScope();
    setUIElements();
    getRecallCascadeInstances();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
        //提示信息
        $scope.alert = {
            type: "",
            msg: "",
            isShow: false
        };
        //页面正在加载
        $scope.isLoading = true;
        $scope.deleteNumber = 0;
        $scope.filterTags = [];
    }

    function initFunctions() {
        //点击确认的响应
        $scope.ok = function () {
            if (new Date($scope.cascadePreRunData.startDate) > new Date($scope.cascadePreRunData.endDate)) {
                showAlert('warning', '开始时间大于结束时间,请修改');
            } else {
                //获取所有选中的instances
                getSelectedNodeBeforeSubmit($scope.treeData);
                if ($scope.cascadePreRunData.taskIds.length == 0) {
                    showAlert('warning', '预跑任务列表为空');
                    return;
                }
                var endDateSplit = $scope.cascadePreRunData.endDate.split("-");
                var endTimeForCalcu = new Date(endDateSplit[0], endDateSplit[1] - 1, endDateSplit[2]);
                endTimeForCalcu.setDate(endTimeForCalcu.getDate() + 1);
                $scope.cascadePreRunData.endDate = $filter("dateFormat")(endTimeForCalcu, "yyyy-MM-dd", 1);
                $modalInstance.close($scope.cascadePreRunData);
            }
        };
        //点击取消的响应
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.alert.isShow = false;
        };
    }

    function setUIElements() {
        //返回给调用页面的信息
        $scope.cascadePreRunData = {
            startDate: '',
            endDate: '',
            taskIds: [],
            instanceIds: []
        };
        var d = new Date(new Date().getTime());
        $scope.cascadePreRunData.startDate = $filter("dateFormat")(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate(), 'yyyy-MM-dd', 0);
        $scope.cascadePreRunData.endDate = $filter("dateFormat")(d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate(), 'yyyy-MM-dd', 0);
        $scope.treeData = {};
    }

    //获得所有需要重跑的instances
    function getRecallCascadeInstances() {
        var result = JobManageService.getTasksForCascadePreRun(
            {
                'taskId': $scope.msg.taskId
            }
        );
        $scope.isLoading = true;
        $scope.closeAlert();
        process(result);
    }

    //处理获得的受影响的任务
    function process(result) {
        result.$promise.then(
            function (data) {
                $scope.isLoading = false;
                if (data.success) {
                    $scope.cascadeJobs = data.results;   //获取的 children 任务
                    angular.forEach($scope.cascadeJobs, function (item) {
                        item.filtered = false;
                        item.deleted = false;
                    });
                    $scope.treeData = getRoot($scope.cascadeJobs);
                }
            }
        );
    }

    //返回树形结构的顶节点
    function getRoot(data) {
        var preIdToTaskRelas = {};
        for (var i = 0; i < data.length; i++) {
            preIdToTaskRelas[data[i].taskPreId] = [];
        }
        for (var i = 0; i < data.length; i++) {
            if (!contains(preIdToTaskRelas, data[i])) //去重
                preIdToTaskRelas[data[i].taskPreId].push(data[i]);
        }
        var elements = [];
        elements.push(data[0]);
        while (elements.length != 0) {
            var element = elements.splice(0, 1)[0];
            var taskId = element.taskId;
            var children = preIdToTaskRelas[taskId];
            if (children) {
                for (var i = 0; i < children.length; i++) {
                    if (!element.children)
                        element.children = [];
                    var cloneObj = cloneTaskRela(children[i]);
                    element.children.push(cloneObj);
                    elements.push(cloneObj);
                }
            }
        }
        return data[0];
    }

    //拷贝taskRela对象
    function cloneTaskRela(obj) {
        var taskRela = {};
        taskRela.cycleGap = obj.cycleGap;
        taskRela.filtered = obj.filtered;
        taskRela.owner = obj.owner;
        taskRela.remark = obj.remark;
        taskRela.taskName = obj.taskName;
        taskRela.taskPreId = obj.taskPreId;
        taskRela.taskId = obj.taskId;
        taskRela.timeStamp = obj.timeStamp;
        return taskRela;
    }

    //taskRela是否在map<taskRela>中存在
    function contains(map, taskRela) {
        var children = map[taskRela.taskPreId];
        for (var i = 0; i < children.length; i++) {
            var child = children[i];
            if (child.taskId == taskRela.taskId)
                return true;
        }
        return false;
    }

    //获取给定节点下所有选中的节点
    function getSelectedNodeBeforeSubmit(node) {
        if (!(node.filtered || node.deleted)) {
            if ($scope.cascadePreRunData.taskIds.indexOf(node.taskId) == -1) {
                $scope.cascadePreRunData.taskIds.push(node.taskId);
            }
        }
        if (node.children == undefined && node._children != undefined) {
            for (var i = 0; i < node._children.length; i++) {
                getSelectedNodeBeforeSubmit(node._children[i]);
            }
        }
        if (node.children != undefined && node._children == undefined) {
            for (var i = 0; i < node.children.length; i++) {
                getSelectedNodeBeforeSubmit(node.children[i]);
            }
        }
    };

    //显示提示信息
    function showAlert(type, msg) {
        $scope.alert.msg = msg;
        $scope.alert.type = type;
        $scope.alert.isShow = true;
    }
};

//展示信息的dialog的controller
var MessageCtrl = function ($scope, $modalInstance, msg) {
    /***********************执行开始***********************/
    initScope();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
    }

    function initFunctions() {
        //点击确认按钮
        $scope.ok = function () {
            $modalInstance.close('ok');
        };
        //点击取消按钮
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }
};

//任务实例状态分析的dialog的controller
var InstanceStateAnalyzeCtrl = function ($scope, $modalInstance, msg, JobMonitorService) {
    /***********************执行开始***********************/
    initScope();
    stateAnalyze();
    /***********************执行结束***********************/


    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //展示的信息
        $scope.msg = msg;
    }

    function initFunctions() {
        //点击确认按钮
        $scope.ok = function () {
            $modalInstance.close('ok');
        };
        //点击取消按钮
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    function stateAnalyze() {
        var result = JobMonitorService.instanceStatusAnalyze({
                'instanceId': $scope.msg.instanceId
            }
        );
        process(result);
    }

    //处理查询结果
    function process(result) {
        result.$promise.then(
            function (data) {
                $scope.isLoading = false;
                if (data.success) {
                    $scope.msg.bodyText = data.result;
                }
            }
        );
    }

};




/**
 * Created by wei.shen on 2015/9/16.
 */

fanliApp.controller('feedbackCtrl',function($scope,$resource) {
    var feedback = $resource('/fanli/feedbacks');
    init();

    function init() {
        $scope.feedback_title = '';
        $scope.feedback_content = '';
        $scope.comments = [];
        getFeedbackList();
    }

    function getFeedbackList() {
        feedback.get({},function(data) {
            if(data.isSuccess) {
                console.log(data.results);
                $scope.comments = data.results;
            }
        })
    }


    $scope.saveFeedback = function() {
        if( $scope.feedback_title == '' || $scope.feedback_content == '') {
            return;
        }
        feedback.save({comment:$scope.feedback_content,title:$scope.feedback_title,user:'somebody'},
            function(data) {
                location.reload();
                console.log(data.id);
            }
        );
    }

    //$scope.getUrl = function(index) {
    //    var fed = $scope.comments[index];
    //    return
    //}

})

/**
 * Created by wei.shen on 2015/11/5.
 */

fanliApp.controller('instanceStatusCtrl',function($scope,$resource,$routeParams) {
    $scope.status_id = $routeParams.instanceId;
    var treeResource = $resource("/fanli/monitor/instanceTree/:instanceId",{instanceId:'@instanceId'});
    var treeChart =echarts.init(document.getElementById('instanceTree'));
    treeResource.get({instanceId:$scope.status_id},
        function(data) {
            if(data.code == 200) {
                var option = getRelaTreeOption(data.tree);
                console.log(JSON.stringify(option));
                console.log('----');
                treeChart.setOption(option);
                console.log(treeChart.getOption())


            }
        }
    )

    var getRelaTreeOption = function(tree) {
        var option = {

        };
        //title
        var title = {
            text: '树图'
        }
        title.subtext = tree.self.instanceId + ':' + tree.self.taskName  + "的血缘树";
        option.title = title;


        //toolbox
        //var toolbox = {
        //    show : true,
        //    feature : {
        //        mark : {show: true},
        //        dataView : {show: true, readOnly: false},
        //        restore : {show: true},
        //        saveAsImage : {show: true}
        //    }
        //};
        //option.toolbox = toolbox;


        //legend
        //var legend = {
        //    orient:'vertical',
        //    x:'right',
        //    data:[
        //        {name:'success',textStyle:{color:'#458B00'}}
        //    ]
        //}
        //option.legend = legend;

        //series
        var series = [
            {
                name: '树图',
                type: 'tree',
                orient: 'vertical',  // vertical horizontal
                rootLocation: {x: 'center', y: 'center'}, // 根节点位置  {x: 100, y: 'center'}
                nodePadding: 150,
                roam: true,
                direction: 'inverse',
                symbol: 'rectangle',
                symbolSize:30,
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            formatter: "{b}"
                        },
                        lineStyle: {
                            color: '#48b',
                            shadowColor: '#000',
                            shadowBlur: 3,
                            shadowOffsetX: 3,
                            shadowOffsetY: 5,
                            type: 'dotted' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                        }
                    },
                    emphasis: {
                        label: {
                            show: true
                        }
                    }
                }
            }
        ];
        var data = getTreeDataRecursion(tree);
        series[0].data = [];
        series[0].data.push(data);
        option.series = series;
        return option;
    }

    function getTreeDataRecursion(tree) {
        var base = {};
        if(tree.self) {
            base.name = tree.self.taskName;
            base.itemStyle = {

                normal:{
                    color:getColorByStatus(tree.self.status)
                }
            }
        };
        var children = [];
        if(tree.parent != null) {
            console.log(tree.parent.length)
            for(var i = 0;i < tree.parent.length;i ++) {
                console.log(tree.parent[i])
                var tmp = getTreeDataRecursion(tree.parent[i]);
                children.push(tmp);
            }
            base.children = children;
        }


        return base;

    }

    function getColorByStatus(status) {
        switch (status) {
            case 0:return '#E3E3E3';
            case 1:return '#458B00';
            case 2:return '#63B8FF';
            case 3:return '#878787';
            case 4:return '#EEEE00';
            case 5:return '#EEAD0E';
            case 6:return '#FFFCC7';
            case 7:return '#0000FF';
            case -1:return '#FF3030';
            default :return '#ffffff';
        }
    }

    var op = {
        "title": {
            "text": "树图",
            "subtext": "1000532015081800的血缘树"
        },
        "toolbox": {
            "show": true,
            "feature": {
                "mark": {
                    "show": true
                },
                "dataView": {
                    "show": true,
                    "readOnly": false
                },
                "restore": {
                    "show": true
                },
                "saveAsImage": {
                    "show": true
                }
            }
        },
        "series": [
            {
                "name": "树图",
                "type": "tree",
                "orient": "vertical",
                "rootLocation": {
                    "x": "center",
                    "y": 350
                },
                "nodePadding": 50,
                "roam": "scale",
                "direction": "inverse",
                "symbol": "rectangle",
                "itemStyle": {
                    "normal": {
                        "label": {
                            "show": true,
                            "formatter": "{b}"
                        },
                        "lineStyle": {
                            "color": "#48b",
                            "shadowColor": "#000",
                            "shadowBlur": 3,
                            "shadowOffsetX": 3,
                            "shadowOffsetY": 5,
                            "type": "curve"
                        }
                    },
                    "emphasis": {
                        "label": {
                            "show": true
                        }
                    }
                },
                "data": {
                    "name": "1000532015081800",
                    "itemStyle": {
                        "normal": {
                            "color": "#458B00"
                        }
                    },
                    "children": [
                        {
                            "name": "1000352015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            }
                        },
                        {
                            "name": "1000412015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            },
                            "children": [
                                {
                                    "name": "1000352015081800",
                                    "itemStyle": {
                                        "normal": {
                                            "color": "#458B00"
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            "name": "1000472015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            }
                        },
                        {
                            "name": "1000492015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            }
                        }
                    ]
                }
            }
        ]
    }


})

/**
 * Created by wei.shen on 2015/7/29.
 */

fanliApp.controller("MonitorCtrl",function($scope,$http,$filter,$modal,$resource,component,ConstantService,JobMonitorService) {
    var dateToStr = function (x, y) {
        var z = {M: x.getMonth() + 1, d: x.getDate(), h: x.getHours(), m: x.getMinutes(), s: x.getSeconds()};
        y = y.replace(/(M+|d+|h+|m+|s+)/g, function (v) {
            return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-2)
        });
        return y.replace(/(y+)/g, function (v) {
            return x.getFullYear().toString().slice(-v.length)
        });
    }
    var initDate = function() {
        return dateToStr(new Date(), "yyyy-MM-dd");
    }
    $scope.startDate = initDate();
    $scope.endDate = initDate();
    $scope.hideTable = true;
    $scope.executionStatusOptions = ConstantService.getJobMonitorStatus();
    $scope.jobStatus = $scope.executionStatusOptions[1].ID;
    $scope.ifPreRun = false;
    $scope.submitSearch = function() {
        setAlert(false,'','');
        setLoding(true);
        $http.get("/fanli/monitor/query",{params:{
            taskId:$scope.taskId,
            owner:$scope.developer,
            startTime:$scope.startDate,
            endTime:$scope.endDate,
            status:$scope.jobStatus,
            isPre:$scope.ifPreRun?1:0
        }}).success(function(data) {
            //$scope.jobInstanses = data.results;
            //$scope.reverse = true;
            //$scope.jobInstanses =  $filter('orderBy')(data.results, 'startTime',$scope.reverse);
            $scope.allTaskList = data.results;
            $scope.table = component.getCustomizedTable($scope, $filter);
            $scope.hideTable = false;
            console.log($scope.table);
            setLoding(false);
        }).error(function() {
            setAlert(true,'alert-danger','未知异常');
            setLoding(false);
        })
    };

    $scope.getTimeid = function(id,cycle) {
        if(id.indexOf('pre') >= 0) {
            if(cycle == 'D') {
                return id.substring(10,14) + '-' + id.substring(14,16) + '-' + id.substring(16,18);
            } else if(cycle == 'H'){
                return id.substring(10,14) + '-' + id.substring(14,16) + '-' + id.substring(16,18) + '  ' + id.substring(18,20);
            } else {
                return id;
            }
        } else {
            if(cycle == 'D') {
                return id.substring(6,10) + '-' + id.substring(10,12) + '-' + id.substring(12,14);
            } else if(cycle == 'H'){
                return id.substring(6,10) + '-' + id.substring(10,12) + '-' + id.substring(12,14) + '  ' +
                    '' + id.substring(14,16);
            } else return id;
        }
        return id;
    }

    $scope.handleJobType = function() {
        $scope.submitSearch();
    }

    $scope.getExecutionCycleLabel = function (cycle) {
        return ConstantService.getCycleCss(cycle);
    };


    $scope.getStatusText = function (status) {
        return ConstantService.statusToText(status);
    };


    $scope.getCycleText = function (cycle) {
        return ConstantService.cycleToText(cycle);
    };

    //响应回车事件
    $scope.enterPress = function (keyEvent) {
        if (keyEvent.which === 13) {
            $scope.submitSearch();
        }
    };

    $scope.instanceInfo = function (index) {
        var job = getJobByIndex(index);
        window.open("#/job_log/" + job.taskStatusId );
    };


    //根据实际的index获得job
    function getJobByIndex(index) {
        var selectIndex = getSelectedIndex(index);
        return $scope.table.displayedDataList[selectIndex]; //需要设置的任务
    }

    //根据当前页的index获得实际的index
    function getSelectedIndex(index) {
        return  index + ($scope.table.startIndex - 1);
    };
    $scope.reverse = true;
    $scope.$watch('reverse',function() {
        $scope.jobInstanses =  $filter('orderBy')($scope.jobInstanses, 'startTime',$scope.reverse);
    })

    $scope.getSortingClass = function () {
            if ($scope.reverse) {
                return 'sorting_asc';
            }
            else {
                return 'sorting_desc';
            }

        return 'sorting';
    };


    $scope.showRelaTree = function(index) {
        var job = getJobByIndex(index);
        window.open("#/instance_status_tree/" + job.taskStatusId );

    }

    //重跑任务
    $scope.reRunJob = function (index) {
        //console.log(index)
        var job = getJobByIndex(index);
        $scope.message = {
            headerText: '提示',
            bodyText: '你是否要重跑任务实例: ' + job.taskStatusId + " ?",
            actionButtonStyle: 'btn-danger',
            showCancel: true
        };
        var modalInstance = $modal.open({
            templateUrl: '/assets/pages/dialog/messageEnsure.html',
            controller: MessageCtrl,
            resolve: {
                msg: function () {
                    return $scope.message;
                }
            }
        });
        modalInstance.result.then(function (data) {
            var result = JobMonitorService.recallInstance({
                    'instanceId': job.taskStatusId
                }
            );
            $scope.isLoading = true;
            result.$promise.then(function(data) {
                $scope.isLoading = false;
                //$scope.jobInstanses[index].status = 0;
                $scope.table.displayedDataList[getSelectedIndex(index)].status = 0;
                setAlert(true,'alert-success','成功添加重跑任务' + job.taskStatusId + ':' + job.taskName);

            },function(){})
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };
    //置为成功
    $scope.successJob = function (index) {
        var job = getJobByIndex(index);
        $scope.message = {
            headerText: '警告: 以下任务实例将会直接受到影响，请确认是否将任务实例' + job.taskStatusId + '置为成功？',
            actionButtonStyle: 'btn-primary',
            instanceId: job.taskStatusId
        };
        var modalInstance = $modal.open({
            templateUrl: '/assets/pages/dialog/directInfluencedInstancesDialog.html',
            controller: DirectInfluencedInstancesDialog,
            windowClass: 'taskQueryDialog',
            resolve: {
                msg: function () {
                    return $scope.message;
                }
            }
        });
        modalInstance.result.then(function (data) {
            var result = JobMonitorService.successInstance({
                    'instanceId': job.taskStatusId
                }
            );
            $scope.isLoading = true;
            setAlert(false,'','');
            result.$promise.then(
                function (data) {
                    setAlert(true,'alert-success',data.messages);
                    $scope.isLoading = false;
                    if (data.isSuccess) {
                        $scope.table.displayedDataList[getSelectedIndex(index)].status = 1;
                    }
                });
        }, function () {
        });
    };

    var setAlert = function(a,b,c) {
        $scope.alertShow = a;
        $scope.alertType = b;
        $scope.AlertMsg = c;
    }

    var setLoding = function(a) {
        $scope.isLoading = a;
    }

    var itemStyle = {
        normal:{

        }
    }



    var option = {
        title : {
            text: '树图',
            subtext: ''
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : false,

        series : [
            {
                name:'树图',
                type:'tree',
                orient: 'vertical',  // vertical horizontal
                rootLocation: {x: 'center',y: 350}, // 根节点位置  {x: 100, y: 'center'}
                nodePadding: 3,
                roam:'scale',
                direction:'inverse',
                symbol:'rectangle',
                itemStyle: {
                    normal: {
                        label: {
                            show: false,
                            formatter: "{b}"
                        },
                        lineStyle: {
                            color: '#48b',
                            shadowColor: '#000',
                            shadowBlur: 3,
                            shadowOffsetX: 3,
                            shadowOffsetY: 5,
                            type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

                        }
                    },
                    emphasis: {
                        label: {
                            show: true
                        }
                    }
                },

                data: [
                    {
                        name: '根节点',
                        value: 6,
                        itemStyle: {
                            normal:{
                                color:'#7b6800'
                            }

                        },
                        children: [
                            {
                                name: '节点1',
                                value: 4,
                                children: [
                                    {
                                        name: '叶子节点1',
                                        value: 4

                                    },
                                    {
                                        name: '叶子节点2',
                                        value: 4
                                    },
                                    {
                                        name: '叶子节点3',
                                        value: 2
                                    },
                                    {
                                        name: '叶子节点4',
                                        value: 2
                                    },
                                    {
                                        name: '叶子节点5',
                                        value: 2
                                    },
                                    {
                                        name: '叶子节点6',
                                        value: 4
                                    }
                                ]
                            },
                            {
                                name: '节点2',
                                value: 4,
                                children: [{
                                    name: '叶子节点7',
                                    value: 4
                                },
                                    {
                                        name: '叶子节点8',
                                        value: 4
                                    }]
                            },
                            {
                                name: '节点3',
                                value: 1,
                                children: [
                                    {
                                        name: '叶子节点9',
                                        value: 4
                                    },
                                    {
                                        name: '叶子节点10',
                                        value: 4
                                    },
                                    {
                                        name: '叶子节点11',
                                        value: 2
                                    },
                                    {
                                        name: '叶子节点12',
                                        value: 2
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    };



})

/**
 * Created by wei.shen on 2015/7/29.
 */
fanliApp.controller('jobLogCtrl',function($scope,$routeParams,$http) {
    $scope.status_id = $routeParams.instanceId;

    var getJobLog = function(id) {
        $http.get("/fanli/log/" + id).success(function(data) {
            $scope.log_content = data.log;
        });
    };

    getJobLog($scope.status_id);

})
/**
 * Created by wei.shen on 2015/7/16.
 */

fanliApp.controller("myTaskCtrl",['$scope','$filter','$modal','$http','JobManageService','component','ConstantService', function($scope,$filter,$modal,$http,JobManageService,component,ConstantService) {
    $scope.taskGroupOptions= ConstantService.getTaskGroupOption();

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

    //设置数据监控
    $scope.setJobMonitor = function(index) {
        var job = getJobByIndex(index);
        $http.get("/fanli/dataMonitor/"+job.taskId)
            .success(function(data) {
                if(data == '') {
                    window.open('#/data_monitor/new/' + job.taskId);
                } else {
                    window.open('#/data_monitor/edit/' + job.taskId);
                }
            })
    }

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

    //生效任务
    $scope.validateJob = function(index) {
        var job = getJobByIndex(index);
        $scope.isLoading = true;
        var result = JobManageService.validateTask({
            'taskId': job.taskId
        });
        $scope.closeAlert();
        result.$promise.then(function(data) {
            if(data.isSuccess) {
                $scope.isLoading = false;
                $scope.showAlert('alert-success','成功上线任务' + job.taskName);
                $scope.submitQuery();

            } else {
                $scope.isLoading = false;
                $scope.showAlert('alert-danger','上线失败！');
            }
        },function(data) {
            $scope.isLoading = false;
            $scope.showAlert('alert-danger','上线失败！');
        })
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
                'taskId': job.taskId
            });
            $scope.isLoading = true;
            $scope.closeAlert();
            result.$promise.then(function(data) {
                if(data.isSuccess) {

                    $scope.isLoading = false;
                    $scope.showAlert('alert-success','成功将任务' + job.taskName + '从调度下线');
                    $scope.submitQuery();
                } else {
                    $scope.isLoading = false;
                    $scope.showAlert('alert-danger','下线失败！');
                }
            },function(data) {
                $scope.isLoading = false;
                $scope.showAlert('alert-danger','下线失败！');
            })
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    $scope.deleteJob = function(index) {
        var job = getJobByIndex(index);
        $scope.message = {
            headerText: '警告: 以下任务将会受到影响，请确认是否删除任务' + job.taskId + '?',
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
            var result = JobManageService.deleteTask({
                'taskId': job.taskId
            });
            $scope.isLoading = true;
            $scope.closeAlert();
            result.$promise.then(function(data) {
                if(data.isSuccess) {

                    $scope.isLoading = false;
                    $scope.showAlert('alert-success','成功将任务' + job.taskName + '从调度删除，如要恢复，请找开发同学');
                    $scope.submitQuery();
                } else {
                    $scope.isLoading = false;
                    $scope.showAlert('alert-danger','删除失败！');
                }
            },function(data) {
                $scope.isLoading = false;
                $scope.showAlert('alert-danger','删除失败！');
            })
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    }


    // * *******************************************************任务操作***************************************************************


    //根据实际的index获得job
    function getJobByIndex(index) {
        var selectIndex = getSelectedIndex(index);
        return $scope.table.displayedDataList[selectIndex]; //需要设置的任务
    }

    //根据当前页的index获得实际的index
    function getSelectedIndex(index) {
        return  index + ($scope.table.startIndex - 1);
    };

    //获得周期的文字描述
    $scope.getCycleText = function (cycle) {
        return ConstantService.cycleToText(cycle);
    };
    //获得周期的css
    $scope.getExecutionCycleLabel = function (cycle) {
        return ConstantService.getCycleCss(cycle);
    };

    $scope.getGroupIdText = function(id) {
        return ConstantService.taskGroupIdToText(id);
    };

    $scope.getTaskGroupCss = function(id) {
        return ConstantService.getTaskGroupCss(id);
    }
    //响应回车事件
    $scope.enterPress = function (keyEvent) {
        if (keyEvent.which === 13) {
            $scope.submitQuery();
        }
    };


}]);


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
    $scope.timePatternOptions = ConstantService.getTimePatterns();
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

    $scope.setCommandTime = function() {
        $scope.conf_para1 = $scope.conf_para1 + ' ' + $scope.time_pattern;
    }


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
            tableName:$scope.conf_table_name,
            resource:getResource(),
            command:$scope.conf_para1,
            cycle:$scope.conf_cycle,
            priority:$scope.conf_priority,
            ifRecall:$scope.conf_ifRecall,
            ifWait:0,
            ifPre:hasPre(),
            ifEnable:1,
            freq:$scope.conf_frequency,
            owner:$scope.conf_developer.cnName,
            waitCode:$scope.conf_waitCode,
            recallCode:"",
            successCode:$scope.conf_successCode,
            timeout:$scope.conf_timeout,
            recallInterval:$scope.conf_recallInterval,
            logFile:"/data1/log/applog",
            addUser:$scope.conf_developer.cnName,
            updateUser:$scope.conf_developer.cnName,
            type:$scope.conf_task_type == 'transfer'?1:2,
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

    function getResource() {
        if($scope.conf_task_type == 'calculate') {
            return 'shell';
        } else if($scope.conf_task_type == 'transfer') {
            return 'shell_transport';
        }
    }

    function getPreList() {
        var list = [];
        if($scope.dependenceTasks.length > 0) {
            for(var i = 0;i < $scope.dependenceTasks.length;i ++) {
                list.push({
                    taskId:$scope.generatedTaskId,
                    preId:$scope.dependenceTasks[i].taskId,
                    offset:parseInt($scope.dependenceTasks[i].offset.substring(1))
                })
            }
        }
        console.log(list);
        return list;
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

    function addPreRelaTaskToDatabase(taskid) {
        $scope.generatedTaskId = taskid;
        if ($scope.dependenceTasks.length > 0) {
            $http.post("/fanli/taskManager/taskpreadd",
                JSON.stringify(getPreList())
            ).success(function (data) {
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

    //function addPreRelaTaskToDatabase(taskid) {
    //    $scope.generatedTaskId = taskid;
    //    if ($scope.dependenceTasks.length > 0) {
    //        $http.post("/fanli/taskManager/taskpreadd", {
    //            taskId: $scope.generatedTaskId,
    //            preId: getPreTasks()
    //        }).success(function (data) {
    //            if(data.isSuccess) {
    //                setLoading(false,'');
    //                setAlert(true,'alert-success',"新增任务成功");
    //                $scope.taskConfSave = true;
    //            } else {
    //                setLoading(false,'');
    //                setAlert(true,'alert-danger',"保存调度任务，但保存依赖信息失败");
    //            }
    //            scrollToTop();
    //        }).error(function() {
    //            setAlert(true,'alert-danger',"保存调度任务，但保存依赖信息失败");
    //            setLoading(false,'');
    //        })
    //    } else {
    //        $scope.taskConfSave = true;
    //        setLoading(false,'');
    //        setAlert(true,'alert-success',"新增任务成功");
    //    }
    //};

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
/**
 * Created by wei.shen on 2015/7/16.
 */

fanliApp.controller("sidebarCtrl",function($scope,$location) {
    $scope.isActive = function (route) {
        return route === $location.path();
    }
});

/**
 * Created by wei.shen on 2015/11/30.
 */

fanliApp.controller('tableAccessCtrl',function($scope,$http) {


    setLoading(false,'');

    $scope.getDdlContent = function() {
        setLoading(true,'正在获取脚本内容...');
        $scope.ddlShow = false;
        var ret = $http.get("/fanli/domain/ddlSql",
            {
                params: {
                    path:$scope.ddlPath
                }
            }
        );
        ret.success(function(data) {

                $scope.ddl = data.log;
            $scope.ddlShow = true;
            setLoading(false,'');
        }).error(function(data) {
            alert("The ddl file is not found");
            setLoading(false,'');
        });
    }

    $scope.excuteDDL = function() {

        if($scope.ddl.toLowerCase().indexOf('drop') > 0) {
            alert('ddl语句中不能有drop');
            return;
        }
        setLoading(true,'正在执行...');
        $scope.exeSuccess = false;
        $http.get("/fanli/table/hiveCommand",{params:{path:$scope.ddlPath}})
            .success(function(data) {
                $scope.ddl +="\n";
                $scope.ddl += data.log;
                $scope.exeSuccess = true;
                setLoading(false,'');
            }).error(function(data) {alert("excute ddl failed");setLoading(false,'');})
    }

    function setLoading(a,b) {
        $scope.isLoading = a;
        $scope.loadingMsg = b;
    }


})
/**
 * Created by wei.shen on 2015/8/4.
 */

fanliApp.controller('taskEditCtrl',function($scope,$resource,$modal,$routeParams,JobManageService,ConstantService){
    $scope.taskid = $routeParams.taskid;
    $scope.Loading = true;
    $scope.LoadingMsg = '加载中...';
    $scope.taskGroupOptions= ConstantService.getTaskGroupOption();
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
        //var ret = JobManageService.updatePre({},JSON.stringify(getPreList()));
        var ret = JobManageService.updatePre({},{
            taskId:$routeParams.taskid,
            cfgs:getPreList()
        });
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
/**
 * Created by wei.shen on 2015/8/6.
 */

fanliApp.controller('transferEditCtrl',function($scope,$routeParams,$modal,$http,$resource,JobManageService,ConstantService,DimService) {
    initUI();
    function initUI() {
        //getDevelopers();
        //设置网页标题为taskid
        window.document.title = $routeParams.taskid;
        $scope.taskGroupOptions = ConstantService.getTaskGroupOption();
        $scope.cycleOptions = ConstantService.getCycleOptions();
        $scope.priorityOptions = ConstantService.getPriorityOption();
        $scope.ifRecallOptions = ConstantService.getIfRecallOption();
        $scope.recallLimitOptions = ConstantService.getRecallLimitOption();
        $scope.recallIntervalOptions = ConstantService.getRecallIntervalOption();
        $scope.offsetOptions = ConstantService.getOffsetOption();
        $scope.timeoutOptions = ConstantService.getTimeOutOption();

        $scope.sql_disable = true;
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
            owner:$scope.conf_developer.cnName,
            waitCode:$scope.conf_waitCode,
            successCode:$scope.conf_successCode,
            timeout:$scope.conf_timeout,
            recallInterval:$scope.conf_recallInterval,
            updateUser:$scope.conf_developer.cnName,
            offset:$scope.conf_offset,
            recallLimit:$scope.conf_recallLimit,
            ifPre:hasPre()
        });

        result.$promise.then(function(data) {
            if(data.isSuccess) {
                updatePres();


            }
        },function() {})

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

    function hasPre() {
        if($scope.dependenceTasks.length > 0) {
            return 1;
        } else return 0;
    };
    var updatePres = function() {

        //var ret = JobManageService.updatePre({},JSON.stringify(getPreList()));
        var ret = JobManageService.updatePre({},{
            taskId:$routeParams.taskid,
            cfgs:getPreList()
        });

        ret.$promise.then(function(data) {
            if(data.isSuccess) {
                submitSql();

            }
        },function() {

        })

    };

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

    var submitSql = function() {
        if(!$scope.sql) {
            setLoading(false,'');
            showAlert('修改成功');
            return;
        }
        var sql = $scope.sql.trim();
        var columns = sql.split('select')[1].trim().split('from')[0];
        var where = '';
        if(sql.indexOf('where') >= 0) {
            where = sql.split('where')[1].trim();
        }
        if(columns == '' || columns == undefined || columns == null) {
            return;
        }
        $scope.paramMap.sql = $scope.sql;
        $scope.paramMap.columns = columns;
        if(where == ''|| where == undefined) delete $scope.paramMap.where;
        else $scope.paramMap.where = where;

        var param = $resource('/fanli/load/sql',{taskid:'@taskid',paramMap:'@paramMap'});
        param.save({},{
            taskid:$routeParams.taskid,
            paramMap:$scope.paramMap
        },function(data) {
            if(data.isSuccess) {
                setLoading(false,'');
                showAlert('修改成功');
                console.log("修改sql成功")

            }
        });
        console.log($scope.paramMap);
    };

    $scope.submitWriterParam =  function () {
        if(!$scope.preSql) {
            $scope.preEdit=!$scope.preEdit;
            return;
        }
        $scope.writerParamMap.pre = $scope.preSql;
        var param = $resource('/fanli/load/pre',{taskid:'@taskid',paramMap:'@paramMap'});
        param.save({},{
            taskid:$routeParams.taskid,
            paramMap:$scope.writerParamMap
        },function(data) {
            if(data.isSuccess) {
                $scope.preEdit=!$scope.preEdit
                console.log("pre sql update successfully!")
            }
        },function(data) {
            alert("pre sql update failed!")
        });
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
                getTransgerSql(id);
                getPresql(id);
                setLoading(false,'');
        },function() {
                $scope.errorShow = true;
                $scope.cfgMsg = '获取传输配置信息失败，请联系开发';
            })
    }

    function getPresql(id) {
        $http.get("/fanli/load/pre",{
            params:{
                taskId:id
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.writerParamMap = JSON.parse(data.result);
                var pre_sql = $scope.writerParamMap.pre;
                $scope.preSql = pre_sql;
                setLoading(false,'');
            }
        }).error(function(data) {

            setLoading(false,'');
        })
    }

    function getTransgerSql(id) {
        var ret = $http.get("/fanli/load/sql",{
            params:{
                taskid:id
            }
        });

        ret.success(function(data) {
            if(data.isSuccess) {
                $scope.paramMap = JSON.parse(data.result);
                var trans_sql = $scope.paramMap.sql;
                $scope.sql = trans_sql;
                setLoading(false,'');
            }
        }).error(function(data) {

            setLoading(false,'');
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

    $scope.getOffsetOptions = function(cycle) {
        return ConstantService.getOffsetsByCycle(cycle);
    }

    $scope.getCycleText = function(cycle) {
        return ConstantService.cycleToText(cycle);
    }

    $scope.getExecutionCycleLabel = function(cycle) {
        return ConstantService.getCycleCss(cycle);
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
'use strict'
/**
 * Created by wei.shen on 2015/8/5.
 */

fanliApp.controller('transportTaskAddCtrl',function($scope,$http,$modal,TableService,DimService,ConstantService,JobManageService,JobMonitorService) {

    initUI();

    function getDevelopers (){
        var rep = DimService.queryAllDevelopers({},{});
        rep.$promise.then(function(data) {
            if(data.isSuccess) {
                $scope.developerOptions = data.results;
            }

        },function() {


        })
    };

    $scope.$watch('[conf_cycle,conf_target_table_type]',function() {
        if($scope.conf_target_table_type == 'full') {
            $scope.partitionDescOptions=[{id:0 ,name:'n',desc:'无分区'}];
        } else{
            switch ($scope.conf_cycle) {
                case 'H':$scope.partitionDescOptions = [
                    {id:1 ,name:'h',desc:'天(ds),小时(hour)'},
                    {id:2 ,name:'d',desc:'天(ds)'}
                ];break;
                case 'D':$scope.partitionDescOptions = [
                    {id:1 ,name:'d',desc:'天(ds)'}
                ];break;
                case 'W':$scope.partitionDescOptions = [
                    {id:1 ,name:'w',desc:'周(week)'}
                ];break;
                case 'M':$scope.partitionDescOptions = [
                    {id:1 ,name:'m',desc:'月(month)'}
                ];break;
                case 'Y':$scope.partitionDescOptions = [
                    {id:1 ,name:'y',desc:'年(year)'}
                ];break;
            }
            if($scope.conf_target_table_type == 'append_only') {
                $scope.partitionDescOptions.push({id:0 ,name:'n',desc:'无分区'});
            }
        }

    });

    $scope.$watch('conf_target_table_type',function() {
        $scope.getTargetTableName();
    })

    $scope.returnStep2 = function() {
        $scope.step3 = false;
        $scope.step2 = true;
    }

    //配置依赖
    $scope.showDependenceDialog = function () {
        $scope.dependenceTasks = [];
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


    $scope.targetSelect  = function () {
        //setLoading(true,"正在查询...");
        //console.log('target select is ' + $scope.conf_target);
        if($scope.conf_target == 'hive') {
            $scope.target_database_options = ['load','ods','dim','tmpdb'];
        }else {
            var source= DimService.querySourceDB({
                type:$scope.conf_target
            });
            source.$promise.then(function(data) {
                if(data.isSuccess) {
                    $scope.target_domain_options = data.results;
                    //setLoading(false,'');
                }
            },function(){})
        }
        console.log($scope.conf_target)
    }

    $scope.sourceSelect = function() {
        //setLoading(true,"正在查询...");
        //console.log('src select is :' + $scope.conf_src);
        $scope.src_database_options = [];
        if($scope.conf_src == 'hive') {
            $scope.src_database_options = ['load','ods','dw','dm','dim','tmpdb'];
        }else {
            var source= DimService.querySourceDB({
                type:$scope.conf_src
            });
            source.$promise.then(function(data) {
                if(data.isSuccess) {
                    $scope.src_domain_options = data.results;
                    //setLoading(false,'');
                }
            },function(){})
        }
    }
    $scope.src_domain_select = function() {
        setLoading(true,'正在获取表...');
        console.log($scope.conf_src_domain);
        $http.get("/fanli/db/tables",{
            params:{connectProp:$scope.conf_src_domain}
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.src_database_options = data.results;
            }
            setLoading(false,'');
        }).error(function() {
            setLoading(false,'');
        })
    }
    $scope.target_domain_select = function() {
        if($scope.conf_target_domain == 'sqlserver_bi') {
            $scope.target_database_options = ['dw','dw_temp'];
            return;
        }
        setLoading(true,'正在获取表...');
        console.log($scope.conf_target_domain);
        $http.get("/fanli/db/tables",{
            params:{connectProp:$scope.conf_target_domain}
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.target_database_options = data.results;
            }
            setLoading(false,'');
        }).error(function() {
            setLoading(false,'');
        })
    };

    $scope.ensureSql = function() {
        setLoading(true,"正在建表...");
        initTaskName();
        if($scope.conf_target == 'sqlserver') {
            console.log($scope.conf_create_table_sql);
            $http.post("/fanli/db/buildTables",{
                connectProp:$scope.conf_target_domain,
                db:$scope.conf_target_db,
                sql:$scope.conf_create_table_sql
            }).success(function(data) {
                if(data.isSuccess) {
                    setLoading(false,"");
                    setAlert(true,'alert-success','建表成功');
                    $scope.buildBtn = true;
                    $scope.step4 = true;
                } else {
                    setLoading(false,"");
                    setAlert(true,'alert-danger','建表失败');
                }
            }).error(function(data) {
                setLoading(false,'');
                setAlert(true,'alert-danger','建表失败');
            })
        } else if($scope.conf_target == 'hive') {
            var createTable = 'use ' + $scope.conf_target_db + ';' +'\n' + $scope.conf_create_table_sql + '\n';
            console.log(createTable);
            var res = TableService.buildHiveTable({},{
                sql:createTable
            });
            res.$promise.then(function(data) {
                if(data.isSuccess) {
                    setLoading(false,"");
                    $scope.buildBtn = true;
                    $scope.step4 = true;
                } else {
                    setLoading(false,"");
                    setAlert(true,'alert-danger',data.log);
                }

            },function(){})
        }

    }

    function initTaskName() {
        $scope.conf_taskName = $scope.conf_src + '2' +$scope.conf_target + '##' + $scope.conf_target_db + '.'+ $scope.conf_targetTable;
    }


    $scope.incrFieldChange = function() {

    }

    $scope.getPrimaryKey = function() {
        $http.get("/fanli/db/primaryKey", {
            params:{
                tableName:$scope.conf_src_table.trim(),
                connectProp:$scope.conf_src_domain,
                db:$scope.conf_src_db
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.primaryOptions = data.result.columns;
            }
            }).error(function() {
                alert("error get primary key")
            })
    }

    function getJdbcColumns() {
        $http.get("/fanli/db/columns",{
            params:{
                tableName:$scope.conf_src_table.trim(),
                connectProp:$scope.conf_src_domain,
                db:$scope.conf_src_db
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.fieldOptions = data.result.columns;
            }
        })
    }
    $scope.submitForm = function() {
        setAlertMessage(false,'');
        if(!checkForm()) {
            return;
        }
        $scope.getTargetTableName();
        getSourceTableInfo();
        //checkSourceTableExists();

    }

    $scope.getTargetTableName = function () {
        if($scope.conf_src == 'hive') {
            $scope.conf_targetTable = $scope.conf_src_table;
            return;
        }
        var conf_storage_pattern = getStoragePattern();
        var conf_partition_desc = $scope.conf_partition_desc!=undefined?$scope.conf_partition_desc:"";
        //var conf_src_db_ab = $scope.conf_src_db_ab!=undefined?$scope.conf_src_db_ab:"";
        var conf_topic = $scope.conf_topic!=undefined?$scope.conf_topic:"";
        var conf_table_name_desc = $scope.conf_table_name_desc!=undefined?$scope.conf_table_name_desc:"";
        if($scope.conf_target=='hive'&&($scope.conf_target_db=='load'||$scope.conf_target_db == 'ods')) {
            $scope.conf_targetTable = conf_storage_pattern+'_'+conf_partition_desc+'_'+$scope.conf_src_table;
        } else if($scope.conf_target=='hive'&&($scope.conf_target_db=='dw'||$scope.conf_target_db == 'dm')) {
            $scope.conf_targetTable = conf_storage_pattern+'_'+conf_partition_desc+'_'+conf_topic+'_'+conf_table_name_desc;
        } else if($scope.conf_target=='hive'&&$scope.conf_target_db=='dim') {
            $scope.conf_targetTable ='s_'+ $scope.conf_src_table;
        } else if($scope.conf_target=='hive'&&$scope.conf_target_db=='tmpdb') {
            $scope.conf_targetTable = $scope.conf_table_name_desc;
        };
        //console.log($scope.conf_targetTable);
    }

    function getStoragePattern() {
        if($scope.conf_target_table_type == 'append'||$scope.conf_target_table_type == 'append_only') {
            return 'incr';
        } else if($scope.conf_target_table_type == 'full') {
            return 'full';
        } else if($scope.conf_target_table_type == 'snapshot'){
            return 'snapshot';
        }
    }

    $scope.getIncrField = function() {
        if($scope.conf_target_table_type == 'full'||$scope.conf_target_table_type == 'snapshot') {
            return;
        }else if($scope.conf_target_table_type == 'append'||$scope.conf_target_table_type == 'append_only') {
            $scope.fieldOptions = [];
            if($scope.conf_src=='hive') {
                getHiveTablePartitionField();
            } else {
                getJdbcColumns();
            }
        }
    }

    function getJdbcColumns() {
        $http.get("/fanli/db/columns",{
            params:{
                tableName:$scope.conf_src_table.trim(),
                connectProp:$scope.conf_src_domain,
                db:$scope.conf_src_db
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.fieldOptions = data.result.columns;
            }
        })
    }

    function getHiveTablePartitionField() {
        $http.get("/fanli/domain/meta",{
                params:{
                    db:$scope.conf_src_db,
                    table:$scope.conf_src_table.trim()
                }}).success(function(data) {
                        $scope.fieldOptions = data.partitions;

                })
    }


    $scope.saveTargetInfo = function() {
        getCommonCreateTableSqlAndTransferSql();
        $scope.step2 = false;
        $scope.step3 = true;
    }


    $scope.setDefaultFreq = function () {
        if($scope.conf_cycle == 'H') {
            $scope.conf_frequency = '0 5 * * * ?';
            return;
        }

        switch ($scope.conf_taskGroup) {
            case 1:$scope.conf_frequency = '0 30 0 * * ?';break;
            case 2:$scope.conf_frequency = '0 5 0 * * ?';break;
            case 3:$scope.conf_frequency = '0 0 3 * * ?';break;
            case 4:$scope.conf_frequency = '0 0 1 * * ?';break;
            case 5:$scope.conf_frequency = '0 0 4 * * ?';break;
            case 6:$scope.conf_frequency = '0 5 0 * * ?';break;
        }
    }

    function getCommonCreateTableSqlAndTransferSql() {
        if($scope.conf_src == 'hive') {
            $http.get("/fanli/domain/meta",{
                params:{
                    db:$scope.conf_src_db,
                    table:$scope.conf_src_table.trim()
                }}).success(function(data) {
                var src_partition = data.partitions;
                var src_column = data.columns;
                $scope.commonColumn = src_column;
                $scope.partitionColumn = src_partition;
                getTransferSql();
                $scope.SRCColumn = mergeHiveColumns(src_partition,src_column);
                requestTogetSql();
            })
        } else{
            $http.get("/fanli/db/columns",{
                params:{
                    tableName:$scope.conf_src_table.trim(),
                    connectProp:$scope.conf_src_domain,
                    db:$scope.conf_src_db
                }
            }).success(function(data) {
                if(data.isSuccess) {
                    $scope.commonColumn = data.result.columns;
                    getTransferSql();
                    var cols = data.result.columns;
                    $scope.SRCColumn = appendIncrAndUpdateFiled(cols);
                    requestTogetSql();
                }
            })
        }


    }

    function appendIncrAndUpdateFiled(cols) {
        var len = cols.length;
        var incr = {name:'ins_date',type:'timestamp',comment:'入库时间',index:len};
        var update = {name:'upd_date',type:'timestamp',comment:'更新时间',index:len + 1};
        cols.push(incr);
        cols.push(update);
        return cols;
    }

    function requestTogetSql() {
        var buildSql = TableService.queryCreateTableSql({},{
            name:$scope.conf_targetTable,
            columns:$scope.SRCColumn,
            partitions:getPartitions(),
            dbType:$scope.conf_target,
            schema:getSchema()
        });
        buildSql.$promise.then(function(data) {
            $scope.conf_create_table_sql = data.result;
            console.log($scope.conf_create_table_sql);
        },function() {})
    }

    function getSchema() {
        return $scope.conf_src_db;
    }

    function getTransferSql() {
        var col = $scope.commonColumn;
        var sql = 'select ';
        //writer的columns字段
        $scope.columns = '';
        $scope.where = '';
        if($scope.conf_src == 'sqlserver') {
            sql = sql +'[' + col[0].name + ']';
            $scope.columns = $scope.columns + '[' + col[0].name + ']';
            for(var i = 1;i < col.length;i ++) {
                sql = sql + ','+'['+ col[i].name + ']';
                $scope.columns = $scope.columns + ',' + '[' + col[i].name + ']';
            }
            sql = sql + ',getdate() as ins_date,getdate() as upd_date';
            $scope.columns = $scope.columns + ',getdate() as ins_date,getdate() as upd_date'
        } else if($scope.conf_src == 'mysql') {
            sql = sql +'`' + col[0].name + '`';
            $scope.columns = $scope.columns + '`' + col[0].name + '`';
            for(var i = 1;i < col.length;i ++) {
                sql = sql + ','+'`'+ col[i].name + '`';
                $scope.columns = $scope.columns + ',' + '`' + col[i].name + '`';
            }
            sql = sql + ',now() as ins_date,now() as upd_date';
            $scope.columns = $scope.columns + ',now() as ins_date,now() as upd_date';
        }else {
            sql = sql +'`' + col[0].name + '`';
            for(var i = 1;i < col.length;i ++) {
                sql = sql + ','+'`'+ col[i].name + '`';
            }
            sql = appendPartitionColumns(sql);
        }
        if($scope.conf_src != 'hive') {
            sql = sql + ' from ' + $scope.conf_src_table ;
        } else {
            sql = sql + ' from ' + $scope.conf_src_db + '.' + $scope.conf_src_table;
        }

        if($scope.conf_target_table_type == 'append'||$scope.conf_target_table_type == 'append_only') {
            var incr = getColumnInfo($scope.conf_incr_field);
            console.log('增量字段是:' + incr);
            var where = handleWhere($scope.conf_src,incr.type);
            sql = sql  + where;
            $scope.where = where.split('where')[1].trim();
        }
        $scope.conf_transfer_sql = sql;
        console.log('查询sql是：' + sql);
    }

    function appendPartitionColumns(sql) {
        var p = $scope.partitionColumn;
        for(var i = 0;i < p.length;i ++) {
            sql = sql+ ',`' + p[i].name + '`';
        }
        return sql;

    }

    function handleWhere(src,type) {
        var ret;
        if(src == 'hive') {
            switch ($scope.conf_cycle){
                case 'H':ret = "ds='${yyyy-MM-dd;P1H}' and hour='${HH;P1H}'";break;
                case 'D':ret = $scope.conf_incr_field + "='${yyyy-MM-dd;P1D}'";break;
                case 'M':ret = $scope.conf_incr_field + ">='${yyyy-MM-01;P1M}' and " +$scope.conf_incr_field + "<'${yyyy-MM-01}'";break;
                case 'W':ret = $scope.conf_incr_field + ">='${yyyy-MM-dd;F1W}' and " + $scope.conf_incr_field + "<'${yyyy-MM-dd;F0W}'";break;
                case 'Y':ret = $scope.conf_incr_field + ">='${yyyy-01-01};P1Y' and " + $scope.conf_incr_field + "<'${yyyy-01-01}'";break;
            }
        } else if(src=='mysql') {
            if('int' == type.toLowerCase()) {
                switch ($scope.conf_cycle) {
                    case 'H':ret = "FROM_UNIXTIME(" + $scope.conf_incr_field + ", '%Y-%m-%d-%H')='${yyyy-MM-dd-HH;P1H}'";break;
                    case 'D':ret = "FROM_UNIXTIME(" + $scope.conf_incr_field + ", '%Y-%m-%d')='${yyyy-MM-dd;P1D}'";break;
                    case 'M':ret = "FROM_UNIXTIME(" + $scope.conf_incr_field + ", '%Y-%m')='${yyyy-MM;P1M}'";break;
                    case 'W':ret = "FROM_UNIXTIME(" + $scope.conf_incr_field + ", '%Y-%m-%d')>='${yyyy-MM-dd;F1W}' and " +
                        "FROM_UNIXTIME("+ $scope.conf_incr_field + ", '%Y-%m-%d')<'${yyyy-MM-dd;F0W}'";break;
                    case 'Y':ret = "FROM_UNIXTIME(" + $scope.conf_incr_field + ", '%Y')='${yyyy;P1Y}'";break;
                }
            }else if('datetime' == type.toLowerCase() || 'timestamp'==type.toLowerCase()) {
                switch ($scope.conf_cycle) {
                    case 'H':ret = $scope.conf_incr_field + ">='${yyyy-MM-dd HH:00:00;P1H}' and " + $scope.conf_incr_field +
                        "<'${yyyy-MM-dd HH:00:00}'";break;
                    case 'D':ret = $scope.conf_incr_field + ">='${yyyy-MM-dd 00:00:00;P1D}' and " + $scope.conf_incr_field +
                        "<'${yyyy-MM-dd 00:00:00}'";break;
                    case 'M':ret = $scope.conf_incr_field + ">='${yyyy-MM-01 00:00:00;P1M}' and " + $scope.conf_incr_field +
                        "<'${yyyy-MM-01 00:00:00}'";break;
                    case 'W':ret = $scope.conf_incr_field + ">='${yyyy-MM-dd 00:00:00;F1W}' and " + $scope.conf_incr_field +
                        "<'${yyyy-MM-dd 00:00:00;F0W}'";break;
                    case 'Y':ret = $scope.conf_incr_field + ">='${yyyy-01-01 00:00:00;P1Y}' and " + $scope.conf_incr_field +
                        "<'${yyyy-01-01 00:00:00}'";break;
                }
            } else {
                return ' where ' + $scope.conf_incr_field;
            }
        }else if(src=='sqlserver') {
            if('datetime' == type.toLowerCase()||'smalldatetime' == type.toLowerCase()) {
                switch ($scope.conf_cycle) {
                    case 'H':ret = $scope.conf_incr_field +">='${yyyy-MM-dd HH:00:00;P1H}' and " +
                        $scope.conf_incr_field + "<'${yyyy-MM-dd HH:00:00}'";break;
                    case 'D':ret = $scope.conf_incr_field +">='${yyyy-MM-dd 00:00:00;P1D}' and " +
                        $scope.conf_incr_field + "<'${yyyy-MM-dd 00:00:00}'";break;
                    case 'M':ret = $scope.conf_incr_field +">='${yyyy-MM-01 00:00:00;P1M}' and " +
                        $scope.conf_incr_field + "<'${yyyy-MM-01 00:00:00}'";break;
                    case 'W':ret = $scope.conf_incr_field +">='${yyyy-MM-dd 00:00:00;F1W}' and " +
                        $scope.conf_incr_field + "<'${yyyy-MM-dd 00:00:00;F0W}'";break;
                    case 'Y':ret = $scope.conf_incr_field +">='${yyyy-01-01 00:00:00;P1Y}' and " +
                        $scope.conf_incr_field + "<'${yyyy-01-01 00:00:00}'";break;
                }
            } else {
                return ' where ' + $scope.conf_incr_field;
            }
        }
        return ' where ' + ret;
    }

    function getColumnInfo(name) {
        for(var i = 0;i < $scope.fieldOptions.length;i ++) {
            if($scope.fieldOptions[i].name == name) {
                return $scope.fieldOptions[i];
            }
        }
    }


    function mergeHiveColumns(p,c) {
        var merged = c;
        for(var i = 0;i < p.length;i ++) {
            merged.push(p[i]);
        }
        return merged;

    }

    function getPartitions() {
        var pt = [];
        if($scope.conf_target_table_type == 'append'||$scope.conf_target_table_type =='snapshot'||$scope.conf_target_table_type=='append_only') {
            switch ($scope.conf_cycle) {
                case 'H':
                    if($scope.conf_partition_desc == 'h') {
                        pt.push({name:'ds',type:'string',comment:''});
                        pt.push({name:'hour',type:'string',comment:''});
                    } else if($scope.conf_partition_desc == 'd') {
                        pt.push({name:'ds',type:'string',comment:''});
                    } else if($scope.conf_partition_desc == 'n') {
                        //no partitions
                    }
                    break;
                case 'D':
                    if($scope.conf_partition_desc == 'd') {
                        pt.push({name:'ds',type:'string',comment:''});
                    }else if($scope.conf_partition_desc == 'n') {
                        //no partitions
                    }
                    break;
                case 'M':
                    if($scope.conf_partition_desc == 'm') {
                        pt.push({name:'month',type:'string',comment:''});
                    }else if($scope.conf_partition_desc == 'n') {
                        //no partitions
                    }
                    break;
                case 'Y':
                    if($scope.conf_partition_desc == 'y') {
                        pt.push({name:'year',type:'string',comment:''});
                    }else if($scope.conf_partition_desc == 'n') {
                        //no partitions
                    }
                    break;
                case 'W':
                    if($scope.conf_partition_desc == 'w') {
                        pt.push({name:'week',type:'string',comment:''});
                    }else if($scope.conf_partition_desc == 'n') {
                        //no partitions
                    }

            }
        }

        //var arr = $scope.conf_hive_partition.trim().split(',');
        //for(var i = 0;i < arr.length;i ++) {
        //    var sp = arr[i].split(/\s+/);
        //
        //    var ele = {name:sp[0],type:sp[1],comment:sp[3]};
        //    pt.push(ele);
        //}
        return pt;
    }

    function getSourceTableInfo() {
        if($scope.conf_src == ''|| $scope.conf_src_table == ''||$scope.conf_src_table==undefined) {
            return;
        };
        if($scope.conf_src == 'mysql' || $scope.conf_src == 'sqlserver'){
            getJdbcTableInfo();
        }else if($scope.conf_src == 'hive') {
            getHiveTableInfo();
        }


    }

    function getHiveTableInfo() {
        $http.get("/fanli/domain/meta",{
            params:{
                db:$scope.conf_src_db,
                table:$scope.conf_src_table.trim()
            }
        }).success(function(data) {
            $scope.src_tables = [];
            var table = data.table;
            $scope.src_tables.push(table);
        })
    }

    function getJdbcTableInfo() {
        $http.get("/fanli/db/tableInfo",{
            params:{
                tableName:$scope.conf_src_table.trim(),
                connectProp:$scope.conf_src_domain,
                db:$scope.conf_src_db
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.src_tables = data.results;
            }
        });
    }

    $scope.add_transfer = function(index) {
        //$scope.src_tables[index]
        initTargetScope();
        $scope.step1 = false;
        $scope.step2 = true;

    }
    function initTargetScope() {
        if($scope.conf_src == 'hive') {
            $scope.targetOptions=[];
            $scope.targetOptions.push('sqlserver');

        }else if($scope.conf_src == 'mysql'||$scope.conf_src == 'sqlserver') {
            $scope.targetOptions = [];
            $scope.targetOptions.push('hive');
        }

        console.log("now conf_target is :" + $scope.conf_target);

    }
    function checkSourceTableExists(){
        $http.get("/fanli/db/tableExists",{
            params:{
                connectProp:$scope.conf_src_domain,
                db:$scope.conf_src_db,
                table:$scope.conf_src_table
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.step1 = false;
                $scope.step2 = true;
            }else {
                $scope.step1 = false;
                $scope.step2 = true;
                setAlertMessage(true,'源表不存在，请检查');
            }
        }).error(function() {
            setAlertMessage(true,'Check源表请求失败');
        })
    }



    function checkForm() {
        var params = [
            {name:'源介质',value:$scope.conf_src},
            {name:'源域名',value:$scope.conf_src_domain},
            //{name:'源库名',value:$scope.conf_src_db},
            {name:'源表名',value:$scope.conf_src_table}
            //{name:'目标介质',value:$scope.conf_target},
            //{name:'目标域名',value:$scope.conf_target_domain},
            ////{name:'目标库名',value:$scope.conf_target_db},
            //{name:'目标表名',value:$scope.conf_targetTable},
            ////{name:'sql',value:$scope.conf_transfer_sql},
            //{name:'刷新类型',value:$scope.conf_target_table_type},
            //{name:'增量字段',value:$scope.conf_target_table_type},
            //{name:'hive分区',value:$scope.conf_hive_partition}];
        ];
        var keepGoing = true;
        angular.forEach(params,function(data) {
            if(keepGoing) {
                if(data.value == ''|| data.value == undefined) {
                    if(data.name == '源域名') {
                        if($scope.conf_src == 'mysql'||$scope.conf_src == 'sqlserver') {
                            setAlertMessage(true,data.name + '不能为空');
                            keepGoing = false;
                        }
                    } else {
                        setAlertMessage(true,data.name + '不能为空');
                        keepGoing = false;
                    }

                }
            }

        })
        return keepGoing;
    }
    function setAlertMessage(a,msg) {
        $scope.formInvalid = a;
        $scope.formCheckMsg = msg;
    }

    function initUI() {
        $scope.step1 = true;
        $scope.conf_src = '';
        $scope.conf_target = '';
        $scope.conf_targetTable = '';
        $scope.conf_hive_partition = '';
        $scope.dependenceTasks = [];
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
        $scope.hivePartitionOptions = ConstantService.getTransferHivePartition();
        $scope.storagePatternOptions = ConstantService.getStoragePattern();
        $scope.partitionDescOptions = ConstantService.getPartitionDesc();
        $scope.topicOptions = ConstantService.getTopic();

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

        $scope.getOffsetOptions = function(cycle) {
            return ConstantService.getOffsetsByCycle(cycle);
        }

        $scope.getCycleText = function(cycle) {
            return ConstantService.cycleToText(cycle);
        }

        $scope.getExecutionCycleLabel = function(cycle) {
            return ConstantService.getCycleCss(cycle);
        }



        $scope.submitTaskCfg = function() {
            generateReaderAndWriterByType();
            setLoading(true,'正在新增传输任务......');
            var req = JobManageService.addTransferTask({},{
                taskGroupId:$scope.conf_taskGroup,
                taskName:$scope.conf_taskName,
                tableName:$scope.conf_targetTable,
                resource:$scope.conf_src == 'hive'?$scope.conf_target_domain:$scope.conf_src_domain,
                command:'',
                cycle:$scope.conf_cycle,
                priority:$scope.conf_priority,
                ifRecall:$scope.conf_ifRecall,
                ifWait:0,
                ifPre:hasPre(),
                ifEnable:1,
                freq:$scope.conf_frequency,
                owner:$scope.conf_developer.cnName,
                waitCode:$scope.conf_waitCode,
                recallCode:"",
                successCode:$scope.conf_successCode,
                timeout:$scope.conf_timeout,
                recallInterval:$scope.conf_recallInterval,
                logFile:"/data1/log/applog",
                addUser:$scope.conf_developer.cnName,
                updateUser:$scope.conf_developer.cnName,
                type:1,
                offset:$scope.conf_offset,
                recallLimit:$scope.conf_recallLimit,
                concurrency:1
            });

            req.$promise.then(function(data) {
                if(data.isSuccess) {
                    $scope.taskId = data.result.taskId;
                    var taskid = data.result.taskId;
                    updateCommand(taskid);
                    addPreRelaTaskToDatabase(taskid);
                }
            },function(data) {
                setLoading(false,'');
            })
        }
    }

    function getPreList() {
        var list = [];
        if($scope.dependenceTasks.length > 0) {
            for(var i = 0;i < $scope.dependenceTasks.length;i ++) {
                list.push({
                    taskId:$scope.generatedTaskId,
                    preId:$scope.dependenceTasks[i].taskId,
                    offset:parseInt($scope.dependenceTasks[i].offset.substring(1))
                })
            }
        }
        console.log(list);
        return list;
    }

    function addPreRelaTaskToDatabase(taskid) {
        $scope.generatedTaskId = taskid;
        if ($scope.dependenceTasks.length > 0) {
            $http.post("/fanli/taskManager/taskpreadd", JSON.stringify(getPreList())).success(function (data) {
                if(data.isSuccess) {
                    addTransferParamToDatabase(taskid);
                }
            }).error(function() {
                setAlert(true,'alert-danger',"保存依赖信息失败");
            })
        } else {
            addTransferParamToDatabase(taskid);
        }
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
    }


    function updateCommand(taskid) {
        var res = JobManageService.updateTransferTask({},{
            taskId:taskid,
            command:'sh /home/hadoop/wormhole/test/bin/wormhole.sh ' + taskid + ' ' + '${unix_timestamp} '  + $scope.conf_offset
        });
        console.log('sh /home/hadoop/wormhole/test/bin/wormhole.sh ' + taskid + ' ' + '${unix_timestamp} ' + $scope.conf_offset);
        res.$promise.then(function(data) {
            if(data.isSuccess) {
                console.log("更新command成功");
            }
        },function(){})
    }

    function addTransferParamToDatabase(taskid) {
        setLoading(true,'正在保存传输参数......');
        var read = JobManageService.addEtlLoadCfg({},{
            taskId:taskid,
            parameterMap:JSON.stringify($scope.reader),
            type:'reader',
            isValid:1
        });
        read.$promise.then(function(data) {
            if(data.isSuccess) {

                var writer = JobManageService.addEtlLoadCfg({},{
                    taskId:taskid,
                    parameterMap:JSON.stringify($scope.writer),
                    type:'writer',
                    isValid:1
                });
                writer.$promise.then(function(data) {
                    if(data.isSuccess) {
                        showAlert('新增传输成功');
                        $scope.suceessAndDisable = true;
                        setLoading(false,'');
                        notifyToMonitorDialog();
                    }
                },function(){setAlert(true,'alert-danger',"保存写参数失败！")})
            }
        },function(){setAlert(true,'alert-danger',"保存读参数失败！")})
    }

    function notifyToMonitorDialog() {
        $scope.message = {
            headerText: '提示',
            bodyText: '新增传输成功！是否配置任务的监控: ' + $scope.taskId + " ?",
            actionButtonStyle: 'btn-danger',
            showCancel: true
        };
        var modalInstance = $modal.open({
            templateUrl: '/assets/pages/dialog/messageEnsure.html',
            controller: MessageCtrl,
            resolve: {
                msg: function () {
                    return $scope.message;
                }
            }
        });
        modalInstance.result.then(function (data) {
            location.href = '#/data_monitor/new/' + $scope.taskId;
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    }



    function generateReaderAndWriterByType() {
        if($scope.conf_src == 'hive'&& $scope.conf_target == 'sqlserver') {
            getHiveToSqlserverReaderAndWriter();
        }else if($scope.conf_src == 'mysql'&& $scope.conf_target == 'hive') {
            getMysqlToHiveReaderAndWriter();
        }else if($scope.conf_src == 'sqlserver'&&$scope.conf_target == 'hive') {
            getSqlserverTohiveReaderAndWriter();
        }
    }

    function getSqlserverTohiveReaderAndWriter() {
        $scope.reader = {
            plugin: "sqlserverreader",
            connectProps:$scope.conf_src_domain ,
            dbname:$scope.conf_src_db,
            tableName:$scope.conf_src_table.trim(),
            columns:$scope.columns,
            encoding: "UTF-8",
            sql: $scope.conf_transfer_sql,
            concurrency: "1",
            needSplit: "true"
        };
        if($scope.conf_transfer_sql.split('where')[1] != ''&& $scope.conf_transfer_sql.split('where')[1]!= undefined) {
            $scope.reader.where = $scope.conf_transfer_sql.split('where')[1].trim();
        }
        if($scope.conf_if_primarykey == "1") $scope.reader.autoIncKey = $scope.conf_primary_key;
        $scope.writer = {
            plugin: "hdfswriter",
            dir: getHiveDir(),
            prefix_filename: $scope.conf_targetTable,
            field_split: "\t",
            line_split: "\n",
            encoding: "UTF-8",
            buffer_size: "4096",
            file_type: "TXT",
            concurrency: "1",
            hive_table_add_partition_switch : hasPartitions(),
            hive_table_add_partition_condition : hivePartitionCondition()
        }
    }

    function hasPartitions() {
        var hasp = getPartitions();
        if(hasp.length > 0) return true;
        else {return false;}
    }

    function getHiveToSqlserverReaderAndWriter() {
         $scope.reader = {
             plugin:'hivereader',
             sql:$scope.conf_transfer_sql,
             mode:'READ_FROM_LOCAL',
             dataDir:'hdfs://namenode171:54310/tmp',
             reduceNumber:'-1',
             concurrency:'1'
        }
        $scope.writer = {
            plugin: "sqlserverwriter",
            connectProps: $scope.conf_target_domain,
            dbname: $scope.conf_target_db,
            encoding: "UTF-8",
            concurrency: "1",
            tableName: $scope.conf_src_db + '.' + $scope.conf_targetTable, //$scope.conf_src_db对应sqlserver的schema
            columns: getHiveColumns(),
            pre: preSql()
        }
    }

    function preSql() {
        if($scope.conf_target_table_type == 'full'||$scope.conf_target_table_type == 'append_only') {
            return "delete from " + $scope.conf_src_db + '.' + $scope.conf_targetTable;
        }else if($scope.conf_target_table_type == 'append'){
            var sql = "delete from " + $scope.conf_src_db + '.' + $scope.conf_targetTable + ' where ';
            var where;
            switch ($scope.conf_cycle){
                case 'H':where = "ds='${yyyy-MM-dd;P1H}' and hour='${HH;P1H}'";break;
                case 'D':where = $scope.conf_incr_field + "='${yyyy-MM-dd;P1D}'";break;
                case 'M':where = $scope.conf_incr_field + ">='${yyyy-MM-01;P1M}' and " +$scope.conf_incr_field + "<'${yyyy-MM-01}'";break;
                case 'W':where = $scope.conf_incr_field + ">='${yyyy-MM-dd;F1W}' and " + $scope.conf_incr_field + "<'${yyyy-MM-dd;F0W}'";break;
                case 'Y':where = $scope.conf_incr_field + ">='${yyyy-01-01;P1Y}' and " + $scope.conf_incr_field + "<'${yyyy-01-01}'";break;
            }
            sql = sql + where;
            return sql;
        }
    }

    function getHiveColumns() {
        var s = $scope.SRCColumn;
        var col = "";
        col = col + s[0].name;
        for(var i = 1;i < s.length;i ++) {
            col = col + "," + s[i].name;
        }
        console.log('hive 列：' + col);
        return col;
        //$http.get("/fanli/db/columns",{
        //    params:{
        //        tableName:$scope.conf_src_table.trim(),
        //        connectProp:$scope.conf_src_domain,
        //        db:$scope.conf_src_db
        //    }
        //}).success(function(data) {
        //    if(data.isSuccess) {
        //
        //    }
        //})
    }

    function getSplitOption() {
        if($scope.conf_if_primarykey == "1") {
            return "true";
        } else {
            return "false"
        }
    }



    function getMysqlToHiveReaderAndWriter() {
        $scope.reader = {
            plugin: "mysqlreader",
            connectProps: $scope.conf_src_domain,
            dbname:$scope.conf_src_db,
            tableName:$scope.conf_src_table.trim(),
            columns:$scope.columns,
            encoding: "UTF-8",
            sql: $scope.conf_transfer_sql,
            concurrency: "1",
            autoIncKey:$scope.conf_primary_key,
            needSplit: "true"
        };
        if($scope.conf_transfer_sql.split('where')[1] != ''&& $scope.conf_transfer_sql.split('where')[1]!= undefined) {
            $scope.reader.where = $scope.conf_transfer_sql.split('where')[1].trim();
        }
        if($scope.conf_if_primarykey == "1") $scope.reader.autoIncKey = $scope.conf_primary_key;
        console.log($scope.reader);
        $scope.writer = {
            plugin: "hdfswriter",
            dir: getHiveDir(),
            prefix_filename: $scope.conf_targetTable,
            field_split: "\t",
            line_split: "\n",
            encoding: "UTF-8",
            buffer_size: "4096",
            file_type: "TXT",
            concurrency: "1",
            hive_table_add_partition_switch : hasPartitions(),
            hive_table_add_partition_condition : hivePartitionCondition()
        }
    }

    function hivePartitionCondition() {
        if (!hasPartitions()) return "";
        var  cond;
        switch ($scope.conf_cycle) {
            case 'H':
                if($scope.conf_partition_desc == 'h') {
                    cond = "ds='${yyyy-MM-dd}',hour='${HH;P1H}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
                } else{
                    cond = "ds='${yyyy-MM-dd;P1D}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
                }
            case 'D':cond = "ds='${yyyy-MM-dd;P1D}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
            case 'W':cond = "ds='${yyyy-MM-dd;F1W}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
            case 'M':cond = "ds='${yyyy-MM;P1M}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
            case 'Y':cond = "ds='${yyyy;P1Y}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
        }
        return cond;
        //return "ds='" + "${CAL_YYYYMMDD_YESTERDAY}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;
    }

    function getHiveDir() {
        var dir = "";
        if($scope.conf_target_db == 'tmpdb') {
            dir = "hdfs://namenode171:54310/tmp/tmp.db/" + $scope.conf_targetTable;
        } else{
            //必须为小写toLowerCase() 否则查不到数据 原因暂不明
            dir = "hdfs://namenode171:54310/user/hive/bi_warehouse/" + $scope.conf_target_db.toUpperCase() + ".db/" + $scope.conf_targetTable.toLowerCase();
        }

        var p = getPartitions();
        var partitionDir = ConstantService.getPartitionByCycle();
        if(p.length > 0) {
            switch ($scope.conf_cycle) {
                case 'H':
                    if($scope.conf_partition_desc == 'h') {
                        dir = dir + partitionDir.H;break;
                    } else if($scope.conf_partition_desc == 'd'){
                        dir = dir + partitionDir.HD;break;
                    }
                case 'D':dir = dir + partitionDir.D;break;
                case 'W':dir = dir + partitionDir.W;break;
                case 'M':dir = dir + partitionDir.M;break;
                case 'Y':dir = dir + partitionDir.Y;break;
            }
            //dir = dir + "/" + "ds=" + "${CAL_YYYYMMDD_YESTERDAY}";
        }

        return dir;
    }


    var hasPre = function() {
        if($scope.dependenceTasks.length > 0) {
            return 1;
        }else {
            return 0;
        }
    }

    function showAlert(msg){
        $scope.showSaveSucess = true;
        $scope.saveSuccessMsg = msg;
    }

    function setAlert(a,b,c) {
        $scope.sucShow = a;
        $scope.alerttype = b;
        $scope.sucMsg = c;
    }

    function setLoading(a,b){
        $scope.Loading = a;
        $scope.LoadingMsg = b;
    }


})

/**
 * Created by Sunny on 14-6-10.
 */

angular.module('common.service', ['ngResource'])
    .value('serverAddress', '/fanli')
    .factory('CommonService', ['$resource', '$routeParams', 'serverAddress',
        function ($resource, $routeParams, serverAddress) {
            return $resource(serverAddress + "/common/:action",
                {},
                {
                    //获得当前值班信息
                    getCurrentMonitor: {
                        method: 'GET',
                        params: {
                            action: 'getCurrentMonitor'
                        }
                    },
                    //获得当前值班信息，强制读取数据库
                    updateMonitorByForce: {
                        method: 'POST',
                        params: {
                            action: 'updateMonitorByForce'
                        }
                    },
                    //发送意见反馈
                    sendReply: {
                        method: 'POST',
                        params: {
                            action: 'sendReply',
                            reply: '@reply',
                            email: '@email'
                        }
                    },
                    //用户是否是管理员
                    isAdmin: {
                        method: 'GET',
                        params: {
                            action: 'isAdmin',
                            resourceId: '@resourceId'
                        }
                    },
                    //用户是否是taskid对应的任务的owner
                    isOwnerByTaskId: {
                        method: 'GET',
                        params: {
                            action: 'isTaskOwner',
                            taskId: '@taskId'
                        }
                    },
                    //导入git
                    importGit: {
                        method: 'GET',
                        params: {
                            action: 'importGit',
                            projectName: '@projectName',
                            fileName: '@fileName'
                        }
                    },
                    //获取当前登陆用户信息
                    getCurrentUser: {
                        method: 'GET',
                        params: {
                            action: 'getCurrentUser'
                        }
                    },
                    //获取登出url
                    getLogoutUrl: {
                        method: 'GET',
                        params: {
                            action: 'getLogoutUrl'
                        }
                    },
                    //获取git目录树
                    getGitTree: {
                        method: 'GET',
                        params: {
                            action: 'getGitTree'
                        }
                    },
                    //发布Git文件
                    publishGitFile: {
                        method: 'POST',
                        params: {
                            action: 'publishGitFile',
                            projectName: '@projectName',
                            fileName: '@fileName'
                        }
                    },
                    //发布Git文件
                    getGitFile: {
                        method: 'POST',
                        params: {
                            action: 'getGitFile',
                            fileName: '@fileName'
                        }
                    }
                }
            )
        }
    ])
    .factory('UserService', ['$rootScope', 'CommonService', function ($rootScope, CommonService) {
        //登陆用户的信息
        var user = {
            isAdmin: false,
            isDeveloper: false,
            pinyinName: '',
            realName: '',
            isOwner: false,
            email: '',
            userId: '',
            loginId: -1
        };

        getCurrentUser();
        getLogoutUrl();

        //获取当前登陆用户信息
        function getCurrentUser() {
            CommonService.getCurrentUser({
            }).$promise.then(function (data) {
                    if (data.success) {
                        setUser(data.result);
                    }
                }, function (error) {
                });
        };

        //设置当前登陆用户
        function setUser(result) {
            user.pinyinName = result.employPinyin;
            user.realName = result.employeeName;
            user.email = result.employeeEmail;
            user.userId = result.employeeId;
            user.loginId = result.loginId;

            for (var i = 0; i < Global.AllTeam.length; i++) {
                if (Global.AllTeam[i][0] == user.pinyinName) {
                    user.isDeveloper = true;
                    break;
                }
            }
            $("#userName").text(user.realName);

            CommonService.isAdmin({
                'resourceId': '1'
            }).$promise.then(function (data) {
                    user.isAdmin = data.success;
                    $rootScope.$broadcast('userVerified');
                }, function (error) {
                });
        }

        //获取登出url
        function getLogoutUrl() {
            CommonService.getLogoutUrl({
            }).$promise.then(function (data) {
                    if (data.success) {
                        $('.user-menu a').attr('href', data.result);
                    }
                }, function (error) {
                });
        }

        //暴露给外部的接口
        return {
            getUser: function () {
                return user;
            }
        }
    }])
;

/**
 * Created by wei.shen on 2015/7/21.
 */

angular.module('component.service', []).
    value('component', {
        getCustomizedTable: function (scope, filter) {
            function table(scope, filter) {
                this.recordPerPageOptions = [5, 10, 25, 50, 100];
                this.selectedRecordPerPage = this.recordPerPageOptions[2];


                this.displayedDataList = scope.allTaskList;
                this.total = this.displayedDataList.length;
                this.currentPage = 1;
                this.startIndex = 1;
                this.endIndex = Math.min(this.selectedRecordPerPage, this.total);
                this.pageNumber = Math.ceil(this.total / this.selectedRecordPerPage);
                this.maxPageNumDisplayed = 5;
                this.query = '';

                this.updateStartIndex = function () {
                    this.startIndex = (this.currentPage - 1) * this.selectedRecordPerPage + 1;
                };
                this.updateEndIndex = function () {
                    this.endIndex = Math.min(this.startIndex + this.selectedRecordPerPage - 1, this.total);
                };
                this.updatePageNumber = function () {
                    this.pageNumber = Math.ceil(this.total / this.selectedRecordPerPage);
                };
                this.updateIndexes = function () {
                    this.updateStartIndex();
                    this.updateEndIndex();
                };

                this.getCurrentPageNumDisplay = function () {
                    var array = [];
                    if (this.total == 0) {
                        return array
                    }
                    for (var i = Math.max(this.currentPage - 2, 1); i <= Math.min(this.pageNumber, this.currentPage + 2); i++) {
                        array.push(i);
                    }
                    var maxLength = Math.min(this.maxPageNumDisplayed, this.pageNumber);
                    if (array.length < maxLength) {
                        if (array[0] === 1) {
                            for (var i = 1; i <= maxLength - array.length + 1; i++) {
                                array.push(array[array.length - 1] + 1);
                            }
                        }
                        else {
                            for (var i = 1; i <= maxLength - array.length + 1; i++) {
                                array.unshift(array[0] - 1);
                            }
                        }
                    }
                    return array;
                };
                this.getActiveLabel = function (pageNum) {
                    if (this.currentPage === pageNum) {
                        return 'active';
                    }
                };
                this.changePage = function (pageNum) {
                    this.currentPage = pageNum;
                };
                this.previousPage = function () {
                    if (this.currentPage > 1) {
                        this.currentPage = this.currentPage - 1;
                    }
                };
                this.nextPage = function () {
                    if (this.currentPage < this.pageNumber) {
                        this.currentPage = this.currentPage + 1;
                    }
                };
                this.getDisableLabelforPrevious = function () {
                    if (this.total === 0 || this.currentPage === 1) {
                        return 'disabled';
                    }
                };
                this.getDisableLabelforNext = function () {
                    if (this.total === 0 || this.currentPage === this.pageNumber) {
                        return 'disabled';
                    }
                };
                this.gotoFirstPage = function () {
                    this.currentPage = 1;
                };
                this.gotoLastPage = function () {
                    this.currentPage = this.pageNumber;
                };
                this.getSortingClass = function (name) {
                    if (this.predicate === name) {
                        if (this.reverse) {
                            return 'sorting_asc';
                        }
                        else {
                            return 'sorting_desc';
                        }
                    }
                    return 'sorting';
                };

                scope.$watch('table.currentPage', function () {
                    scope.table.updateIndexes();
                });
                scope.$watch('table.selectedRecordPerPage', function () {
                    scope.table.currentPage = 1;
                    scope.table.updateIndexes();
                    scope.table.updatePageNumber();
                });
                scope.$watch('table.total', function () {
                    if (scope.table.total == 0) {
                        scope.table.startIndex = 0;
                        scope.table.endIndex = 0;
                    } else {
                        scope.table.updateIndexes();
                        scope.table.updatePageNumber();
                    }
                });
                scope.$watch('table.query', function () {
                    scope.table.displayedDataList = filter('filter')(scope.allTaskList, scope.table.query);
                    scope.table.total = scope.table.displayedDataList.length;
                    scope.table.currentPage = 1;
                });
                scope.$watchCollection('[table.predicate, table.reverse]', function () {
                    scope.table.displayedDataList = filter('orderBy')(scope.table.displayedDataList, scope.table.predicate, scope.table.reverse);
                });
                scope.$watchCollection('allTaskList', function () {
                    scope.table.displayedDataList = scope.allTaskList;
                    if (scope.table.query) {
                        scope.table.displayedDataList = filter('filter')(scope.table.displayedDataList, scope.table.query);
                    }
                    if (scope.table.reverse) {
                        scope.table.displayedDataList = filter('orderBy')(scope.table.displayedDataList, scope.table.predicate, scope.table.reverse);
                    }
                    scope.table.total = scope.table.displayedDataList.length;
                });
            }

            return new table(scope, filter);
        },
        getSpecTialTable: function (scope, filter, dataList) {
            function jobInstanceTable(scope, filter, dataList) {
                this.recordPerPageOptions = [10, 25, 50, 100];
                this.selectedRecordPerPage = this.recordPerPageOptions[0];

                this.displayedDataList = dataList;
                this.total = this.displayedDataList.length;
                this.currentPage = 1;
                this.startIndex = 1;
                this.endIndex = Math.min(this.selectedRecordPerPage, this.total);
                this.pageNumber = Math.ceil(this.total / this.selectedRecordPerPage);
                this.maxPageNumDisplayed = 5;

                this.justShowSuccess = false;
                this.statusConditions = [
                    {"ID": -3, "Text": "--选择全部--"},
                    {"ID": 1, "Text": "success"},
                    {"ID": -2, "Text": "unsuccess"},
                    {"ID": -1, "Text": "fail"},
                    {"ID": 0, "Text": "init"},
                    {"ID": 2, "Text": "running"},
                    {"ID": 3, "Text": "suspend"},
                    {"ID": 4, "Text": "init_error"},
                    {"ID": 5, "Text": "wait"},
                    {"ID": 6, "Text": "ready"},
                    {"ID": 7, "Text": "timeout"}
                ];
                this.showStatus = this.statusConditions[2].ID;
                this.query = '';

                this.updateStartIndex = function () {
                    this.startIndex = (this.currentPage - 1) * this.selectedRecordPerPage + 1;
                };
                this.updateEndIndex = function () {
                    this.endIndex = Math.min(this.startIndex + this.selectedRecordPerPage - 1, this.total);
                };
                this.updatePageNumber = function () {
                    this.pageNumber = Math.ceil(this.total / this.selectedRecordPerPage);
                };
                this.updateIndexes = function () {
                    this.updateStartIndex();
                    this.updateEndIndex();
                };

                this.getCurrentPageNumDisplay = function () {
                    var array = [];
                    if (this.total == 0) {
                        return array
                    }
                    ;
                    for (var i = Math.max(this.currentPage - 2, 1); i <= Math.min(this.pageNumber, this.currentPage + 2); i++) {
                        array.push(i);
                    }
                    var maxLength = Math.min(this.maxPageNumDisplayed, this.pageNumber);
                    if (array.length < maxLength) {
                        if (array[0] === 1) {
                            for (var i = 1; i <= maxLength - array.length + 1; i++) {
                                array.push(array[array.length - 1] + 1);
                            }
                        }
                        else {
                            for (var i = 1; i <= maxLength - array.length + 1; i++) {
                                array.unshift(array[0] - 1);
                            }
                        }
                    }
                    return array;
                };
                this.getActiveLabel = function (pageNum) {
                    if (this.currentPage === pageNum) {
                        return 'active';
                    } else {
                        return '';
                    }
                };
                this.changePage = function (pageNum) {
                    this.currentPage = pageNum;
                };
                this.previousPage = function () {
                    if (this.currentPage > 1) {
                        this.currentPage = this.currentPage - 1;
                    }
                };
                this.nextPage = function () {
                    if (this.currentPage < this.pageNumber) {
                        this.currentPage = this.currentPage + 1;
                    }
                };
                this.getDisableLabelforPrevious = function () {
                    if (this.total === 0 || this.currentPage === 1) {
                        return 'disabled';
                    }
                };
                this.getDisableLabelforNext = function () {
                    if (this.total === 0 || this.currentPage === this.pageNumber) {
                        return 'disabled';
                    }
                };
                this.gotoFirstPage = function () {
                    this.currentPage = 1;
                };
                this.gotoLastPage = function () {
                    this.currentPage = this.pageNumber;
                };
                this.getSortingClass = function (name) {
                    if (this.predicate === name) {
                        if (this.reverse) {
                            return 'sorting_asc';
                        }
                        else {
                            return 'sorting_desc';
                        }
                    }
                    return 'sorting';
                };
            }

            return new jobInstanceTable(scope, filter, dataList);
        },
        getMultiHiveTableColumnTable: function (filter, dataList) {
            function jobInstanceTable(filter, dataList) {
                this.recordPerPageOptions = [10, 25, 50, 100];
                this.selectedRecordPerPage = this.recordPerPageOptions[0];

                this.displayedDataList = dataList;
                this.total = this.displayedDataList.length;
                this.currentPage = 1;
                this.startIndex = 1;
                this.endIndex = Math.min(this.selectedRecordPerPage, this.total);
                this.pageNumber = Math.ceil(this.total / this.selectedRecordPerPage);
                this.maxPageNumDisplayed = 5;

                this.query = '';

                this.updateStartIndex = function () {
                    if (this.total == 0)
                        this.startIndex = 0;
                    else
                        this.startIndex = (this.currentPage - 1) * this.selectedRecordPerPage + 1;
                };
                this.updateEndIndex = function () {
                    if (this.total == 0)
                        this.endIndex = 0;
                    else
                        this.endIndex = Math.min(this.startIndex + this.selectedRecordPerPage - 1, this.total);
                };
                this.updatePageNumber = function () {
                    this.pageNumber = Math.ceil(this.total / this.selectedRecordPerPage);
                };
                this.updateIndexes = function () {
                    this.updateStartIndex();
                    this.updateEndIndex();
                };

                this.getCurrentPageNumDisplay = function () {
                    var array = [];
                    if (this.total == 0) {
                        return array
                    }
                    for (var i = Math.max(this.currentPage - 2, 1); i <= Math.min(this.pageNumber, this.currentPage + 2); i++) {
                        array.push(i);
                    }
                    var maxLength = Math.min(this.maxPageNumDisplayed, this.pageNumber);
                    if (array.length < maxLength) {
                        if (array[0] === 1) {
                            for (var i = 1; i <= maxLength - array.length + 1; i++) {
                                array.push(array[array.length - 1] + 1);
                            }
                        }
                        else {
                            for (var i = 1; i <= maxLength - array.length + 1; i++) {
                                array.unshift(array[0] - 1);
                            }
                        }
                    }
                    return array;
                };
                this.getActiveLabel = function (pageNum) {
                    if (this.currentPage === pageNum) {
                        return 'active';
                    } else {
                        return '';
                    }
                };
                this.changePage = function (pageNum) {
                    this.currentPage = pageNum;
                    this.updateIndexes();
                };
                this.previousPage = function () {
                    if (this.currentPage > 1) {
                        this.currentPage = this.currentPage - 1;
                        this.updateIndexes();
                    }
                };
                this.nextPage = function () {
                    if (this.currentPage < this.pageNumber) {
                        this.currentPage = this.currentPage + 1;
                        this.updateIndexes();
                    }
                };
                this.getDisableLabelforPrevious = function () {
                    if (this.total === 0 || this.currentPage === 1) {
                        return 'disabled';
                    }
                };
                this.getDisableLabelforNext = function () {
                    if (this.total === 0 || this.currentPage === this.pageNumber) {
                        return 'disabled';
                    }
                };
                this.gotoFirstPage = function () {
                    this.currentPage = 1;
                    this.updateIndexes();
                };
                this.gotoLastPage = function () {
                    this.currentPage = this.pageNumber;
                    this.updateIndexes();
                };
                this.getSortingClass = function (name) {
                    if (this.predicate === name) {
                        if (this.reverse) {
                            return 'sorting_asc';
                        }
                        else {
                            return 'sorting_desc';
                        }
                    }
                    return 'sorting';
                };
                this.selectedRecordPerPageChanged = function () {
                    this.currentPage = 1;
                    this.updateIndexes();
                    this.updatePageNumber();
                };
                this.queryChanged = function () {
                    this.displayedDataList = filter('filter')(dataList, this.query);
                    this.total = this.displayedDataList.length;
                    this.currentPage = 1;
                    this.updateIndexes();
                    this.updatePageNumber();
                };
            }

            return new jobInstanceTable(filter, dataList);
        }
    });
/**
 * Created by wei.shen on 2015/7/22.
 */

angular.module("constant.service",[])
    .factory("ConstantService",[function(){
        return {

            getTableRefreshCycle:function() {
                return [
                    {ID: 'any', Text: '--请选择--'},
                    {ID: 'full', Text: '全量'},
                    {ID: 'append', Text: '增量'}
                ];
            },
            getJobMonitorStatus:function() {
                return [
                    {ID: 100, Text: '不限'},
                    {ID: 99,  Text:'unsuccess'},
                    {ID: 1, Text: 'success'},
                    {ID: -1, Text: 'fail'},
                    {ID: 0, Text: 'init'},
                    {ID: 4, Text: 'init-error'},
                    {ID: 3, Text: 'suspend'},
                    {ID: 5, Text: 'wait'},
                    {ID: 2, Text: 'running'},
                    {ID: 6, Text: 'ready'},
                    {ID: 7, Text: 'timeout'}
                ];
            },
            //获得周期对应的css
            getCycleCss: function (text) {
                switch (text) {
                    case 'H':
                        return 'label-warning';
                    case 'D':
                        return 'label-primary';
                    case 'W':
                        return 'label-success';
                    case 'M':
                        return 'label-danger';
                    case 'Y':
                        return 'label-purple';
                }
            },

            getTaskGroupCss:function(groupid) {
              switch (groupid) {
                  case 1:
                      return 'label label-warning arrowed arrowed-right';
                  case 2:
                      return 'label arrowed';
                  case 3:
                      return 'label label-success arrowed-in arrowed-in-right';
                  case 4:
                      return 'label label-danger arrowed';
                  case 5:
                      return 'label label-info arrowed-right arrowed-in';
                  case 6:
                      return 'label label-yellow arrowed-in';
              }
            },

            taskGroupIdToText:function(groupid) {
              switch (groupid) {
                  case 1:return 'ODS';
                  case 2:return 'LOAD';
                  case 3:return 'DM';
                  case 4:return 'DW';
                  case 5:return 'EXPORT';
                  case 6:return 'DIM';
                  case 7:return 'ONLINE'
              }
            },

            getTimePatterns:function() {
              return [
                  {id:1,name:'今天(YYYY-MM-DD)',v:'${this_day10}'},
                  {id:2,name:'昨天(YYYY-MM-DD)',v:'${date}'},
                  {id:3,name:'当前小时(YYYY-MM-DD HH:00:00)',v:'${this_hour}'},
                  {id:4,name:'UNIX_TIMESTAMP',v:'${unix_timestamp}'},
                  {id:5,name:'前一小时(YYYY-MM-DD HH:00:00)',v:'${last_hour}'}
              ]
            },

            getCycleOptions:function(){
                return {
                    H: '时', D: '日', W: '周', M: '月', Y: '年'
                };
            },
            getTaskGroupOption:function() {
              return [
                  {ID: 1, Text: 'ods'},
                  {ID: 2, Text: 'load'},
                  {ID: 3, Text: 'dm'},
                  {ID: 4, Text: 'dw'},
                  {ID: 5, Text: 'export'},
                  {ID: 6, Text: 'dim'},
                  {ID: 7, Text: 'online'}
              ];
            },

            //根据周期获得偏移量的选项
            getOffsetsByCycle: function (cycle) {
                switch (cycle) {
                    case 'H':
                        return ['H0', 'H1', 'H2', 'H3', 'H4', 'H5', 'H6', 'H7', 'H8', 'H9', 'H10', 'H11', 'H12', 'H13', 'H14', 'H15', 'H16',
                            'H17', 'H18', 'H19', 'H20', 'H21', 'H22', 'H23', 'H24'
                        ];
                    case 'D':
                        return ['D0', 'D1', 'D2', 'D3', 'D4', 'D5', 'D6', 'D7', 'D8', 'D9', 'D10', 'D11', 'D12', 'D13', 'D14', 'D15', 'D16',
                            'D17', 'D18', 'D19', 'D20', 'D21', 'D22', 'D23', 'D24', 'D25', 'D26', 'D27', 'D28', 'D29', 'D30', 'D31'
                        ];
                    case 'W':
                        return ['W0', 'W1', 'W2', 'W3', 'W4'];
                    case 'M':
                        return ['M0', 'M1', 'M2', 'M3', 'M4', 'M5', 'M6'];
                }
            },

            getPriorityOption:function() {
              return [
                  {ID: 1, Text: '高'},
                  {ID: 2, Text: '中'},
                  {ID: 3, Text: '低'}
              ];
            },

            getIfRecallOption:function() {
              return [
                  {ID: 1, Text: '是'},
                  {ID: 0, Text: '否'}
              ];
            },

            getRecallLimitOption:function() {
              return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            },

            getRecallIntervalOption:function() {
              return [
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
            },

            getOffsetOption:function() {
              return ['D0', 'D1', 'D2', 'D3', 'D4', 'D5', 'D6', 'D7', 'D8', 'D9', 'D10',
                  'M0', 'M1', 'M2', 'M3', 'M4', 'M5', 'M6', 'M7', 'M8', 'M9', 'M10'];
            },

            getTimeOutOption:function() {
              return [
                  {ID: 30, Text: '30分钟'},
                  {ID: 60, Text: '1小时'},
                  {ID: 90, Text: '1.5小时'},
                  {ID: 120, Text: '2小时'},
                  {ID: 150, Text: '2.5小时'},
                  {ID: 180, Text: '3小时'},
                  {ID: 210, Text: '3.5小时'},
                  {ID: 240, Text: '4小时'}
              ];
            },


            statusToText: function (status) {
                switch (status) {
                    case 1:
                        return 'success';
                    case -1:
                        return 'fail';
                    case 0:
                        return 'init';
                    case 4:
                        return 'init-error';
                    case 3:
                        return 'suspend';
                    case 5:
                        return 'wait';
                    case 2:
                        return 'running';
                    case 6:
                        return 'ready';
                    case 7:
                        return 'timeout';
                    default :
                        return '未知';
                }
            },
            //获得cycle的中文解释
            cycleToText: function (cycle) {
                switch (cycle) {
                    case 'H':
                        return '小时';
                    case 'D':
                        return '日';
                    case 'W':
                        return '周';
                    case 'M':
                        return '月';
                    case 'mi':
                        return '分';
                }
            },
            getTransferHivePartition:function() {
                return [
                    {id:1,name:'无分区',partition:''},
                    {id:2,name:'ds',partition:'ds string'},
                    {id:3,name:'ds,hour',partition:'ds string,hour string'}
                ]
            },
            getStoragePattern:function() {
                return [
                    {id: 1,pattern:'incr'},
                    {id: 2,pattern:'snapshot'},
                    {id: 3,pattern:'zip'},
                    {id: 4,pattern:'full'},
                    {id: 5,pattern:'chage'}
                ]
            },
            getPartitionDesc:function() {
                return [
                    {id:1 ,name:'h',desc:'天(ds),小时(hour)'},
                    {id:2 ,name:'d',desc:'天(ds)'},
                    {id:3 ,name:'w',desc:'周(week)'},
                    {id:4 ,name:'m',desc:'月(month)'},
                    {id:5 ,name:'y',desc:'年(year)'},
                    {id:7 ,name:'n',desc:'无分区'}
                ]
            },
            getTopic:function() {
                return [
                    {id:1,name:'common',ab:'com'},
                    {id:2,name:'user',ab:'usr'},
                    {id:3,name:'order',ab:'odr'},
                    {id:4,name:'traffic',ab:'tra'}
                ]
            },
            getPartitionByCycle:function() {
                return {
                    H:"/ds=${yyyy-MM-dd;P1H}/hour=${HH;P1H}",
                    HD:"/ds=${yyyy-MM-dd;P1D}",
                    D:"/ds=${yyyy-MM-dd;P1D}",
                    W:"/week=${yyyy-MM-dd;F1W}",
                    M:"/month=${yyyy-MM;P1M}",
                    Y:"/year=${yyyy;P1Y}"
                }
            }

        }
    }]);

/**
 * Created by wei.shen on 2015/7/31.
 */

fanliApp.factory("DimService",['$resource', 'serverAddress',function($resource,serverAddress) {
    return $resource(serverAddress + '/dim/:action',{},
        {
            queryAllDevelopers:{
                method: 'GET',
                params:{
                    action:'developers'
                }
            },

            queryAllTargetTables:{
                method: 'GET',
                params:{
                    action:'targetDBs'
                }
            },
            querySourceDB:{
                method:'GET',
                params:{
                    action:'dbSource',
                    type:'@type'
                }
            }
        }
    )
}]);

/**
 * Created by wei.shen on 2015/8/27.
 */


fanliApp.factory("DolService",['$resource', 'serverAddress',function($resource,serverAddress) {
    return $resource(serverAddress + '/dol/:action',{},
        {
            parseDolToGetTable:{
                method: 'GET',
                params:{
                    action:'tableName',
                    dolName:'@dolName'
                }
            }
        }
    )
}]);


angular.module('job_manage.service', ['ngResource'])
    .factory('JobManageService', ['$resource', 'serverAddress',
        function ($resource, serverAddress) {
            return $resource(serverAddress + '/taskManager/:action',
                {},
                {
                    addTask:{
                      method: 'POST',
                        params:{
                            action:'taskConfAdd'
                        }

                    },
                    //根据条件查询tasks
                    queryTasks: {
                        method: 'GET',
                        params: {
                            action: 'queryTasks',
                            taskGroupId: '@group',
                            owner: '@owner',
                            taskId: '@id',
                            isValid:'@isValid'
                        }
                    },
                    updateTask:{
                        method:'POST',
                        params:{
                            action:'updateTask'
                        }
                    },
                    queryTaskById:{
                        method:'GET',
                        params:{
                            action:'task',
                            taskid:'@taskid'
                        }
                    },
                    queryPreTaskById:{
                        method:'GET',
                        params:{
                            action:'queryPre',
                            taskid:'@taskid'
                        }
                    },
                    updatePre:{
                        method:'POST',
                        params:{
                            action:'updateTaskRela'
                        }
                    },
                    addTransferTask:{
                        method:'POST',
                        params:{
                            action:'transferTaskAdd'
                        }
                    },
                    getTransferTaskById:{
                        method:'GET',
                        params:{
                            action:'queryTransferById',
                            taskid:'@taskid'
                        }
                    },
                    updateTransferTask:{
                        method:'POST',
                        params:{
                            action:'updateTransfer'
                        }
                    },
                    addEtlLoadCfg:{
                        method:'POST',
                        params:{
                            action:'insertTransferParam'
                        }
                    },
                    //失效任务
                    invalidTask: {
                        method: 'POST',
                        params: {
                            action: 'invalidTask',
                            taskId: '@taskId'
                        }
                    },
                    validateTask:{
                        method: 'POST',
                        params: {
                            action: 'validateTask',
                            taskId: '@taskId'
                        }
                    },
                    deleteTask:{
                        method:'POST',
                        params:{
                            action:'deleteTask',
                            taskId:'@taskId'
                        }
                    },
                    getInfluencedTasksByTaskId:{
                      method:'GET',
                        params:{
                            action:'relaTask',
                            taskId:'@taskId'
                        }
                    },
                    //预跑任务
                    preRunTask: {
                        method: 'POST',
                        params: {
                            action: 'preRunTask',
                            startTime: '@startTime',
                            endTime: '@endTime',
                            taskId: '@taskId'
                        }
                    },
                    addPreTask:{

                    }
                });
        }
    ]);
angular.module('job_monitor.service', ['ngResource'])
    .factory('JobMonitorService', ['$resource', 'serverAddress',
        function ($resource, serverAddress) {
            return $resource(serverAddress + '/monitor/:action',
                {},
                {
                    //重跑任务
                    recallInstance: {
                        method: 'POST',
                        params: {
                            action: 'recallInstance',
                            instanceId: '@instanceId'
                        }
                    },
                    ////根据instanceId显示其自身以及所有的post tasks
                    //getSelfAndPostInstances: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getSelfAndPostInstances',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    //
                    ////批量重跑
                    //recallInstances: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'recallInstances'
                    //    }
                    //},
                    ////挂起任务
                    //suspendInstance: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'suspendInstance',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////批量挂起
                    //suspendInstances: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'suspendInstances'
                    //    }
                    //},
                    ////置为成功任务
                    successInstance: {
                        method: 'POST',
                        params: {
                            action: 'successInstance',
                            instanceId: '@instanceId'
                        }
                    },
                    ////批量置为成功
                    //successInstances: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'successInstances'
                    //    }
                    //},
                    ////直接依赖
                    //queryDirectRelation: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'queryDirectRelation',
                    //        instanceId: '@taskStatusId'
                    //    }
                    //},
                    ////所有依赖
                    //queryAllRelation: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'queryAllRelation',
                    //        instanceId: '@taskStatusId'
                    //    }
                    //},
                    ////查看最长路径
                    //getLongestPath: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getLongestPath',
                    //        instanceId: '@taskStatusId'
                    //    }
                    //},
                    ////快速通道
                    //raisePriorityInstance: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'raisePriorityInstance',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////级联重跑获得需要重跑的节点
                    //recallCascadeGetInstances: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'recallCascadeGetInstances'
                    //    }
                    //},
                    ////级联重跑
                    //recallCascade: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'recallCascade'
                    //    }
                    //},
                    ////获得任务实例log
                    //getInstanceLog: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getInstanceLog',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////查询预跑instances
                    //queryPreRunInstances: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'queryPreRunInstances',
                    //        startDate: '@startDate',
                    //        taskCommitter: '@taskCommitter',
                    //        taskNameOrId: '@taskNameOrId'
                    //    }
                    //},
                    ////实例状态分析
                    //instanceStatusAnalyze: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'instanceStatusAnalyze',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    //根据instanceId获得其直接下游的instances及其自身
                    getDirectInfluencedInstancesByInstanceId: {
                        method: 'GET',
                        params: {
                            action: 'getDirectInfluencedInstancesByInstanceId',
                            instanceId: '@instanceId'
                        }
                    }
                });
        }
    ]);
/**
 * Created by wei.shen on 2015/7/24.
 */

angular.module('table.service', ['ngResource'])
    .factory('TableService', ['$resource', '$routeParams', 'serverAddress',
        function ($resource, $routeParams, serverAddress) {
            return $resource(serverAddress + "/table/:action",
                {},
                {
                    queryCreateTableSql: {
                        method: 'POST',
                        params: { action: 'buildTableSql' }
                    },

                    buildHiveTable: {
                        method: 'POST',
                        params: { action: 'build' }
                    }

                }
            );
        }
    ]);
/**
 * Created by wei.shen on 2015/7/29.
 */

angular.module('fanli.filter',[])
    .filter('StringReplace',function() {
        return function (string, regex, dst) {
            var regExp = new RegExp(regex, "g");
            return string === undefined ? '' : string.replace(regExp, dst);
        }
    }).filter("dateFormat", function () {
        return function (input, formatStr, type) {
            Date.prototype.format = function (format) {
                var o = {
                    'M+': this.getMonth() + 1,
                    'd+': this.getDate(),
                    'h+': this.getHours(),
                    'm+': this.getMinutes(),
                    's+': this.getSeconds(),
                    'q+': Math.floor((this.getMonth() + 3) / 3),
                    'S': this.getMilliseconds()
                };
                if (/(y+)/.test(format) || /(Y+)/.test(format)) {
                    format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
                }
                for (var k in o) {
                    if (new RegExp('(' + k + ')').test(format)) {
                        format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
                    }
                }
                return format;
            };
            var date = new Date();
            if (type == 0) {
                var ymd = input.split('-');
                date.setFullYear(ymd[0], ymd[1] - 1, ymd[2]);
                return date.format(formatStr);

            }
            else
                return new Date(input).format(formatStr);
        }
    })

/**
 * Created by wei.shen on 2015/12/1.
 */

fanliApp.directive('bsTooltip', function(){
    return {
        restrict: 'A',
        link: function(scope, element, attrs){
            $(element).hover(function(){
                // on mouseenter
                $(element).tooltip('show');
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });
        }
    };
});
