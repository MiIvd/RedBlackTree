package exceptions;

/**
 * The file format exception is thrown when we are trying to read a file
 * but it is not in the format that you expect! 
 * 
 * This should be a checked exception, which forces users to wrap this in
 * a try/catch rather than a runtime exception.
 *  
 * Most of the work in the file format exception can be handled by the super class!
 *  
 * @author
 *
 */
public class FileFormatException extends Exception {
	public FileFormatException() {
		super();
	}
	public FileFormatException(String s) {
		super(s);
	}
}
