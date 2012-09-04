define ['backbone'
  ,'app/admin/form_factory'
  ,'app/admin/views/container_view'
  ,'app/base/base_model'
  ,'templates'
  ], (Backbone,FormFactory,ContainerView,BaseModel,templates) ->
    console.log("BASE MODEL? "+BaseModel)
    ()->
      createTestModel1 = ()->
        class TestModel1 extends BaseModel
          initialize:()->
            super
            @getSchema().CharField( label:'stringfieldLABEL', name: 'stringfield', required:true)
            @getSchema().CharField( label:'stringfieldLABEL', name: 'stringfield2', required:false)
            @getSchema().CharField( label:'stringfieldLABEL', name: 'stringfield3', editable:false)
            @
            
        result =new TestModel1(
            stringfield: 'test_string_value'
            stringfield2: 'not_requied...'
            stringfield3: 'not_editable...'
            integerfield: 5
            realfield:    10.3
            );
        result
      
      describe("FormFactory Tests",()->
        formFactory=null
        testModel=null
        beforeEach(()->
          formFactory = new FormFactory();
          testModel = createTestModel1()
        )
        
        it("should pass this test",()->
          expect(true).toBeTruthy();
          )
        it("should be able to create an admin container view",()->
            
            view =formFactory.getDefaultAdminContainer();
            #console.log("FORM FACTORY CREATED VIEW? "+view)
            #console.dir view.render().el
            htmlString = view.render().$el.html()
            $('#testTarget').empty().html(htmlString);
            expect(htmlString.length > 0).toBeTruthy();
          )
        
        it("should be able to create a STRING field",()->
            fieldView =formFactory.buildViewForField('stringfield',testModel,{},{})
            htmlString = fieldView.render().$el.html()
            expect(htmlString.length > 0).toBeTruthy();
            
            #$('#testTarget').empty().html(htmlString);
          )
          
        it("should be able to create a form of string fields",()->
            view = formFactory.buildFormForModel(testModel)
            htmlElement = view.render().$el;
            $('#testTarget').empty().html(htmlElement);
            htmlString = htmlElement.html()
            expect(htmlString.length > 0).toBeTruthy();
          )
      )
