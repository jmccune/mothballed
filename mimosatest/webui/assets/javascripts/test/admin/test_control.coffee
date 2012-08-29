define ['test/admin/form_factory_test'], (formFactoryTests) ->
    console.log("LOADED ADMIN TEST _CONTROL")
    ()->
      
      describe("Admin Test Control Suite",()->
        console.log("\t Running Admin Test Control Suite");
        formFactoryTests();
      )