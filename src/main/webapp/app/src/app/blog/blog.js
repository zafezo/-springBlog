angular.module('ngBoilerplate.blog', ['ui.router', 'ngResource', 'ngBoilerplate.account', 'hateoas'])
    .config(function($stateProvider) {
        $stateProvider.state('manageBlogs', {
            url:'/manage/blogs?accountId',
            views:{
                "main":{
                    templateUrl:'blog/manage-blogs.tpl.html',
                    controller:'ManageBlogCtrl'
                }
            },
            resolve:{
                account: function (accountService,$stateParams) {
                    return accountService.getAccountById($stateParams.accountId);
                },
                blogs:function (blogService,$stateParams) {
                    return blogService.getBlogsByAccountId($stateParams.accountId);
                }
            },
            data:{
                pageTitle:'Blogs'
            }
        });
        $stateProvider.state('allBlogs',{
           url:'/blogs',
            views:{
                'main':{
                    templateUrl:'blog/all-blogs.tpl.html',
                    controller:'ShowBlogs'
                }
            },
            data:{
                pageTitle:'Blogs'
            },
            resolve:{
                blogs:function (blogService) {
                    return blogService.getAllBlogs();
                }
            }
        });
    })
    .factory('blogService',function ($resource,$q) {
        var service = {};
        service.createBlog = function (accountId, blogData) {
            var Blog = $resource("/SpringBlog/rest/accounts/:paramAccountId/blogs");
            return Blog.save({paramAccountId:accountId},blogData).$promise;
        };
        service.getAllBlogs = function () {
            var Blog = $resource('/SpringBlog/rest/blogs');
            return Blog.get().$promise.then(function (data) {
                return data.blogs;
            });
        };
        service.getBlogsByAccountId = function (accountId) {
            var deferred = $q.defer();
            var Account = $resource("/SpringBlog/rest/accounts/:paramAccountId");
            Account.get({paramAccountId:accountId},function (returnedData) {
                var Blog = returnedData.resource("blogs");
                Blog.get(null,
                function (data) {
                    deferred.resolve(data.blogs);
                },function () {
                        deferred.reject();
                    });
            });
            return deferred.promise;
        };
        return service;
    })
    .controller('ManageBlogCtrl',function ($scope, account, blogs, $state, blogService) {
        $scope.name = account.name;
        $scope.blogs = blogs;
        $scope.createBlog = function (blogName) {
            blogService.createBlog(account.rid,{title:blogName})
                .then(function () {
                    $state.go('manageBlogs',{accountId:account.rid},{reload:true});
                });
        };
    })
    .controller('ShowBlogs',function ($scope,blogs) {
        $scope.blogs = blogs;
    })
;