(function() {
    'use strict';

    angular
        .module('cover')
        .service('userService', userService);

    userService.$inject = ['$http', '$q'];

    function userService($http, $q) {
        var self = this;

        console.log("service was called");

        self.existingIn = existingIn;
        self.createNew = createNew;

        function existingIn(user) {
            return $http.put('http://localhost:8084/movie-rest/api/UserInfo/Signin', user)
                .then(successFn, errorFn);
        }

        function createNew(user) {
            return $http.post('http://localhost:8084/movie-rest/api/UserInfo', user)
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