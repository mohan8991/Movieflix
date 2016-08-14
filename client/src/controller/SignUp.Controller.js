(function() {
    'use strict';

    angular
        .module('cover')
        .controller('SignUp', SignUp);

    SignUp.$inject = ['userService','$localStorage'];

    function SignUp(userService, $localStorage) {
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
                    console.log(SignedIndata);
                    $localStorage.Token =  SignedIndata.token;         /*TODO please set expiration for local storage*/
                }, function (SignInError) {
                    console.log(SignInError);
                })
        }

    }
})();