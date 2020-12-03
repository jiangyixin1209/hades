package top.jiangyixin.hades.common;

/**
 * @author jiangyixin
 */
public class HadesException extends RuntimeException {
	private String message;
	private int code;

	public HadesException(String message) {
		super(message);
		this.message = message;
	}

	public HadesException(String message, int code) {
		super(message);
		this.message = message;
		this.code = code;
	}

	public HadesException(String message, Throwable throwable) {
		super(message, throwable);
		this.message = message;
	}

	public HadesException(String message, int code, Throwable throwable) {
		super(message, throwable);
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
