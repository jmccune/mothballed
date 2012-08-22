define ['jquery', 'templates','backbone',
         'app/models/ui_element'
         'app/utilities/admin_util'], ($, templates,
         backbone,UiElement,AdminUtil) ->
  
  console.log("HERE @ BACKBONE")
  console.dir backbone
  console.dir templates  
      
  class ExampleView
    
    render: (element) ->
      
      adminUtil = new AdminUtil()
      adminUtil.createObjectMeta( model: 5)
      
      uiEl = new UiElement()
      uiEl.doSomething("ABC")
      
      $(element).append templates.example({name:'Handlebars', css:'sass'})
      $(element).append templates['another-example']({name:'Handlebars'})
 
  ExampleView