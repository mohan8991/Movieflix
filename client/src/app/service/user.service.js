(function() {
    'use strict';

    angular
        .module('cover')
        .service('userService', userService);

    userService.$inject = ['$http', '$q', 'CONFIG'];

    function userService($http, $q, CONFIG) {
        var self = this;

        console.log("service was called");

        self.existingIn = existingIn;
        self.createNew = createNew;

        function existingIn(user) {
            return $http.put( CONFIG.API_HOST + '/UserInfo/Signin', user)
                .then(successFn, errorFn);
        }

        function createNew(user) {
            return $http.post(CONFIG.API_HOST + '/UserInfo', user)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data; //RESOLVE
        }

        function errorFn(response) {
            return $q.reject(error.status); //REJECT
        }
    }
})();