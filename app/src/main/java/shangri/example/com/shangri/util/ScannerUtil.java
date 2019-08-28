package shangri.example.com.shangri.util;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/1/15.
 */

public class ScannerUtil {


    public static String tenThousand(String m) {
//        Scanner scan = new Scanner(System.in);
//        String c = scan.next();
        if (m.equals("0")){
            return "0";
        }
        String c = m;
        double youIn = Double.parseDouble(c);
        double l = 1000;
        DecimalFormat df = new DecimalFormat("0.00");
//        System.out.println(df.format(youIn/l));
        return df.format(youIn/l);
    }
}
