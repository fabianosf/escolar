angular.module("clienteApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'Cliente' ];

function GeneralController($scope, Cliente) {
	
	$scope.clientes = Cliente.query();

	$scope.cliente = {};
	
	$scope.buttonText="Submit";
	 
	
	$scope.saveCliente = function() {
		if ($scope.cliente.id !== undefined) {
			Cliente.update($scope.cliente, function() {
				$scope.clientes = Cliente.query();
				$scope.cliente = {};
				$scope.buttonText="Submit";
			});
		} else {
			Cliente.save($scope.cliente, function() {
				$scope.clientes = Cliente.query();
				$scope.cliente = {};
			});
		}
	}

	$scope.updateClienteInit = function(cliente) {
		$scope.buttonText="Update";
		$scope.cliente = cliente;
	}

	$scope.deleteCliente = function(cliente) {
		cliente.$delete({id: cliente.id}, function() {
			$scope.clientes = Cliente.query();
		});
	}
	
	

	
}