import Ember from 'ember';

// routes/xref/add.js
export default Ember.Route.extend({
  renderTemplate: function() {
  	console.log("***** RENDER XREF.ADD");
  	// this.render('xref.index',{
   //  	into: 'application.index',
   //  	outlet:'main'
   //  });
    this.render('xref.add',{
    	into: 'xref',
    	outlet:'xref-main'
    });
  }
  
});