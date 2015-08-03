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
            }
        }
    )
}]);
