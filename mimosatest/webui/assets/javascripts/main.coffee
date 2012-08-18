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
    'app/example-view'
    'vendor/jquery'
    'vendor/underscore'
    'vendor/backbone0_9_2'
  ]
  , (ExampleView) ->
    console.log("HERE @ MAIN CALLING EXAMPLE VIEW")
    view = new ExampleView()
    view.render('body')