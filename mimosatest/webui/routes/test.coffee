test = (config) ->

  options =
    title: 'Justins TEST PAGE'
    reload:    config.server.useReload
    optimize:  config.optimize ? false
    cachebust: if process.env.NODE_ENV isnt "production" then "?b=#{(new Date()).getTime()}" else ''
    runtest: true
  
  # Render...using the template engine.
  #  (in this case -- it's using jade.. and the views
  #  under the lowest views directory)
  (req, res) ->  res.render 'test2', options

exports.test = test