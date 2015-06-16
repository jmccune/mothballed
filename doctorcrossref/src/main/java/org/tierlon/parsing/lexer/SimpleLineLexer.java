package org.tierlon.parsing.lexer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/** 
 * Return usable lines one by one skipping
 * comments and whitespace.
 * 
 * @author justinanddiana
 */
public class SimpleLineLexer {
	
	private static String COMMENT_REGEX = "\\s*#.*";
	private BufferedReader inputReader;
	private boolean hasNextDataLine;
	private String dataLine="";
	
	// ==============================================================
	// Constructor
	// ==============================================================
	public SimpleLineLexer(InputStream input) throws IOException {
		inputReader
    		= new BufferedReader(
    				new InputStreamReader(input));
		//Assume true:
		hasNextDataLine =true;
		scanForNextDataLine(); 
	}
	
	public SimpleLineLexer(String string) throws IOException {
		this(new ByteArrayInputStream(string.getBytes()));
	}

	// ==============================================================
	// Public Methods
	// ==============================================================
	public boolean hasNextLine() {
		return hasNextDataLine;
	}
	
	public String getLineData() throws IOException {
		String result = dataLine;
		scanForNextDataLine();
		return result;
	}
	
	// ==============================================================
	// Private Methods
	// ==============================================================
	private void scanForNextDataLine() throws IOException {
		if (!hasNextDataLine)
			return;
		
		boolean done=false;
		String lineRead="";
		while (!done) {
			lineRead = inputReader.readLine();
			if (lineRead==null) {
				hasNextDataLine=false;
				dataLine="";
				return;
			}
			
			//Skip comment lines and blanklines.
			if (lineRead.matches(COMMENT_REGEX))
				//Keep going...
				continue;
			if (lineRead.trim().isEmpty())
				continue;
			done=true;
		}
		dataLine = lineRead;
	}
}