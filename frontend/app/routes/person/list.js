import Ember from 'ember';

// routes/xref/add.js
export default Ember.Route.extend({
  model: function() {
    return this.store.find('person');
  },
  renderTemplate: function() {
    this.render('person.list',{
    	into: 'person',
    	outlet:'person-main',
    	controller: 'person.list'
    });
  }
});