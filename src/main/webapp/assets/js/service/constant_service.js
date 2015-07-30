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
            },
            getJobMonitorStatus:function() {
                return [
                    {ID: 100, Text: '不限'},
                    {ID: 1, Text: 'success'},
                    {ID: -1, Text: 'fail'},
                    {ID: 0, Text: 'init'},
                    {ID: 4, Text: 'init-error'},
                    {ID: 3, Text: 'suspend'},
                    {ID: 5, Text: 'wait'},
                    {ID: 2, Text: 'running'},
                    {ID: 6, Text: 'ready'},
                    {ID: 7, Text: 'timeout'}
                ];
            },
            //获得周期对应的css
            getCycleCss: function (text) {
                switch (text) {
                    case 'H':
                        return 'label-warning';
                    case 'D':
                        return 'label-primary';
                    case 'W':
                        return 'label-success';
                    case 'M':
                        return 'label-danger';
                    case 'Y':
                        return 'label-purple';
                }
            },
            statusToText: function (status) {
                switch (status) {
                    case 1:
                        return 'success';
                    case -1:
                        return 'fail';
                    case 0:
                        return 'init';
                    case 4:
                        return 'init-error';
                    case 3:
                        return 'suspend';
                    case 5:
                        return 'wait';
                    case 2:
                        return 'running';
                    case 6:
                        return 'ready';
                    case 7:
                        return 'timeout';
                    default :
                        return '未知';
                }
            },
            //获得cycle的中文解释
            cycleToText: function (cycle) {
                switch (cycle) {
                    case 'H':
                        return '小时';
                    case 'D':
                        return '日';
                    case 'W':
                        return '周';
                    case 'M':
                        return '月';
                    case 'mi':
                        return '分';
                }
            }

        }
    }]);
