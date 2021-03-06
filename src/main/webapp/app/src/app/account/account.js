angular.module('ngBoilerplate.account',[
    'ui.router',
    'ngResource'])
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
        $stateProvider.state('register',{
            url:'/register',
            views:{
                'main':{
                    controller: 'RegisterCtrl',
                    templateUrl:'account/register.tpl.html'
                }
            },
            data:{
                pageTitle:'Register'
            }
        });
        $stateProvider.state('accountSearch',{
            url:"/accounts/search",
            views:{
                'main':{
                    templateUrl:'account/search.tpl.html',
                    controller:'AccountSearchCtrl'
                }
            },
            data:{
                pageTitle:'Search Accounts'
            },
            resolve: {
                accounts: function(accountService) {
                    return accountService.getAllAccounts();
                }
            }
        });
    })
    .factory('sessionService',function () {
        var service = {};
        service.login = function (data) {
            localStorage.setItem("session",data);
        };
        service.logout = function () {
            localStorage.removeItem("session");
        };
        service.isLogIn = function () {
            return localStorage.getItem("session") != null;
        };
        return service;
    })
    .factory('accountService',function ($resource) {
        var service = {};
        service.register = function (account, success, failure) {
            var Account = $resource("/SpringBlog/rest/accounts");
            Account.save({},account,success,failure);
        };
        service.login =  function (account,success,failure) {
            var Account = $resource("/SpringBlog/rest/accounts");
            var data = Account.get({name:account.name},function () {
                var accounts = data.accounts;
                if(accounts.length !== 0){
                    success(accounts[0]);
                }else {
                    failure();
                }
            });
        };
        service.getAllAccounts = function () {
            var Account = $resource("/SpringBlog/rest/accounts");
            return Account.get().$promise.then(function (data) {
                return data.accounts;
            });
        };
        service.getAccountById = function (accountId) {
            var Account = $resource("/SpringBlog/rest/accounts/:paramId");
            return Account.get({paramId:accountId}).$promise;
        };
        return service;
    })
    .controller('LoginCtrl',function ($scope,sessionService,$state,accountService) {
        $scope.login = function () {
           accountService.login($scope.account,function (data) {
               sessionService.login(data);
               alert("user login with " +  data.name + " and "+  data.password);
               $state.go('home');
           },function () {
            alert("Error in login user");
           });
        };  
    })
    .controller('RegisterCtrl',function ($scope,sessionService,$state,accountService) {
      $scope.registry = function () {
          accountService.register($scope.account,
          function (returnedData) {
              sessionService.login(returnedData);
              alert("user registry with " + returnedData.name + " and "+ returnedData.password);
              $state.go('home');
          },
          function () {
              alert("Error registering user");
          });
      };
    })
    .controller('AccountSearchCtrl',function ($scope,accounts) {
        $scope.accounts = accounts;
    })
;