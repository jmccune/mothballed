define ['backbone','underscore','jquery_ui'
  ,'app/base/shared_info'
  ,'app/admin/views/admin_view'
  ,'templates'] 
  ,(Backbone, _,$
  ,sharedInfo
  ,AdminView
  ,templates) ->
    
    class DateFieldView extends AdminView
      template: templates.admin_field_date
      
      initialize:(options) ->
        super(options)
        
      events:
        "change input"  : 'validateFinal'
        #"keyup" : 'validateTransient'
        
      render:()->
        super
        @$el.find('.warning-triangle').hide();
        fieldSchema = @getFieldSchema()
        date_format = fieldSchema.get('date_format')
        @$el.find('.datepicker').datepicker({
          dateFormat: date_format
        });
        @
      
      getFieldValue:()->
        dateValue = @model.get(@options.field)
        format = @getFieldSchema().get('date_format')
        stringValue = $.datepicker.formatDate(format,dateValue) 
      validateTransient:()->
        @_doValidation(true)
      
      validateFinal:()->
        @_doValidation(false)
      
      _doValidation:(transient)->
        value =@$el.find('.datepicker').datepicker("getDate")
        result = @getFieldSchema().validate(transient,value,@model,{})
        
        if (result is true or not result?)
          @$el.find('.warning-triangle').hide();
        else if result is false
          @$el.find('.warning-triangle').show();
        else if typeof result is 'string'
          
          @$el.find('.warning-triangle').show();
        
    DateFieldView