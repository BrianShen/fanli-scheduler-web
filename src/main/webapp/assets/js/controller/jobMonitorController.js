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
        console.log(index)
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
