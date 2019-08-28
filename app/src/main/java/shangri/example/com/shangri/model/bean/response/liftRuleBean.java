package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class liftRuleBean implements Serializable {


    /**
     * rule : [{"level":"1","level_name":"初级经纪人","invite_register_num":"6","invite_resume_num":"3"},{"level":"2","level_name":"中级经纪人","invite_register_num":"16","invite_resume_num":"5"},{"level":"3","level_name":"高级经纪人","invite_register_num":"31","invite_resume_num":"20"},{"level":"4","level_name":"砖石经纪人","invite_register_num":"61","invite_resume_num":"35"},{"level":"5","level_name":"王者经纪人","invite_register_num":"200","invite_resume_num":"160"}]
     * welfare : [{"level":0,"level_name":"主播","invite_register":"10","invite_resume":"100","resume_viewed":"20"},{"level":"1","level_name":"初级经纪人","invite_register":"20","invite_resume":"120","resume_viewed":"30"},{"level":"2","level_name":"中级经纪人","invite_register":"30","invite_resume":"130","resume_viewed":"40"},{"level":"3","level_name":"高级经纪人","invite_register":"40","invite_resume":"140","resume_viewed":"50"},{"level":"4","level_name":"砖石经纪人","invite_register":"60","invite_resume":"160","resume_viewed":"70"},{"level":"5","level_name":"王者经纪人","invite_register":"100","invite_resume":"200","resume_viewed":"100"}]
     * is_accord_rule : 0
     * level_name : 高级经纪人
     * register_anchor : 4
     * register_resume : 0
     */

    private int is_accord_rule;
    private int level;
    private String level_name;
    private int register_anchor;
    private int register_resume;
    private List<RuleBean> rule;
    private List<WelfareBean> welfare;


    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getIs_accord_rule() {
        return is_accord_rule;
    }

    public void setIs_accord_rule(int is_accord_rule) {
        this.is_accord_rule = is_accord_rule;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public int getRegister_anchor() {
        return register_anchor;
    }

    public void setRegister_anchor(int register_anchor) {
        this.register_anchor = register_anchor;
    }

    public int getRegister_resume() {
        return register_resume;
    }

    public void setRegister_resume(int register_resume) {
        this.register_resume = register_resume;
    }

    public List<RuleBean> getRule() {
        return rule;
    }

    public void setRule(List<RuleBean> rule) {
        this.rule = rule;
    }

    public List<WelfareBean> getWelfare() {
        return welfare;
    }

    public void setWelfare(List<WelfareBean> welfare) {
        this.welfare = welfare;
    }

    public static class RuleBean {
        /**
         * level : 1
         * level_name : 初级经纪人
         * invite_register_num : 6
         * invite_resume_num : 3
         */

        private String level;
        private String level_name;
        private String invite_register_num;
        private String invite_resume_num;


        public RuleBean(String level_name, String invite_register_num, String invite_resume_num) {
            this.level_name = level_name;
            this.invite_register_num = invite_register_num;
            this.invite_resume_num = invite_resume_num;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getInvite_register_num() {
            return invite_register_num;
        }

        public void setInvite_register_num(String invite_register_num) {
            this.invite_register_num = invite_register_num;
        }

        public String getInvite_resume_num() {
            return invite_resume_num;
        }

        public void setInvite_resume_num(String invite_resume_num) {
            this.invite_resume_num = invite_resume_num;
        }
    }

    public static class WelfareBean {
        /**
         * level : 0
         * level_name : 主播
         * invite_register : 10
         * invite_resume : 100
         * resume_viewed : 20
         */

        private int level;
        private String level_name;
        private String invite_register;
        private String invite_resume;
        private String resume_viewed;

        public WelfareBean(String level_name, String invite_register, String invite_resume, String resume_viewed) {
            this.level_name = level_name;
            this.invite_register = invite_register;
            this.invite_resume = invite_resume;
            this.resume_viewed = resume_viewed;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getInvite_register() {
            return invite_register;
        }

        public void setInvite_register(String invite_register) {
            this.invite_register = invite_register;
        }

        public String getInvite_resume() {
            return invite_resume;
        }

        public void setInvite_resume(String invite_resume) {
            this.invite_resume = invite_resume;
        }

        public String getResume_viewed() {
            return resume_viewed;
        }

        public void setResume_viewed(String resume_viewed) {
            this.resume_viewed = resume_viewed;
        }
    }
}
