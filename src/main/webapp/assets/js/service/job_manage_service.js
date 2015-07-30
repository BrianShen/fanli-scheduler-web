
angular.module('job_manage.service', ['ngResource'])
    .factory('JobManageService', ['$resource', 'serverAddress',
        function ($resource, serverAddress) {
            return $resource(serverAddress + '/taskManager/:action',
                {},
                {
                    addTask:{
                      method: 'POST',
                        params:{
                            action:'taskConfAdd'
                        }

                    },
                    //根据条件查询tasks
                    queryTasks: {
                        method: 'GET',
                        params: {
                            action: 'queryTasks',
                            taskGroupId: '@group',
                            owner: '@owner',
                            taskId: '@id'
                        }
                    }
                });
        }
    ]);