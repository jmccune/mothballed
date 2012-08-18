define ['vendor/backbone0_9_2','jquery'], (Backbone, $) ->
  
  class AdminUtil 
    
    createObjectaMetaIfNeeded:(jsonObject) ->
      if (typeof jsonObject) isnt 'object'
        throw "jsonObject is not a valid object"
    
      if jsonObject[_meta]?
        return
      return @createObjectMeta(jsonObject)
    
    
    createObjectMeta: (jsonObject) ->
      if (typeof jsonObject) isnt 'object'
        throw "jsonObject is not a valid object"
    
      #Force user to deal with whether they want to replace 
      # or not an existing meta.
      if jsonObject['_meta']?
        throw "Object already has meta!"
        
      
      metaObject={}
      for k,v of jsonObject
        if ((k+"").indexOf("$")>0)
          throw "Illegal property in jsonObject: "+k
        
        
      
  AdminUtil