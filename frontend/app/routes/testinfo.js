import Ember from 'ember';

// routes/todos.js
export default Ember.Route.extend({
  model: function() {
    return this.store.find('testinfo');
  },
  renderTemplate: function() {
    this.render({ outlet: 'main' });
  }
});