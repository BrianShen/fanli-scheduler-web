/**
 * Created by wei.shen on 2015/9/16.
 */

fanliApp.controller('feedbackCtrl',function($scope,$resource) {
    var feedback = $resource('/fanli/feedbacks');
    init();

    function init() {
        $scope.feedback_title = '';
        $scope.feedback_content = '';
        $scope.comments = [];
        getFeedbackList();
    }

    function getFeedbackList() {
        feedback.get({},function(data) {
            if(data.isSuccess) {
                console.log(data.results);
                $scope.comments = data.results;
            }
        })
    }


    $scope.saveFeedback = function() {
        if( $scope.feedback_title == '' || $scope.feedback_content == '') {
            return;
        }
        feedback.save({comment:$scope.feedback_content,title:$scope.feedback_title,user:'somebody'},
            function(data) {
                location.reload();
                console.log(data.id);
            }
        );
    }

    $scope.getUrl = function(index) {
        var fed = $scope.comments[index];
        return
    }

})
