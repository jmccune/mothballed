define ['backbone','underscore'
  ,'app/admin/views/admin_view'
  ,'templates'] 
  ,(Backbone, _
  ,AdminView
  ,templates) ->
    class InputFieldView extends AdminView
      template: templates.admin_field_input
      
      initialize:(options) ->
        super(options)
        console.log("INITIALIZE OF INPUT FIELD VIEW")
        console.dir options
        console.dir @model
        
    InputFieldView