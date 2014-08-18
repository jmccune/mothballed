import Ember from 'ember';

// routes/author.js
export default Ember.Route.extend({
  model: function() {
    return  []; //this.store.find('author');
  }  
});