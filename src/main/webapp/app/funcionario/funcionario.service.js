angular.module('funcionarioApp').factory('Funcionario', Funcionario);

Funcionario.$inject = [ '$resource' ];

function Funcionario($resource) {
	var resourceUrl = 'api/funcionario/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}