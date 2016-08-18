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
                templateUrl: 'templates/inHome.template.html'

            })
            .when('/catalog', {
                templateUrl: 'templates/catalog.template.html',
                controller: 'Catalog',
                controllerAs: 'catalogVm'
            })
            .when('/movieTitle/:id', {
                templateUrl: 'templates/movieTitle.template.html',
                controller: 'movieDetailsController',
                controllerAs: 'mDetails'
            })
            .when('/editProfile', {
                templateUrl: 'templates/editProfile.template.html',
                controller: 'editUserController',
                controllerAs: 'editUser'
            })
            .when('/editMovie', {
                templateUrl: 'templates/editMovie.template.html',
                controller: 'movieController',
                controllerAs: 'movieCtrl'
            })
            .when('/deleteMovie', {
                templateUrl: 'templates/deleteMovie.template.html',
                controller: 'movieController',
                controllerAs: 'movieCtrl'
            })
            .when('/createMovie', {
                templateUrl: 'templates/createMovie.template.html',
                controller: 'movieController',
                controllerAs: 'movieCtrl'
            })
            .otherwise({
                redirectTo: '/coverIn'
            });
    }

    moduleRun.$inject = [];

    function moduleRun() {
        console.log('App Started module');
    }


})();