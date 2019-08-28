package shangri.example.com.shangri.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengaofu on 2017/6/25.
 */

public class MultiRequestCounter {

    public static MultiRequestCounter mInstance;
    private static Map<String,ArrayList<String>> mRequestMap = new HashMap<String,ArrayList<String>>();
    public MultiRequestCounter(String uiName){
        mRequestMap.put(uiName,new ArrayList<String>());
    }

    public static MultiRequestCounter getInstance(String uiName){
        if(mInstance == null){
            synchronized (MultiRequestCounter.class){
                if(mInstance == null){
                    mInstance = new MultiRequestCounter(uiName);
                }
            }
        }
        return mInstance;
    }

    /**
     * 根据界面名称添加 请求的方法名
     * @param uiName
     * @param requestName
     */
    public void addRequestName(String uiName, String requestName){
        ArrayList<String> nameList = mRequestMap.get(uiName);
        if(!nameList.contains(requestName)){
            nameList.add(requestName);
        }
    }

    /**
     * 删除requestName
     * @param requestName
     */
    public void removeRequestName(String requestName){
        for (Map.Entry<String,ArrayList<String>> entry : mRequestMap.entrySet()) {
            ArrayList<String> nameList = mRequestMap.get(entry.getKey());
            if(nameList.contains(requestName)){
                nameList.remove(requestName);
            }
        }
    }

    public int getRequestNamesCount(String className){
        if(mRequestMap.containsKey(className)){
            ArrayList<String> nameList = mRequestMap.get(className);
            return nameList.size();
        }
        return 0;
    }

}
