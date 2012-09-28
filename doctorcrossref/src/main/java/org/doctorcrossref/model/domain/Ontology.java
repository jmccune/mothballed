package org.doctorcrossref.model.domain;

/**
 * The &gt; symbol implies that the hierarchical designation on the left
 * is greater (e.g. the parent/ancestor) of the designated thing on the right.
 * @author justinanddiana
 *
 */
public class Ontology {

	final static public String BOOK_TYPE 	= "Entity>Publication>Book";  //Primary Definition
	final static public String BOOK2_TYPE 	= "Method>Organization>Subdivision";
	
	final static public String WEBPAGE_TYPE = "Entity>Publication>Web>Page";
	
	final static public String DATE_START = 
			"Specification>Time>Date>Start | " +
			"Method>Measure>Time>Instant";
	final static public String DATE_END   = 
			"Specification>Time>Date>End | " +
			"Method>Measure>Time>Instant";
	
	final static public String AUTHOR = "Entity>Person>Author";
	final static public String ORGANIZATION = "Entity>Organization";
	
	//ROOT ENTITY_TYPES
	final static public String[] ROOTS={
		"Entity",  /* Entity>Publication   WHAT
		              Entity>Person>Author WHO */
		
		"Method",  /* Method   HOW?
					  Method>Measure>Time>Span>Seconds
					  Method>Measure>Time>Instant>Milliseconds>Since1970
					  Method>Measure>Space>Distance>Meters
					  Method>Organization>Subdivision>Book
					*/
		              
		"Specification",  /* Specification>Spatial WHERE (to find something)
		 			    Specification>Time    WHEN  (to find something)
		 			 */
		
		"Reason"  /* WHY */
		
	};
}
