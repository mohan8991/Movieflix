(function() {
    'use strict';

    angular
        .module('cover', ['ngMessages', 'ngRoute', 'ngStorage'])
        .config(moduleConfig)
        .run(moduleRun);

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig($routeProvider) {
        $routeProvider
            .when('/cover', {
                templateUrl: 'Templates/carousel.template.html'
            })
            .when('/signin', {
                templateUrl: 'Templates/SignIn.template.html',
                controller: 'SignIn',
                controllerAs: 'userIn'
            })
            .when('/signup', {
                templateUrl: 'Templates/SignUp.template.html',
                controller: 'SignUp',
                controllerAs: 'register'
            })
            .otherwise({
                redirectTo: '/cover'
            });
    }

    moduleRun.$inject = [];

    function moduleRun() {
        console.log('App Started');
    }
})();