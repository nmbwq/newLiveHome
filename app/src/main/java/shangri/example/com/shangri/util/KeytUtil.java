package shangri.example.com.shangri.util;

import android.util.Log;

import com.google.gson.Gson;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 密钥
 * Created by chengaofu on 2017/6/23.
 */

public class KeytUtil {


    public static BaseRequestEntity<?> getRSAKeyt(BaseRequestEntity<?> requestEntity, String token) {

//        requestEntity.setTimestamp("123456878456");
//        requestEntity.setNonce("jkhkgfds-=gfdfsgf+gfsgf");
        String data = new Gson().toJson(requestEntity.getData());
//        Log.e("Wislie","data:"+data);
//        String md5Data = MD5Util.getMD5(data);
//        Log.e("Wislie","md5Data"+md5Data);
        TreeMap<String, String> params = new TreeMap<>();
//        params.put("data", md5Data);
//        params.put("timestamp", requestEntity.getTimestamp());
//        params.put("nonce", requestEntity.getNonce());
        if (token != null && !token.trim().equals("")) {
            params.put("token", token); //添加token值
        }
//        StringBuilder builder = new StringBuilder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            String key = entry.getKey().trim();
//            String value = entry.getValue().trim();
//            builder.append(key + "=" + value + "&");
//        }
//        //排好序的字符串
//        String sortStr = builder.toString();
//        if (sortStr.length() > 0 && sortStr.charAt(sortStr.length() - 1) == '&') { //最后的字符是'&'
//            //RSA公钥加密
//            String sortResult = sortStr.substring(0, sortStr.length() - 1); //排序后的值
//            try {
//                byte[] datas = sortResult.getBytes();
//                byte[] encodeData = RSAUtils.encryptByPublicKey(datas, Constant.KEY);
//                //返回加密后的sign值
//                String sign = replaceBlank(new String(Base64Utils.encode(encodeData)));
//                requestEntity.setSign(sign);
//
////                Log.e("Wislie","sign:"+sign);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        convertToJson(requestEntity);
        return requestEntity;
    }

    private static void convertToJson(BaseRequestEntity result){
        Gson gson = new Gson();
        String json = gson.toJson(result);
        Log.e("Wislie", "request json:"+json);
    }



    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
