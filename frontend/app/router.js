import Ember from 'ember';

var Router = Ember.Router.extend({
  location: FrontendENV.locationType
});

Router.map(function() {
	this.route('home',{ path: '/'});
	this.route('about',{ path: '/about'});
	this.resource('xref',{ path: '/xref'}, function() {
		this.route('add');
		this.route('list');
	});
	this.resource('testinfo' ,{ path: '/test'});
});


export default Router;
