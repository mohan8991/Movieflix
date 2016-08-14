(function() {
    'use strict';

    angular
        .module('cover')
        .controller('SignIn', SignIn);

    SignIn.$inject = ['userService','$localStorage'];

    function SignIn(userService, $localStorage) {
        var user = this;

        user.signIn = signIn;

        init();

        function init() {
            console.log('in sign in controller');
        }

        function signIn() {
            console.log("I am the controller you are calling");
            console.dir(user.existing);
            userService
                .existingIn(user.existing)
                .then(function (SignedIndata) {
                    $localStorage.Token =  SignedIndata.token;
                }, function (SignInError) {
                    console.log(SignInError);
                })
        }

    }
})();