angular.module('country', ['ngResource', 'ngSanitize']).
    factory('Country', function($resource) {
        return $resource('/angular-ee6/rest/address/country');
    });


