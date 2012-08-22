test = (useReload, optimize) ->

  options =
    title:    "Express (MY TEST)"
    runtest:  true
    reload:   useReload
    optimize: optimize ? false
    env:      process.env.NODE_ENV ? "development"

  # Render...using the template engine.
  #  (in this case -- it's using jade.. and the views
  #  under the lowest views directory)
  console.log "OPTIONS!!!"
  console.dir options
  (req, res) ->  res.render 'test2', options

exports.test = test