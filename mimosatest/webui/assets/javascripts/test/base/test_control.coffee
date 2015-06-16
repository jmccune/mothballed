define ['test/base/schema_model_test'], (baseTests) ->
    console.log("LOADED BASE TEST _CONTROL")
    ()->
      
      describe("Base Test Control Suite",()->
        console.log("\t Running Base Test Control Suite");
        baseTests();
      )