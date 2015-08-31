'use strict'
/**
 * Created by wei.shen on 2015/8/5.
 */

fanliApp.controller('transportTaskAddCtrl',function($scope,$http,TableService,DimService,ConstantService,JobManageService,JobMonitorService) {

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


    $scope.targetSelect  = function () {
        //setLoading(true,"正在查询...");
        console.log('target select is ' + $scope.conf_target);
        if($scope.conf_target == 'hive') {
            $scope.target_database_options = ['load','ods','dim'];
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
        console.log('src select is :' + $scope.conf_src);
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
                }

            },function(){})
        }

    }

    function initTaskName() {
        $scope.conf_taskName = $scope.conf_src + '2' +$scope.conf_target + '##' + $scope.conf_targetTable;
    }


    $scope.incrFieldChange = function() {

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
        var conf_storage_pattern = $scope.conf_storage_pattern!=undefined?$scope.conf_storage_pattern:"";
        var conf_partition_desc = $scope.conf_partition_desc!=undefined?$scope.conf_partition_desc:"";
        var conf_src_db_ab = $scope.conf_src_db_ab!=undefined?$scope.conf_src_db_ab:"";
        var conf_topic = $scope.conf_topic!=undefined?$scope.conf_topic:"";
        var conf_table_name_desc = $scope.conf_table_name_desc!=undefined?$scope.conf_table_name_desc:"";
        if($scope.conf_target=='hive'&&($scope.conf_target_db=='load'||$scope.conf_target_db == 'ods')) {
            $scope.conf_targetTable = conf_storage_pattern+'_'+conf_partition_desc+'_'+conf_src_db_ab+'_'+$scope.conf_src_table;
        } else if($scope.conf_target=='hive'&&($scope.conf_target_db=='dw'||$scope.conf_target_db == 'dm')) {
            $scope.conf_targetTable = conf_storage_pattern+'_'+conf_partition_desc+'_'+conf_topic+'_'+conf_table_name_desc;
        } else if($scope.conf_target=='hive'&&$scope.conf_target_db=='dim') {
            $scope.conf_targetTable ='s_'+ conf_src_db_ab+'_'+ $scope.conf_src_table;
        };
        console.log($scope.conf_targetTable);
    }

    $scope.getIncrField = function() {
        if($scope.conf_target_table_type == 'full'||$scope.conf_target_table_type == 'snapshot') {
            return;
        }else if($scope.conf_target_table_type == 'append') {
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
                    $scope.SRCColumn = data.result.columns;
                    requestTogetSql();
                }
            })
        }


    }

    function requestTogetSql() {
        var buildSql = TableService.queryCreateTableSql({},{
            name:$scope.conf_targetTable,
            columns:$scope.SRCColumn,
            partitions:getPartitions(),
            dbType:$scope.conf_target
        });
        buildSql.$promise.then(function(data) {
            $scope.conf_create_table_sql = data.result;
            console.log($scope.conf_create_table_sql);
        },function() {})
    }

    function getTransferSql() {
        var col = $scope.commonColumn;
        var sql = 'select ';
        if($scope.conf_src == 'sqlserver') {
            sql = sql +'[' + col[0].name + ']';
            for(var i = 1;i < col.length;i ++) {
                sql = sql + ','+'['+ col[i].name + ']';
            }
        } else {
            sql = sql +'`' + col[0].name + '`';
            for(var i = 1;i < col.length;i ++) {
                sql = sql + ','+'`'+ col[i].name + '`';
            }
        }
        sql = sql + ' from ' + $scope.conf_src_table ;
        if($scope.conf_target_table_type == 'append') {
            var incr = getColumnInfo($scope.conf_incr_field);
            console.log('增量字段是:' + incr);
            var where = handleWhere($scope.conf_src,incr.type);
            sql = sql  + where;
        }
        $scope.conf_transfer_sql = sql;
        console.log('查询sql是：' + sql);
    }

    function handleWhere(src,type) {
        var ret;
        if(src == 'hive') {
            switch ($scope.conf_cycle){
                case 'H':ret = "ds='${yyyy-MM-dd}' and hour='${HH;P1H}'";break;
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

    function getSourceColumns() {

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
        if($scope.conf_target_table_type == 'append'||$scope.conf_target_table_type =='snapshot') {
            switch ($scope.conf_cycle) {
                case 'H':
                    pt.push({name:'ds',type:'string',comment:''});
                    pt.push({name:'hour',type:'string',comment:''});
                    break;
                case 'D':
                    pt.push({name:'ds',type:'string',comment:''});
                    break;
                case 'M':
                    pt.push({name:'month',type:'string',comment:''});
                    break;
                case 'Y':
                    pt.push({name:'year',type:'string',comment:''});
                    break;
                case 'W':
                    pt.push({name:'week',type:'string',comment:''});
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



        $scope.submitTaskCfg = function() {
            generateReaderAndWriterByType();
            setLoading(true,'正在新增传输任务......');
            var req = JobManageService.addTransferTask({},{
                taskGroupId:$scope.conf_taskGroup,
                taskName:$scope.conf_taskName,
                resource:$scope.conf_src,
                command:'',
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
                    updateCommand(taskid);
                    addTransferParamToDatabase(taskid);


                }
            },function(data) {
                setLoading(false,'');
            })
        }
    }

    function updateCommand(taskid) {
        var res = JobManageService.updateTransferTask({},{
            taskId:taskid,
            command:'sh /home/hadoop/wormhole/release/bin/wormhole.sh ' + taskid + ' ' + '${unix_timestamp} '  + $scope.conf_offset
        });
        console.log('sh /home/hadoop/wormhole/release/bin/wormhole.sh ' + taskid + ' ' + '${unix_timestamp} ' + $scope.conf_offset);
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
                    }
                },function(){})
            }
        },function(){})
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
            encoding: "UTF-8",
            sql: $scope.conf_transfer_sql,
            concurrency: "1",
            needSplit: "false"
        };
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
            tableName: $scope.conf_targetTable,
            columns: getHiveColumns(),
            pre: ""
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

    function getMysqlToHiveReaderAndWriter() {
        $scope.reader = {
            plugin: "mysqlreader",
            connectProps: $scope.conf_src_domain,
            dbname:$scope.conf_src_db,
            encoding: "UTF-8",
            sql: $scope.conf_transfer_sql,
            concurrency: "1",
            needSplit: "false"
        };
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
            case 'H':cond = "ds='${yyyy-MM-dd}',hour='${HH;P1H}'@" + $scope.conf_targetTable+ "." + $scope.conf_target_db;break;
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
            dir = "hdfs://namenode171:54310/user/hive/bi_warehouse/" + $scope.conf_target_db.toUpperCase() + ".db/" + $scope.conf_targetTable;
        }

        var p = getPartitions();
        var partitionDir = ConstantService.getPartitionByCycle();
        if(p.length > 0) {
            switch ($scope.conf_cycle) {
                case 'H':dir = dir + partitionDir.H;break;
                case 'D':dir = dir + partitionDir.D;break;
                case 'W':dir = dir + partitionDir.W;break;
                case 'M':dir = dir + partitionDir.M;break;
                case 'Y':dir = dir + partitionDir.Y;break;
            }
            //dir = dir + "/" + "ds=" + "${CAL_YYYYMMDD_YESTERDAY}";
        }

        return dir;
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
