define ['backbone','jquery'], (Backbone, $) ->
  
  # Marries the view to the model using...
  class BaseComponent extends Backbone.View
    
    componentInfo:null
      #field: null   ##Required -- which field of the model are we binding to?
      #field_meta:   ##Usually pulled from the model's meta information
                     ## But may be augmented or tailored for each component. 
    
  BaseComponent
