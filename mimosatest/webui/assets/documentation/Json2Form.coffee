# THIS IS NOT REALLY A COFFEESCRIPT FILE
# It's documentation for how I want to turn a JSON object
# into an automatic form.


exampleData:
  first_name: "John"
  last_name: "Doe"
  address: "12345|Thezen Way|#3|Nihao|TX|98765"
  age: 37
  sex: "M"
  customer_rating: 5.3
  reviews: [  
    {
      title: "This Does Not Look Good"
      description: "When you're out numbered 3 to one..."  
    },
    {
      title: "Check this out!"
      description: "You may wish to avoid this mistake I made..."  
    }
  ]
  
  
  # Can be passed with the object -- or inferred based on the type.
  #  (Validators obviously cannot be)
  _meta: {
    first_name:
      type : "string"
      range: "[0..35]"   #How many characters can this be?
              #Optionally: a function that accepts...
      validator: "function_name" # A name for a given validator
             #Optionally: [ "validator1name","validator2name","etc."]
      required: true
      label: "Given name"
  }
    
    


# MAPPINGS:
#
#  String     -->   INPUT FIELD   |  TEXT AREA | AUTOCOMPLETE FIELD
#  Number     -->   INPUT FIELD   |  SLIDER    |  SPINNER
#  "ENUM"     -->   COMBO BOX
#  Date       -->   CALENDAR WIDGET
#  Time       -->   TIME WIDGET
#  Array/Collection -->  TABLE  | COLLECTION_VIEW (?)
#
#
# CONVENTION:
#   *SINGLE FIELDS*
#      Span 2         Span 6
#      Field Label:   FieldControl
#
#   *COLLECTIONS*
#      Span 2
#      Collection Label:
#      Span 10
#      Table Column Headers
#      Table Columns  (per row/entry/item)
