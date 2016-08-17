(function() {
  'use strict';

  angular
    .module('movieflix')
    .constant('CONFIG', {
      'API_HOST': 'http://localhost:8088/movie-rest/api'
    });

})();