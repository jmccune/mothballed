define ['backbone','underscore','jquery','templates'
  ,'app/base/schema/schema_model']
  ,(Backbone, _,$,template
  ,SchemaModel) ->
    
    class BaseModel extends Backbone.Model
      initialize:() ->
        super
        @_schema = new SchemaModel()
        @
        
      getSchema:()=>
        @_schema
        
    BaseModel