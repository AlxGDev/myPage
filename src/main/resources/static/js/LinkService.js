

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
			  url: '/api/links?url='+url,
			  headers: {
				   'Content-Type': 'application/x-www-form-urlencoded'
				 }
			}).then(function successCallback(response) {
				return deferred.resolve(response.link);
			  }, function errorCallback(response) {
				NotificationService.add(response.error.message, 'error');
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
