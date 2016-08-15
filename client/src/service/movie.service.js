(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('movieService', movieService);

    movieService.$inject = ['$http', '$q', '$localStorage'];

    function movieService($http, $q, $localStorage) {
        var movies = this;

        console.log('movie service was called');

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;

        movies.getMoviesbyTitle = getMoviesbyTitle;
        movies.findmoviesbyTitle = findmoviesbyTitle;
        movies.getMoviesbyId = getMoviesbyId;
        movies.createMovie = createMovie;
        movies.updateMovie = updateMovie;
        movies.deleteMovie = deleteMovie;


        function getMoviesbyTitle() {
            return $http.get('http://localhost:8084/movie-rest/api/Movies/sortbyTitle')
                .then(successFn, errorFn);
        }

        function findmoviesbyTitle(title) {
            return $http.get('http://localhost:8084/movie-rest/api/Movies//title/' + title)
                .then(successFn, errorFn);
        }

        function getMoviesbyId(id) {
            return $http.get('http://localhost:8084/movie-rest/api/Movies/' + id)
                .then(successFn, errorFn);
        }

        function createMovie(Movie) {
            return $http.post('http://localhost:8084/movie-rest/api/Movies/', Movie)
                .then(successFn, errorFn);
        }

        function updateMovie(Movie) {
            return $http.put('http://localhost:8084/movie-rest/api/Movies/', Movie)
                .then(successFn, errorFn);
        }

        function deleteMovie(id) {
            return $http.delete('http://localhost:8084/movie-rest/api/Movies/' + id)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            console.log(response.data);
            return response.data; //RESOLVE
        }

        function errorFn(error) {
            return $q.reject(error.status); //REJECT
        }
    }
})();