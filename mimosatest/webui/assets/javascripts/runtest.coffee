requirejs.config
  urlArgs: "b=#{(new Date()).getTime()}"
  paths:
    vendor: 'vendor'
    require: 'vendor/require'
    jquery: 'vendor/jquery'
    backbone: 'vendor/backbone0_9_2'
    underscore: 'vendor/underscore'
  shim: 
    'backbone':
       #These script dependencies should be loaded before loading
       #backbone.js
       deps: ['underscore', 'jquery'],
       #Once loaded, use the global 'Backbone' as the
       #module value.
       exports: 'Backbone'
    'underscore':
      deps: []
      exports: '_'

requirejs [
    'test/main_test_control'
    'backbone'
    'jquery'
    'underscore'
  ]
  , (mainTestControl,backbone,$,underscore) ->
    
    #console.log("CALLING MAIN TEST RUNNER!")
    #console.dir mainTestControl
    #console.log("JQUERY? ")
    #console.dir $
    #console.log("BACKBONE")
    #console.dir backbone
    #console.log("UNDERSCORE")
    #console.dir underscore
    
    console.log("============= END OF TEST LOADING ==============");
    console.log("\n\n============= STARTING TESTING ==============\n");
    mainTestControl();
    
    $(()->
      jasmineEnv = jasmine.getEnv();
      jasmineEnv.updateInterval = 250;
      
      htmlReporter = new jasmine.HtmlReporter();
      jasmineEnv.addReporter(htmlReporter);
      
      jasmineEnv.specFilter = (spec) ->
        return htmlReporter.specFilter(spec);
      
      $('body').append("JASMINE VERSION: "+jasmineEnv.versionString())
      jasmineEnv.execute();
    )