

(function (angular) {
  'use strict';

  angular
    .module('linkApp')
    .service('LinkService', ['$http', '$q', 'NotificationService',
      function LinkService($http, $q, NotificationService) {

      return {

        shorten: function (url) {
          var deferred = $q.defer();
		  
		  $http({
			  method: 'POST',
			  url: '/api/links',
			  headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			  data: 'url='+url
			}).then(function successCallback(link) {
				return deferred.resolve(link);
			  }, function errorCallback(error) {
				NotificationService.add(error.message, 'error');
			  });

          /*$http.post('/api/links?url='+url)
            .success(function(link) {
              return deferred.resolve(link);
            }).error(function(error) {
              NotificationService.add(error.message, 'error');
            });*/

          return deferred.promise;
        }
      };
    }]);
})(angular);
