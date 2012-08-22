requirejs.config
  urlArgs: "b=#{(new Date()).getTime()}"
  paths:
    require: 'vendor/require'
    jquery: 'vendor/jquery'
    underscore: 'vendor/underscore'
  shim: 
    'vendor/backbone0_9_2':
       #These script dependencies should be loaded before loading
       #backbone.js
       deps: ['vendor/underscore', 'vendor/jquery'],
       #Once loaded, use the global 'Backbone' as the
       #module value.
       exports: 'Backbone'

requirejs [
    'test/csplayerspec'
    'jquery'
  ]
  , (testSpecFn,$) ->
    console.log("CALLING MAIN TEST RUNNER!")
    console.log("JQUERY? "+$)
    testSpecFn();
    
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