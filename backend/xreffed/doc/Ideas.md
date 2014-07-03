#Catalog of ideas for this application

## Data Migration
Data migration will be a key feature.
To enable me to get the data up and running initially--
all fields will be text.

Later on, they will be moved to their own proper relational
data set. 

As an example, originally I will type in the author:
"David Bednar" or "David A Bednar" as the author of a quote.

Later on, there will be an entry for Author:


    Author:
    id: 1239491
    FirstName: David
    LastName: Bednar
    Middle Name/Initial:  A
    Description: An Apostle in the Church of Jesus Christ of Latter Day Saints
    Born: ...
    Died: ---
    ...And other details...
    

The local reference will still say:
"David Bednar"

However, a LINK will be provided to the full description--
separated by the pipe character. "|"


    Data Reference 1:
    id: 001
    referenceText: "This is quote #1"
    authors: [ "1239491|David Bednar"] 
    
    Data Reference 2:
    id: 043
    referenceText: "This is quote #2"
    authors: [ "1239491|David A Bednar"]
    
The hybrid representation keeps the information paired
It keeps the short representation that is sufficient for
initial display (e.g. in a tabular format).
However, the ID allows dynamic look ups (for hovering display
and other such uses.)


__This Pattern can & will be used with many of the fields.__

The pattern is simply:
    
    fieldValue =  "text" |  ("<id> + '|' + text") 
    
The dual value of the field allows us to do data migration
   as development progresses.  The id is optional.  Thus data
   can be displayed even before the "author" component is up.

It is assumed that the field key will indicate what the id is too.
   
   
