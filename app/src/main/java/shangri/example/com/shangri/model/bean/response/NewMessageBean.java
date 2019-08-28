package shangri.example.com.shangri.model.bean.response;

import java.util.List;

/**
 * Description:新消息bean
 * Data：2018/11/19-18:23
 * Author: lin
 */
public class NewMessageBean {
    String current_page;
    String total_page;
//    约聊未读数
    int  chat_number;
    //    推荐职位未读数
    int  recommend_noread_total;
    //    系统通知未读数
    int  system_noread_total;

    public int getChat_number() {
        return chat_number;
    }

    public void setChat_number(int chat_number) {
        this.chat_number = chat_number;
    }

    public int getRecommend_noread_total() {
        return recommend_noread_total;
    }

    public void setRecommend_noread_total(int recommend_noread_total) {
        this.recommend_noread_total = recommend_noread_total;
    }

    public int getSystem_noread_total() {
        return system_noread_total;
    }

    public void setSystem_noread_total(int system_noread_total) {
        this.system_noread_total = system_noread_total;
    }

    List<Messages> messages;

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getTotal_page() {
        return total_page;
    }

    public void setTotal_page(String total_page) {
        this.total_page = total_page;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public class Messages{
        String id;
        String register_id;
        String rcd_url;
        String main_id;
        String title;
        String f_title;
        String icon_type;
        String create_time;
        String is_read;
        String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegister_id() {
            return register_id;
        }

        public void setRegister_id(String register_id) {
            this.register_id = register_id;
        }

        public String getRcd_url() {
            return rcd_url;
        }

        public void setRcd_url(String rcd_url) {
            this.rcd_url = rcd_url;
        }

        public String getMain_id() {
            return main_id;
        }

        public void setMain_id(String main_id) {
            this.main_id = main_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getF_title() {
            return f_title;
        }

        public void setF_title(String f_title) {
            this.f_title = f_title;
        }

        public String getIcon_type() {
            return icon_type;
        }

        public void setIcon_type(String icon_type) {
            this.icon_type = icon_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
