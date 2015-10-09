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
            console.log($scope.developer.chName);
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
            $scope.conf_developer = $scope.developer.chName;
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
