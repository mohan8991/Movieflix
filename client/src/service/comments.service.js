(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('commentService', commentService);

    commentService.$inject = ['$http', '$q', '$localStorage'];

    function commentService($http, $q, $localStorage) {
        var comments = this;

        console.log('comments service was called');

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;

        comments.findbyMovieTitle = findbyMovieTitle;
        comments.createComment = createComment;
        comments.submitStar = submitStar;
        comments.getStars = getStars;
        comments.getAvg = getAvg;


        function findbyMovieTitle(Title) {
            return $http.get('http://localhost:8084/movie-rest/api/Comments/findbyTitle/' + Title)
                .then(successFn, errorFn);
        }

        function createComment(movId, comment) {
            return $http.post('http://localhost:8084/movie-rest/api/Comments/' + movId, comment)
                .then(successFn, errorFn);
        }

        function submitStar(movId, star) {
            return $http.post('http://localhost:8084/movie-rest/api/Comments/star/' + movId, star)
                .then(successFn, errorFn);
        }

        function getStars(movId) {
            return $http.get('http://localhost:8084/movie-rest/api/Comments/findStarByMov/' + movId)
                .then(successFn, errorFn);
        }

        function getAvg(movId) {
            return $http.get('http://localhost:8084/movie-rest/api/Comments/getAvgStar/' + movId)
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