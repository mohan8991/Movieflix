(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('global', global);

    global.$inject = ['$localStorage', '$window', 'user2Service'];

    function global($localStorage, $window) {
        var user = this;
        user.firstName = $localStorage.firstName;
        user.role = $localStorage.role;

        user.signOut = signOut;


        init();

        function init() {
        }

        function signOut() {
            $localStorage.$reset();
            $window.location = 'Index.html';
        }


    }
})();