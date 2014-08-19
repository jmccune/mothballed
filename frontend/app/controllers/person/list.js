import Ember from 'ember';
import utilities from '../../utils/utility-general';

// controllers/xref/add.js
export default Ember.ArrayController.extend({
	actions: {
		addPerson: function() {						
			console.log("ADD PERSON");			
			var person = this.store.createRecord('person',{	
				roles: ['author'],
				nameComponent: {
					'NAME': 'James Michener'
				}			
  			});

			person.save().then(function() {
				console.log("AFTER SAVE >> ID: "+person.get('id'));
			});
			
		}		
	}
});




