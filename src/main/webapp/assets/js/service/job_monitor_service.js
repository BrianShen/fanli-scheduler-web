angular.module('job_monitor.service', ['ngResource'])
    .factory('JobMonitorService', ['$resource', 'serverAddress',
        function ($resource, serverAddress) {
            return $resource(serverAddress + '/monitor/:action',
                {},
                {
                    //重跑任务
                    recallInstance: {
                        method: 'POST',
                        params: {
                            action: 'recallInstance',
                            instanceId: '@instanceId'
                        }
                    }
                    ////根据instanceId显示其自身以及所有的post tasks
                    //getSelfAndPostInstances: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getSelfAndPostInstances',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    //
                    ////批量重跑
                    //recallInstances: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'recallInstances'
                    //    }
                    //},
                    ////挂起任务
                    //suspendInstance: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'suspendInstance',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////批量挂起
                    //suspendInstances: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'suspendInstances'
                    //    }
                    //},
                    ////置为成功任务
                    //successInstance: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'successInstance',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////批量置为成功
                    //successInstances: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'successInstances'
                    //    }
                    //},
                    ////直接依赖
                    //queryDirectRelation: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'queryDirectRelation',
                    //        instanceId: '@taskStatusId'
                    //    }
                    //},
                    ////所有依赖
                    //queryAllRelation: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'queryAllRelation',
                    //        instanceId: '@taskStatusId'
                    //    }
                    //},
                    ////查看最长路径
                    //getLongestPath: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getLongestPath',
                    //        instanceId: '@taskStatusId'
                    //    }
                    //},
                    ////快速通道
                    //raisePriorityInstance: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'raisePriorityInstance',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////级联重跑获得需要重跑的节点
                    //recallCascadeGetInstances: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'recallCascadeGetInstances'
                    //    }
                    //},
                    ////级联重跑
                    //recallCascade: {
                    //    method: 'POST',
                    //    params: {
                    //        action: 'recallCascade'
                    //    }
                    //},
                    ////获得任务实例log
                    //getInstanceLog: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getInstanceLog',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////查询预跑instances
                    //queryPreRunInstances: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'queryPreRunInstances',
                    //        startDate: '@startDate',
                    //        taskCommitter: '@taskCommitter',
                    //        taskNameOrId: '@taskNameOrId'
                    //    }
                    //},
                    ////实例状态分析
                    //instanceStatusAnalyze: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'instanceStatusAnalyze',
                    //        instanceId: '@instanceId'
                    //    }
                    //},
                    ////根据instanceId获得其直接下游的instances及其自身
                    //getDirectInfluencedInstancesByInstanceId: {
                    //    method: 'GET',
                    //    params: {
                    //        action: 'getDirectInfluencedInstancesByInstanceId',
                    //        instanceId: '@instanceId'
                    //    }
                    //}
                });
        }
    ]);