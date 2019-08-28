package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class companyOrgBean implements Serializable {


    /**
     * code : 0
     * message : success
     * data : {"company":{"id":"3","registrant_id":"2","company_name":"Ceshi","company_scale":"1222人","location":"Ceshiduqu","logo":null},"guild":[{"guild_id":"1234548255","guild_name":"菠萝街（coco）","admins":[{"id":"353","nickname":"18668121550","avatar_url":"","anchors":[{"uid":"4565950","anchor_name":"66666666","avatar_url":""}]}],"otheranchors":[{"register_id":"47","uid":"7300783","nickname":"盾上加鹿","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"48","uid":"7443730","nickname":"四爷","avatar_url":""},{"register_id":"49","uid":"5202247","nickname":"大大","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497506759846958.jpg"},{"register_id":"55","uid":"8933227","nickname":"习惯一个人","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-04/151507391341954725.jpg"},{"register_id":"58","uid":"8925119","nickname":"张杨轩儿","avatar_url":""},{"register_id":"168","uid":"11123496","nickname":"飘爷","avatar_url":""},{"register_id":"172","uid":"4704350","nickname":"我叫小雨吖","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/5a9ce496668ef.jpg"},{"register_id":"166","uid":"6886478","nickname":"栀子","avatar_url":""},{"register_id":"73","uid":"10243769","nickname":"菠萝街","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/152023859865917359.jpg"},{"register_id":"176","uid":"6203296","nickname":"性感小狐狸","avatar_url":""},{"register_id":"185","uid":"9365032","nickname":"梅倪","avatar_url":""},{"register_id":"215","uid":"9325396","nickname":"抒情派茉","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-14/5aa8cf57221e0.jpg"},{"register_id":"228","uid":"11852438","nickname":"静静","avatar_url":""}]},{"guild_id":"650418055593","guild_name":"UP（猫咪）","admins":[],"otheranchors":[]},{"guild_id":"323ruyuwenhua","guild_name":"一直播（猫咪）","admins":[],"otheranchors":[{"register_id":"47","uid":"66697599","nickname":"哥雨v有","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"}]},{"guild_id":"53425479107","guild_name":"小米（猫咪）","admins":[],"otheranchors":[]},{"guild_id":"432418055593","guild_name":"NOW（猫咪）","admins":[],"otheranchors":[{"register_id":"58","uid":"170183042","nickname":"张杨轩儿","avatar_url":""},{"register_id":"47","uid":"71085734","nickname":"GG","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"47","uid":"98130599","nickname":"春好吧","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"47","uid":"199595472","nickname":"哥吃吃吃","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"47","uid":"235994803","nickname":"宝宝","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"69","uid":"168649890","nickname":"葫芦_?","avatar_url":""},{"register_id":"47","uid":"199276926","nickname":"加班","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"194","uid":"203787319","nickname":"?大眼萌娃","avatar_url":""},{"register_id":"227","uid":"374804239","nickname":"小影","avatar_url":""},{"register_id":"260","uid":"386837141","nickname":"小希希","avatar_url":""}]},{"guild_id":"665418055593","guild_name":"火山（猫咪）","admins":[],"otheranchors":[]},{"guild_id":"345135151566","guild_name":"kk唱响","admins":[],"otheranchors":[]},{"guild_id":"700","guild_name":"www","admins":[],"otheranchors":[]},{"guild_id":"336","guild_name":"asfaf","admins":[],"otheranchors":[]},{"guild_id":"869","guild_name":"sfsf","admins":[],"otheranchors":[]},{"guild_id":"550","guild_name":"sfs","admins":[],"otheranchors":[]},{"guild_id":"473","guild_name":"sdfsf","admins":[],"otheranchors":[]}]}
     */
    private CompanyBean company;
    private List<GuildBean> guild;

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public List<GuildBean> getGuild() {
        return guild;
    }

    public void setGuild(List<GuildBean> guild) {
        this.guild = guild;
    }

    public static class CompanyBean implements Serializable{
        /**
         * id : 3
         * registrant_id : 2
         * company_name : Ceshi
         * company_scale : 1222人
         * location : Ceshiduqu
         * logo : null
         */

        private String id;
        private String registrant_id;
        private String company_name;
        private String company_scale;
        private String location;
        private Object logo;
        //管理员申请个数
        private int admins;
        //主播申请个数
        private int anchors;

        public int getAdmins() {
            return admins;
        }

        public void setAdmins(int admins) {
            this.admins = admins;
        }

        public int getAnchors() {
            return anchors;
        }

        public void setAnchors(int anchors) {
            this.anchors = anchors;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegistrant_id() {
            return registrant_id;
        }

        public void setRegistrant_id(String registrant_id) {
            this.registrant_id = registrant_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_scale() {
            return company_scale;
        }

        public void setCompany_scale(String company_scale) {
            this.company_scale = company_scale;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }
    }

    public static class GuildBean {
        /**
         * guild_id : 1234548255
         * guild_name : 菠萝街（coco）
         * admins : [{"id":"353","nickname":"18668121550","avatar_url":"","anchors":[{"uid":"4565950","anchor_name":"66666666","avatar_url":""}]}]
         * otheranchors : [{"register_id":"47","uid":"7300783","nickname":"盾上加鹿","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg"},{"register_id":"48","uid":"7443730","nickname":"四爷","avatar_url":""},{"register_id":"49","uid":"5202247","nickname":"大大","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497506759846958.jpg"},{"register_id":"55","uid":"8933227","nickname":"习惯一个人","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-04/151507391341954725.jpg"},{"register_id":"58","uid":"8925119","nickname":"张杨轩儿","avatar_url":""},{"register_id":"168","uid":"11123496","nickname":"飘爷","avatar_url":""},{"register_id":"172","uid":"4704350","nickname":"我叫小雨吖","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/5a9ce496668ef.jpg"},{"register_id":"166","uid":"6886478","nickname":"栀子","avatar_url":""},{"register_id":"73","uid":"10243769","nickname":"菠萝街","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-05/152023859865917359.jpg"},{"register_id":"176","uid":"6203296","nickname":"性感小狐狸","avatar_url":""},{"register_id":"185","uid":"9365032","nickname":"梅倪","avatar_url":""},{"register_id":"215","uid":"9325396","nickname":"抒情派茉","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-03-14/5aa8cf57221e0.jpg"},{"register_id":"228","uid":"11852438","nickname":"静静","avatar_url":""}]
         */

        private String guild_id;
        private String guild_name;
        private Boolean isShow = false;
        private List<AdminsBean> admins = new ArrayList<>();
        private List<OtheranchorsBean> otheranchors = new ArrayList<>();


        public Boolean getShow() {
            return isShow;
        }

        public void setShow(Boolean show) {
            isShow = show;
        }

        public String getGuild_id() {
            return guild_id;
        }

        public void setGuild_id(String guild_id) {
            this.guild_id = guild_id;
        }

        public String getGuild_name() {
            return guild_name;
        }

        public void setGuild_name(String guild_name) {
            this.guild_name = guild_name;
        }

        public List<AdminsBean> getAdmins() {
            return admins;
        }

        public void setAdmins(List<AdminsBean> admins) {
            this.admins = admins;
        }

        public List<OtheranchorsBean> getOtheranchors() {
            return otheranchors;
        }

        public void setOtheranchors(List<OtheranchorsBean> otheranchors) {
            this.otheranchors = otheranchors;
        }

        public static class AdminsBean {
            /**
             * id : 353
             * nickname : 18668121550
             * avatar_url :
             * anchors : [{"uid":"4565950","anchor_name":"66666666","avatar_url":""}]
             */

            private String id;
            private String nickname;
            private String avatar_url;
            private List<AnchorsBean> anchors = new ArrayList<>();
            private Boolean isShow = false;
            public int is_me;

            public int getIs_me() {
                return is_me;
            }

            public void setIs_me(int is_me) {
                this.is_me = is_me;
            }

            /**
             * 是否打开状态
             */
            private boolean isOpen = false;

            public boolean isOpen() {
                return isOpen;
            }

            public void setOpen(boolean open) {
                isOpen = open;
            }

            public Boolean getShow() {
                return isShow;
            }

            public void setShow(Boolean show) {
                isShow = show;
            }

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

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public List<AnchorsBean> getAnchors() {
                return anchors;
            }

            public void setAnchors(List<AnchorsBean> anchors) {
                this.anchors = anchors;
            }

            public static class AnchorsBean {
                /**
                 * uid : 4565950
                 * anchor_name : 66666666
                 * avatar_url :
                 */
                private String register_id;
                private String uid;
                private String anchor_name;
                private String avatar_url;
                private String nickname;

                /**
                 * 是否打开状态
                 */
                private boolean isOpen = false;

                public boolean isOpen() {
                    return isOpen;
                }

                public void setOpen(boolean open) {
                    isOpen = open;
                }
                public String getRegister_id() {
                    return register_id;
                }

                public void setRegister_id(String register_id) {
                    this.register_id = register_id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getAnchor_name() {
                    return anchor_name;
                }

                public void setAnchor_name(String anchor_name) {
                    this.anchor_name = anchor_name;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }
            }
        }

        public static class OtheranchorsBean {
            /**
             * register_id : 47
             * uid : 7300783
             * nickname : 盾上加鹿
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
             */

            private String register_id;
            private String uid;
            private String nickname;
            private String avatar_url;
            private String anchor_name;

            /**
             * 是否打开状态
             */
            private boolean isOpen = false;

            public boolean isOpen() {
                return isOpen;
            }

            public void setOpen(boolean open) {
                isOpen = open;
            }

            public String getAnchor_name() {
                return anchor_name;
            }

            public void setAnchor_name(String anchor_name) {
                this.anchor_name = anchor_name;
            }

            public String getRegister_id() {
                return register_id;
            }

            public void setRegister_id(String register_id) {
                this.register_id = register_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }
        }
    }
}


