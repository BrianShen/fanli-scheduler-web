/**
 * Created by wei.shen on 2015/8/27.
 */


fanliApp.factory("DolService",['$resource', 'serverAddress',function($resource,serverAddress) {
    return $resource(serverAddress + '/dol/:action',{},
        {
            parseDolToGetTable:{
                method: 'GET',
                params:{
                    action:'tableName',
                    dolName:'@dolName'
                }
            }
        }
    )
}]);
