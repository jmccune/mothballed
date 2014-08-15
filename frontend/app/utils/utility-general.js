import _ from '../../vendor/lodash/dist/lodash.es6min';


var exists=function(x) {
	return typeof x !== "undefined";
}

var convertIdObject=function(idObjectList,nameParameter) {

	if (!exists(nameParameter)) {
		nameParameter='value';
	}
	var results = [];	
	_.forEach(idObjectList,function(idObj) {
		results.push(idObj.id+":"+idObj[nameParameter]);
	});
	return results;
}

export default {
	exists: exists,
	convertIdEntitiesToTransportFormat: convertIdObject
};