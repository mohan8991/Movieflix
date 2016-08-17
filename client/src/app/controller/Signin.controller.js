(function() {
    'use strict';

    angular
        .module('cover')
        .controller('SignIn', SignIn);

    SignIn.$inject = ['userService','$localStorage', '$window'];

    function SignIn(userService, $localStorage, $window) {
        var user = this;

        user.signIn = signIn;

        init();

        function init() {
        }

        function signIn() {
            userService
                .existingIn(user.existing)
                .then(function (SignedIndata) {
                    $localStorage.token =  SignedIndata.token;
                    $localStorage.firstName = SignedIndata.firstName;
                    $localStorage.role = SignedIndata.role;
                    $window.location = 'SignedinHomepage.html';
                }, function (SignInError) {
                    console.log(SignInError);
                })

        }

    }
})();