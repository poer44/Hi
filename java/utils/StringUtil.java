package utils;

/**
 * Copy by Sam on 17/10/31.
 */

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * 字串公用函数�?
 */

public final class StringUtil {

	/**
	 * 判断字符串是否不为空
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	/**
	 * 判断字符串是否为�?
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return string == null ? true : string.trim().length() == 0;
	}
	
	/**
	 * 判断字符串是否为空，不去除空�?
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmptyExceptSpace(String string) {
		return string == null ? true : string.length() == 0;
	}

	/**
	 * 替换�?��字串.
	 * 
	 * @param oldStr
	 *            要进行替换的原字�?
	 * @param findStr
	 *            查找的字�?
	 * @param replStr
	 *            替换为的字串
	 * @return 替换后的字串
	 */
	public static String replace(String oldStr, String findStr, String replStr) {
		return repl(oldStr, findStr, replStr, 0);
	}

	/**
	 * 替换字串,指定替换次数.
	 * 
	 * @param oldStr
	 *            要进行替换的原字�?
	 * @param findStr
	 *            查找的字�?
	 * @param replStr
	 *            替换为的字串
	 * @param times
	 *            次数
	 * @return 替换后的字串
	 */
	public static String replace(String oldStr, String findStr, String replStr, int times) {
		return repl(oldStr, findStr, replStr, times);
	}

	/** �?��是否�?��替换 */
	private static boolean isValid(String oldStr, String findStr, String replStr) {
		if (oldStr == null || oldStr.length() < 1 || findStr == null || findStr.length() < 1 || replStr == null
				|| findStr.equals(replStr))
			return false;

		return true;
	}

	/**
	 * 替换实现.
	 * 
	 * @see replace(String oldStr,String findStr,String replStr,int times)
	 * @see replace(String oldStr,String findStr,String replStr)
	 */
	private static String repl(String oldStr, String findStr, String replStr, int times) {
		if (!isValid(oldStr, findStr, replStr))
			return oldStr;

		StringBuffer strBuff = new StringBuffer();

		if (times < 1) { // 替换�?��
			if (findStr.length() == 1 && replStr.length() == 1) { // 单字符替�?
				return oldStr.replace(findStr.charAt(0), replStr.charAt(0));
			} else { // 多字符替�?
				for (int i = 0, len = oldStr.length(); i < len;) {
					int j = oldStr.indexOf(findStr, i);

					if (j >= 0) {// 找到要替换的字串
						strBuff = strBuff.append(oldStr.substring(i, j));
						strBuff = strBuff.append(replStr);
						i = j + findStr.length();
					} else {// 找不到要替换的字�?
						strBuff = strBuff.append(oldStr.substring(i));
						break;
					}
				}
				return new String(strBuff);
			}
		} else { // 替换指定次数
			int i = 0;
			int len = oldStr.length();

			for (int k = 0; i < len && k < times;) {
				int j = oldStr.indexOf(findStr, i);

				if (j >= 0) {// 找到要替换的字串
					strBuff = strBuff.append(oldStr.substring(i, j));
					strBuff = strBuff.append(replStr);
					i = j + findStr.length();
					k++;
				} else {// 找不到要替换的字�?
					strBuff = strBuff.append(oldStr.substring(i));
					i = len;
				}
			}

			if (i < len) { // 完成替换次数，但串中还有可替换的字串
				strBuff = strBuff.append(oldStr.substring(i));
			}

			return new String(strBuff);
		}
	}

	/**
	 * 从原字串中删除从�?��字串到结束字串之间的字串,匹配指定次数.�?* 比如<br>
	 * <code>
	 *    String s = "abcd";
	 *    String t = deleteString("abcd","b","c",1);
	 *    t.equals("ad") (true)
	 * </code> </p>
	 * 
	 * @param oldStr
	 *            原字�?
	 * @param beginStr
	 *            �?��字串.
	 * @param endStr
	 *            终止字串.
	 * @param times
	 *            删除指定次数,如小�?,立即返回;否则�?��匹配到指定次数为�?
	 * @return 删除后的字串.
	 */
	public static String deleteString(String oldStr, String beginStr, String endStr, int times) {
		if (times < 1)
			return oldStr;

		if (!isValid(oldStr))
			return oldStr;

		String retStr = oldStr;
		String tmpStr = delStr(oldStr, beginStr, endStr);
		while (times-- > 0 && !(retStr.equals(tmpStr))) {
			retStr = tmpStr;
			tmpStr = delStr(retStr, beginStr, endStr);
		}
		return retStr;
	}

