package ex05문제발생에대비하기.ex505변수로원인노출.sub;

public class MalformedMessageException extends IllegalArgumentException {

	final String raw;
	
	public MalformedMessageException(String message, String raw) {
		super(String.format("%s in '%s'", message, raw));
		this.raw = raw;
	}
	
	public MalformedMessageException(String message, String raw, Throwable cause) {
		super(String.format("%s in '%s'", message, raw), cause);
		this.raw = raw;
	}
}
