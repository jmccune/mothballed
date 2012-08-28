define ['test/utilities/test_control'
  ,'test/components/test_control']
  ,(utilitiesTests,componentsTests) ->
    console.log("MAIN TEST LOADED")
    ()->
      console.log("About 2 RUNNING MAIN TEST(S)")
      #console.dir utilitiesTest
      describe("Jasmine Verification -- Up and running?",()->
        it("True == True", ()->
          expect(true).toBe(true)
          )
        )
      describe("Main Control Suite",()->
        console.log("RUNNING MAIN TEST()")
        utilitiesTests();
        componentsTests();
       
        )