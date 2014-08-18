import Ember from 'ember';

// routes/xref/add.js
export default Ember.Route.extend({
  model: function() {
    return this.store.find('author');
  },
  renderTemplate: function() {
    this.render('author.list',{
    	into: 'author',
    	outlet:'author-main',
    	controller: 'author.list'
    });
  }
});