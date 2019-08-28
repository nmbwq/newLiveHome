package shangri.example.com.shangri.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class RegexUtil {
    /**
     * 正则：手机号（精确）
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";

    public static String defaultDatePattern = "yyyy-MM-dd ";

    public static final String REGEX_QQ = "[1-9][0-9]{4,14}";//第一位1-9之间的数字，第二位0-9之间的数字，数字范围4-14个之间

    public static final String REGEX_CODE = "[0-9]{6}"; //判断是否为4位验证码

    /**
     * 校验手机号
     *
     * @param mobile 精确
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE_SIMPLE, mobile);
    }

    /**
     * 校验验证码
     * @param code
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isCode(String code) {
        return Pattern.matches(REGEX_CODE, code);
    }

    //手机验证简单
    public static boolean isPhone(String phone) {
        return isMobileSimple(phone);
    }

    /**
     * 验证手机号（简单）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMobileSimple(CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    public static boolean isQQ(String qq){
        return Pattern.matches(REGEX_QQ, qq);
    }

    public static String format(Date date, String pattern) {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    public static String format(Long date, String pattern) {
        return date == null ? " " : new SimpleDateFormat(pattern).format(new Date(date));
    }


}
