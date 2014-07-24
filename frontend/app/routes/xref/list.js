import Ember from 'ember';

// routes/xref/add.js
export default Ember.Route.extend({
  model: function() {
    return this.store.find('xref');
  },
  renderTemplate: function() {
  	console.log("***** RENDER XREF.list");
  	console.dir(this);
  	// this.render('xref.index',{
   //  	into: 'application.index',
   //  	outlet:'main'
   //  });
    this.render('xref.list',{
    	into: 'xref',
    	outlet:'xref-main',
    	controller: 'xref.list'
    });
  }
});