/**
 * Created by wei.shen on 2015/7/29.
 */

fanliApp.controller("MonitorCtrl",function($scope,$http,$filter,ConstantService) {
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

    $scope.submitSearch = function() {
        $http.get("/fanli/monitor/query",{params:{
            taskId:$scope.taskId,
            owner:$scope.developer,
            startTime:$scope.startDate,
            endTime:$scope.endDate,
            status:$scope.jobStatus
        }}).success(function(data) {
            $scope.jobInstanses = data.results;
            console.log("success");
        })
    };
    //获得周期的css
    $scope.getExecutionCycleLabel = function (cycle) {
        return ConstantService.getCycleCss(cycle);
    };

    //获得状态的文字描述
    $scope.getStatusText = function (status) {
        return ConstantService.statusToText(status);
    };

    //获得周期的文字描述
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


})
