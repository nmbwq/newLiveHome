package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class CompactMobanResponse implements Serializable {


        private List<TemplatesBean> templates;

        public List<TemplatesBean> getTemplates() {
            return templates;
        }

        public void setTemplates(List<TemplatesBean> templates) {
            this.templates = templates;
        }

        public static class TemplatesBean {
            /**
             * id : 1
             * template_name : 艺人网络演出合作协议
             * required_item : 5项必填
             * table_name : legal_contract
             * status : 1
             */

            private int id;
            private String template_name;
            private String required_item;
            private String table_name;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTemplate_name() {
                return template_name;
            }

            public void setTemplate_name(String template_name) {
                this.template_name = template_name;
            }

            public String getRequired_item() {
                return required_item;
            }

            public void setRequired_item(String required_item) {
                this.required_item = required_item;
            }

            public String getTable_name() {
                return table_name;
            }

            public void setTable_name(String table_name) {
                this.table_name = table_name;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

}

