(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('movieDetailsController', movieDetailsController);

    movieDetailsController.$inject = ['movieService', '$routeParams', 'commentService'];

    function movieDetailsController(movieService, $routeParams, commentService) {
        var movie = this;

        movie.getCommentsbyTitle = getCommentsbyTitle;
        movie.createComments = createComments;
        movie.submitStars = submitStars;
        movie.getStars = getStars;

        var totalStar = 0;

        init();

        function init() {
            movieService
                .getMoviesbyId($routeParams.id)
                .then(function(data) {
                    movie.details = data;
                    movie.getCommentsbyTitle();
                    movie.getStars();
                }, function(error) {
                    console.log(error);
                });
        }

        function getCommentsbyTitle() {
            commentService
                .findbyMovieTitle(movie.details.title)
                .then(function(data) {
                    movie.comments = data;
                }, function(error) {
                    console.log(error);
                });
        }

        function createComments() {
            movie.newComments.date = new Date();
            commentService
                .createComment(movie.details.id, movie.newComments)
                .then(function(data) {
                    movie.comments.push(data);
                }, function(error) {
                    console.log(error);
                });
        }


        function submitStars() {
            commentService
                .submitStar(movie.details.id, movie.userStar)
                .then(function(data) {
                    movie.stars=data;

                }, function(error) {
                    console.log(error);
                });
        }

        function getStars() {
            commentService
                .getStars(movie.details.id)
                .then(function(data) {
                    movie.stars=data;

                    for(var stars in movie.stars){
                        totalStar = totalStar + movie.stars[stars].star;
                        movie.stars.totalStar = totalStar;
                    }
                }, function(error) {
                    console.log(error);
                });
        }


    }
})();