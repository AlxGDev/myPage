

(function (angular) {
  'use strict';

  angular
    .module('linkApp')
    .controller('LinkController', ['$location','$scope', '$log', 'LinkService', 'NotificationService',
      function LinkController($location,$scope, $log, LinkService, NotificationService) {
        $scope.longUrl = null;
        $scope.link = null;
        $scope.notifications = NotificationService.queue;

        $scope.shorten = function (url) {


          if (url) {
            $log.log('Try to shorten: ' + url);
            var promise = LinkService.shorten(url);

            promise.then(function (linko) {
			$log.log(linko);
			  
              $scope.link = linko.data;
			  $scope.link.shortUrl =$location.absUrl()+$scope.link.hash;
            });
          }

        };
      }]);
})(angular);
