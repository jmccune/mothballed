define ['test/utilities/test_control'
  ,'test/admin/test_control'
  ,'test/base/test_control']
  ,(utilitiesTests,adminTests,baseTests) ->
    console.log("MAIN TEST LOADED")
    
    ()->
      #console.dir utilitiesTest
      describe("Jasmine Verification -- Up and running?",()->
        it("True == True", ()->
          expect(true).toBe(true)
          )
        )
      describe("Main Control Suite",()->
        console.log("Running Main Control Suite");
        utilitiesTests();
        adminTests();
        baseTests(); 
        )