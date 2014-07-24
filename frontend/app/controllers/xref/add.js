import Ember from 'ember';

// controllers/xref/add.js
export default Ember.ObjectController.extend({
	exampleProperty: 'the controller example property',
	referenceText: null,
	actions: {
		addXref: function() {
			console.log("BUTTON PRESSED");
			var referenceText = this.get('referenceText');
			console.log("Received data>> "+referenceText);			
		}
	}
});




