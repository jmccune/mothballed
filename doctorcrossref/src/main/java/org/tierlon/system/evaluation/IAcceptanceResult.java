package org.tierlon.system.evaluation;

import java.util.List;

/**
 * The result of a boolean test.  This is an enrichment of the straight boolean.
 * The motivation for a result like this are several, although not all reasons
 * are as important:
 * <ul>
 *   <li> Getting a human reason why--  it's annoying to get a simple 
 *   	  pass fail, and when something failed then expect the object
 *   	  (with a second call) to tell you what the problem was.  There
 *   	  are potential issues with transience (the reason changed, or
 *   	  what wasn't ok now is), efficiency (if you had to evaluate a
 *   	  complex state, you either have to do so again or have cached
 *   	  the error message, etc.   While other methods may work, this
 *   	  representation seems the most clean and proper <i> general </i>
 *   	  representation that may be used across any computer system. </li>
 *   <li> Ranking -- if you are parsing a document, validating a form, 
 *   	  or performing some other operation in parallel or effectively
 *   	  in parallel, current patterns tend to return just the first error.
 *        While this is sometimes
 *   	  the desired behavior, there are times where perhaps we would
 *   	  want the user to fix more important problems first.  Allowing
 *   	  us to rank the gravity of the "problem" (or "success") can
 *   	  enhance behavior. </li>
 *   <li> Finer grained logic.  Sometimes the system is sure something
 *   	  is wrong, or that everything is ok-- but sometimes it's not.
 *   	  If the user cares to capture it, this is how this works.
 *   </li>
 * </ul>
 * 
 * @author justinanddiana
 *
 */
public interface IAcceptanceResult {

	/** Note:  Convention is that indeterminate results are NOT acceptable.
	 * Therefore !isAcceptable includes both failure and indeterminate ranges.
	 * @return true iff the asNumber() representation is >=1 indicating 
	 * success.
	 */
	public boolean 		isAcceptable();
	
	public boolean      isIndeterminate();
	
	public boolean      isUnacceptable();
	
	
	/** Get a human readable reason/explanation of the result. */
	public String 		getReason();
	
	/** Failure is -1 or below.  success is 1 or above.  (-1,1) is 
	 * indeterminate, with 0 being completely indeterminate.  
	 * @return a number that represents the degree of failure or 
	 * or acceptability.
	 */
	public double		asNumber();
	
	/** Get any reasons that may support this conclusion. */
	public List<IAcceptanceResult> getSupportingInfo();
	
}
