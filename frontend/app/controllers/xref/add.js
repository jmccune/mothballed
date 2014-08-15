import Ember from 'ember';
import utilities from '../../utils/utility-general';

// controllers/xref/add.js
export default Ember.ObjectController.extend({
	referenceText: null,
	authors: [ { id:null, value:'James Dean'} ],
	//model: 'xref',
	actions: {
		addXref: function() {			
			var referenceText = this.get('referenceText');

			var authorList =this.get('authors');
			console.dir(authorList);
			authorList = utilities.convertIdEntitiesToTransportFormat(authorList);
			console.dir(authorList);

			var xref = this.store.createRecord('xref',{				
  				referenceText: referenceText,
  				authors: authorList
			});

			xref.save().then(function() {
				console.log("AFTER SAVE >> ID: "+xref.get('id'));
			});
			console.log("Received data>> "+referenceText);			
		},		
		authorAutoCompletion: function(eventInfo, selectedObject ,dataSet) {   			
   			var authors = this.get('authors').pushObject(selectedObject)   			   			
   		}
	}
});




