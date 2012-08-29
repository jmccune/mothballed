define ['backbone','underscore'
  ,'app/admin/fields/input'
  ,'app/admin/views/container_view'] 
  ,(Backbone, _
  ,InputFieldView
  ,DefaultContainerView) ->
  
    # The Form Specification includes the following:
    #   fields:  (Fields to use)  if not specified, all the fields (in the 
    #            model) are used.
    #   exclude: (Fields to exclude) -- assumed that it's easier to say use
    #               everything except fields "A" and "B"
    #
    class FieldFactory
    
      constructor: ()->
        @fieldConstructionMap = _.clone(@_defaultFieldConstructionMap());
      
      
      buildViewForField: (field,model,spec,options)->
        fieldType = @_getFieldType(field,model)
        console.log("RECEIVED FIELDTYPE: "+fieldType)
        console.dir @fieldConstructionMap
        if (not fieldType?)
          throw "Unable to determine field type for: "+field
        if not @fieldConstructionMap[fieldType]?
          throw "Unable to build view for field: "+field+" of type: "+fieldType
        #TODO: Finish this 
        
        result =@fieldConstructionMap[fieldType](fieldType,field,model,spec,options)
        console.dir result
        console.log("Built View")
        result
        
      #================================================================
      # PRIVATE
      #================================================================
      
      _defaultFieldConstructionMap:()->
        result =
          string: (fieldType, field,model,spec,options)->
             new InputFieldView(model: model, field: field)
        result
      
      _getFieldType: (field,model) ->
        #TODO: Make this return real information
        return "string"
    FieldFactory
