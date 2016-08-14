(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('Catalog', Catalog);

    Catalog.$inject = ['movieService'];

    function Catalog(movieService) {
        var movie = this;

                init();

        function init() {
            movieService
                .getMoviesbyTitle()
                .then(function (movieList) {
                    console.dir(movieList);
                    movie.movieList = movieList;
                }, function (error) {
                    console.log(error);
                })
        }



    }
})();