/**
 * Created by wei.shen on 2015/7/24.
 */

angular.module('table.service', ['ngResource'])
    .factory('TableService', ['$resource', '$routeParams', 'serverAddress',
        function ($resource, $routeParams, serverAddress) {
            return $resource(serverAddress + "/table/:action",
                {},
                {
                    ////查询表的信息
                    //getTableMeta: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getTableInfo',
                    //        DBType: '@DBType',
                    //        DBName: '@DBName',
                    //        tableName: '@tableName'
                    //    }
                    //},

                    //查询建表语句
                    queryCreateTableSql: {
                        method: 'POST',
                        params: { action: 'queryCreateTableSql' }
                    },
                    //建表
                    buildTable: {
                        method: 'POST',
                        params: { action: 'buildTable' }
                    }
                }
            );
        }
    ]);