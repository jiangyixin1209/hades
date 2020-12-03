package top.jiangyixin.hades.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangyixin
 */
public class Result extends HashMap<String, Object> {

	public Result() {
		put("code", 0);
	}

	public static Result ok(int code, String message) {
		Result r = new Result();
		r.put("code", code);
		r.put("message", message);
		return r;
	}

	public static Result ok(Map<String, Object> data) {
		Result r = new Result();
		r.putAll(data);
		return r;
	}

	public static Result ok() {
		return new Result();
	}

	public static Result error(int code, String message) {
		Result r = new Result();
		r.put("code", code);
		r.put("message", message);
		return r;
	}

	public static Result error() {
		return error(500, "未知异常");
	}

	public static Result error(String message) {
		return error(500, message);
	}

	public Result add(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
