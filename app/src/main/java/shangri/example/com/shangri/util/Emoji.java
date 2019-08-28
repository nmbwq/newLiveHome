package shangri.example.com.shangri.util;

/**
 * Created by Administrator on 2018/1/11.
 */

public class Emoji {
    private String setEmojiToTextView(){
        int unicodeJoy = 0x1F602;
        String emojiString = getEmojiStringByUnicode(unicodeJoy);
        return emojiString;
    }

    private String getEmojiStringByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
