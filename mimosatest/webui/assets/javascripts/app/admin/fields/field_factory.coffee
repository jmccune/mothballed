define ['backbone','underscore'
  ,'app/admin/fields/input'
  ,'app/admin/fields/date'
  ,'app/admin/views/container_view'] 
  ,(Backbone, _
  ,InputFieldView
  ,DateFieldView
  ,DefaultContainerView) ->
    console.log("*********************")
    console.dir InputFieldView
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
        #console.log("RECEIVED FIELDTYPE: "+fieldType)
        #console.dir @fieldConstructionMap
        if (not fieldType?)
          throw "Unable to determine field type for: "+field
        if not @fieldConstructionMap[fieldType]?
          throw "Unable to build view for field: "+field+" of type: "+fieldType
        #TODO: Finish this 
        
        result =@fieldConstructionMap[fieldType](fieldType,field,model,options)
        #console.dir result
        #console.log("Built View")
        result
        
      #================================================================
      # PRIVATE
      #================================================================
      
      _defaultFieldConstructionMap:()->
        result =
          CharField: (fieldType, field,model,options)->
            new InputFieldView(model: model, field: field)
          DateField: (fieldType, field, model,options)->
            new DateFieldView(model: model, field:field)
            
        result
      
      _getFieldType: (field,model) ->
        if not model.getSchema?
          throw "Not a usable model -- no schema definition!"
        schema =model.getSchema()
        
        if not schema?
          throw "Schema undefined??"
        
        fieldData = schema.get(field)
        if not fieldData? 
          throw "No data/model definition for field: "+field
        
        return fieldData.get('fieldType') 
        
    FieldFactory
