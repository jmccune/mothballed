import Ember from 'ember';

// controllers/xref/add.js
export default Ember.ObjectController.extend({
	referenceText: null,
	authors: [ { id:null, value:'James Dean'} ],
	//model: 'xref',
	actions: {
		addXref: function() {
			console.log("BUTTON PRESSED");
			var referenceText = this.get('referenceText');

			var authorList =this.get('authors');
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
   			console.log("AUTO COMPLETION!!!!!");
   			var authors = this.get('authors').pushObject(selectedObject)   			
   			
   		}
	}
});




