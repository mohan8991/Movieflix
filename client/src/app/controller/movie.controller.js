(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('movieController', movieController);

    movieController.$inject = ['movieService'];

    function movieController(movieService) {
        var movie = this;

        movie.createMovie = createMovie;
        movie.editMovie = editMovie;
        movie.deleteMovie = deleteMovie;



        init();

        function init() {
            console.log('movie controller was called');

        }

        function createMovie() {
            movieService
                .createMovie(movie.newMovie)
                .then(function(data) {
                    movie.movieCreated = data;
                }, function(error) {
                    console.log(error);
                });

        }

        function editMovie() {
            var id;
            movieService
                .findmoviesbyTitle(movie.updateMovie.title)
                .then(function(data) {
                    movie.updateMovie.id = data.id;
                    movieService
                        .updateMovie(movie.updateMovie)
                        .then(function(data) {
                            movie.movieUpdated = data;
                        }, function(error) {
                            console.log(error);
                        });

                }, function(error) {
                    console.log(error);
                });
        }

        function deleteMovie() {
            var id;
            movieService
                .findmoviesbyTitle(movie.deleteMovie.title)
                .then(function(data) {
                    id = data.id;

                    movieService
                        .deleteMovie(id)
                        .then(function(data) {
                            console.log('deleted');
                        }, function(error) {
                            console.log(error);
                        });

                }, function(error) {
                    console.log(error);
                });


        }


    }
})();