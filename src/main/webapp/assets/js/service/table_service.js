/**
 * Created by wei.shen on 2015/7/24.
 */

angular.module('table.service', ['ngResource'])
    .factory('TableService', ['$resource', '$routeParams', 'serverAddress',
        function ($resource, $routeParams, serverAddress) {
            return $resource(serverAddress + "/table/:action",
                {},
                {
                    ////��ѯ�����Ϣ
                    //getTableMeta: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getTableInfo',
                    //        DBType: '@DBType',
                    //        DBName: '@DBName',
                    //        tableName: '@tableName'
                    //    }
                    //},

                    //��ѯ�������
                    queryCreateTableSql: {
                        method: 'POST',
                        params: { action: 'queryCreateTableSql' }
                    },
                    //����
                    buildTable: {
                        method: 'POST',
                        params: { action: 'buildTable' }
                    }
                }
            );
        }
    ]);