	/**
	 * 从原字串中删除从�?��字串到结束字串之间的字串,�?��匹配到不可再匹配为止.�?* 比如<br>
	 * <code>
	 *    String s = "abcd";
	 *    String t = deleteString("abcdbcd","b","c");
	 *    t.equals("add") (true)
	 * </code> </p>
	 * 
	 * @param oldStr
	 *            原字�?
	 * @param beginStr
	 *            �?��字串.
	 * @param endStr
	 *            终止字串.
	 * @return 删除后的字串.
	 */
	public static String deleteString(String oldStr, String beginStr, String endStr) {
		if (!isValid(oldStr))
			return oldStr;

		String retStr = oldStr;
		String tmpStr = delStr(oldStr, beginStr, endStr);
		while (!(retStr.equals(tmpStr))) {
			retStr = tmpStr;
			tmpStr = delStr(retStr, beginStr, endStr);
		}
		return retStr;
	}

	/**
	 * 获得不含路径的文件名.
	 * 
	 * @param fileName
	 *            文件�?可能含，也可能不含路�?为空/空串返回空串
	 * @return 不含路径的文件名.
	 */
	public static String getFileNameNoPath(String fileName) {
		fileName = trim(fileName);
		if (fileName.length() < 1)
			return fileName;

		String tmpStr = fileName;
		int idx1 = fileName.lastIndexOf("/");
		int idx2 = fileName.lastIndexOf("\\");
		idx1 = idx1 >= idx2 ? idx1 : idx2;
		tmpStr = fileName.substring(idx1 + 1);
		return tmpStr;
	}

	/**
	 * �?��是否空串或null.
	 */
	public static boolean isValid(String str) {
		if (str == null || str.trim().length() < 1)
			return false;
		return true;
	}

	/**
	 * 删除字串的实�?
	 */
	private static String delStr(String oldStr, String beginStr, String endStr) {

		if (oldStr == null || oldStr.length() < 1 || beginStr == null || beginStr.length() < 1 || endStr == null
				|| endStr.length() < 1)
			return oldStr;

		String retStr = oldStr;
		int i, j, k;
		i = oldStr.indexOf(beginStr);// find beginStr
		if (i >= 0) {
			j = i + beginStr.length();
			if (j < oldStr.length()) {
				k = oldStr.indexOf(endStr, j);// find endStr
				if (k >= 0) {
					k += endStr.length();
					retStr = oldStr.substring(0, i) + oldStr.substring(k);
				}
			}
		}

		return retStr;
	}

	/**
	 * 去掉字串两边空格.
	 * 
	 * @param s
	 *            原字�?如为null,返回空串.
	 * @return 去掉两边空格的字�?
	 */
	public static String trim(String s) {
		return s == null ? "" : s.trim();
	}

	/**
	 * 进行二维数组某一列字符串的替�? 使用示例�?String[][] string= new String[][]{{"string",
	 * "cying"},{"ss-strstd", "string"}, {"dir-str", ""},{"teacher",""}};
	 * String[][] newStr = ReplaceString.replace(str, 0, "str", "cry");
	 * 你将会得到这样的结果数组�?newStr = {{"crying", "cying"},{"ss-crystd", "string"},
	 * {"dir-cry", ""},{"teacher",""}};
	 * 
	 * @param str
	 *            要进行替换的字符�?
	 * @param columnIndex
	 *            要进行替换的字符串所在的列数 columnIndex�?�?��
	 * @param oldString
	 *            原字符串
	 * @param newString
	 *            新字符串
	 * @return 新的二维数组
	 * @author 张岭
	 */
	public static String[][] replace(String[][] str, int columnIndex, String oldString, String newString) {
		if (str == null)// 二位数组为空，返回原来的二位数组;
			return str;

		if (str.length < 1)// 数组长度小于1，返回原来的二位数组;
			return str;

		if (str[0].length < columnIndex + 1)// 数组的列数小于index
											// +
											// 1,返回原来的二位数�?
			return str;

		for (int i = 0; i < str.length; i++) {
			String replacedString = str[i][columnIndex];

			int replacedIndex = replacedString.indexOf(oldString) + 1;

			if (replacedString.indexOf(oldString) == -1)
				continue;

			String first = replacedString.substring(0, replacedIndex - 1);
			String middle = newString;
			String end = replacedString.substring(replacedIndex - 1 + oldString.length());

			String newStrings = first + middle + end;

			str[i][columnIndex] = newStrings;
		}

		return str;
	}

