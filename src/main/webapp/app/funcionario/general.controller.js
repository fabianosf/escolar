angular.module("funcionarioApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'Funcionario' ];

function GeneralController($scope, Funcionario) {
	
	$scope.funcionarios = Funcionario.query();

	$scope.funcionario = {};
	
	$scope.buttonText="Save";
	
	$scope.saveFuncionario = function() {
		if ($scope.funcionario.id !== undefined) {
			Funcionario.update($scope.funcionario, function() {
				$scope.funcionarios = Funcionario.query();
				$scope.funcionario = {};
				$scope.buttonText="Save";
			});
		} else {
			Funcionario.save($scope.funcionario, function() {
				$scope.funcionarios = Funcionario.query();
				$scope.funcionario = {};
			});
		}
	}

	$scope.updateFuncionarioInit = function(funcionario) {
		$scope.buttonText="Update";
		$scope.funcionario = funcionario;
	}

	$scope.deleteFuncionario = function(funcionario) {
		funcionario.$delete({id: funcionario.id}, function() {
			$scope.funcionarios = Funcionario.query();
		});
	}
	
	

	
}