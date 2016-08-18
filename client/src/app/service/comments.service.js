(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('commentService', commentService);

    commentService.$inject = ['$http', '$q', '$localStorage', 'CONFIG'];

    function commentService($http, $q, $localStorage, CONFIG) {
        var comments = this;

        console.log('comments service was called');

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;

        comments.findbyMovieTitle = findbyMovieTitle;
        comments.createComment = createComment;
        comments.submitStar = submitStar;
        comments.getStars = getStars;
        comments.getAvg = getAvg;


        function findbyMovieTitle(Title) {
            return $http.get(CONFIG.API_HOST + '/Comments/findbyTitle/' + Title)
                .then(successFn, errorFn);
        }

        function createComment(movId, comment) {
            return $http.post(CONFIG.API_HOST + '/Comments/' + movId, comment)
                .then(successFn, errorFn);
        }

        function submitStar(movId, star) {
            return $http.post(CONFIG.API_HOST + '/Comments/star/' + movId, star)
                .then(successFn, errorFn);
        }

        function getStars(movId) {
            return $http.get(CONFIG.API_HOST + '/Comments/findStarByMov/' + movId)
                .then(successFn, errorFn);
        }

        function getAvg(movId) {
            return $http.get(CONFIG.API_HOST + '/Comments/getAvgStar/' + movId)
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