angular.module('project', ['address']);

function ListAddressController($scope, Address, Province, Country) {
    $scope.addresses = Address.query();
    $scope.provinces = Province.query();
    $scope.countries = Country.query();

    $scope.addAddress = function () {
        Address.save({
            description: $scope.address.description,
            lineOne: $scope.address.lineOne,
            lineTwo: $scope.address.lineTwo,
            city: $scope.address.city,
            province: $scope.address.province,
            postCode: $scope.address.postCode,
            country: $scope.address.country
        }, function(newAddress){
            $scope.addresses.push(newAddress);
        });
        $scope.address.description = '';
//        $scope.myForm.$setPristine(); //https://github.com/angular/angular.js/issues/856 needs version 1.2 it seems, merged PR in master
        $scope.address.lineOne = '';
        $scope.address.lineTwo = '';
        $scope.address.city = '';
        $scope.address.province = undefined;
        $scope.address.postCode = '';
        $scope.address.country = undefined;
    };
}
