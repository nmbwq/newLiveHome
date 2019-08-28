package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by admin on 2017/12/26.
 */

public class MessageResonse extends BaseBeen {



        private List<MessagesBean> messages;

        public List<MessagesBean> getMessages() {
            return messages;
        }

        public void setMessages(List<MessagesBean> messages) {
            this.messages = messages;
        }

        public static class MessagesBean {
            /**
             * id : 1
             * main_id : 58
             * msg_type : 1
             * content : dsf
             * is_read : 0
             * msg_time : 1970-01-01 08:00
             * member_time : 1528336398
             * memner_status : 2
             */

            private int id;
            private int main_id;
            private int msg_type;
            private String content;
            private int is_read;
            private String msg_time;
            private String member_time;
            private int memner_status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMain_id() {
                return main_id;
            }

            public void setMain_id(int main_id) {
                this.main_id = main_id;
            }

            public int getMsg_type() {
                return msg_type;
            }

            public void setMsg_type(int msg_type) {
                this.msg_type = msg_type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public String getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(String msg_time) {
                this.msg_time = msg_time;
            }

            public String getMember_time() {
                return member_time;
            }

            public void setMember_time(String member_time) {
                this.member_time = member_time;
            }

            public int getMemner_status() {
                return memner_status;
            }

            public void setMemner_status(int memner_status) {
                this.memner_status = memner_status;
            }
        }

}
