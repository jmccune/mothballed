define ['backbone','jquery','templates']
  , (Backbone, $, template) ->
    
    class SharedInfo extends Backbone.Model
      defaults:
        globalObjectCount: 0
        
      getNextUID:()->
        uid =@get('globalObjectCount')
        nextVal = uid+1
        @set('globalObjectCount',nextVal)
        nextUid =@get('globalObjectCount')
        console.log("****************************************")
        console.log("UID: "+uid+" NEXT UID: "+nextUid)
        uid
        
    return new SharedInfo() 