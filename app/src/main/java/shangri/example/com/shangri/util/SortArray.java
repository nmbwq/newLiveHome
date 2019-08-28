package shangri.example.com.shangri.util;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class SortArray {
    public static List<Long> orderByAsc(List<Long> scores){
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = 1; j < scores.size() - i; j++) {
                Long a;
                if ((scores.get(j - 1)).compareTo(scores.get(j)) > 0) {

                    a = scores.get(j - 1);
                    scores.set((j - 1), scores.get(j));
                    scores.set(j, a);
                }
            }
        }
        return scores;
    }
}
