package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 登陆返回的用户信息
 * Created by chengaofu on 2017/6/21.
 */

public class ReadPhotoBean implements Serializable {

        private List<ResumeBean> resume;
        private List<CaptureBean> capture;

        public List<ResumeBean> getResume() {
            return resume;
        }

        public void setResume(List<ResumeBean> resume) {
            this.resume = resume;
        }

        public List<CaptureBean> getCapture() {
            return capture;
        }

        public void setCapture(List<CaptureBean> capture) {
            this.capture = capture;
        }

        public static class ResumeBean implements Serializable{
            /**
             * place : 1
             * img_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-09-29/5baf367001ee7.jpg
             */

            private String place;
            private String img_url;

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }

        public static class CaptureBean implements Serializable{
            /**
             * place : 1
             * img_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/resume_phone/2018-10-11/5bbf0f5383695.jpg
             */

            private String place;
            private String img_url;

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
}



