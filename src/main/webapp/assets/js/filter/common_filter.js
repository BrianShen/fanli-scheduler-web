/**
 * Created by wei.shen on 2015/7/29.
 */

angular.module('fanli.filter',[])
    .filter('StringReplace',function() {
        return function (string, regex, dst) {
            var regExp = new RegExp(regex, "g");
            return string === undefined ? '' : string.replace(regExp, dst);
        }
    })
