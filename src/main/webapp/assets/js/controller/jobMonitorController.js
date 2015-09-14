/**
 * Created by wei.shen on 2015/7/29.
 */

fanliApp.controller("MonitorCtrl",function($scope,$http,$filter,$modal,ConstantService,JobMonitorService) {
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
            $scope.jobInstanses =  $filter('orderBy')(data.results, 'startTime',$scope.reverse);
            setLoding(false);
        }).error(function() {
            setAlert(true,'alert-danger','未知异常');
            setLoding(false);
        })
    };

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

    $scope.instanceInfo = function (index) {
        var job = getJobByIndex(index);
        window.open("#/job_log/" + job.taskStatusId );
    };

    var getJobByIndex = function(index) {
        return $scope.jobInstanses[index];
    }
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


    //重跑任务
    $scope.reRunJob = function (index) {
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
                $scope.jobInstanses[index].status = 0;
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
                        $scope.jobInstanses[index].status = 1;
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


})
