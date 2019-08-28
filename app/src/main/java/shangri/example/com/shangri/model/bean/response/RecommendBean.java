package shangri.example.com.shangri.model.bean.response;


import java.util.List;

/**
 * Description: 小播推荐
 * Data：2018/11/14-17:50
 * Author: lin
 */
public class RecommendBean  {

    List<Resumes> resumes;
    int total_page;
    int current_page;

    public List<Resumes> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resumes> resumes) {
        this.resumes = resumes;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public class Resumes{

        String id;
        String nickname;
        String create_time;
        String photo_first;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPhoto_first() {
            return photo_first;
        }

        public void setPhoto_first(String photo_first) {
            this.photo_first = photo_first;
        }
    }
}
