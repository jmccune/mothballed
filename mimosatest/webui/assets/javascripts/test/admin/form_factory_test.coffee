define ['backbone'
  ,'app/admin/form_factory'
  ,'app/admin/views/container_view'
  ,'templates'
  ], (Backbone,FormFactory,ContainerView,templates) ->
    ()->
      describe("FormFactory Tests",()->
        formFactory=null
        testModel=null
        beforeEach(()->
          formFactory = new FormFactory();
          testModel = new Backbone.Model(
            stringfield: 'test_string_value'
            integerfield: 5
            realfield:    10.3
            _meta:
              stringfield:
                label: "Testing Stringfield"
            );
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
            fieldView =formFactory.buildViewForField('stringfield',testModel,{},{})
            htmlString = fieldView.render().$el.html()
            expect(htmlString.length > 0).toBeTruthy();
            
            $('#testTarget').empty().html(htmlString);
          )
      )
