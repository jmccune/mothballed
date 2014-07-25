import DS from 'ember-data';

var XrefModel = DS.Model.extend({  
  referenceText: DS.attr('string')
});

/*
XrefModel.reopenClass({
  FIXTURES: [
  {
    id: "1",
    referenceText: 'Some Quoted Text',    
  }, {
    id: "2",
    referenceText: 'Some Quoted 2 Text',    
  }
]});
*/

export default XrefModel; 