	/**
	 * 根据传入的包名，获得相对的文件路�? 比如<br>
	 * <code>
	 *    String s = getRelativeDir("kd.util");
	 *    s.equals("kd/util")
	 * </code> </p>
	 * 
	 * @param packageName
	 *            包名,packageName为null或一空串时，返回null
	 * @return String 与包名对应的相对路径
	 */
	public static String getRelativeDir(String packageName) {
		packageName = packageName == null ? "" : packageName.trim();

		if (packageName.length() < 1)
			return null;

		if (packageName.indexOf(".") >= 0) {
			return replace(packageName, ".", "/");
		} else
			return packageName;
	}

	/**
	 * 将字符串的第�?��字母变大�?
	 * 
	 * @param s
	 *            要转变的字串
	 */
	public static String firstUpperCase(String s) {
		if (s == null || s.length() < 1)
			return s;

		String first = s.substring(0, 1);
		String other = s.substring(1);
		s = first.toUpperCase() + other;
		return s;
	}

	/**
	 * 将字符串的第�?��字母变大�?其余是小�?
	 * 
	 * @param s
	 *            要转变的字串
	 * @return 将字符串的第�?��字母变大�?其余是小�?
	 */
	public static String firstUpperCaseOnly(String s) {
		if (s == null || s.length() < 1)
			return s;

		return firstUpperCase(s.toLowerCase());
	}

	/**
	 * 将字符串的第�?��字母变小�?
	 * 
	 * @param s
	 *            要转变的字串
	 */
	public static String firstLowerCase(String s) {
		if (s != null && s.length() > 0) {
			String first = s.substring(0, 1);
			String other = s.substring(1);
			s = first.toLowerCase() + other;
		}
		return s;
	}

	/**
	 * 判断指定的字符串是否是合法的Java标识符[Added By Yao Yantao].
	 * 
	 * @param s
	 *            要判断的字符串串
	 * @return true,如果是Java的标识符; false，如果不是Java标识符或者是null.
	 */
	public static boolean isJavaIdentifier(String s) {
		if (s == null || "".equals(s))
			return false;
		char[] arr = s.toCharArray();
		if (!Character.isJavaIdentifierStart(arr[0]))
			return false;
		for (int i = 1, len = arr.length; i < len; i++) {
			if (!Character.isJavaIdentifierPart(arr[i]))
				return false;
		}
		return true;
	}

	/**
	 * 将源字符串数组中的指定范围中的元素拷贝到目标字符串数组中，注意：这里的拷贝是值拷贝�?
	 * �?��注意的是，如果任何下面的情况出现了，会抛出空指针异常（NullPointerException）， 但源数组与目标都保持不便�?
	 * 如果dst为null�?如果src为null.
	 * 如果任何下面的情况出现了，会抛出数组越界异常（IndexOutOfBoundsException），�?源数组与目标数组保持不变�?
	 * 如果srcOffset为负数； 如果dstOffset为负数； 如果length为负数； 如果srcOffset +
	 * length大于src.length�?如果dstOffset + length大于dst.length.
	 * 
	 * @param src
	 *            源数�?
	 * @param src_position
	 *            源数组起始位�?
	 * @param dst
	 *            目标数组
	 * @param dst_position
	 *            目标数组起始位置
	 * @param length
	 *            长度
	 * @throws NullPointerException
	 *             (如上)
	 * @throws IndexOutOfBoundsException
	 *             (如上)
	 */
	public static void arrayCopy(String[][] src, int src_position, String[][] dst, int dst_position, int length) {
		System.arraycopy(src, src_position, dst, dst_position, length);
		for (int i = src_position; i < src_position + length; i++) {
			String[] tem = new String[src[i].length];
			System.arraycopy(src[i], 0, tem, 0, tem.length);
			src[i] = tem;
		}
	}

