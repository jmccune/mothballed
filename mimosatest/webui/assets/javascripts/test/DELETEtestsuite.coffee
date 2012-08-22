#define ['jquery','test/csplayer','test/cssong'], ($,Player,Song) ->
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

requirejs ['jquery','test/csplayer','test/cssong'], ($,Player,Song)->
  
  console.log("HERE>>> LOADED TESTSUITE!")
  console.log("PLAYER: "+Player)
  console.log("jQuery loaded? "+$)
  $('h1').css('background-color','red')
