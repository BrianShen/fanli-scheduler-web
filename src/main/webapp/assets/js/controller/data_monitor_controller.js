/**
 * Created by wei.shen on 2015/11/25.
 */

fanliApp.controller('dataMonitorCtrl',function($scope,$resource,$routeParams,DimService) {
    //获取url里taskId
    $scope.taskId = $routeParams.taskId;
    //获取url中的操作选项 op='new'新增配置  op='edit' 修改配置
    $scope.op = $routeParams.op;

    $scope.options = [{src:'id',target:'id'},{src:'name',target:'name'}];

    //定义资源
    var monitorResource = $resource('/fanli/dataMonitor/:taskId',{},
        {
            queryMonitor:{
                 method:'GET',
                 params:{
                     taskId:'@taskId'
                 }
            },
            updateMonitor:{
                method:'PUT',
                params:{
                    taskId:'@taskId'
                }
            }
        });
    var newResource = $resource('/fanli/dataMonitor');
    //var updateResource = $resource('/fanli/dataMonitor/:taskId',{id:'@'},{update:{method:'PUT'}});
    var reader = $resource('/fanli/load/sql',{taskid:'@taskid'});
    var writer = $resource('/fanli/load/pre',{taskId:'@taskId'});

    if($scope.op == 'edit') {
        var ret = monitorResource.queryMonitor({taskId:$scope.taskId});
        ret.$promise.then(function(monitor) {
                $scope.detailId=monitor.detailId;
                $scope.owner = {cnName:monitor.ownerName};
                $scope.mail_suject = monitor.mailSubject;
                $('#mail-input-to').val(monitor.mailTo);
                $('#msg-input').val(monitor.mobile);
                $('#mail-input-cc').val(monitor.mailCc);
                $scope.developerOptions = DimService.queryAllDevelopers().$promise.then(function(data) {
                    $scope.developerOptions = data.results;
                    //生成邮件array
                    var mailList = [];
                    angular.forEach($scope.developerOptions,function(ele){
                        mailList.push(ele.ownerMailAddress)
                    });
                    var phoneList = [];
                    angular.forEach($scope.developerOptions,function(ele){
                        phoneList.push(ele.owneMobile);
                    });
                    //初始化tag input
                    initTagInput(mailList,phoneList);
                });
                $scope.mail_content = monitor.mailMessage;
                var contrast = JSON.parse(monitor.contrast);
                $scope.diff_type = contrast.diff_type;
                $scope.min_diff = contrast.min_diff;
                $scope.max_diff = contrast.max_diff;
        });
        //monitorResource.get({taskId:$scope.taskId},function(monitor) {
        //    $scope.ownerName = monitor.ownerName;
        //    $scope.mail_suject = monitor.mailSubject;
        //    $('#mail-input-to').val(monitor.mailTo);
        //    $('#msg-input').val(monitor.mobile);
        //    $('#mail-input-cc').val(monitor.mailCc);
        //    $scope.developerOptions = DimService.queryAllDevelopers().$promise.then(function(data) {
        //        $scope.developerOptions = data.results;
        //        //生成邮件array
        //        var mailList = [];
        //        angular.forEach($scope.developerOptions,function(ele){
        //            mailList.push(ele.ownerMailAddress)
        //        });
        //        var phoneList = [];
        //        angular.forEach($scope.developerOptions,function(ele){
        //            phoneList.push(ele.owneMobile);
        //        });
        //        //初始化tag input
        //        initTagInput(mailList,phoneList);
        //    });
        //    $scope.mail_content = monitor.mailMessage;
        //    var contrast = JSON.parse(monitor.contrast);
        //    $scope.diff_type = contrast.diff_type;
        //    $scope.min_diff = contrast.min_diff;
        //    $scope.max_diff = contrast.max_diff;
        //
        //},function(error) {
        //    console.log("error!")
        //})
    } else if($scope.op == 'new') {
        //生成默认邮件主题
        $scope.mail_suject = '任务' + $scope.taskId + '的告警';
        initDevelopers();
        generateMonitorInfoFromLoadCfg();
    }

    $scope.submitMonitorConfig = function() {
        if($scope.owner == undefined ||$scope.mail_suject == ''||$scope.mail_suject == undefined) {
            return;
        }
        if($('#mail-input-to').val() == ''||$('#mail-input-to').val() == undefined) {
            alert('Mail address can not be empty!');
            return;
        }

        if( $scope.min_diff == undefined||$scope.max_diff == undefined) {
            return;
        }

        if($scope.diff_type ==undefined) {
            alert('请选择差值类型');
            return;
        }

        if($scope.op == 'new') {
            newResource.save({
                ownerId:$scope.owner.ownerId,
                ownerName:$scope.owner.cnName,
                taskId:$scope.taskId,
                mobile:$('#msg-input').val(),
                mailTo:$('#mail-input-to').val(),
                mailCc:$('#mail-input-cc').val(),
                mailSubject:$scope.mail_suject,
                mailMessage:$scope.mail_content,
                sourceDatasource:$scope.src_connectProps,
                targetDatasource:$scope.target_connectProps,
                sourceSql:$scope.src_sql,
                targetSql:$scope.target_sql,
                contrast:JSON.stringify(getContrastItem())
            },function(data) {$scope.saveSuccess = true})
        } else{
            var ret =  monitorResource.updateMonitor({},{
                detailId:$scope.detailId,
                taskId:$scope.taskId,
                ownerName:$scope.owner.cnName,
                mobile:$('#msg-input').val(),
                mailTo:$('#mail-input-to').val(),
                mailCc:$('#mail-input-cc').val(),
                mailSubject:$scope.mail_suject,
                mailMessage:$scope.mail_content,
                contrast:JSON.stringify(getContrastItem())
            });

            ret.$promise.then(function(data) {alert('accepted')})
        }

    }


    function getContrastItem() {
        var ret = {};
        ret.diff_type = $scope.diff_type;
        ret.min_diff = $scope.min_diff;
        ret.max_diff = $scope.max_diff;
        return ret;
    }

    function initDevelopers() {
        //获取开发者名单
        $scope.developerOptions = DimService.queryAllDevelopers().$promise.then(function(data) {
            $scope.developerOptions = data.results;
            //生成邮件array
            var mailList = [];
            angular.forEach($scope.developerOptions,function(ele){
                mailList.push(ele.ownerMailAddress)
            });
            var phoneList = [];
            angular.forEach($scope.developerOptions,function(ele){
                phoneList.push(ele.owneMobile);
            });
            //初始化tag input
            initTagInput(mailList,phoneList);
        });
    }




    //setEvent();
    //function setEvent() {
    //    $("input[id^='mail-input']").on('removed', function (e, value) {
    //        // do something...
    //        alert("remove!")
    //    })
    //}

    function initTagInput(mailList,phoneList) {
        var tag_input_mail = $("input[id^='mail-input']");
        tag_input_mail.tag(
            {
                placeholder:tag_input_mail.attr('placeholder'),
                //enable typeahead by specifying the source array
                source: mailList//defined in ace.js >> ace.enable_search_ahead
            }
        );

        var tag_input_msg = $("#msg-input");
        tag_input_msg.tag({
            placeholder:tag_input_msg.attr('placeholder'),
            source:phoneList
            //enable typeahead by specifying the source array
            //source: mailList//defined in ace.js >> ace.enable_search_ahead
        });
    }





    function generateMonitorInfoFromLoadCfg() {
        reader.get({taskid:$scope.taskId},function(data) {
            var readParam = JSON.parse(data.result);
            var where = '';
            //console.log(readParam.sql);
            var sql = readParam.sql;
            if(sql.toLowerCase().indexOf('where') > 0) {
                if(readParam.where != ''&& readParam.where != undefined && readParam.where != null) {
                    where = readParam.where
                } else {
                    where = sql.split('where')[1].trim();
                }
            }

            var tableName = '';
            if(readParam.tableName != ''&& readParam.tableName != undefined && readParam.tableName != null) {
                tableName = readParam.tableName;
            } else {
                if(sql.indexOf('where') > 0) {
                    tableName = sql.match(/from(.+)where/)[1].trim();
                } else tableName = sql.match(/from(.+)/)[1].trim();
            }

            if(readParam.plugin == 'hivereader') {
                $scope.src_connectProps = 'hive172';
                $scope.src_sql = 'select count(1) as cnt from ' + tableName;
            } else {
                $scope.src_connectProps = readParam.connectProps;
                $scope.src_sql = 'select count(1) as cnt from ' + readParam.dbname + '.'  + tableName;
            }
            if(where != '') {
                $scope.src_sql += ' where ' + where;
            }
            console.log($scope.src_sql);
        });

        writer.get({taskId:$scope.taskId},function(data) {
            var writeParam = JSON.parse(data.result);
            var tableName = '';
            var dbName = '';
            var hive_table_add_partition_switch = writeParam.hive_table_add_partition_switch;
            var hive_table_add_partition_condition = writeParam.hive_table_add_partition_condition;
            var partionArray = [];
            if(hive_table_add_partition_switch) {
                var partitions = hive_table_add_partition_condition.split('@')[0];
                partionArray = partitions.split(',');
            }
            var sql = 'select count(1) as cnt from ';
            if(writeParam.plugin == 'hdfswriter') {
                tableName = writeParam.dir.match(/\.db\/([^\/]+)/)[1].trim();
                var dbPath = writeParam.dir.match(/.*\/(.*)\.db\/.*/)[1].trim();
                if(dbPath == 'tmp') {
                    dbName = 'tmpdb'
                } else {
                    dbName = dbPath.toLowerCase();
                }
                sql += dbName + '.' + tableName;
                $scope.target_connectProps = 'hive172';
                if(hive_table_add_partition_switch) {
                    sql += ' where ';
                    sql += partionArray[0];
                    for(var i = 1;i <  partionArray.length;i ++) {
                        sql +=  ' and ' + partionArray[i];
                    }
                }
            } else if(writeParam.plugin == 'sqlserverwriter') {
                tableName = writeParam.tableName;
                dbName = writeParam.dbname;
                sql += dbName + '.' + tableName;

                if(writeParam.pre.toLowerCase().indexOf('where') > 0) {
                    var where = writeParam.pre.match(/where(.*)/i)[1].trim();
                    sql+= ' where ' + where;
                }
                $scope.target_connectProps = writeParam.connectProps;
            }

            $scope.target_sql = sql;
            console.log($scope.target_sql);
        })
    }




    //选中开发者 默认设置初始化邮件，电话等配置
    //$scope.generateInitConfig = function() {
    //
    //    var owner = $scope.owner;
    //    var mailTo = owner.ownerMailAddress;
    //    //邮件列表赋初值
    //    if(tagInput.val().split(',').length > 1) {
    //        tagInput.val(mailTo);//先这样吧 不作替换
    //    } else {
    //        tagInput.val(mailTo);
    //    }
    //
    //    //alert($scope.mailTo);
    //    //alert($("#form-field-tags1").val());
    //    //initBootStrapTag();
    //
    //}

    //function initBootStrapTag() {
    //    var tag_input = $("input[id^='form-field-tags']");
    //    if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) )
    //    {
    //        tag_input.tag(
    //            {
    //                placeholder:tag_input.attr('placeholder'),
    //                //enable typeahead by specifying the source array
    //                source: ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
    //            }
    //        );
    //    }
    //    else {
    //        //display a textarea for old IE, because it doesn't support this plugin or another one I tried!
    //        tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
    //        //$('#form-field-tags').autosize({append: "\n"});
    //    }
    //}
})
