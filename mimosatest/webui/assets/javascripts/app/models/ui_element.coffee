define ['vendor/backbone0_9_2','jquery'], (Backbone, $) ->
  console.log("UI_ELEMENT>>> ")
  console.dir Backbone
  console.dir $
  
  class UiElement extends Backbone.Model
    
    
    doSomething: (msg)->
      console.log("DOING SOMETHING: Message>> "+msg)
      
  UiElement