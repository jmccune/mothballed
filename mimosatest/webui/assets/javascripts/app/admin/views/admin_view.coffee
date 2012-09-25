define ['backbone','underscore','jquery'
  ,'app/components/base/view'
  ,'templates']
  , (Backbone, _,$,View,template) ->
    
    class AdminFieldView extends View
      
      initialize:()->
        super
        if not @options?.field?
          throw "No field for this admin *field* view"
        @
      
      getFieldSchema:()->
        schema = @getModelSchema()
        if (not schema?)
          return undefined
        schema.get(@options.field)
       
      getModelSchema:()->
        if not @model.getSchema?
          return undefined
          
        schema =@model.getSchema()
        
      #We rescope the admin views to context on the field they support.
      getTemplateContext:()->
        context = {}
        context.fullmodel = super();        
        context.field={}
        context.field.value = @getFieldValue()
        
        fieldData = @getFieldSchema()
        if fieldData?
           context.field = _.extend(fieldData.toJSON(),context.field)
           
        context
      
      getFieldValue:()->
        @model.get(@options.field)
        
    AdminFieldView