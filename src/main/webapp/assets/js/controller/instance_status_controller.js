/**
 * Created by wei.shen on 2015/11/5.
 */

fanliApp.controller('instanceStatusCtrl',function($scope,$resource,$routeParams) {
    $scope.status_id = $routeParams.instanceId;
    var treeResource = $resource("/fanli/monitor/instanceTree/:instanceId",{instanceId:'@instanceId'});
    var treeChart =echarts.init(document.getElementById('instanceTree'));
    treeResource.get({instanceId:$scope.status_id},
        function(data) {
            if(data.code == 200) {
                var option = getRelaTreeOption(data.tree);
                console.log(JSON.stringify(option));
                console.log('----');
                treeChart.setOption(option);
                console.log(treeChart.getOption())


            }
        }
    )

    var getRelaTreeOption = function(tree) {
        var option = {

        };
        //title
        var title = {
            text: '树图'
        }
        title.subtext = tree.self.instanceId  + "的血缘树";
        option.title = title;


        //toolbox
        //var toolbox = {
        //    show : true,
        //    feature : {
        //        mark : {show: true},
        //        dataView : {show: true, readOnly: false},
        //        restore : {show: true},
        //        saveAsImage : {show: true}
        //    }
        //};
        //option.toolbox = toolbox;


        //legend
        //var legend = {
        //    orient:'vertical',
        //    x:'right',
        //    data:[
        //        {name:'success',textStyle:{color:'#458B00'}}
        //    ]
        //}
        //option.legend = legend;

        //series
        var series = [
            {
                name: '树图',
                type: 'tree',
                orient: 'vertical',  // vertical horizontal
                rootLocation: {x: 'center', y: 'center'}, // 根节点位置  {x: 100, y: 'center'}
                nodePadding: 150,
                roam: 'scale',
                direction: 'inverse',
                symbol: 'rectangle',
                symbolSize:30,
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            formatter: "{b}"
                        },
                        lineStyle: {
                            color: '#48b',
                            shadowColor: '#000',
                            shadowBlur: 3,
                            shadowOffsetX: 3,
                            shadowOffsetY: 5,
                            type: 'dotted' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                        }
                    },
                    emphasis: {
                        label: {
                            show: true
                        }
                    }
                }
            }
        ];
        var data = getTreeDataRecursion(tree);
        series[0].data = [];
        series[0].data.push(data);
        option.series = series;
        return option;
    }

    function getTreeDataRecursion(tree) {
        var base = {};
        if(tree.self) {
            base.name = tree.self.instanceId;
            base.itemStyle = {

                normal:{
                    color:getColorByStatus(tree.self.status)
                }
            }
        };
        var children = [];
        if(tree.parent != null) {
            console.log(tree.parent.length)
            for(var i = 0;i < tree.parent.length;i ++) {
                console.log(tree.parent[i])
                var tmp = getTreeDataRecursion(tree.parent[i]);
                children.push(tmp);
            }
            base.children = children;
        }


        return base;

    }

    function getColorByStatus(status) {
        switch (status) {
            case 0:return '#E3E3E3';
            case 1:return '#458B00';
            case 2:return '#63B8FF';
            case 3:return '#878787';
            case 4:return '#EEEE00';
            case 5:return '#EEAD0E';
            case 6:return '#FFFCC7';
            case 7:return '#0000FF';
            default :return '#ffffff';
        }
    }

    var op = {
        "title": {
            "text": "树图",
            "subtext": "1000532015081800的血缘树"
        },
        "toolbox": {
            "show": true,
            "feature": {
                "mark": {
                    "show": true
                },
                "dataView": {
                    "show": true,
                    "readOnly": false
                },
                "restore": {
                    "show": true
                },
                "saveAsImage": {
                    "show": true
                }
            }
        },
        "series": [
            {
                "name": "树图",
                "type": "tree",
                "orient": "vertical",
                "rootLocation": {
                    "x": "center",
                    "y": 350
                },
                "nodePadding": 50,
                "roam": "scale",
                "direction": "inverse",
                "symbol": "rectangle",
                "itemStyle": {
                    "normal": {
                        "label": {
                            "show": true,
                            "formatter": "{b}"
                        },
                        "lineStyle": {
                            "color": "#48b",
                            "shadowColor": "#000",
                            "shadowBlur": 3,
                            "shadowOffsetX": 3,
                            "shadowOffsetY": 5,
                            "type": "curve"
                        }
                    },
                    "emphasis": {
                        "label": {
                            "show": true
                        }
                    }
                },
                "data": {
                    "name": "1000532015081800",
                    "itemStyle": {
                        "normal": {
                            "color": "#458B00"
                        }
                    },
                    "children": [
                        {
                            "name": "1000352015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            }
                        },
                        {
                            "name": "1000412015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            },
                            "children": [
                                {
                                    "name": "1000352015081800",
                                    "itemStyle": {
                                        "normal": {
                                            "color": "#458B00"
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            "name": "1000472015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            }
                        },
                        {
                            "name": "1000492015081800",
                            "itemStyle": {
                                "normal": {
                                    "color": "#458B00"
                                }
                            }
                        }
                    ]
                }
            }
        ]
    }


})
