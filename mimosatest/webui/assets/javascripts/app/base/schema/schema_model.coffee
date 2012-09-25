define ['backbone','underscore','jquery','templates'
  ,'app/base/schema/schema_field']
  ,(Backbone, _,$,template
  ,SchemaField) ->
    
    # Each field type that we use will have the following arguments
    # that it can accept:
    class SchemaModel extends Backbone.Model
      initialize:()->
        @
      
      getFields:()->
        _.keys(@.toJSON())
        
      #Args:
      #   <All the Basic Args>  ## See Class Documentation
      #   max_length: <number>      # Maximum number of characters
       
      CharField:(args)->
        argMap = @_processBasicArgs(args,'CharField');
        argMap = _.extend(argMap, _.pick(args,'max_length','empty_allowed'))
        
        @set(argMap.name,new SchemaField(argMap))
        @
      
      #Args:
      #   <All the Basic Args>  ## See Class Documentation
      #   auto_now: <boolean>   # Default to the current date
      DateField:(args)->
        argMap = @_processBasicArgs(args,'DateField')
        argMap = _.extend(argMap, _.pick(args,'date_format'))
        argMap.date_format = 'yy-mm-dd' unless argMap.date_format?
        @set(argMap.name,new SchemaField(argMap))
        @
      
      BooleanField:(args)->
        argMap = _processBasicArgs(args,'BooleanField');
        @set(argMap.name,new SchemaField(argMap))
        @
      
      # =============================================================
      # PRIVATE  
      # =============================================================
      _processBasicArgs: (args,fieldType) ->
        if (not args.name?)
          throw "Required field name is not supplied!"
        if (@get(args.name)?)
          throw "Cannot duplicate that field name-- already exists!"
        
        whitelist = ['name','label'
          ,'required','null_allowed','editable'
          ,'validator','default']
        context= _.pick(args,whitelist)
        context.fieldType = fieldType
        #Establish defaults:
        context.required=false unless context.required?
        context.null_allowed=true unless context.null_allowed?
        context.editable =true unless context.editable?
        
        context
        
    SchemaModel