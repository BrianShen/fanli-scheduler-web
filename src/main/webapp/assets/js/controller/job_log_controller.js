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