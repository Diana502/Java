package diana.maven.project.common;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Suna
 * @since 2020/4/15 16:59
 */
public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
	}

	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static R error(String message) {
		return error(500, message);
	}

	public static R error(int code, String message) {
		R r = new R();
		r.put("code", code);
		r.put("message", message);
		return r;
	}

	public static R ok(String message) {
		R r = new R();
		r.put("message", message);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok(int total,Object obj) {
		R r = new R();
		r.put("code",0);
		r.put("total",total);
		r.put("data",obj);
		return r;
	}
	public static R ok(Object obj) {
		R r = new R();
		r.put("code",0);
		r.put("data",obj);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
