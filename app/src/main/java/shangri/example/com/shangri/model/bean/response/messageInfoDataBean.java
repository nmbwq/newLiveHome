package shangri.example.com.shangri.model.bean.response;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by Administrator on 2017/12/30.
 */

public class messageInfoDataBean extends BaseBeen {


        /**
         * count_total : 10
         * list : {"current_page":1,"data":[{"id":3,"chairman_id":2,"resume_id":139,"create_time":1545814687,"guild_delete_time":0,"anchor_delete_time":0,"anchor_not_read_count":0,"guild_not_read_count":6,"avatar":{"id":2,"nickname":"白小白","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-07-20/5b51af6e00014.jpg"},"last_info":{"id":23,"chat_id":3,"send_id":139,"send_type":2,"receive_id":2,"message_type":4,"content":"白小白的微信号","contact_way":"cbsg","img_url":"","recruit_id":0,"is_read":1,"create_time":1545814687}}],"first_page_url":"http://zhibozhijia.com/api/swop/message/info?page=1","from":1,"last_page":1,"last_page_url":"http://zhibozhijia.com/api/swop/message/info?page=1","next_page_url":null,"path":"http://zhibozhijia.com/api/swop/message/info","per_page":20,"prev_page_url":null,"to":1,"total":1}
         */

        private int count_total;
        private ListBean list;

        public int getCount_total() {
            return count_total;
        }

        public void setCount_total(int count_total) {
            this.count_total = count_total;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * current_page : 1
             * data : [{"id":3,"chairman_id":2,"resume_id":139,"create_time":1545814687,"guild_delete_time":0,"anchor_delete_time":0,"anchor_not_read_count":0,"guild_not_read_count":6,"avatar":{"id":2,"nickname":"白小白","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-07-20/5b51af6e00014.jpg"},"last_info":{"id":23,"chat_id":3,"send_id":139,"send_type":2,"receive_id":2,"message_type":4,"content":"白小白的微信号","contact_way":"cbsg","img_url":"","recruit_id":0,"is_read":1,"create_time":1545814687}}]
             * first_page_url : http://zhibozhijia.com/api/swop/message/info?page=1
             * from : 1
             * last_page : 1
             * last_page_url : http://zhibozhijia.com/api/swop/message/info?page=1
             * next_page_url : null
             * path : http://zhibozhijia.com/api/swop/message/info
             * per_page : 20
             * prev_page_url : null
             * to : 1
             * total : 1
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBean> data;

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

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
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

            public Object getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(Object next_page_url) {
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

            public Object getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(Object prev_page_url) {
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

            public static class DataBean {
                /**
                 * id : 3
                 * chairman_id : 2
                 * resume_id : 139
                 * create_time : 1545814687
                 * guild_delete_time : 0
                 * anchor_delete_time : 0
                 * anchor_not_read_count : 0
                 * guild_not_read_count : 6
                 * avatar : {"id":2,"nickname":"白小白","avatar_url":"http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-07-20/5b51af6e00014.jpg"}
                 * last_info : {"id":23,"chat_id":3,"send_id":139,"send_type":2,"receive_id":2,"message_type":4,"content":"白小白的微信号","contact_way":"cbsg","img_url":"","recruit_id":0,"is_read":1,"create_time":1545814687}
                 */

                private int id;
                private int chairman_id;
                private int resume_id;
                private int create_time;
                private int guild_delete_time;
                private int anchor_delete_time;
                private int anchor_not_read_count;
                private int guild_not_read_count;
                private AvatarBean avatar;
                private LastInfoBean last_info;
                boolean isOpen;



                public boolean isOpen() {
                    return isOpen;
                }

                public void setOpen(boolean open) {
                    isOpen = open;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getChairman_id() {
                    return chairman_id;
                }

                public void setChairman_id(int chairman_id) {
                    this.chairman_id = chairman_id;
                }

                public int getResume_id() {
                    return resume_id;
                }

                public void setResume_id(int resume_id) {
                    this.resume_id = resume_id;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public int getGuild_delete_time() {
                    return guild_delete_time;
                }

                public void setGuild_delete_time(int guild_delete_time) {
                    this.guild_delete_time = guild_delete_time;
                }

                public int getAnchor_delete_time() {
                    return anchor_delete_time;
                }

                public void setAnchor_delete_time(int anchor_delete_time) {
                    this.anchor_delete_time = anchor_delete_time;
                }

                public int getAnchor_not_read_count() {
                    return anchor_not_read_count;
                }

                public void setAnchor_not_read_count(int anchor_not_read_count) {
                    this.anchor_not_read_count = anchor_not_read_count;
                }

                public int getGuild_not_read_count() {
                    return guild_not_read_count;
                }

                public void setGuild_not_read_count(int guild_not_read_count) {
                    this.guild_not_read_count = guild_not_read_count;
                }

                public AvatarBean getAvatar() {
                    return avatar;
                }

                public void setAvatar(AvatarBean avatar) {
                    this.avatar = avatar;
                }

                public LastInfoBean getLast_info() {
                    return last_info;
                }

                public void setLast_info(LastInfoBean last_info) {
                    this.last_info = last_info;
                }

                public static class AvatarBean {
                    /**
                     * id : 2
                     * nickname : 白小白
                     * avatar_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/avatar/2018-07-20/5b51af6e00014.jpg
                     */

                    private int id;
                    private String nickname;
                    private String avatar_url;

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
                }

                public static class LastInfoBean {
                    /**
                     * id : 23
                     * chat_id : 3
                     * send_id : 139
                     * send_type : 2
                     * receive_id : 2
                     * message_type : 4
                     * content : 白小白的微信号
                     * contact_way : cbsg
                     * img_url :
                     * recruit_id : 0
                     * is_read : 1
                     * create_time : 1545814687
                     */

                    private int id;
                    private int chat_id;
                    private int send_id;
                    private int send_type;
                    private int receive_id;
                    private int message_type;
                    private String content;
                    private String contact_way;
                    private String img_url;
                    private int recruit_id;
                    private int is_read;
                    private int create_time;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getChat_id() {
                        return chat_id;
                    }

                    public void setChat_id(int chat_id) {
                        this.chat_id = chat_id;
                    }

                    public int getSend_id() {
                        return send_id;
                    }

                    public void setSend_id(int send_id) {
                        this.send_id = send_id;
                    }

                    public int getSend_type() {
                        return send_type;
                    }

                    public void setSend_type(int send_type) {
                        this.send_type = send_type;
                    }

                    public int getReceive_id() {
                        return receive_id;
                    }

                    public void setReceive_id(int receive_id) {
                        this.receive_id = receive_id;
                    }

                    public int getMessage_type() {
                        return message_type;
                    }

                    public void setMessage_type(int message_type) {
                        this.message_type = message_type;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getContact_way() {
                        return contact_way;
                    }

                    public void setContact_way(String contact_way) {
                        this.contact_way = contact_way;
                    }

                    public String getImg_url() {
                        return img_url;
                    }

                    public void setImg_url(String img_url) {
                        this.img_url = img_url;
                    }

                    public int getRecruit_id() {
                        return recruit_id;
                    }

                    public void setRecruit_id(int recruit_id) {
                        this.recruit_id = recruit_id;
                    }

                    public int getIs_read() {
                        return is_read;
                    }

                    public void setIs_read(int is_read) {
                        this.is_read = is_read;
                    }

                    public int getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(int create_time) {
                        this.create_time = create_time;
                    }
                }
            }
        }
}
