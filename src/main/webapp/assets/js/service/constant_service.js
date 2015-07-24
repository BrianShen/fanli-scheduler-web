/**
 * Created by wei.shen on 2015/7/22.
 */

angular.module("constant.service",[])
    .factory("ConstantService",[function(){
        return {
            getGroupOptions:function() {
                return [
                    {ID: 1, Text: 'ods'},
                    {ID: 2, Text: 'load'},
                    {ID: 3, Text: 'dm'},
                    {ID: 4, Text: 'dw'},
                    {ID: 5, Text: 'rpt'},
                    {ID: 6, Text: 'dim'}
                ];
            },
            getTableRefreshCycle:function() {
                return [
                    {ID: 'any', Text: '--请选择--'},
                    {ID: 'full', Text: '全量'},
                    {ID: 'append', Text: '增量'}
                ];
            }
        }
    }]);
