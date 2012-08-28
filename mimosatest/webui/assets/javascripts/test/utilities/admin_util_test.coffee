define ['app/utilities/admin_util'], (AdminUtil) ->
  ()->
    console.log("ADMIN_UTIL TEST")
    describe("AdminUtil CODE",()->
      console.log("RUNNING ADMIN UTIL CODE")
      adminUtil=new AdminUtil();
      console.dir adminUtil
       
      it("should be able to create object meta", ()->
        object =  
          stringtype : "a string"
          numbertype: 5
        
        objectMeta = adminUtil.createObjectMeta(object)
   
        expect(objectMeta['stringtype']['type']).toEqual('string');
        expect(objectMeta['numbertype']['type']).toEqual('number');
        )
    )
