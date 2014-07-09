import Ember from 'ember';

var Router = Ember.Router.extend({
  location: FrontendENV.locationType
});

Router.map(function() {
	this.resource('test',{ path: '/'});
	this.resource('about',{ path: '/about'});
});

export default Router;
