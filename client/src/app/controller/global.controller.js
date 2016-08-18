(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('global', global);

    global.$inject = ['$localStorage', '$window', 'user2Service'];

    function global($localStorage, $window, user2Service) {
        var user = this;
        user.firstName = $localStorage.firstName;
        user.role = $localStorage.role;

        user.signOut = signOut;

        console.log('i was called');


        init();

        function init() {
        }

        function signOut() {
            user2Service
                .signOut()
                .then(function () {
                    console.log('signed Out');
                    $localStorage.$reset();
                    $window.location = 'Index.html';
                }, function (SignInError) {
                    console.log(SignInError);
                })

        }


    }
})();