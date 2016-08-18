(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('user2Service', user2Service);

    user2Service.$inject = ['$http', '$q', '$localStorage', 'CONFIG'];

    function user2Service($http, $q, $localStorage, CONFIG) {
        var self = this;

        console.log("service was called");

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;

        self.getUserInfo = getUserInfo;
        self.updateUser = updateUser;
        self.signOut = signOut;

        function getUserInfo() {
            return $http.get(CONFIG.API_HOST + '/UserInfo')
                .then(successFn, errorFn);
        }

        function updateUser(userInfo) {
            return $http.put(CONFIG.API_HOST + '/UserInfo', userInfo)
                .then(successFn, errorFn);
        }

        function signOut() {
            return $http.get(CONFIG.API_HOST + '/UserInfo/SignOut')
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