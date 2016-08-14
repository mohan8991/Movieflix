(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('movieService', movieService);

    movieService.$inject = ['$http', '$q', '$localStorage'];

    function movieService($http, $q, $localStorage) {
        var movies = this;

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;

        movies.getMoviesbyTitle = getMoviesbyTitle;
        movies.getMoviesbyId = getMoviesbyId;

        function getMoviesbyTitle() {
            return $http.get('http://localhost:8084/movie-rest/api/Movies/sortbyTitle')
                .then(successFn, errorFn);
        }

        function getMoviesbyId(id) {
            return $http.get('http://localhost:8084/movie-rest/api/Movies/' + id)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data; //RESOLVE
        }

        function errorFn(response) {
            return $q.reject(error.status); //REJECT
        }
    }
})();