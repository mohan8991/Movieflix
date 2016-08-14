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
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
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
            .when('/editUser', {
                templateUrl: 'Templates/editUser.template.html'
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
            })
            .when('/editProfile', {
                templateUrl: 'Templates/editProfile.template.html'
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
            })
            .when('/editMovie', {
                templateUrl: 'Templates/editMovie.template.html'
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
            })
            .when('/deleteUser', {
                templateUrl: 'Templates/deleteUser.template.html'
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
            })
            .when('/deleteMovie', {
                templateUrl: 'Templates/deleteMovie.template.html'
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
            })
            .when('/createMovie', {
                templateUrl: 'Templates/createMovie.template.html'
                // controller: 'UserListController',
                // controllerAs: 'userListVm'
            })
            .otherwise({
                redirectTo: '/coverIn'
            });
    }

    moduleRun.$inject = ['$localStorage'];

    function moduleRun($localStorage) {
        console.log('App Started');
    }


})();