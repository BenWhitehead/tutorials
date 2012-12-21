angular.module('address', ['province', 'country', 'ngResource', 'ngSanitize']).
    factory('Address', function($resource) {
        return $resource('/angular-ee6/rest/address/:id');
    });



