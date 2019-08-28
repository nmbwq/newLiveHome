package shangri.example.com.shangri.model.bean.request;

/**
 * Created by admin on 2017/12/25.
 */

public class BossBeen extends BaseBeen {
    //城市名称
    private String area_name;
    //平台id 逗号隔开(1,2,3)
    private String plat_id;
    //平台id 逗号隔开(1,2,3)
    private String anchor_type;
    //薪资区间底
    private String pay_low;
    // 	薪资区间高
    private String pay_high;
    //结算方式 1月结 2周结 3日结
    private String salary_type;
    private String page;
    //    底薪(3000-5000)
    private String keep_pay;

    //收藏取消收藏用
    private String recruit_id;
    //1电话2形象卡片
    private String type;
    private String telephone;
    private String image;
    // 	1列表 2卡片 默认2
    private String nolike;
    //1已滑完(卡片全部滑完后点击去了解更多传次参数 传1)  (已没用)
    private String clickLike;
    ///当前第几条数据 卡片现在是第几个数据   位置是数据下标 从0开始
    private String offset;
    //    3只看官方
    private String publish_type;

    //  标签id  点击优质精选跳转的列表
    private String anchor_tab;
    //   	快速回复 1快速回复（导航类型 type == 5） 0否
    private String response_level;

    //会长给主播留电话接口
    //1查看剩余次数 2留电话(需传下面参数) 默认1
    private String is_leave;
    private String anchor_id;
    private String resume_id;
    private String bd_num;

    //    排序 1推荐 2最新
    private String recommend_new;


    public String getResponse_level() {
        return response_level;
    }

    public void setResponse_level(String response_level) {
        this.response_level = response_level;
    }

    public String getRecommend_new() {
        return recommend_new;
    }

    public void setRecommend_new(String recommend_new) {
        this.recommend_new = recommend_new;
    }

    public String getBd_num() {
        return bd_num;
    }

    public void setBd_num(String bd_num) {
        this.bd_num = bd_num;
    }

    public String getAnchor_tab() {
        return anchor_tab;
    }

    public void setAnchor_tab(String anchor_tab) {
        this.anchor_tab = anchor_tab;
    }

    public String getIs_leave() {
        return is_leave;
    }

    public void setIs_leave(String is_leave) {
        this.is_leave = is_leave;
    }

    public String getAnchor_id() {
        return anchor_id;
    }

    public void setAnchor_id(String anchor_id) {
        this.anchor_id = anchor_id;
    }

    public String getResume_id() {
        return resume_id;
    }

    public void setResume_id(String resume_id) {
        this.resume_id = resume_id;
    }

    public String getPublish_type() {
        return publish_type;
    }

    public void setPublish_type(String publish_type) {
        this.publish_type = publish_type;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    private String job_method;

    public String getJob_method() {
        return job_method;
    }

    public void setJob_method(String job_method) {
        this.job_method = job_method;
    }

    public String getNolike() {
        return nolike;
    }

    public void setNolike(String nolike) {
        this.nolike = nolike;
    }

    public String getClickLike() {
        return clickLike;
    }

    public void setClickLike(String clickLike) {
        this.clickLike = clickLike;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecruit_id() {
        return recruit_id;
    }

    public void setRecruit_id(String recruit_id) {
        this.recruit_id = recruit_id;
    }

    public String getKeep_pay() {
        return keep_pay;
    }

    public void setKeep_pay(String keep_pay) {
        this.keep_pay = keep_pay;
    }

    public BossBeen(String area_name, String plat_id, String anchor_type, String pay_low, String pay_high, String salary_type, String page, String keep_pay) {
        this.area_name = area_name;
        this.plat_id = plat_id;
        this.anchor_type = anchor_type;
        this.pay_low = pay_low;
        this.pay_high = pay_high;
        this.salary_type = salary_type;
        this.page = page;
        this.keep_pay = keep_pay;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public BossBeen() {
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getPlat_id() {
        return plat_id;
    }

    public void setPlat_id(String plat_id) {
        this.plat_id = plat_id;
    }

    public String getAnchor_type() {
        return anchor_type;
    }

    public void setAnchor_type(String anchor_type) {
        this.anchor_type = anchor_type;
    }

    public String getPay_low() {
        return pay_low;
    }

    public void setPay_low(String pay_low) {
        this.pay_low = pay_low;
    }

    public String getPay_high() {
        return pay_high;
    }

    public void setPay_high(String pay_high) {
        this.pay_high = pay_high;
    }

    public String getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(String salary_type) {
        this.salary_type = salary_type;
    }
}
