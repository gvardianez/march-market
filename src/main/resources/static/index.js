angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    const contextPath = 'http://localhost:8189/market/api/v1';

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.fillTable = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.fillCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/cart/change_quantity',
            method: 'GET',
            params: {
                id: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.fillCart();
        });
    }

    $scope.setNewQuantity = function (productId, newQuantity) {
        $http({
            url: contextPath + '/cart/set_quantity',
            method: 'GET',
            params: {
                id: productId,
                newQuantity: newQuantity
            }
        }).then(function (response) {
            $scope.fillCart();
        });
    }


    $scope.createNewOrder = function () {
        $http.get(contextPath + '/orders/create')
            .then(function (response) {
                $scope.fillCart();
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.removeProductFromCart = function (id) {
        $http.get(contextPath + '/cart/remove/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/market/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.fillTable();
    $scope.fillCart();
});