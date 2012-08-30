define ['backbone','underscore','jquery','templates','app/base/schema_model']
  ,(Backbone, _,$,template,SchemaModel) ->
    ()->
      console.log("************************************")
      console.dir(SchemaModel)
      describe("SchemaModel Tests",()->
        
        schemaModel = null;
        beforeEach(()->
          schemaModel = new SchemaModel()
          console.log("BEFORE EACH>D>FDFSDLKFJSLDFJSD")
          console.dir schemaModel
          )
          
        it("should pass this test",()->
          expect(true).toBeTruthy();
          )
          
        it("is able to process basic args",()->
          result1= schemaModel._processBasicArgs( name: 'testname')
          expect(result1.name).toEqual('testname')
          
          result2= schemaModel._processBasicArgs( name: 'test2', ignoreme: true
                                                  ,required: true)
          expect(result2.name).toEqual('test2')
          expect(result2.required).toBeTruthy()
          expect(result2.ignoreme?).toBeFalsy()
          )
      )
