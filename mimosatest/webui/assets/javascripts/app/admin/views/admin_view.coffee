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
        
      #We rescope the admin views to context on the field they support.
      getTemplateContext:()->
        context = {}
        context.fullmodel = super();
        
        console.log("******* ADMIN VIEW>> GETTING CONTEXT 4 TEMPLATE")
        console.dir @
        
        context.field={}
        context.field.value = @model.get(@options.field)
        
        if @model.getSchema? 
          schema =@model.getSchema()
          fieldData = schema.get(@options.field)
          if (fieldData?)
            console.log("USING FIELD DATA>>DFSDLFJKSLDKFJSDLJFLSKDJFLSKDJFLSDKJ")
            console.dir fieldData
            context.field = _.extend(fieldData,context.field)
            
        #context.read_only = false
        #console.log("OPTIONS > FIELD"+@options.field)
        #console.dir @model
        #if @options?.field? and @model?.get('_meta')?[@options.field]
        #  data = @model.get('_meta')[@options.field]
        #  console.log("DATA>> ")
        #  console.dir data
        #  context.field = _.clone(data)
        #  fieldValue =  @model.get(@options.field)
        #  context.field.value = fieldValue if fieldValue?
        context
        
    AdminFieldView