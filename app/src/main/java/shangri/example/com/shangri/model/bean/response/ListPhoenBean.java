package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class ListPhoenBean implements Serializable {



        private List<ContactBean> contact;

        public List<ContactBean> getContact() {
            return contact;
        }

        public void setContact(List<ContactBean> contact) {
            this.contact = contact;
        }

        public static class ContactBean {
            /**
             * id : 1
             * job : 财务
             * name : 张大大
             * telephone : 13254682546
             */

            private int id;
            private String job;
            private String name;
            private String telephone;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }
    }



