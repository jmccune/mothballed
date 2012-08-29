define ['backbone','underscore','jquery'
  ,'app/components/base/view'
  ,'templates']
  , (Backbone, _,$,View,template) ->
    
    class AdminView extends View
      
      
      #We rescope the admin views to context on the field they support.
      getTemplateContext:()->
        context = {}
        context.mainmodel = super();
        context.read_only = false
        console.log("OPTIONS > FIELD"+@options.field)
        console.dir @model
        if @options?.field? and @model?.get('_meta')?[@options.field]
          data = @model.get('_meta')[@options.field]
          console.log("DATA>> ")
          console.dir data
          context.field = _.clone(data)
          fieldValue =  @model.get(@options.field)
          context.field.value = fieldValue if fieldValue?
        context
        
    AdminView