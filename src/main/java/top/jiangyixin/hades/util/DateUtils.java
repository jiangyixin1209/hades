package top.jiangyixin.hades.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/16 上午11:03
 */
public class DateUtils {
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	/**
	 * 时间格式化
	 * @param date      日期对象
	 * @param pattern   格式化模式
	 * @return          日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return null;
	}
}
