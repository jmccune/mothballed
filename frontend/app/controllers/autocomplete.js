import Ember from 'ember';

export default Ember.Controller.extend({
	searchText: null,

	searchResults: function() {
		var searchText = this.get('searchText');
		console.log("CHANGED SEARCH TEXT: "+searchText);
		if (!searchText) {return;}

		var regex = new RegExp(searchText,'i');
		return ['one','two','three'].filter(function(name) {
			return name.match(regex);
		});
	}.property('searchText')

});