define ['backbone','underscore','jquery','templates'
  ,'app/base/schema_model']
  ,(Backbone, _,$,View,template
  ,SchemaModel) ->
    
    class BaseModel extends Backbone.Model
      initialize:() ->
        super
        @_schema = new SchemaModel()
        @
        
      getSchema:()->
        @_schema
        
    SchemaModel