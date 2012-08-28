define ['app/components/form_factory'
  ,'app/components/base/container_view'
  ,'templates'
  ], (FormFactory,ContainerView,templates) ->
    ()->
      console.log("Form Factory TEST")
      console.dir FormFactory
      describe("FormFactory Tests",()->
        formFactory=null
        testModel=null
        beforeEach(()->
          formFactory = new FormFactory();
          testModel = 
            stringfield: 'test_string_value'
            integerfield: 5
            realfield:    10.3
        )
        
        it("should pass this test",()->
          expect(true).toBeTruthy();
          )
        it("should be able to create an admin container view",()->
            
            view =formFactory.getDefaultAdminContainer();
            console.log("FORM FACTORY CREATED VIEW? "+view)
            console.dir view.render().el
            html = view.render().$el.html()
            
            expect(html.length > 0).toBeTruthy();
          )
        
        it("should be able to create a form field for a STRING",()->
            fieldView =formFactory.buildViewForField('stringfield',@testModel,{},{})
            html = fieldView.render().$el.html()
            expect(html.length > 0).toBeTruthy();
          )
      )
