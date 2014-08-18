import DS from 'ember-data';

var NameComponentModel = DS.Model.extend({  
  NAME: DS.attr('string'),
  DISPLAY_NAME: DS.attr('string'),
  FORMER_NAMES: DS.attr('stringArray'),
  ALIASES: DS.attr('stringArray'),
  NICKNAMES: DS.attr('stringArray')
});

//
/*NAME, 
        DISPLAY_NAME,
        PREFERRED_NAME,
        GIVEN_NAME,
        OFFICIAL_NAME,
        MAIDEN_NAME,
    };

    static public enum MultiNameType implements NameType {
        //LIST EXPECTED
        FORMER_NAMES,
        ALIASES,
        NICKNAMES
        */

export default NameComponentModel; 

