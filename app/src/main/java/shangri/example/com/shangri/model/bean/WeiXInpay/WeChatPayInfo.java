package shangri.example.com.shangri.model.bean.WeiXInpay;

/**
 * Created by Administrator on 2018/3/13.
 * 支付信息
 */

public class WeChatPayInfo {

    /**
     * code : 0
     * message : success
     * data : {"appid":"wx60ef7e838f55e199","partnerid":"1499176652","prepayid":"wx201803131145571153b8f8ab0361434395","noncestr":"5aa74975d12d6","timestamp":1520912757,"package":"Sign=WXPay","sign":"9D5D5FDC7F40FC8522709910DAB554D9"}
     */
    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class     DataBean {
        /**
         * appid : wx60ef7e838f55e199
         * partnerid : 1499176652
         * prepayid : wx201803131145571153b8f8ab0361434395
         * noncestr : 5aa74975d12d6
         * timestamp : 1520912757
         * package : Sign=WXPay
         * sign : 9D5D5FDC7F40FC8522709910DAB554D9
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;
        private String packageX;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "appid='" + appid + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