	/**
	 * 拷贝源字符串数组中的指定范围中的元素到一个新的字符串数组中，注意：这里的拷贝是�?拷贝�?
	 * �?��注意的是，如果任何下面的情况出现了，会抛出空指针异常（NullPointerException）， 但源数组保持不便�?如果src为null.
	 * 如果任何下面的情况出现了，会抛出数组越界异常（IndexOutOfBoundsException），�?源数组保持不变：
	 * 如果srcOffset为负数； 如果length为负数； 如果srcOffset + length大于src.length�?
	 * 
	 * @param src
	 *            源数�?
	 * @param src_position
	 *            源数组起始位�?
	 * @param length
	 *            长度
	 * @throws NullPointerException
	 *             (如上)
	 * @throws IndexOutOfBoundsException
	 *             (如上)
	 */
	public static String[][] arrayCopy(String[][] src, int src_position, int length) {
		String[][] dst = new String[length][];
		arrayCopy(src, src_position, dst, 0, length);
		return dst;
	}

	/**
	 * 将源字符串数组中的所有元素拷贝到新的字符串数组中，注意：这里的拷贝是值拷贝�?
	 * �?��注意的是，如果任何下面的情况出现了，会抛出空指针异常（NullPointerException）， 但源数组保持不便�?如果src为null.
	 * 如果任何下面的情况出现了，会抛出数组越界异常（IndexOutOfBoundsException），�?源数组保持不变：
	 * 如果srcOffset为负数； 如果length为负数； 如果srcOffset + length大于src.length�?
	 * 
	 * @param src
	 *            源数�?
	 * @throws NullPointerException
	 *             (如上)
	 * @throws IndexOutOfBoundsException
	 *             (如上)
	 */
	public static String[][] arrayCopy(String[][] src) {
		return arrayCopy(src, 0, src.length);
	}

	/**
	 * @param s
	 * @param delimiter
	 * @return String[]
	 */
	public static String[] split(String s, String delimiter) {
		StringTokenizer tokenizer = new StringTokenizer(s, delimiter);
		String[] result = new String[tokenizer.countTokens()];
		int i = 0;
		while (tokenizer.hasMoreTokens())
			result[i++] = tokenizer.nextToken();
		return result;
	}

	/**
	 * 比较两个字符串是否相�?
	 * 
	 * @param s1
	 *            String
	 * @param s2
	 *            String
	 */
	public static boolean equals(Object s1, Object s2) {
		if (s1 == s2)
			return true;
		if (s1 == null)
			return false;
		return s1.equals(s2);
	}

	/**
	 * 将传入的LIST中的数组自动合并成一个大数组.
	 * 
	 * @param list
	 *            要合并的数组的列�?要求数组列数必须相等.
	 * @return 合并后的数组.
	 */
	public static String[][] arrayCombine(@SuppressWarnings("rawtypes") List list) {
		if (list == null || list.size() < 1) {
			return null;
		}

		String[][] str = null;
		String[][] ret = null;

		// 合计�?��数组的行�?
		int size = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			str = (String[][]) list.get(i);
			if (str != null && str.length > 0) {
				size += str.length;
			}
		}

		if (size > 0) {
			int j = 0; // 目前COPY数组的位�?
			int k = 0; // 每个数组的长�?
			ret = new String[size][];
			for (int i = 0, len = list.size(); i < len; i++) {
				str = (String[][]) list.get(i);
				if (str != null && str.length > 0) {
					k = str.length;
					StringUtil.arrayCopy(str, 0, ret, j, k);
					j += k;
				}
			}
		}

