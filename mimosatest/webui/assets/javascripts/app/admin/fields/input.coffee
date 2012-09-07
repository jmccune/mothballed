define ['backbone','underscore'
  ,'app/base/shared_info'
  ,'app/admin/views/admin_view'
  ,'templates'] 
  ,(Backbone, _
  ,sharedInfo
  ,AdminView
  ,templates) ->
    idCount=0;
    class InputFieldView extends AdminView
      template: templates.admin_field_input
      
      initialize:(options) ->
        super(options)
        #@id= "InputField"+sharedInfo.getNextUID()
        
      events:
        "change input"  : 'validateFinal'
        "keyup" : 'validateTransient'
        
      render:()->
        super
        @$el.find('.warning-triangle').hide();
        @
        
      validateTransient:()->
        @_doValidation(true)
      
      validateFinal:()->
        @_doValidation(false)
      
      _doValidation:(transient)->
        value = @$el.find('input').val()
        fieldSchema = @getFieldSchema()
        result = fieldSchema.validate(transient,value,@model,{})
        console.log("RESULT OF VALIDATION: "+result)
        
        
        if (result is true or not result?)
          @$el.find('.warning-triangle').hide();
        else if result is false
          @$el.find('.warning-triangle').show();
        else if typeof result is 'string'
          
          @$el.find('.warning-triangle').show();
        
    InputFieldView