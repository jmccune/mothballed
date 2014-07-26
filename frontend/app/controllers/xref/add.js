import Ember from 'ember';

// controllers/xref/add.js
export default Ember.ObjectController.extend({
	referenceText: null,
	
	actions: {
		addXref: function() {
			console.log("BUTTON PRESSED");
			var referenceText = this.get('referenceText');

			var xref = this.store.createRecord('xref',{				
  				referenceText: referenceText
			});

			xref.save().then(function() {
				console.log("AFTER SAVE >> ID: "+xref.get('id'));
			});
			console.log("Received data>> "+referenceText);			
		}
	}
});




