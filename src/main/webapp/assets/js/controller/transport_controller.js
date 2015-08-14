'use strict'
/**
 * Created by wei.shen on 2015/8/5.
 */

fanliApp.controller('transportTaskAddCtrl',function($scope,$http,DimService,ConstantService,JobManageService) {

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

    $scope.targetSelect  = function () {
        $scope.showPartition = true;
        //setLoading(true,"正在查询...");
        if($scope.conf_target == 'hive') {
            $scope.target_database_options = ['load','ods','dw','dm','dim','tmpdb'];
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
        $scope.setTaskName();
    }

    $scope.sourceSelect = function() {
        //setLoading(true,"正在查询...");
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
        $scope.setTaskName();
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


    $scope.incrFieldChange = function() {

    }
    $scope.submitForm = function() {
        setAlertMessage(false,'');
        if(!checkForm()) {
            return;
        }
        getSourceTableInfo();

        //checkSourceTableExists();



    }

    $scope.getIncrField = function() {
        if($scope.conf_target_table_type == 'full'||$scope.conf_target_table_type == 'snapshot') {
            return;
        }else if($scope.conf_target_table_type == 'append') {
            $scope.fieldOptions = [];
            if($scope.conf_src=='hive') {
                getHiveTablePartitionField();
            } else {
                getJdbcIncreaseField();
            }
        }
    }

    function getJdbcIncreaseField() {
        $http.get("/fanli/db/columns",{
            params:{
                tableName:$scope.conf_src_table.trim(),
                connectProp:$scope.conf_src_domain,
                db:$scope.conf_src_db
            }
        }).success(function(data) {
            if(data.isSuccess) {
                $scope.fieldOptions = data.columns;
                //var a = [];
                //a = data.columns;
                //for(var i = 0;i < a.length;i ++) {
                //    $scope.fieldOptions.push(a[i].name);
                //}
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
                        //var p = [];
                        //p = data.partitions;
                        //for(var i = 0;i < p.length;i ++) {
                        //    $scope.fieldOptions.push(p[i].name);
                        //}

                })
    }


    $scope.saveTargetInfo = function() {
        console.log($scope.conf_target + '  ' + $scope.conf_incr_field );
    }

    function getSourceTableInfo() {
        if($scope.conf_src == ''|| $scope.conf_src_table == ''||$scope.conf_src_table==undefined) {
            return;
        };
        if($scope.conf_src == 'mysql' || $scope.conf_src == 'sqlserver'){
            getJdbcTableInfo();
        }else if($scope.conf_src = 'hive') {
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
            generateReaderAndWriterByType();
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
                    var taskid = data.result.taskId;
                    addTransferParamToDatabase(taskid);
                    showAlert('新增传输成功');
                    setLoading(false,'')
                }
            },function() {

            })
        }
    }

    function addTransferParamToDatabase(taskid) {
        $http.post("/")
    }

    function generateReaderAndWriterByType() {
        if($scope.conf_src == 'hive'&& $scope.conf_target == 'sqlserver') {
            getHiveToSqlserverReaderAndWriter();
        }else if($scope.conf_src == 'mysql'&& $scope.conf_target == 'hive') {
            getOtherToHiveReaderAndWriter();
        }
    }

    function getHiveToSqlserverReaderAndWriter() {
         $scope.reader = {
            plugin:'hive',
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
            tableName: $scope.conf_targetTable,
            columns: "orderid,fromorderid,orderstatus,orderdate,ds",
            pre: "delete from ctrip_dd_test"
        }
    }

    function getOtherToHiveReaderAndWriter() {

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
