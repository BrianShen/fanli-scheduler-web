
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
                            group: '@group',
                            developer: '@developer',
                            id: '@id'
                        }
                    },
                    //根据taskId获得task
                    getTaskByTaskId: {
                        method: 'GET',
                        params: {
                            action: 'getTaskByTaskId',
                            taskId: '@taskId'
                        }
                    },
                    //根据taskIds获得tasks
                    getTasksByTaskIds: {
                        method: 'GET',
                        params: {
                            action: 'getTasksByTaskIds',
                            taskIds: '@taskIds'
                        }
                    },
                    //根据taskId获得其影响的tasks（后继任务+同源任务58、59）
                    getInfluencedTasksByTaskId: {
                        method: 'GET',
                        params: {
                            action: 'getInfluencedTasksByTaskId',
                            taskId: '@taskId'
                        }
                    },
                    //根据taskId获得同源的tasks
                    getSameSourceTasksByTaskId: {
                        method: 'GET',
                        params: {
                            action: 'getSameSourceTasksByTaskId',
                            taskId: '@taskId'
                        }
                    },
                    //获得所有的task names
                    getAllTaskNames: {
                        method: 'GET',
                        params: {
                            action: 'getAllTaskNames'
                        }
                    },
                    //获取任务id获得任务的目标表信息
                    getTargetTables: {
                        method: 'GET',
                        params: {
                            action: 'getTargetTables',
                            taskId: '@taskId'
                        }
                    },
                    //级联预跑获得需要预跑的任务
                    getTasksForCascadePreRun: {
                        method: 'GET',
                        params: {
                            action: 'getTasksForCascadePreRun',
                            taskId: '@taskId'
                        }
                    },
                    //级联重跑
                    cascadePreRun: {
                        method: 'POST',
                        params: {
                            action: 'cascadePreRun'
                        }
                    },
                    //预跑任务
                    preRunTask: {
                        method: 'POST',
                        params: {
                            action: 'preRunTask',
                            startTime: '@startTime',
                            endTime: '@endTime',
                            taskId: '@taskId',
                            committer: '@committer',
                            type: '@type'
                        }
                    },
                    //批量预跑任务
                    preRunTasks: {
                        method: 'POST',
                        params: {
                            action: 'preRunTasks',
                            startTime: '@startTime',
                            endTime: '@endTime',
                            taskIds: '@taskIds'
                        }
                    },
                    //失效任务
                    invalidTask: {
                        method: 'POST',
                        params: {
                            action: 'invalidTask',
                            taskId: '@taskId',
                            type: '@type'
                        }
                    },
                    //生效任务
                    validTask: {
                        method: 'POST',
                        params: {
                            action: 'validTask',
                            taskId: '@taskId',
                            type: '@type'
                        }
                    },
                    //删除任务
                    deleteTask: {
                        method: 'DELETE',
                        params: {
                            action: 'deleteTask',
                            taskId: '@taskId',
                            type: '@type'
                        }
                    },
                    //计算任务保存调度配置
                    updateCalculateTask: {
                        method: 'POST',
                        params: {
                            action: 'updateCalculateTask'
                        }
                    },
                    //更新传输任务调度配置信息与McTaskInfo
                    updateTransferTask: {
                        method: 'POST',
                        params: {
                            action: 'updateTransferTask'
                        }
                    },
                    //新增计算任务
                    addCalculateTask: {
                        method: 'POST',
                        params: {
                            action: 'addCalculateTask'
                        }
                    },
                    //更新计算任务的目标表
                    updateTargetTables: {
                        method: 'POST',
                        params: {
                            action: 'updateTargetTables'
                        }
                    },
                    //将所有预跑实例置为挂起
                    batchStopTask: {
                        method: 'POST',
                        params: {
                            action: 'batchStopTask',
                            taskId: '@taskId'
                        }
                    },
                    //修改所有taskId对应的任务的owner
                    batchChangeOwner: {
                        method: 'POST',
                        params: {
                            action: 'batchChangeOwner',
                            taskIds: '@taskIds',
                            owner: '@owner'
                        }
                    }
                });
        }
    ]);