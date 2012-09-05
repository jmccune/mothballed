define ['backbone','underscore','jquery','templates','app/base/schema/schema_model']
  ,(Backbone, _,$,template,SchemaModel) ->
    ()->
      #console.log("************************************")
      #console.dir(SchemaModel)
      describe("SchemaModel Tests",()->
        
        schemaModel = null;
        beforeEach(()->
          schemaModel = new SchemaModel()
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
          
        it("is able to create a CharField", ()->
          simple1 = 
            label: 'cfield1label'
            name: 'cfield1'
            required: true
            
          schemaModel.CharField(simple1)
          data=schemaModel.get('cfield1')
          expect(data.get('name')).toEqual('cfield1')
          expect(data.get('required')).toBeTruthy()
          expect(data.get('label')).toEqual('cfield1label')
          expect(data.get('fieldType')).toEqual('CharField')
          )
      )
