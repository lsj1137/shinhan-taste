package shinhantaste.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// util.date->sql.date
	public static java.sql.Date convertSQLDate(Date dt) {
		java.sql.Date dt2 = new java.sql.Date(dt.getTime());
		return dt2;

	}

	public static Date convertUtilDate(java.sql.Date dt) {
		Date dt2 = new Date(dt.getTime());
		return dt2;

	}

	// sql에 저장하기 위해 String을 sqlDate로 바꿈
	public static java.sql.Date convertSQLDate(String datestr) {
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		java.sql.Date dt3 = null;

		// Exception: 1)unCheckedException, 2)CheckedException: 컴파일시 반드시 처리
		try {
			Date dt2 = sdf.parse(datestr);
			dt3 = new java.sql.Date(dt2.getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt3;
	}
}
