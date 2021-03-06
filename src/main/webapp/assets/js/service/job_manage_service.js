
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
                            taskId: '@id',
                            isValid:'@isValid'
                        }
                    },
                    updateTask:{
                        method:'POST',
                        params:{
                            action:'updateTask'
                        }
                    },
                    queryTaskById:{
                        method:'GET',
                        params:{
                            action:'task',
                            taskid:'@taskid'
                        }
                    },
                    queryPreTaskById:{
                        method:'GET',
                        params:{
                            action:'queryPre',
                            taskid:'@taskid'
                        }
                    },
                    updatePre:{
                        method:'POST',
                        params:{
                            action:'updateTaskRela'
                        }
                    },
                    addTransferTask:{
                        method:'POST',
                        params:{
                            action:'transferTaskAdd'
                        }
                    },
                    getTransferTaskById:{
                        method:'GET',
                        params:{
                            action:'queryTransferById',
                            taskid:'@taskid'
                        }
                    },
                    updateTransferTask:{
                        method:'POST',
                        params:{
                            action:'updateTransfer'
                        }
                    },
                    addEtlLoadCfg:{
                        method:'POST',
                        params:{
                            action:'insertTransferParam'
                        }
                    },
                    //失效任务
                    invalidTask: {
                        method: 'POST',
                        params: {
                            action: 'invalidTask',
                            taskId: '@taskId'
                        }
                    },
                    validateTask:{
                        method: 'POST',
                        params: {
                            action: 'validateTask',
                            taskId: '@taskId'
                        }
                    },
                    deleteTask:{
                        method:'POST',
                        params:{
                            action:'deleteTask',
                            taskId:'@taskId'
                        }
                    },
                    getInfluencedTasksByTaskId:{
                      method:'GET',
                        params:{
                            action:'relaTask',
                            taskId:'@taskId'
                        }
                    },
                    //预跑任务
                    preRunTask: {
                        method: 'POST',
                        params: {
                            action: 'preRunTask',
                            startTime: '@startTime',
                            endTime: '@endTime',
                            taskId: '@taskId'
                        }
                    },
                    addPreTask:{

                    }
                });
        }
    ]);