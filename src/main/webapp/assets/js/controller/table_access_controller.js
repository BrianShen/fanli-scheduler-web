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

        //if($scope.ddl.toLowerCase().indexOf('drop') > 0) {
        //    alert('ddl语句中不能有drop');
        //    return;
        //}
        setLoading(true,'正在执行...');
        $scope.exeSuccess = false;
        $http.get("/fanli/table/hiveCommand",{params:{path:$scope.ddlPath}})
            .success(function(data) {
                if(data.isSuccess) {
                    $scope.ddl +="\n";
                    //$scope.ddl += data.result;
                    $scope.exeSuccess = true;
                } else alert("excute ddl failed");
                setLoading(false,'');
            }).error(function(data) {setLoading(false,'');})
    }

    function setLoading(a,b) {
        $scope.isLoading = a;
        $scope.loadingMsg = b;
    }


})