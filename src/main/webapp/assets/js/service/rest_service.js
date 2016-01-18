/**
 * Created by wei.shen on 2015/12/10.
 */


fanliApp.factory('restfulService',function($resource,$http,$q){
    return {
        reader : $resource("/fanli/load/sql",{tableid:'@tableid'}),

        writer : $resource("/fanli/load/pre",{taskId:'@taskId'}),

        domain : $resource("/fanli/dim/domain",{prop:'@prop'}),

        columnsComment : $resource("/meta/columns",{tableId:'@tableId',columnIds:'@columnIds',columnComments:'@columnComments'})
    }
})
