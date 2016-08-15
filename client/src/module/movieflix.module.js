(function() {
    'use strict';

    angular
        .module('movieflix', ['ngMessages', 'ngRoute', 'ngStorage'])
        .config(moduleConfig)
        .run(moduleRun);

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig($routeProvider) {
        $routeProvider
            .when('/coverIn', {
                templateUrl: 'Templates/inHome.template.html'

            })
            .when('/catalog', {
                templateUrl: 'Templates/catalog.template.html',
                controller: 'Catalog',
                controllerAs: 'catalogVm'
            })
            .when('/movieTitle/:id', {
                templateUrl: 'Templates/movieTitle.template.html',
                controller: 'movieDetailsController',
                controllerAs: 'mDetails'
            })
            .when('/editProfile', {
                templateUrl: 'Templates/editProfile.template.html',
                controller: 'editUserController',
                controllerAs: 'editUser'
            })
            .when('/editMovie', {
                templateUrl: 'Templates/editMovie.template.html',
                controller: 'movieController',
                controllerAs: 'movieCtrl'
            })
            .when('/deleteMovie', {
                templateUrl: 'Templates/deleteMovie.template.html',
                controller: 'movieController',
                controllerAs: 'movieCtrl'
            })
            .when('/createMovie', {
                templateUrl: 'Templates/createMovie.template.html',
                controller: 'movieController',
                controllerAs: 'movieCtrl'
            })
            .otherwise({
                redirectTo: '/coverIn'
            });
    }

    moduleRun.$inject = [''];

    function moduleRun() {
        console.log('App Started');
    }


})();