define ['backbone','underscore'
  ,'app/base/shared_info'
  ,'app/admin/views/admin_view'
  ,'templates'] 
  ,(Backbone, _
  ,sharedInfo
  ,AdminView
  ,templates) ->
    idCount=0;
    class InputFieldView extends AdminView
      template: templates.admin_field_input
      
      initialize:(options) ->
        super(options)
        @id= "InputField"+sharedInfo.getNextUID()
        console.log("MY ID>> "+@id)
        #console.log("INITIALIZE OF INPUT FIELD VIEW")
        #console.dir options
        #console.dir @model
        
      events:
        "change input"  : 'validateFinal'
      #  "keypress" : 'validateTransient'
      #  "hover label" : 'hover'
        
      render:()->
        super
        #@$el.find('input').parent('div').css('background-color','red')
        #@$el.attr('id',@id)
        #@$el.find('label').on('hover',@hover)
        cbFunc=()=>
          console.log("INVOKING FIELD TIMEOUT")
          #console.dir @el 
          #$('#'+@id).css('background-color','green')
          #$('#'+@id).change(@validateTransient)
          #$('#'+@id).find('label').hover(()->
          #  $(this).css('background-color','yellow')
          #  )
          #@$el.css('background-color','yellow')
          #console.dir @$el.get(0)
          #console.dir $('#'+@id).get(0)
          console.log @$el.get(0) is $('#'+@id).get(0)
          
        setTimeout(cbFunc,150)
        @
      hover:()->
        console.log("HOVER")
        
      validateTransient:()->
        console.log("VALIDATE TRANSIENT")
      
      validateFinal:()->
        console.log("VALIDATE FINAL")
        
    InputFieldView