test = (useReload, optimize) ->

  options =
    title:    "Express (MY TEST)"
    reload:   useReload
    optimize: optimize ? false
    env:      process.env.NODE_ENV ? "development"

  # Render...using the template engine.
  #  (in this case -- it's using jade.. and the views
  #  under the lowest views directory)
  (req, res) ->  res.render 'test', options

exports.test = test