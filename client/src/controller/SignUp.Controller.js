(function() {
    'use strict';

    angular
        .module('cover')
        .controller('SignUp', SignUp);

    SignUp.$inject = ['userService','$localStorage', '$window'];

    function SignUp(userService, $localStorage, $window) {
        var user = this;

        user.signUp = signUp;

        init();

        function init() {
            console.log('in signUP controller');                      /*TODO please remove this when Done*/
        }

        function signUp() {
            user.newUser.role = 'User';                  /* New User Should be User and Not admin */

            userService
                .createNew(user.newUser)
                .then(function (SignedIndata) {
                    $localStorage.token =  SignedIndata.token;         /*TODO please set expiration for local storage*/
                    $localStorage.firstName = SignedIndata.firstName;
                    $localStorage.role = SignedIndata.role;
                    $window.location = 'SignedinHomepage.html';
                }, function (SignInError) {
                    console.log(SignInError);
                })
        }

    }
})();