package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class CompactDetailResponse implements Serializable {



        /**
         * contract : {"title":"测试合同艺人显示","end_date":"2018-06-29","status":6,"note":"第三方斯蒂芬","create_date":"2018-06-27","q_date":"2018-06-29","j_name":"杭州嘉州科技","j_address":"浙江杭州","j_code":"30018","j_telephone":"15862145262","y_name":"杭州嘉州科技","y_address":"浙江杭州","y_code":"30018","y_telephone":"15862145262","y_idcard":"641321145985264115","validity":12,"rmb":"1254632","number":2,"p_info":"{\"一直播\":\"2452622\",\"酷狗繁星\":\"548556\"}","y_push":""}
         */

        private ContractBean contract;

        public ContractBean getContract() {
            return contract;
        }

        public void setContract(ContractBean contract) {
            this.contract = contract;
        }

        public static class ContractBean {
            /**
             * title : 测试合同艺人显示
             * end_date : 2018-06-29
             * status : 6
             * note : 第三方斯蒂芬
             * create_date : 2018-06-27
             * q_date : 2018-06-29
             * j_name : 杭州嘉州科技
             * j_address : 浙江杭州
             * j_code : 30018
             * j_telephone : 15862145262
             * y_name : 杭州嘉州科技
             * y_address : 浙江杭州
             * y_code : 30018
             * y_telephone : 15862145262
             * y_idcard : 641321145985264115
             * validity : 12
             * rmb : 1254632
             * number : 2
             * p_info : {"一直播":"2452622","酷狗繁星":"548556"}
             * y_push :
             */

            private String title;
            private String end_date;
            private int status;
            private String note;
            private String create_date;
            private String q_date;
            private String j_name;
            private String j_address;
            private String j_code;
            private String j_telephone;
            private String y_name;
            private String y_address;
            private String y_code;
            private String y_telephone;
            private String y_idcard;
            private int validity;
            private String rmb;
            private int number;
            private String p_info;
            private String y_push;
            private String h5_str;
            private String j_assign_addr;

            public String getJ_assign_addr() {
                return j_assign_addr;
            }

            public void setJ_assign_addr(String j_assign_addr) {
                this.j_assign_addr = j_assign_addr;
            }

            public String getH5_str() {
                return h5_str;
            }

            public void setH5_str(String h5_str) {
                this.h5_str = h5_str;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getQ_date() {
                return q_date;
            }

            public void setQ_date(String q_date) {
                this.q_date = q_date;
            }

            public String getJ_name() {
                return j_name;
            }

            public void setJ_name(String j_name) {
                this.j_name = j_name;
            }

            public String getJ_address() {
                return j_address;
            }

            public void setJ_address(String j_address) {
                this.j_address = j_address;
            }

            public String getJ_code() {
                return j_code;
            }

            public void setJ_code(String j_code) {
                this.j_code = j_code;
            }

            public String getJ_telephone() {
                return j_telephone;
            }

            public void setJ_telephone(String j_telephone) {
                this.j_telephone = j_telephone;
            }

            public String getY_name() {
                return y_name;
            }

            public void setY_name(String y_name) {
                this.y_name = y_name;
            }

            public String getY_address() {
                return y_address;
            }

            public void setY_address(String y_address) {
                this.y_address = y_address;
            }

            public String getY_code() {
                return y_code;
            }

            public void setY_code(String y_code) {
                this.y_code = y_code;
            }

            public String getY_telephone() {
                return y_telephone;
            }

            public void setY_telephone(String y_telephone) {
                this.y_telephone = y_telephone;
            }

            public String getY_idcard() {
                return y_idcard;
            }

            public void setY_idcard(String y_idcard) {
                this.y_idcard = y_idcard;
            }

            public int getValidity() {
                return validity;
            }

            public void setValidity(int validity) {
                this.validity = validity;
            }

            public String getRmb() {
                return rmb;
            }

            public void setRmb(String rmb) {
                this.rmb = rmb;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getP_info() {
                return p_info;
            }

            public void setP_info(String p_info) {
                this.p_info = p_info;
            }

            public String getY_push() {
                return y_push;
            }

            public void setY_push(String y_push) {
                this.y_push = y_push;
            }
        }

}

