define ['backbone','jquery'], (Backbone, $) ->
  
  #Defines the basic view and some common patterns throughout this
  # application.  
  class View extends Backbone.View
    template: null  # The template to use to apply.
    css: null       # Any dynamic CSS that needs to be applied to the
                    # main container
    style: null     # Class style to apply to the main element.
  
    render:()->
      super()
      if @template? 
        @$el.html(@template(@getTemplateContext()))
      @
    
    getTemplateContext:()->
      templateContext = {}
      templateContext = @model.toJSON()  if @model?
      templateContext
      
  View
