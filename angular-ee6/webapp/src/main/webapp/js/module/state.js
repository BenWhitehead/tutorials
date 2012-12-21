angular.module('province', ['ngResource', 'ngSanitize']).
    factory('Province', function($resource) {
        return $resource('/angular-ee6/rest/address/state');
    });


