package shangri.example.com.shangri.model.bean.response;

import java.util.List;

public class ReportBean {

        private List<GuildsBean> guilds;
        private List<AdminsBean> admins;

        public List<GuildsBean> getGuilds() {
            return guilds;
        }

        public void setGuilds(List<GuildsBean> guilds) {
            this.guilds = guilds;
        }

        public List<AdminsBean> getAdmins() {
            return admins;
        }

        public void setAdmins(List<AdminsBean> admins) {
            this.admins = admins;
        }

        public static class GuildsBean {
            /**
             * guild_id : 53425479107
             * guild_name : 小米（猫咪）
             * icon_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/icon/2017-12-11/151298468491184578.jpg
             * data : {"name":"收益星票","current":{"gift":0,"anchor_count":0},"pre_current":{"gift":0,"anchor_count":0}}
             */

            private String guild_id;
            private String guild_name;
            private String icon_url;
            private DataBean data;

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

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * name : 收益星票
                 * current : {"gift":0,"anchor_count":0}
                 * pre_current : {"gift":0,"anchor_count":0}
                 */

                private String name;
                private CurrentBean current;
                private PreCurrentBean pre_current;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public CurrentBean getCurrent() {
                    return current;
                }

                public void setCurrent(CurrentBean current) {
                    this.current = current;
                }

                public PreCurrentBean getPre_current() {
                    return pre_current;
                }

                public void setPre_current(PreCurrentBean pre_current) {
                    this.pre_current = pre_current;
                }

                public static class CurrentBean {
                    /**
                     * gift : 0
                     * anchor_count : 0
                     */

                    private int gift;
                    private int anchor_count;

                    public int getGift() {
                        return gift;
                    }

                    public void setGift(int gift) {
                        this.gift = gift;
                    }

                    public int getAnchor_count() {
                        return anchor_count;
                    }

                    public void setAnchor_count(int anchor_count) {
                        this.anchor_count = anchor_count;
                    }
                }

                public static class PreCurrentBean {
                    /**
                     * gift : 0
                     * anchor_count : 0
                     */

                    private int gift;
                    private int anchor_count;

                    public int getGift() {
                        return gift;
                    }

                    public void setGift(int gift) {
                        this.gift = gift;
                    }

                    public int getAnchor_count() {
                        return anchor_count;
                    }

                    public void setAnchor_count(int anchor_count) {
                        this.anchor_count = anchor_count;
                    }
                }
            }
        }

        public static class AdminsBean {
            /**
             * id : 47
             * nickname : 13185025851
             * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-01-03/151497090394139368.jpg
             * inspect_num : 0
             * inspect_anchors : 0
             * pre_inspect_num : 7
             * pre_inspect_anchors : 7
             */

            private int id;
            private String nickname;
            private String avatar_url;
            private int inspect_num;
            private int inspect_anchors;
            private int pre_inspect_num;
            private int pre_inspect_anchors;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public int getInspect_num() {
                return inspect_num;
            }

            public void setInspect_num(int inspect_num) {
                this.inspect_num = inspect_num;
            }

            public int getInspect_anchors() {
                return inspect_anchors;
            }

            public void setInspect_anchors(int inspect_anchors) {
                this.inspect_anchors = inspect_anchors;
            }

            public int getPre_inspect_num() {
                return pre_inspect_num;
            }

            public void setPre_inspect_num(int pre_inspect_num) {
                this.pre_inspect_num = pre_inspect_num;
            }

            public int getPre_inspect_anchors() {
                return pre_inspect_anchors;
            }

            public void setPre_inspect_anchors(int pre_inspect_anchors) {
                this.pre_inspect_anchors = pre_inspect_anchors;
            }
        }

}
