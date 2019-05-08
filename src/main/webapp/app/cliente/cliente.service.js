angular.module('clienteApp').factory('Cliente', Cliente);

Cliente.$inject = [ '$resource' ];

function Cliente($resource) {
	var resourceUrl = 'api/cliente/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}