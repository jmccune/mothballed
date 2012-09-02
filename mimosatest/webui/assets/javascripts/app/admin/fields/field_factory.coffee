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
      
      
      buildViewForField: (field,model,options)->
        fieldType = @_getFieldType(field,model)
        console.log("RECEIVED FIELDTYPE: "+fieldType)
        console.dir @fieldConstructionMap
        if (not fieldType?)
          throw "Unable to determine field type for: "+field
        if not @fieldConstructionMap[fieldType]?
          throw "Unable to build view for field: "+field+" of type: "+fieldType
        #TODO: Finish this 
        
        result =@fieldConstructionMap[fieldType](fieldType,field,model,options)
        console.dir result
        console.log("Built View")
        result
        
      #================================================================
      # PRIVATE
      #================================================================
      
      _defaultFieldConstructionMap:()->
        result =
          CharField: (fieldType, field,model,options)->
             new InputFieldView(model: model, field: field)
        result
      
      _getFieldType: (field,model) ->
        if not model.getSchema?
          throw "Not a usable model -- no schema definition!"
        schema =model.getSchema()
        console.log("HAS SCHEMA???? >>>>>>>>")
        console.dir schema
        
        fieldData = schema.get(field)
        if not fieldData? 
          throw "No data/model definition for field: "+field
        
        return fieldData.fieldType 
        
    FieldFactory
