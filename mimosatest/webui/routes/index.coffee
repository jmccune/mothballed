index = (useReload, optimize) ->

  options =
    title:    "Express (David's Reference)"
    reload:   useReload 
    optimize: optimize ? false
    env:      process.env.NODE_ENV ? "development"

  (req, res) -> res.render 'test', options

exports.index = index