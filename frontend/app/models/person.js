import DS from 'ember-data';
import NameComponent from './name-component';

var getNameInfo=function(nameComponent, nameType) {

	if (!Ember.isEmpty(nameComponent[nameType])) {
		return nameComponent[nameType];
	}
	return nameComponent['NAME'];
}

var PersonModel = DS.Model.extend({  
  roles: DS.attr('stringArray'),  
  nameComponent:  DS.attr('nameComponent'),
  name: function() {
    return getNameInfo(this.get('nameComponent'),'NAME');
  }.property('nameComponent')
});


export default PersonModel; 

