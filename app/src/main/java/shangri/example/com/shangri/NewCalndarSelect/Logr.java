package shangri.example.com.shangri.NewCalndarSelect;

import android.util.Log;

/** Log utility class to handle the log tag and DEBUG-only logging. */
final class Logr {
  public static void d(String message) {
      Log.d("TimesSquare", message);
  }

  public static void d(String message, Object... args) {
      d(String.format(message, args));
  }
}
