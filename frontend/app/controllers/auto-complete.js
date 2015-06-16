import Ember from 'ember';

//NOTE TEMPORARILY REMOVED FROM USE -- May return when we do autocomplete list functionality.
export default Ember.Controller.extend({
	searchText: null,
	exampleProperty: "THIS IS A TEST!",
	searchResults: function() {
		var searchText = this.get('searchText');
		var data = this.get('data');

		console.log("CHANGED SEARCH TEXT: "+searchText);
		console.log("DATA TYPE? "+data);
		if (!searchText) {return;}
		

		var regex = new RegExp(searchText,'i');
		return ['one','two','three'].filter(function(name) {
			return name.match(regex);
		});
	}.property('searchText'),
	actions: {
		turnItUp2: function(level) {
			console.log("TURNED UP LEVEL: "+level);
		}
	}

});