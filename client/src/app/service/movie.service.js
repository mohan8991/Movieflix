(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('movieService', movieService);

    movieService.$inject = ['$http', '$q', '$localStorage', 'CONFIG'];

    function movieService($http, $q, $localStorage, CONFIG) {
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
            return $http.get(CONFIG.API_HOST + '/Movies/sortbyTitle')
                .then(successFn, errorFn);
        }

        function findmoviesbyTitle(title) {
            return $http.get(CONFIG.API_HOST + '/Movies/title/' + title)
                .then(successFn, errorFn);
        }

        function getMoviesbyId(id) {
            return $http.get(CONFIG.API_HOST + '/Movies/' + id)
                .then(successFn, errorFn);
        }

        function createMovie(Movie) {
            return $http.post(CONFIG.API_HOST + '/Movies/', Movie)
                .then(successFn, errorFn);
        }

        function updateMovie(Movie) {
            return $http.put(CONFIG.API_HOST + '/Movies/', Movie)
                .then(successFn, errorFn);
        }

        function deleteMovie(id) {
            return $http.delete(CONFIG.API_HOST + '/Movies/' + id)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data; //RESOLVE
        }

        function errorFn(error) {
            return $q.reject(error.status); //REJECT
        }
    }
})();