import DS from 'ember-data';

var TestinfoModel = DS.Model.extend({
  title: DS.attr('string'),
  link: DS.attr('string'),
  postData: DS.attr('string')
});

TestinfoModel.reopenClass({
  FIXTURES: [
  {
    id: "1",
    title: 'test link 1',
    link: "http://link1"
  }, {
    id: "2",
    title: 'install additional dependencies',
    link: "http://link2"
  }
]});

export default TestinfoModel; 
