angular.module('ngBoilerplate.account',['ui.router','ngResource'])
    .config(function config( $stateProvider ) {
        $stateProvider.state( 'login', {
            url: '/login',
            views: {
                "main": {
                    controller: 'LoginCtrl',
                    templateUrl: 'account/login.tpl.html'
                }
            },
            data:{ pageTitle: 'Login' }
        });
    })
    .controller('LoginCtrl',function ($scope) {
        
    })
;