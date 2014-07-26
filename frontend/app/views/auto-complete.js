// views/auto-complete.js
import Ember from "ember";
import AutoCompleteController from "../controllers/auto-complete";

export default Ember.View.extend({
	attributeBindings: ['data-completiontype'],
	controller: new AutoCompleteController(),
	templateName: 'auto-complete',
	didInsertElement : function(){
		this._super();
		var myController = this.get('controller');
		var myDataCompletionType = this.$().attr('data-completiontype');
		console.log("My Controller>> "+myController);
		console.log("myDataCompletionType >> "+myDataCompletionType);
	}
});
