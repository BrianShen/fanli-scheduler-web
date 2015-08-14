/**
 * Created by wei.shen on 2015/7/24.
 */

angular.module('table.service', ['ngResource'])
    .factory('TableService', ['$resource', '$routeParams', 'serverAddress',
        function ($resource, $routeParams, serverAddress) {
            return $resource(serverAddress + "/table/:action",
                {},
                {
                    queryCreateTableSql: {
                        method: 'POST',
                        params: { action: 'buildTableSql' }
                    },

                    buildTable: {
                        method: 'POST',
                        params: { action: 'buildTable' }
                    }

                }
            );
        }
    ]);