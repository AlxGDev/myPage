
(function (angular) {
  'use strict';

  angular
    .module('linkApp')
    .service('NotificationService', [ 'noty', function NotificationService(noty) {

      // var queue = [];

      return {
        //  queue: queue,

        add: function (item, type) {
          noty.show(item, type);

          /*queue.push(item);

           setTimeout(function () {
           // remove the alert after 2000 ms
           $('.alerts .alert').eq(0).remove();
           queue.shift();
           }, 4000);
           */
        }

        //  pop: function () {
        //    return this.queue.pop();
        //  }
      };
    }])
}(angular));
