(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('Catalog', Catalog);

    Catalog.$inject = ['movieService', 'commentService'];

    function Catalog(movieService, commentService) {
        var movie = this;
        var totalStar = 0;
        movie.getStars = getStars;

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

        function getStars(id) {
            commentService
                .getAvg(id)
                .then(function(data) {
                    console.log(data);
                    movie.stars = data;
                    console.dir(movie);
                }, function(error) {
                    console.log(error);
                });
        }



    }
})();