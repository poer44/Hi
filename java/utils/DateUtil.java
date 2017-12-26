package utils;

/**
 * Copy by Sam on 17/10/31.
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 * 提供对日期时间操作的几个日常方法.
 */
public final class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class.getName());
	private static final long ONE_DAY = 24 * 60 * 60 * 1000;
	private static final long ONE_MINUTE = 60 * 1000;
	private static String _datePattern = "yyyyMMdd";
	private static String datePattern = "yyyy-MM-dd";
	private static String _timePattern = "HHmmss";
	private static String timePattern = "HH:mm:ss";
	private static SimpleDateFormat _dateFormat = new SimpleDateFormat(_datePattern);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	private static SimpleDateFormat _datetimeFormat = new SimpleDateFormat(_timePattern);
	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);

	public static String _toDateString(Date date) {
		if (date == null) {
			return null;
		}
		return _dateFormat.format(date);
	}

	public static String _toDatetimeString(Date date) {
		if (date == null) {
			return null;
		}
		return _datetimeFormat.format(date);
	}

	public static Date afterDay(int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, n);
		Date date = c.getTime();
		return date;
	}

	// 当前日期前几天或者后几天的日�?
	public static String afterNDay(int n) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(afterDay(n));
	}

	/**
	 * 计算两个日期间相隔的周数
	 * 
	 * @param startDate
	 *            �?��日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static int computeWeek(Date startDate, Date endDate) {
		int weeks = 0;
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		while (beginCalendar.before(endCalendar)) {
			// 如果�?��日期和结束日期在同年、同月且当前月的同一周时结束循环
			if (beginCalendar.get(Calendar.YEAR) == endCalendar.get(Calendar.YEAR) && beginCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)
					&& beginCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == endCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
				break;
			} else {
				beginCalendar.add(Calendar.DAY_OF_YEAR, 7);
				weeks += 1;
			}
		}

		return weeks;
	}

	/**
	 * 获取下一月的第一�?
	 * 
	 * @param date
	 * @param afterNum
	 * @return
	 */
	public static String getAfterMonthFirst(String date, int afterNum) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(toDate(date));
		cal.add(Calendar.MONTH, afterNum);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置�?�?当前日期既为本月第一�?
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 获取下一季度的第�?��
	 * 
	 * @param date
	 * @param afterNum
	 * @return
	 */
	public static String getAfterQuarterFirst(String date, int afterNum) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(toDate(date));
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置�?�?当前日期既为本月第一�?
		if (currentMonth >= 1 && currentMonth <= 3)
			cal.set(Calendar.MONTH, 0);
		else if (currentMonth >= 4 && currentMonth <= 6)
			cal.set(Calendar.MONTH, 3);
		else if (currentMonth >= 7 && currentMonth <= 9)
			cal.set(Calendar.MONTH, 6);
		else if (currentMonth >= 10 && currentMonth <= 12)
			cal.set(Calendar.MONTH, 9);
		cal.add(Calendar.MONTH, afterNum * 3);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 获取下一周的第一�?
	 * 
	 * @return
	 */
	public static String getAfterWeekFirst() {
		return getMonday(afterNDay(7));
	}

	/**
	 * 获取系统当前时间，待后期可扩展到取数据库时间
	 * 
	 * @return 系统当前时间
	 */
	public static String getCurrDate() {
		return toDateString(new Date());

	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss转化为yyyy-MM-dd
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getCurrDate(String dateTime) {
		return toDateString(DateUtil.toDateTime(dateTime));
	}

	public static String getCurrDateNumber() {
		return _toDateString(new Date());

	}

	/**
	 * 返回当前系统时间
	 * @return
	 */
	public static String getCurrDateTime() {
		return toDatetimeString(new Date());

	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getCurrDay() {
		return new Date();
	}

	public static String getCurrTimeNumber() {
		return _toDatetimeString(new Date());
	}

	/**
	 * 当前星期�?
	 * @return
	 */
	public static String getCurrWeek() {
		return getWeekOfDate(new Date());
	}

	/**
	 * 当前星期几索引号
	 * @return
	 */
	public static int getCurrWeekNo() {
		return getWeekNo(new Date());
	}

	/**
	 * 返回当前日期:yyyy-MM-dd
	 * @return
	 */
	public static String getCurrYearMonthDay() {
		return getCurrYearMonthDay(new Date());
	}
	public static String getCurrYearMonthDay(Date date) {
		return dateFormat.format(date);
	}
	/**
	 * 返回当前日期后几天的日期：yyyy-MM-dd
	 * @param n 后几�?
	 * @return
	 */
	public static String getAfterYearMonthDay(int n) {
		return getCurrYearMonthDay(afterDay(n));
	}
	
	/**
	 * 计算两天的相差天�?不足�?��按一天算.
	 * 
	 * @param stopDate
	 *            结束日期.
	 * @param startDate
	 *            �?��日期.
	 * @return 相差天数 = 结束日期 - �?��日期.
	 */
	public static int getDateDiff(String stopDate, String startDate) {
		long t2 = toDate(stopDate).getTime();
		long t1 = toDate(startDate).getTime();

		int diff = (int) ((t2 - t1) / ONE_DAY); // 相差天数
		// 如有剩余时间，不足一天算�?��
		diff += (t2 > (t1 + diff * ONE_DAY) ? 1 : 0);
		return diff;
	}

	/**
	 * 只获取日期部�?获取日期时间型的日期部分.
	 * 
	 * @param currDate
	 *            日期[时间]型的字串.
	 * @return 日期部分的字�?
	 */
	public static String getDatePart(String currDate) {
		if (currDate != null && currDate.length() > 10) {
			return currDate.substring(0, 10);
		}

		return currDate;
	}

	public static int getDay() {
		return getDay(new Date());
	}

	/**
	 * 返回日期，为1－－ �?31�?
	 */
	public static int getDay(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得周五的日�?
	 */
	public static String getFriday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 获得将来的日�?如果 days > 0,返回将来的时�?否则，返回过去的时间.
	 * 
	 * @param currDate
	 *            现在日期.
	 * @param days
	 *            经过的天�?
	 * @return 经过days天后的日�?
	 */
	public static Date getFutureDate(Date currDate, int days) {
		long l = currDate.getTime();
		long l1 = days * ONE_DAY;

		l += l1;
		return new Date(l);
	}

	/**
	 * 获得将来的日�?如果timeDiffInMillis > 0,返回将来的时�?否则，返回过去的时间
	 * 
	 * @param currDate
	 *            现在日期.
	 * @param timeDiffInMillis
	 *            毫秒级的时间�?
	 * @return 经过 timeDiffInMillis 毫秒后的日期.
	 */
	public static Date getFutureDate(Date currDate, long timeDiffInMillis) {
		long l = currDate.getTime();

		l += timeDiffInMillis;
		return new Date(l);
	}

	/**
	 * 获得将来的日�?如果 days > 0,返回将来的时�?否则，返回过去的时间.
	 * 
	 * @param currDate
	 *            现在日期,字符型如2005-05-05 [14:32:10].
	 * @param days
	 *            经过的天�?
	 * @return 经过days天后的日�?
	 */
	public static Date getFutureDate(String currDate, int days) {
		return getFutureDate(toDate(currDate), days);
	}

	/**
	 * 获得将来的日�?如果timeDiffInMillis > 0,返回将来的时�?否则，返回过去的时间.
	 * 
	 * @param currDate
	 *            现在日期.
	 * @param timeDiffInMillis
	 *            毫秒级的时间�?
	 * @return 经过 timeDiffInMillis 毫秒后的日期.
	 */
	public static Date getFutureDate(String currDate, long timeDiffInMillis) {
		return getFutureDate(toDate(currDate), timeDiffInMillis);
	}

	/**
	 * 计算两天的相差分�?不足�?��钟按�?��钟算.
	 * 
	 * @param stopDate
	 *            结束日期.
	 * @param startDate
	 *            �?��日期.
	 * @return 相差分钟�?= 结束日期 - �?��日期.
	 */
	public static int getMinutesDiff(String stopDate, String startDate) {
		long t2 = toDate(stopDate).getTime();
		long t1 = toDate(startDate).getTime();

		int diff = (int) ((t2 - t1) / ONE_MINUTE); // 相差分钟�?
		// 如有剩余时间，不足一天算�?��
		diff += (t2 > (t1 + diff * ONE_MINUTE) ? 1 : 0);
		return diff;
	}
	
	/**
	 * 计算当前日期和传入日期之间相差的秒数
	 * @param startDate
	 * @return
	 */
	public static  int calLastedTime(Date overDate) {
		  long a = new Date().getTime();
		  long b = overDate.getTime();
		  int c = (int)((b - a) / 1000);
		  return c;
	}

	/**
	 * 获取周一的日�?
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 获取周一的日�?
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonday(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(date));
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static int getMonth() {
		return getMonth(new Date());
	}

	/**
	 * 返回月份，为1－－ �?12�?
	 */
	public static int getMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取得当前的季�?
	 * 
	 * @return
	 */
	public static final int getQuarter() {
		return getQuarter(getMonth());
	}

	/**
	 * 取得季度
	 * 
	 * @param d
	 *            日期类型
	 * @return
	 */
	public static final int getQuarter(Date d) {
		return getQuarter(getMonth(d));
	}

	/**
	 * 传�?月份,取得季度
	 * 
	 * @param num
	 * @return
	 */
	public static final int getQuarter(int num) {
		num = num % 3 == 0 ? num / 3 : (num / 3 + 1);
		return num % 4 == 0 ? 4 : num % 4;

	}

	/**
	 * 根据参数，获取相对日�?
	 * 
	 * @param date
	 * @param flag
	 * @param intervals
	 * @return
	 */
	public static Date getRelativeDate(Date date, char flag, int intervals) {
		Date currDate = null;
		if (date != null) {
			Calendar newDate;
			(newDate = Calendar.getInstance()).setTime(date);
			switch (flag) {
			case 'y':
				newDate.add(Calendar.YEAR, intervals);
				break;
			case 'M':
				newDate.add(Calendar.MONTH, intervals);
				break;
			case 'd':
				newDate.add(Calendar.DATE, intervals);
				break;
			case 'w':
				newDate.add(Calendar.WEEK_OF_YEAR, intervals);
				break;
			case 'h':
				newDate.add(Calendar.HOUR, intervals);
				break;
			case 'm':
				newDate.add(Calendar.MINUTE, intervals);
				break;
			case 's':
				newDate.add(Calendar.SECOND, intervals);
				break;
			case 'S':
				newDate.add(Calendar.MILLISECOND, intervals);
			}
			currDate = newDate.getTime();
		}
		return currDate;
	}

	/**
	 * 按月获取周序�?
	 * 
	 * @param currDate
	 * @return
	 */
	public static int getSeqWeekByMonth(Date currDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(currDate);
		c.setFirstDayOfWeek(Calendar.MONDAY);

		return c.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 按年获取周序�?
	 * 
	 * @param currDate
	 * @return
	 */
	public static int getSeqWeekByYear(Date currDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(currDate);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int weekNo = c.get(Calendar.WEEK_OF_YEAR);

		Calendar lastDate = Calendar.getInstance();

		if (weekNo == 1) {
			// 获取周五时间
			lastDate.setTime(DateUtil.toDate(getFriday(c.getTime())));
			if (c.get(Calendar.YEAR) != lastDate.get(Calendar.YEAR)) {
				lastDate.setTime(DateUtil.toDate(getMonday(c.getTime())));
				lastDate.add(Calendar.DATE, -1);
				lastDate.setFirstDayOfWeek(Calendar.MONDAY);
				weekNo = lastDate.get(Calendar.WEEK_OF_YEAR) + 1;
			}
		}
		return weekNo;
	}

	/**
	 * 获取周日
	 * 
	 * @param date
	 * @param afterNum
	 * @return
	 */
	public static String getSundayOfWeek(String date) {
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(toDate(date));
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 当前几天后是周几
	 * @param n
	 * @return
	 */
	public static String getWeekAfterDay(int n) {
		return getWeekOfDate(afterDay(n));
	}

	/**
	 * 获取指定日期是星期几索引�?
	 * @param dt 日期类型
	 * @return "星期�?�?, "星期�?:1, "星期�?:2, "星期�?�?, "星期�?�?, "星期�?�?, "星期�?�?
	 */
	public static int getWeekNo(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 获取指定日期是星期几索引�?
	 * @param dt yyyy-MM-dd格式的字符串
	 * @return "星期�?�?, "星期�?:1, "星期�?:2, "星期�?�?, "星期�?�?, "星期�?�?, "星期�?�?
	 */
	public static int getWeekNo(String dt) {
		return getWeekNo(toDate(dt));
	}
	
	/**
	 * 当前几天后是周几索引�?
	 * @param n
	 * @return
	 */
	public static int getWeekNoAfterDay(int n) {
		return getWeekNo(afterDay(n));
	}
	
	

	/**
	 * 获取指定日期是星期几 
	 * @param dt 日期类型
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return weekDays[getWeekNo(dt)];
	}

	/**
	 * 获取指定日期是星期几
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(String dt) {
		return getWeekOfDate(toDate(dt));
	}

	public static int getYear() {
		return getYear(new Date());
	}

	/**
	 * 返回年份，如2004.
	 */
	public static int getYear(Date d) {

		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 当月�?���?��
	 * @return "2014-01-31 23:59:59"
	 */
	public static String getYearMonthEndDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		Date endTime = cal.getTime();
		String endTime1 = dateFormat.format(endTime) + " 23:59:59";
		return endTime1;
	}

	/**
	 * 计算某年某月的结束日�?
	 * 
	 * @return interger
	 */
	public static String getYearMonthEndDay(int yearNum, int monthNum) {

		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(monthNum);
		String tempDay = "31";
		if (tempMonth.equals("1") || tempMonth.equals("3") || tempMonth.equals("5") || tempMonth.equals("7") || tempMonth.equals("8") || tempMonth.equals("10") || tempMonth.equals("12")) {
			tempDay = "31";
		}
		if (tempMonth.equals("4") || tempMonth.equals("6") || tempMonth.equals("9") || tempMonth.equals("11")) {
			tempDay = "30";
		}
		if (tempMonth.equals("2")) {
			if (isLeapYear(yearNum)) {
				tempDay = "29";
			} else {
				tempDay = "28";
			}
		}

		String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
		return tempDate;

	}

	/**
	 * 当月第一�?
	 * 
	 * @return "2014-01-01 00:00:00"
	 */
	public static String getYearMonthFirstDay() {
		// 当前月的第一�?
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		String beginTime1 = dateFormat.format(beginTime) + " 00:00:00";
		return beginTime1;
	}

	/**
	 * 计算某年某月的开始日�?
	 * 
	 * @return interger
	 */
	public static String getYearMonthFirstDay(int yearNum, int monthNum) {

		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(monthNum);
		String tempDay = "1";
		String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
		return setDateFormat(tempDate, "yyyy-MM-dd");
	}

	/**
	 * 计算某年某周的开始日�?
	 * 
	 * @return interger
	 */
	public static String getYearWeekFirstDay(int yearNum, int weekNum) {

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
		return setDateFormat(tempDate, "yyyy-MM-dd");
	}

	/**
	 * �?��是否在核算期�?
	 * 
	 * @param currDate
	 *            当前时间.
	 * @param dateRange
	 *            核算期日期范�?
	 * @return 是否在核算期�?
	 */
	public static boolean isDateInRange(String currDate, String[] dateRange) {
		if (currDate == null || dateRange == null || dateRange.length < 2) {
			throw new IllegalArgumentException("传入参数非法");
		}

		currDate = getDatePart(currDate);
		return (currDate.compareTo(dateRange[0]) >= 0 && currDate.compareTo(dateRange[1]) <= 0);
	}

	/**
	 * 判断某年是否为闰�?
	 * 
	 * @return boolean
	 */
	public static boolean isLeapYear(int yearNum) {
		boolean isLeep = false;
		/** 判断是否为闰年，赋�?给一标识符flag */
		if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
			isLeep = true;
		} else if (yearNum % 400 == 0) {
			isLeep = true;
		} else {
			isLeep = false;
		}
		return isLeep;
	}

	/**
	 * 判断是否为同�?��
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean isOneDay(String beginTime, String endTime) {
		try {
			Date beginDate = DateUtil.toDateTime(beginTime);
			Date endDate = DateUtil.toDateTime(endTime);
			String bymd = DateUtil.getYear(beginDate) + "-" + DateUtil.getMonth(beginDate) + "-" + DateUtil.getDay(beginDate);
			String eymd = DateUtil.getYear(endDate) + "-" + DateUtil.getMonth(endDate) + "-" + DateUtil.getDay(endDate);
			if (bymd.equals(eymd))
				return true;
			else
				return false;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 判断两个日期是否在同�?��
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setFirstDayOfWeek(Calendar.MONDAY);
		cal2.setFirstDayOfWeek(Calendar.MONDAY);
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的�?���?��横跨来年第一周的话则�?���?��即算做来年的第一�?
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
	
	/**
	 * @see 取得指定时间的给定格�?
	 * @return String
	 */
	public static String setDateFormat(String myDate, String strFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			String sDate = sdf.format(sdf.parse(myDate));
			return sDate;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将字符串转换为日期对象，字符串必须符合yyyy-MM-dd的格�?
	 * @param s 要转化的字符�?
	 * @return 字符串转换成的日�?如字符串为NULL或空�?返回NULL.
	 */
	public static Date toDate(String s) {
		s = StringUtil.trim(s);
		if (s.length() < 1) {
			return null;
		}
		try {
			if (s.length() <= 10) {
				return dateFormat.parse(s);
			}
			return toDate(Timestamp.valueOf(s));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将Timestamp转换为日�?
	 * 
	 * @param timestamp
	 *            时间�?
	 * @return 日期对象.如时间戳为NULL,返回NULL.
	 */
	public static Date toDate(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return new Date(timestamp.getTime());
	}

	/**
	 * 将日期对象转换为字符串，格式为yyyy-MM-dd.
	 * 
	 * @param date
	 *            日期.
	 * @return 日期对应的日期字符串.
	 */
	public static String toDateString(Date date) {
		if (date == null) {
			return null;
		}
		return dateFormat.format(date);
	}
	
	/**
	 * 将时间戳对象转化成字符串.
	 * 
	 * @param t
	 *            时间戳对�?
	 * @return 时间戳对应的字符�?如时间戳对象为NULL,返回NULL.
	 */
	public static String toDateString(Timestamp t) {
		if (t == null) {
			return null;
		}
		return toDateString(toDate(t));
	}
	/**
	 * 将字符串转换为日期对象，字符串必须符�?yyyy-MM-dd HH:mm:ss)的格�?
	 * 
	 * @param s
	 * @return
	 */
	public static Date toDateTime(String s) {
		s = StringUtil.trim(s);
		if (s.length() < 19) {
			return null;
		}
		try {
			return toDate(Timestamp.valueOf(s));
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 将日期对象转换为字符串，转换后的格式为yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param date
	 *            要转换的日期对象.
	 * @return 字符�?格式为yyyy-MM-dd HH:mm:ss.
	 */
	public static String toDatetimeString(Date date) {
		if (date == null) {
			return null;
		}
		return datetimeFormat.format(date);
	}

	/**
	 * 将Timestamp转换为日期时间字符串.
	 * 
	 * @param t
	 *            时间戳对�?
	 * @return Timestamp对应的日期时间字符串.如时间戳对象为NULL,返回NULL.
	 */
	public static String toDatetimeString(Timestamp t) {
		if (t == null) {
			return null;
		}
		return toDatetimeString(toDate(t));
	}
	
	/**
	 * 将日期转换为Timestamp.
	 * @param date 日期.
	 * @return 时间�?如日期为NULL,返回NULL.
	 */
	public static Timestamp toTimestamp(Date date) {
		if (date == null) {
			return null;
		}

		return new Timestamp(date.getTime());
	}
	/**
	 * 将日期字符串转换为Timestamp对象.
	 * 
	 * @param s
	 *            日期字符�?
	 * @return 日期时间字符串对应的Timestamp.如字符串对象为NULL,返回NULL.
	 */

	public static Timestamp toTimestamp(String s) {
		return toTimestamp(toDate(s));
	}
    /**
     * 当前系统时间 + 10位随机数,作为�?��唯一的订单编�?
     * @return 以yyyyMMdd为格式的当前系统时间 + 10位随机数
     */
    public static String getOrderNum() {
        return getCurrDateNumber() + StringUtil.buildRandom(5) + "" + StringUtil.buildRandom(5);
    }
	/**
	 * 计算某年某周的结束日�?
	 * 
	 * @return interger
	 */
	public String getYearWeekEndDay(int yearNum, int weekNum) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
		return setDateFormat(tempDate, "yyyy-MM-dd");
	}

}
