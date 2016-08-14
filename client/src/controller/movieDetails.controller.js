(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('movieDetailsController', movieDetailsController);

    movieDetailsController.$inject = ['movieService', '$routeParams'];

    function movieDetailsController(movieService, $routeParams) {
        var movie = this;

        init();

        function init() {
            movieService
                .getMoviesbyId($routeParams.id)
                .then(function(data) {
                    movie.details = data;
                    console.dir(movie)
                }, function(error) {
                    console.log(error);
                });
        }
    }
})();