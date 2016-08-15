(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('editUserController', editUserController);

    editUserController.$inject = ['user2Service'];

    function editUserController(user2Service) {
        var user = this;

        user.updateUser = updateUser;

        init();

        function init() {
            user2Service
                .getUserInfo()
                .then(function (data) {
                    user.currentUser = data;
                    console.log(data);
                }, function (error) {
                    console.log(error);
                })
        }


        function updateUser() {
            user2Service
                .updateUser(user.updatingUser)
                .then(function (data) {
                    user.currentUser = data;
                    console.log(data);
                }, function (error) {
                    console.log(error);
                })
        }


    }
})();