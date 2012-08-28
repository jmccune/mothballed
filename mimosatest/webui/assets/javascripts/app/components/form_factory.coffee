define ['backbone','underscore'
  ,'app/components/base/field_factory'
  ,'app/components/base/container_view'] 
  ,(Backbone, _
  ,FieldFactory
  ,DefaultContainerView) ->
  
    # The Form Specification includes the following:
    #   fields:  (Fields to use)  if not specified, all the fields (in the 
    #            model) are used.
    #   exclude: (Fields to exclude) -- assumed that it's easier to say use
    #               everything except fields "A" and "B"
    #
    class FormFactory
    
      buildFormForModel:(model,spec, options) ->
        containerForm = @getDefaultAdminContainer()
        usableFields = @_getUsableFields(model,spec,options)
        
        for field in usableFields
          view = @buildViewForField(field,model,spec,options)
          containerForm.add(view)
        
      getDefaultAdminContainer: () ->
        new DefaultContainerView()
        
      buildViewForField: (field,model,spec,options)->
        @fieldFactory = new FieldFactory() unless @fieldFactory?
        result=
          @fieldFactory.buildViewForField(field,model,spec,options)
        console.log("*** BUILDVIEW FOR FIELD: ")
        console.dir result
        result
        
      #================================================================
      # PRIVATE
      #================================================================
      
      _getUsableFields: (model,spec,options) ->
        @
        
        
      
    FormFactory
