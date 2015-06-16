import DS from "ember-data";
import Ember from 'ember';

//app/transforms/string-array
export default DS.Transform.extend({
	deserialize: function(serialized) {
		if (Ember.typeOf(serialized) !== "object")  {
			throw "Illegal serialization!"
		}
		console.log("NAME COMP SERIALIZER>> >>>>>*************************************");
		console.dir(serialized);
		
		
		return serialized;
	},
	serialize:function(deserialized) {
		return deserialized;
	}
});