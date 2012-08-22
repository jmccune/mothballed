define ['backbone','jquery'], (Backbone, $) ->
  console.log("UI_ELEMENT>>> ")
  console.dir Backbone
  console.dir $
  
  class UiElement extends Backbone.Model
    
    
    doSomething: (msg)->
      console.log("DOING SOMETHING: Message>> "+msg)
      
  UiElement