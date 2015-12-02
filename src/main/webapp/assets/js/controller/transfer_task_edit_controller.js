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