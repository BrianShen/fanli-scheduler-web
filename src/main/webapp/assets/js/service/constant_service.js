/**
 * Created by wei.shen on 2015/7/22.
 */

angular.module("constant.service",[])
    .factory("ConstantService",[function(){
        return {

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
                    {ID: 99,  Text:'unsuccess'},
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

            getTaskGroupCss:function(groupid) {
              switch (groupid) {
                  case 1:
                      return 'label label-warning arrowed arrowed-right';
                  case 2:
                      return 'label arrowed';
                  case 3:
                      return 'label label-success arrowed-in arrowed-in-right';
                  case 4:
                      return 'label label-danger arrowed';
                  case 5:
                      return 'label label-info arrowed-right arrowed-in';
                  case 6:
                      return 'label label-yellow arrowed-in';
              }
            },

            taskGroupIdToText:function(groupid) {
              switch (groupid) {
                  case 1:return 'ODS';
                  case 2:return 'LOAD';
                  case 3:return 'DM';
                  case 4:return 'DW';
                  case 5:return 'EXPORT';
                  case 6:return 'DIM';
                  case 7:return 'ONLINE'
              }
            },

            getTimePatterns:function() {
              return [
                  {id:1,name:'今天(YYYY-MM-DD)',v:'${this_day10}'},
                  {id:2,name:'昨天(YYYY-MM-DD)',v:'${date}'},
                  {id:3,name:'当前小时(YYYY-MM-DD HH:00:00)',v:'${this_hour}'},
                  {id:4,name:'UNIX_TIMESTAMP',v:'${unix_timestamp}'},
                  {id:5,name:'前一小时(YYYY-MM-DD HH:00:00)',v:'${last_hour}'}
              ]
            },

            getCycleOptions:function(){
                return {
                    H: '时', D: '日', W: '周', M: '月', Y: '年'
                };
            },
            getTaskGroupOption:function() {
              return [
                  {ID: 1, Text: 'ods'},
                  {ID: 2, Text: 'load'},
                  {ID: 3, Text: 'dm'},
                  {ID: 4, Text: 'dw'},
                  {ID: 5, Text: 'export'},
                  {ID: 6, Text: 'dim'},
                  {ID: 7, Text: 'online'}
              ];
            },

            //根据周期获得偏移量的选项
            getOffsetsByCycle: function (cycle) {
                switch (cycle) {
                    case 'H':
                        return ['H0', 'H1', 'H2', 'H3', 'H4', 'H5', 'H6', 'H7', 'H8', 'H9', 'H10', 'H11', 'H12', 'H13', 'H14', 'H15', 'H16',
                            'H17', 'H18', 'H19', 'H20', 'H21', 'H22', 'H23', 'H24'
                        ];
                    case 'D':
                        return ['D0', 'D1', 'D2', 'D3', 'D4', 'D5', 'D6', 'D7', 'D8', 'D9', 'D10', 'D11', 'D12', 'D13', 'D14', 'D15', 'D16',
                            'D17', 'D18', 'D19', 'D20', 'D21', 'D22', 'D23', 'D24', 'D25', 'D26', 'D27', 'D28', 'D29', 'D30', 'D31'
                        ];
                    case 'W':
                        return ['W0', 'W1', 'W2', 'W3', 'W4'];
                    case 'M':
                        return ['M0', 'M1', 'M2', 'M3', 'M4', 'M5', 'M6'];
                }
            },

            getPriorityOption:function() {
              return [
                  {ID: 1, Text: '高'},
                  {ID: 2, Text: '中'},
                  {ID: 3, Text: '低'}
              ];
            },

            getIfRecallOption:function() {
              return [
                  {ID: 1, Text: '是'},
                  {ID: 0, Text: '否'}
              ];
            },

            getRecallLimitOption:function() {
              return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            },

            getRecallIntervalOption:function() {
              return [
                  {ID: 1, Text: '1分钟'},
                  {ID: 2, Text: '2分钟'},
                  {ID: 3, Text: '3分钟'},
                  {ID: 4, Text: '4分钟'},
                  {ID: 5, Text: '5分钟'},
                  {ID: 6, Text: '6分钟'},
                  {ID: 7, Text: '7分钟'},
                  {ID: 8, Text: '8分钟'},
                  {ID: 9, Text: '9分钟'},
                  {ID: 10, Text: '10分钟'}
              ];
            },

            getOffsetOption:function() {
              return ['D0', 'D1', 'D2', 'D3', 'D4', 'D5', 'D6', 'D7', 'D8', 'D9', 'D10',
                  'M0', 'M1', 'M2', 'M3', 'M4', 'M5', 'M6', 'M7', 'M8', 'M9', 'M10'];
            },

            getTimeOutOption:function() {
              return [
                  {ID: 30, Text: '30分钟'},
                  {ID: 60, Text: '1小时'},
                  {ID: 90, Text: '1.5小时'},
                  {ID: 120, Text: '2小时'},
                  {ID: 150, Text: '2.5小时'},
                  {ID: 180, Text: '3小时'},
                  {ID: 210, Text: '3.5小时'},
                  {ID: 240, Text: '4小时'}
              ];
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
            },
            getTransferHivePartition:function() {
                return [
                    {id:1,name:'无分区',partition:''},
                    {id:2,name:'ds',partition:'ds string'},
                    {id:3,name:'ds,hour',partition:'ds string,hour string'}
                ]
            },
            getStoragePattern:function() {
                return [
                    {id: 1,pattern:'incr'},
                    {id: 2,pattern:'snapshot'},
                    {id: 3,pattern:'zip'},
                    {id: 4,pattern:'full'},
                    {id: 5,pattern:'chage'}
                ]
            },
            getPartitionDesc:function() {
                return [
                    {id:1 ,name:'h',desc:'天(ds),小时(hour)'},
                    {id:2 ,name:'d',desc:'天(ds)'},
                    {id:3 ,name:'w',desc:'周(week)'},
                    {id:4 ,name:'m',desc:'月(month)'},
                    {id:5 ,name:'y',desc:'年(year)'},
                    {id:7 ,name:'n',desc:'无分区'}
                ]
            },
            getTopic:function() {
                return [
                    {id:1,name:'common',ab:'com'},
                    {id:2,name:'user',ab:'usr'},
                    {id:3,name:'order',ab:'odr'},
                    {id:4,name:'traffic',ab:'tra'}
                ]
            },
            getPartitionByCycle:function() {
                return {
                    H:"/ds=${yyyy-MM-dd;P1H}/hour=${HH;P1H}",
                    HD:"/ds=${yyyy-MM-dd}",
                    D:"/ds=${yyyy-MM-dd;P1D}",
                    W:"/week=${yyyy-MM-dd;F1W}",
                    M:"/month=${yyyy-MM;P1M}",
                    Y:"/year=${yyyy;P1Y}"
                }
            }

        }
    }]);
