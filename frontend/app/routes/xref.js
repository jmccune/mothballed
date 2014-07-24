import Ember from 'ember';

// routes/todos.js
export default Ember.Route.extend({
  model: function() {
    return this.store.find('xref');
  },
  // renderTemplate: function() {
  // 	console.log("***** RENDER XREF (INDEX)");
  //   this.render('xref.index',{
  //   	outlet:'main'
  //   });
  //   // this.render('xref.nav',{
  //   // 	into: 'xref.index',
  //   // 	outlet:'xref-nav'
  //   // });   
  // }
});