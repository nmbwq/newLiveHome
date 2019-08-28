package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Description:主播收藏
 * Data：2018/11/13-10:17
 * Author: lin
 */
public class CollecBean extends BaseBeen{
    Resume resume;

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public class Resume{
        int current_page;
        String first_page_url;
        String from;
        int last_page;
        String last_page_url;
        String next_page_url;
        String path;
        int per_page;
        String prev_page_url;
        int to;
        int total;
        List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean{
            String relation_id;
            GetResume get_resume;
            GetResumePhotoFirst get_resume_photo_first;

            public String getRelation_id() {
                return relation_id;
            }

            public void setRelation_id(String relation_id) {
                this.relation_id = relation_id;
            }

            public GetResume getGet_resume() {
                return get_resume;
            }

            public void setGet_resume(GetResume get_resume) {
                this.get_resume = get_resume;
            }

            public GetResumePhotoFirst getGet_resume_photo_first() {
                return get_resume_photo_first;
            }

            public void setGet_resume_photo_first(GetResumePhotoFirst get_resume_photo_first) {
                this.get_resume_photo_first = get_resume_photo_first;
            }

            public class GetResume{
                String id;
                String nickname;

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
            }
            public class GetResumePhotoFirst{
                String resume_id;
                String img_url;

                public String getResume_id() {
                    return resume_id;
                }

                public void setResume_id(String resume_id) {
                    this.resume_id = resume_id;
                }

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }
            }
        }
    }
}
