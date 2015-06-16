package org.universaldataengine.common.model;

import java.util.Map;


/**
 * The Open Model is an extension and convention that extends
 * a standard multi-level key-value Map as used by Jackson
 * (and other libraries?) to allow us to define arbitrary
 * objects in an open and modifiable way for the purposes
 * of transformation and data processing.
 * 
 * At its core, this object is just a map of keys and
 * values (the values themselves can be complex objects
 * such as another OpenModel, Map, Array, String, Object,
 * etc.)   
 * 
 * NOTE: It is generally preferable to represent all values
 * as well known data types (integer, float, string, etc.)
 * and only List,Map as the complex types,
 * and not to use other complex objects (e.g. 
 * MyDataObject, My3DModel, etc.) themselves as values.
 * 
 * The goal is to allow metadata to be associated
 * with each key that provides more information to other
 * users of the data.  This meta-data for a particular key
 * is defined using a reserved double underscore (
 * e.g. "__") prefix as exemplified
 * below.   Meta data  for the whole object (at a given
 * level of the map) may be represented with the "__metadata__"
 * key which is also a reserved key.
 * 
 * Plain Object:
 * 
 * {   "name" : "Sunny's Flaming Burgers",
 *     "location" : [12.345,67.890],
 *     "cuisine" : ["american"],
 *     "tags" : ["spicy","hotspot"],
 *     "stars": 4
 * }
 * 
 * Enriched/ w-metadata:
 * { 
 * 	   "__metadata__": { 
 * 			"specificationUrl":"http://abc.example.com/reviews.xml",
 * 			"entity-type" : "restaurant"
 *     } 	
 * 	   "name" : "Sunny's Flaming Burgers",
 *     "location" : [12.345,67.890],
 *     "__location.dimension":"dimension.spatial.coordinate.2d.latlon.array",
 *     "__location.units":"units.spatial.geospatial.degrees.decimal",
 *     "cuisine" : ["american"],
 *     "tags" : ["spicy","hotspot"],
 *     "stars" : 4,
 *     "__stars.dimension":"dimension.rating.review.personal.star",
 *     "__stars.range.min": 1,
 *     "__stars.range.max": 5,
 * }
 * 
 * The beauty of this approach is that the meta-data is not
 * usually required for processors that know the data they
 * operate on.   For general case processors, that can't make
 * assumptions about the data.  
 * 
 * And separate/independent steps can add
 * the enriched information in as necessary.
 * 
 * 
 * @author justinanddiana
 *
 */
public interface OpenModel extends Map<String,Object>{

}
