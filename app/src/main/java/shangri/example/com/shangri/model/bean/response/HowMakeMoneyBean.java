package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/30.
 */

public class HowMakeMoneyBean implements Serializable {


    /**
     * base : {"use_bb":0,"bb_rate":10,"status":0}
     * send_resume : {"is_resume":1,"send_status":1,"apply_status":0}
     * invite_anchor : {"stage":1,"status":0,"need_total":2,"has_total":0,"get_bb":40,"get_max_bb":280}
     * invite_send : {"stage":1,"status":0,"need_total":3,"has_total":0,"get_bb":60,"get_max_bb":360}
     * focus_wx : {"is_focus":0,"get_bb":50}
     * resume_view : {"stage":1,"status":0,"need_total":4,"has_total":0,"get_bb":80,"get_max_bb":600}
     */

    private BaseBean base;
    private SendResumeBean send_resume;
    private InviteAnchorBean invite_anchor;
    private InviteSendBean invite_send;
    private FocusWxBean focus_wx;
    private ResumeViewBean resume_view;

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public SendResumeBean getSend_resume() {
        return send_resume;
    }

    public void setSend_resume(SendResumeBean send_resume) {
        this.send_resume = send_resume;
    }

    public InviteAnchorBean getInvite_anchor() {
        return invite_anchor;
    }

    public void setInvite_anchor(InviteAnchorBean invite_anchor) {
        this.invite_anchor = invite_anchor;
    }

    public InviteSendBean getInvite_send() {
        return invite_send;
    }

    public void setInvite_send(InviteSendBean invite_send) {
        this.invite_send = invite_send;
    }

    public FocusWxBean getFocus_wx() {
        return focus_wx;
    }

    public void setFocus_wx(FocusWxBean focus_wx) {
        this.focus_wx = focus_wx;
    }

    public ResumeViewBean getResume_view() {
        return resume_view;
    }

    public void setResume_view(ResumeViewBean resume_view) {
        this.resume_view = resume_view;
    }

    public static class BaseBean {
        /**
         * use_bb : 0
         * bb_rate : 10
         * status : 0
         */

        private int use_bb;
        private int bb_rate;
        private int status;

        public int getUse_bb() {
            return use_bb;
        }

        public void setUse_bb(int use_bb) {
            this.use_bb = use_bb;
        }

        public int getBb_rate() {
            return bb_rate;
        }

        public void setBb_rate(int bb_rate) {
            this.bb_rate = bb_rate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class SendResumeBean {
        /**
         * is_resume : 1
         * send_status : 1
         * apply_status : 0
         */

        private int is_resume;
        private int send_status;
        private int apply_status;

        public int getIs_resume() {
            return is_resume;
        }

        public void setIs_resume(int is_resume) {
            this.is_resume = is_resume;
        }

        public int getSend_status() {
            return send_status;
        }

        public void setSend_status(int send_status) {
            this.send_status = send_status;
        }

        public int getApply_status() {
            return apply_status;
        }

        public void setApply_status(int apply_status) {
            this.apply_status = apply_status;
        }
    }

    public static class InviteAnchorBean {
        /**
         * stage : 1
         * status : 0
         * need_total : 2
         * has_total : 0
         * get_bb : 40
         * get_max_bb : 280
         */

        private int stage;
        private int status;
        private int need_total;
        private int has_total;
        private int get_bb;
        private int get_max_bb;

        private String  code;

//        二维码地址
        private String qrcode_url;
//        邀请地址
        private String invitation_img_url;
//        分享地址
        private String share_url;

        public String getQrcode_url() {
            return qrcode_url;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setQrcode_url(String qrcode_url) {
            this.qrcode_url = qrcode_url;
        }

        public String getInvitation_img_url() {
            return invitation_img_url;
        }

        public void setInvitation_img_url(String invitation_img_url) {
            this.invitation_img_url = invitation_img_url;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNeed_total() {
            return need_total;
        }

        public void setNeed_total(int need_total) {
            this.need_total = need_total;
        }

        public int getHas_total() {
            return has_total;
        }

        public void setHas_total(int has_total) {
            this.has_total = has_total;
        }

        public int getGet_bb() {
            return get_bb;
        }

        public void setGet_bb(int get_bb) {
            this.get_bb = get_bb;
        }

        public int getGet_max_bb() {
            return get_max_bb;
        }

        public void setGet_max_bb(int get_max_bb) {
            this.get_max_bb = get_max_bb;
        }
    }

    public static class InviteSendBean {
        /**
         * stage : 1
         * status : 0
         * need_total : 3
         * has_total : 0
         * get_bb : 60
         * get_max_bb : 360
         */

        private int stage;
        private int status;
        private int need_total;
        private int has_total;
        private int get_bb;
        private int get_max_bb;

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNeed_total() {
            return need_total;
        }

        public void setNeed_total(int need_total) {
            this.need_total = need_total;
        }

        public int getHas_total() {
            return has_total;
        }

        public void setHas_total(int has_total) {
            this.has_total = has_total;
        }

        public int getGet_bb() {
            return get_bb;
        }

        public void setGet_bb(int get_bb) {
            this.get_bb = get_bb;
        }

        public int getGet_max_bb() {
            return get_max_bb;
        }

        public void setGet_max_bb(int get_max_bb) {
            this.get_max_bb = get_max_bb;
        }
    }

    public static class FocusWxBean {
        /**
         * is_focus : 0
         * get_bb : 50
         */

        private int is_focus;
        private int get_bb;
        String wx;
        String gzh;

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public String getGzh() {
            return gzh;
        }

        public void setGzh(String gzh) {
            this.gzh = gzh;
        }

        public int getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(int is_focus) {
            this.is_focus = is_focus;
        }

        public int getGet_bb() {
            return get_bb;
        }

        public void setGet_bb(int get_bb) {
            this.get_bb = get_bb;
        }
    }

    public static class ResumeViewBean {
        /**
         * stage : 1
         * status : 0
         * need_total : 4
         * has_total : 0
         * get_bb : 80
         * get_max_bb : 600
         */

        private int stage;
        private int status;
        private int need_total;
        private int has_total;
        private int get_bb;
        private int get_max_bb;

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNeed_total() {
            return need_total;
        }

        public void setNeed_total(int need_total) {
            this.need_total = need_total;
        }

        public int getHas_total() {
            return has_total;
        }

        public void setHas_total(int has_total) {
            this.has_total = has_total;
        }

        public int getGet_bb() {
            return get_bb;
        }

        public void setGet_bb(int get_bb) {
            this.get_bb = get_bb;
        }

        public int getGet_max_bb() {
            return get_max_bb;
        }

        public void setGet_max_bb(int get_max_bb) {
            this.get_max_bb = get_max_bb;
        }
    }
}
