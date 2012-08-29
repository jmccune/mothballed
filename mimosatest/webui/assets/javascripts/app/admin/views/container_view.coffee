define ['backbone','jquery'
  ,'app/components/base/view'
  ,'templates']
  , (Backbone, $,View,template) ->
    
    class ContainerView extends View
      template: template.component_admin_container
      test: 14
      
      #addView: (view) ->
      #  console.log("ADDING VIEW")
      #  #TODO: Fill this out 
      #  @
      initialize:()->
        #console.log("TEMPLATES>>>>>>>>>>>>>>>>>>>")
        #console.dir @template
    
    ContainerView