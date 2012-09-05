define ['backbone','underscore','jquery','templates']
  ,(Backbone, _,$,View,template) ->
    
    #Defines the expected attributes of a particular field.
    # This is the basic definition -- although particular field types
    # may extend this definition in certain ways.
    #
    #   name:      <string>       # REQUIRED
    #   required:  <boolean>      # Is the field given required in the model
    #   label:     <string>       # Label
    #   empty_allowed: <boolean>  # Is empty allowed? (E.g. from the user?)
    #   null_allowed:  <boolean>  # Is null allowed?  (E.g. from the server?)
    #   validator: <function>     # TBD
    #   default: <?>              # The default value (for UI) 
    #   editable: <boolean>|TRUE
    #
    # ------
    #  Validator function:
    #     #params:
    #          transient - <boolean> true if the user is still editing...
    #          value - <?> the value of the data
    #          model - undefined or a <Model> - the model of the overall
    #                        object-- usually a Backbone Model, but other
    #                        representations could be used.
    #          options - undefined or {Object} -- various options. 
    #                        (Future Capability)
    #
    #     validate(transient, value,model,options)
    #     returns true = valid, false= invalid, undefined (meaning not sure),
    #             "string" meaning invalid, with explanation
    #             { warning: "msg"}   the warning message
    #             
    #             { suggestion: "msg" } a suggestion to the user 
    class SchemaField extends Backbone.Model
      
      initialize:(options)->
        @options =options
        if (not @options.name?)
          throw "Required field name is not supplied!"
        @
        
        
      validate: (transient, value,model,options) ->
        #console.log("VALDIATION>> "+transient+" "+value+" "+model+" "+options)
        validator = @get('validator')
        if not validator?
          return
        return validator(transient,value,model,options)
        
    SchemaField