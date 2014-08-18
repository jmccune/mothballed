import DS from 'ember-data';
import NameComponent from './name-component';

var PersonModel = NameComponent.extend({  
  roles: DS.attr('stringArray')  
});


export default PersonModel; 

