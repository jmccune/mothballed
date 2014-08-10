// views/auto-complete.js
import Ember from "ember";
import AutoCompleteController from "../controllers/auto-complete";

export default Ember.View.extend({
	attributeBindings: ['data-completiontype'],	
	templateName: 'auto-complete',
	didInsertElement : function(){
		this._super();

		var myController = this.get('controller');

		// Parameterization/Configuration
		var myDataCompletionType = this.$().attr('data-completiontype');		
		var completionEventName = myDataCompletionType+'AutoCompletion';
		var clearAfterSelection = true;

		console.log("My Controller>> "+myController);
		console.log("myDataCompletionType >> "+myDataCompletionType);		

		var bestAnswers = new Bloodhound({
			datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
			queryTokenizer: Bloodhound.tokenizers.whitespace,
			remote: 'http://localhost:8080/autocomplete/author/%QUERY'
		});

		bestAnswers.initialize();

		var substringMatcher = function(strs) {
		  return function findMatches(q, cb) {
		    var matches, substrRegex;
		 
		    // an array that will be populated with substring matches
		    matches = [];
		 
		    // regex used to determine if a string contains the substring `q`
		    substrRegex = new RegExp(q, 'i');
		 
		    // iterate through the pool of strings and for any string that
		    // contains the substring `q`, add it to the `matches` array
		    $.each(strs, function(i, str) {
		      if (substrRegex.test(str)) {
		        // the typeahead jQuery plugin expects suggestions to a
		        // JavaScript object, refer to typeahead docs for more info
		        matches.push({ value: str });
		      }
		    });
		 
		    cb(matches);
		  };
		};
 
		// See https://github.com/twitter/typeahead.js/blob/master/doc/jquery_typeahead.md#api		
		var typeAheadEl = this.$('input.typeahead').typeahead(null,
		{
		  name: 'authors',
		  displayKey: 'value',
		  source: bestAnswers.ttAdapter()
		});

		typeAheadEl.keypress(function (eventInfo) {
  			if (eventInfo.which == 13) {
    			var value = typeAheadEl.val();
    			if (clearAfterSelection) {
    				typeAheadEl.typeahead('val', "");
    			}
    			var selectedObject = {
    				id: null,  //Object needs to be defined.
    				value: value
    			};
    			myController.send(completionEventName,eventInfo,selectedObject);      			
  			}
		});

		typeAheadEl.on('typeahead:selected', function (eventInfo, selectedObject ,dataSet) {
			console.log("SEND Event? "+completionEventName);
			myController.send(completionEventName,eventInfo,selectedObject,dataSet);  
			if (clearAfterSelection) {
				typeAheadEl.typeahead('val', "");
			} 
		});
	}
});
