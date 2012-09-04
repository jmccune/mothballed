define ['backbone','jquery'
  ,'app/components/base/view'
  ,'templates']
  , (Backbone, $,View,template) ->
    
    class ContainerView extends View
      template: template.component_admin_container
      
      addView: (view) ->
        @views=[] unless @views?
        @views[@views.length]=view
        
      initialize:()->
        super
        @views=[] unless @views?
        #console.log("TEMPLATES>>>>>>>>>>>>>>>>>>>")
        #console.dir @template
        
      render:()->
        super
        #listDiv=@$el
        listDiv = @$el.find('.placeStuffHere')
        listDiv.empty()
        
        for view in @views
          
          viewEl = view.render().el
          console.log("RENDERed SUBVIEW")
          console.dir viewEl
          @$el.append(viewEl)
          
          
          #$(viewEl).css('background-color','blue')
        @
    
    ContainerView