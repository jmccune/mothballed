requirejs.config
  urlArgs: "b=#{(new Date()).getTime()}"
  paths:
    vendor: 'vendor'
    require: 'vendor/require'
    jquery: 'vendor/jquery'
    underscore: 'vendor/underscore'
  shim: 
    'vendor/backbone0_9_2':
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
    'app/example-view'
    'vendor/backbone0_9_2'
    'jquery'
    'underscore'
  ]
  , (ExampleView,Backbone,underscore,jquery) ->
    console.log("HERE @ MAIN CALLING EXAMPLE VIEW")
    console.log("JQUERY? ")
    console.dir jquery
    console.log("UNDERSCORE")
    console.dir underscore
    console.log("BACKBONE")
    console.dir Backbone
    view = new ExampleView()
    view.render('body')