import Ember from 'ember';
import utilities from '../../utils/utility-general';

// controllers/xref/add.js
export default Ember.ArrayController.extend({
	name: "",
	actions: {
		addPerson: function() {									
			var name = this.get('name');
			var person = this.store.createRecord('person',{	
				roles: ['author'],
				nameComponent: {
					'NAME': name
				}			
  			});

			person.save().then(function() {
				console.log("AFTER SAVE >> ID: "+person.get('id')+">>"+person.get('name'));
			});
			
		}		
	}
});