		return ret;
	}

	/**
	 * 返回用指定分隔符隔开的字�?如分隔符为null或空�?抛出运行错误.
	 * 
	 * @param arr
	 *            要连接的数组,如长度为1,返回该元�?否则，返回用分隔符隔�??成的字串.
	 * @param delim
	 *            分隔�?
	 * @return 将数组转换成字串，以便可执行逆操作split().
	 * @exception AppException
	 *                当分隔符为null或空串时抛出.
	 */
	public static String join(String[] arr, String delim) {
		// 首先判断是否为NULL
		if (arr == null || arr.length < 1) {
			return null;
		}

		// 判断参数是否有效
		delim = StringUtil.trim(delim);
		if (delim.length() < 1) {
			//throw new AppException("参数非法，连接数组时，分隔符不可为null或空�?);
		}

		// 取数组长�?
		int len = arr.length;

		// 如为1，立刻返回第�?��
		if (len < 2) {
			return arr[0];
		}

		// 构�?用于返回的字符串对象
		StringBuffer sb = new StringBuffer(StringUtil.trim(arr[0]));

		// 拼字�?
		for (int i = 1; i < len; i++) {
			sb.append(delim).append(StringUtil.trim(arr[i]));
		}

		return sb.toString();
	}

	/**
	 * 将超过长度的字符�?..表示
	 * 
	 * @param src
	 * @param len
	 * @return
	 */
	public static String limitLength(String src, int len) {
		String strOut = "";
		if (src == null) {
			return strOut;
		}
		if (src.length() > len) {
			strOut = src.substring(0, len) + "...";
		} else {
			strOut = src;
		}
		return strOut;
	}

	/**
	 * 将一个iso8859-1字符串转换为给定的编码格�?
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String translateFromISO(String str, String charsetName) throws UnsupportedEncodingException {
		if (str != null)
			return new String(str.getBytes("ISO8859-1"), charsetName);
		return null;
	}

	/**
	 * 将一个字符串转换为给定的编码格式
	 * 
	 * @param str
	 * @param originCharsetName
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String translate(String str, String originCharsetName, String charsetName)
			throws UnsupportedEncodingException {
		if (!(originCharsetName.equals(charsetName))) {
			if (str != null)
				return new String(str.getBytes(originCharsetName), charsetName);
			return null;
		}
		return str;
	}

	/**
	 * 向左补号
	 * 
	 * @param val
	 * @param size
	 * @param ch
	 * @return
	 */
	public static String leftPad(long val, int size, String ch) {
		String result = String.valueOf(val);
		if (StringUtil.isEmpty(ch)) {
			ch = " ";
		}
		while (result.length() < size) {
			result = ch + result;
		}
		return result;
	}

	/**
	 * 向右补号
	 * 
	 * @param val
	 * @param size
	 * @param ch
	 * @return
	 */
	public static String rightPad(long val, int size, String ch) {
		String result = String.valueOf(val);
		if (StringUtil.isEmpty(ch)) {
			ch = " ";
		}
		while (result.length() < size) {
			result = result + ch;
		}

		return result;
	}

	/**
	 * 向左补号
	 * 
	 * @param val
	 * @param size
	 * @param ch
	 * @return
	 */
	public static String leftPad(Object val, int size, String ch) {
		String result = String.valueOf(val);
		if (StringUtil.isEmpty(ch)) {
			ch = " ";
		}
		while (result.length() < size) {
			result = ch + result;
		}
		return result;
	}

	/**
	 * 向右补号
	 * 
	 * @param val
	 * @param size
	 * @param ch
	 * @return
	 */
	public static String rightPad(Object val, int size, String ch) {
		String result = String.valueOf(val);
		if (StringUtil.isEmpty(ch)) {
			ch = " ";
		}
		while (result.length() < size) {
			result = result + ch;
		}

		return result;
	}

	/**
	 * 解析数�?
	 * 
	 * @param in
	 * @return
	 */
	public final static String extractNumber(String in) {
		if (in == null) {
			return "0";
		}

		StringBuffer result = new StringBuffer();
		boolean seenDot = false;
		boolean seenMinus = false;
		boolean seenNumber = false;

		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);

			if (c == '.') {
				// insert dot if not yet encountered
				if (!seenDot) {
					seenDot = true;

					if (!seenNumber) {
						result.append('0'); // padding zero
											// if no number
											// yet
					}

					result.append('.');
				}
			} else if (c == '-') {
				// insert minus sign if not yet encountered
				if (!seenMinus) {
					seenMinus = true;
					result.append('-');
				}
			} else if ((c == '0') || ((c >= '1') && (c <= '9'))) {
				// add number
				seenNumber = true;
				result.append(c);
			}
		}

		// remove trailing .
		int length = result.length();
		if ((length > 0) && (result.charAt(length - 1) == '.')) {
			result.deleteCharAt(length - 1);
		}
		return (result.length() == 0) ? "0" : result.toString();
	}

	/**
	 * 反转�?��字符�?
	 * 
	 * @param str
	 * @return
	 */
	public static final String reverse(String str) {
		if (null == str)
			return str;
		int len = str.length();
		char[] dist = new char[len];
		str.getChars(0, len, dist, 0);
		char a;
		for (int i = 0; i < len / 2; i++) {
			a = dist[i];
			dist[i] = dist[len - i - 1];
			dist[len - i - 1] = a;
		}
		return new String(dist);
	}

	/**
	 * 校验是否为手机号
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * <p>
	 * Map<String,String> map = new HashMap<String,String>(); map.put("id",
	 * "111111111"); map.put("data", "ssssss"); StringUtil.applyTemplate(
	 * "Java represses  {data} oracular {id}", map);
	 * </p>
	 * @param template
	 * @param map
	 * @return String
	 */
	public static String applyTemplate(String template, Map<String, String> map) {
		if (StringUtils.isEmpty(template)) {
			return "";
		}
		Pattern pattern = Pattern.compile("\\{([\\w-]+)\\}");
		String result = template;
		Matcher matcher = pattern.matcher(template);
		while (matcher.find()) {
			String group = matcher.group();
			String str = group.substring(1, group.length() - 1);
			String value = map.get(str);
			if (value != null) {
				result = result.replace(group, value);
			} else {
				result = result.replace(group, "");
			}
		}
		return result;
	}
	
	public static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	public static byte[] ASCII_To_BCD(byte[] ascii) {
		int asc_len = ascii.length;
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}
	
	public static String stringZip(String str) throws UnsupportedEncodingException{
		byte[] res = str.getBytes();

		byte[] test = ASCII_To_BCD(res);
		Charset cs = Charset.defaultCharset();
		ByteBuffer bb = ByteBuffer.allocate(test.length);
		bb.put(test);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return new String(cb.array());
	}
	
	public static String stringUnZip(String str){
		byte[] res = str.getBytes();
		return bcd2Str(res);
	}

	/**
	 * <p>�?6进制表示的字符串转化为实际表示的字符�?/p>
	 * <code>
	 * <br>
	 * convertUTF8ToString("E4B88AE6B5B7")--->上海
	 * </code>
	 * @param str
	 * @return
	 */
	public static String convertUTF8ToString(String str) {
		if (StringUtil.isEmpty(str)) {
			return null;
		}

		try {
			str = str.toUpperCase();
			int total = str.length() / 2;
			int pos = 0;

			byte[] buffer = new byte[total];
			for (int i = 0; i < total; i++) {

				int start = i * 2;

				buffer[i] = (byte) Integer.parseInt(str.substring(start, start + 2), 16);
				pos++;
			}

			return new String(buffer, 0, pos, "UTF-8");

		} catch (UnsupportedEncodingException e) {
		}
		return str;
	}

	/**
	 * <p>将字符串转化为UTF-8编码�?6进制字符�?/p>
	 * <code>
	 * <br>
	 * convertStringToUTF8("上海")--->E4B88AE6B5B7
	 * </code>
	 * @param str
	 * @return
	 */
	public static String convertStringToUTF8(String str) {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			char c;
			for (int i = 0; i < str.length(); i++) {
				c = str.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;

					b = Character.toString(c).getBytes("UTF-8");

					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0)
							k += 256;
						sb.append(Integer.toHexString(k).toUpperCase());
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
		}
		return sb.toString();
	}
	
	/**
     * 取出�?��指定长度大小的随机正整数.
     * @param length
     *            int 设定�?��出随机数的长度�?length小于11
     * @return int 返回生成的随机数�?
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
	
	static Logger logger = Logger.getLogger(StringUtil.class.getName());
	
	public static void main(String[] args) throws Exception {
		
	}
}