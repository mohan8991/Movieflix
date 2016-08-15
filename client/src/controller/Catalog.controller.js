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
                    movie.movieList = movieList;
                }, function (error) {
                    console.log(error);
                })
        }

        function getStars(id) {
            commentService
                .getAvg(id)
                .then(function(data) {
                    movie.stars = data;
                }, function(error) {
                    console.log(error);
                });
        }



    }
})();