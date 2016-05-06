/**
 * Created by swen on 5/5/16.
 */
angular.module('ngBoilerplate.entry',['ui.router','ngBoilerplate.blog','hateoas','ngResource'])
    .config(function ($stateProvider) {
        $stateProvider.state('manageEntry',{
            url:"/blogs/entries?blogId",
            views:{
                'main':{
                    templateUrl:'entry/manage-entries.tpl.html',
                    controller:'ManageEntriesCtrl'
                }
            },
            data:{
                pageTitle:'Entries'
            },
            resolve:{
                blog:function (blogService,$stateParams) {
                    return blogService.getBlogById($stateParams.blogId);
                },
                entries:function (blogEntryService, $stateParams) {
                    return blogEntryService.getAllBlogEntriesByBlogId($stateParams.blogId);
                }
            }
        });
    })
    .factory('blogEntryService',function ($resource) {
        var service = {};
        service.getAllBlogEntriesByBlogId = function (blogId) {
            var BlogEntry = $resource('/SpringBlog/rest/blogs/:blogId/blog-entries');
            return BlogEntry.get({blogId:blogId}).$promise.then(function (data) {
                return data.entries;
            });
        };
        service.createBlogEntry = function (blogId, blogEntryData) {
            var BlogEntry = $resource('/SpringBlog/rest/blogs/:blogId/blog-entries');
            return BlogEntry.save({blogId:blogId},blogEntryData).$promise;
        };
        return service;
    })
    .controller('ManageEntriesCtrl',function ($scope,blog,entries,blogEntryService,$state) {
        $scope.title = blog.title;
        $scope.entries = entries;
        $scope.createBlogEntry = function () {
          blogEntryService.createBlogEntry(blog.rid,$scope.blogEntry)
              .then(function () {
                  console.log($scope.blogEntry.title);
                 $state.go('manageEntry',{blogId:blog.rid},{reload:true});
          });
        };
    })
;