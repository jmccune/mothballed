define ['backbone','underscore'
  ,'app/admin/fields/field_factory'
  ,'app/admin/views/container_view'] 
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
    
      buildFormForModel:(model,options) ->
        containerForm = @getDefaultAdminContainer()
        usableFields = @_getUsableFields(model,options)
        
        for field in usableFields
          view = @buildViewForField(field,model,options)
          containerForm.addView(view)
          
        containerForm
        
      getDefaultAdminContainer: () ->
        new DefaultContainerView()
        
      buildViewForField: (field,model,options)->
        @fieldFactory = new FieldFactory() unless @fieldFactory?
        result=
          @fieldFactory.buildViewForField(field,model,options)
        console.log("*** BUILDVIEW FOR FIELD: ")
        console.dir result
        result
        
      #================================================================
      # PRIVATE
      #================================================================
      
      _getUsableFields: (model,spec,options) ->
        if (not model?)
          throw "No model given!"
        fieldList =model.getSchema().getFields()
        
        
      
    FormFactory
