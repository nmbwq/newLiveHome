package shangri.example.com.shangri.util;

/**
 * Created by chengaofu on 2017/7/7.
 */

public class StringUtil {

    /**
     * 输入字符串，限定长度
     *
     * @param title
     * @param limit
     * @return
     */
    public static String getSubString(String title, int limit) {
        if (title.length() > limit) {
            String newString = title.substring(0, limit);
            newString = newString + "...";
            return newString;
        }
        return title;
    }

    public static int string2num(String s) {

        if (s.isEmpty()) {
            return 0;
        }
        int num = Integer.parseInt(s.replace(",", "").replace(".", "").replace("，", ""));
        return num;
    }
}
