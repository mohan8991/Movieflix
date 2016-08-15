(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('user2Service', user2Service);

    user2Service.$inject = ['$http', '$q', '$localStorage'];

    function user2Service($http, $q, $localStorage) {
        var self = this;

        console.log("service was called");

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;

        self.getUserInfo = getUserInfo;
        self.updateUser = updateUser;

        function getUserInfo() {
            return $http.get('http://localhost:8084/movie-rest/api/UserInfo')
                .then(successFn, errorFn);
        }

        function updateUser(userInfo) {
            return $http.put('http://localhost:8084/movie-rest/api/UserInfo', userInfo)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data; //RESOLVE
        }

        function errorFn(error) {
            return $q.reject(error.status); //REJECT
        }
    }
})();