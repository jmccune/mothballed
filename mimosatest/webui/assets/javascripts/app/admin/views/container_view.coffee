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
        listDiv = @$el.find('.placeStuffHere')
        listDiv.empty()
        
        for view in @views
          console.log("RENDERING SUBVIEW")
          listDiv.append(view.render().el)
        @
    
    ContainerView