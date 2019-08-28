package shangri.example.com.shangri;

import android.util.Log;

import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.util.Prefs;

/**
 * 用户配置信息
 * Created by chengaofu on 2017/6/28.
 */

public class UserConfig {

    public static final String APP_FILE = "file"; //文件名
    public static final String APP_TOKEN = "token"; //APP应用内的token值
    public static final String APP_ROLE = "role"; //  角色0无权限 1公会长 2主播 3管理员

    public static final String APP_VISITOR = "visitor"; // 0 不是游客身份   1是游客身份（注册了 没有选角色也是游客身份）

    public static final String USER_NAME = "username"; //用户名
    public static final String USER_ID = "id"; //用户id
    public static final String USER_MOBILE = "mobile"; //手机号
    public static final String WXINFO_ID = ""; //微信id

    public static final String USER_AVATAR = "avatar"; //头像
    public static final String NICKNAME = "nickname"; //昵称
    public static final String USER_GENDER = "gender"; //性别
    public static final String USER_SIGN = "sign"; //个性签名
    public static final String BINGPHONE = "bingphone"; //绑定手机号


    public static final String USER_ENABLE = "enable";
    public static final String USER_ROLEID = "roleId";
    public static final String LOGIN_MODEL = "login_model"; //登陆方式 1为手机验证登陆 2 为用户密码登陆
    public static final String REMEMBER_PWD = "remember_pwd"; //记住密码
    public static final String WIFI_LIMITED = "wifi_limited"; //仅wifi可以播放
    public static final String USER_PWD = "pwd"; //密码
    public static final String USER_PWD_IS_SET = "pwd_is_set"; //设置界面显示 设置密码 还是修改密码
    public static final String DATE = ""; //用来判断是不是同一天的

    public static final String IS_RESUME = "is_resume"; //是否有简历 1是 0否

    public static final String IS_BOSS_SHOW = "is_boss_show"; //boss提示图是都显示过   1是 0否

    public static final String IS_FIVE_SHOW = "is_newweb_show"; //   1 点击过新手攻略或是如何赚钱按钮 0  默认没有

    public static final String IS_SEROLL_SHOW = "is_scroll_show"; // 0是没有滑动过得  1是滑动过的

    public static final String IS_FAWU_SHOW = "is_fawu_show"; //法务提示图是都显示过   1是 0否
    public static final String RESUME_TELEPHONE = "resume_telephone"; //简历电话
    public static final String IS_FIRST_IN = "is_first_in"; //第一次進入app?    1是 0否

    private static UserConfig mINSTANCE;

    public static UserConfig getInstance() {
        if (mINSTANCE == null) {
            mINSTANCE = new UserConfig();
        }
        return mINSTANCE;
    }

    public String getIsFirstIn() {
        return Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(IS_FIRST_IN, "0");
    }

    public void setIsFirstIn(String isFirstIn) {
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(IS_FIRST_IN, isFirstIn);
    }


    public void pwdIsSet(boolean pwdIsSet) { //设置界面显示 设置密码 还是修改密码
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).writeBoolean(USER_PWD_IS_SET, pwdIsSet);
    }

    public boolean getPwdIsSet() {
        boolean pwdIsSet = Prefs.with(GlobalApp.getAppContext(), APP_FILE).readBoolean(USER_PWD_IS_SET, false);
        return pwdIsSet;
    }


    public void pwdIsBossShow(String is_boss_show) { //
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(IS_BOSS_SHOW, is_boss_show);
    }

    public String getIsBossShow() {
        String date = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(IS_BOSS_SHOW, "0");
        return date;
    }

    public void pwdIsFiveShow(String is_newweb_show) { //
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(IS_FIVE_SHOW, is_newweb_show);
    }

    public String getIsFiveShow() {
        String date = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(IS_FIVE_SHOW, "0");
        return date;
    }


    public void pwdIsScrollShow(String is_scroll_show) { //
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(IS_SEROLL_SHOW, is_scroll_show);
    }

    public String getIsScrollShow() {
        String date = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(IS_SEROLL_SHOW, "0");
        return date;
    }


    public void pwdIsFawuShow(String is_fawu_show) { //
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(IS_FAWU_SHOW, is_fawu_show);
    }

    public String getIsFawuShow() {
        String date = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(IS_FAWU_SHOW, "0");
        return date;
    }


    public void setDate(String date) { //设置时间
        if (date != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(DATE, date);
        }
    }

    public String getDate() {
        String date = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(DATE, "");
        return date;
    }

    public void setIsresume(String is_resume) { //设置密码
        if (is_resume != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(IS_RESUME, is_resume);
        }
    }

    public String getIsresume() {
        String is_resume = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(IS_RESUME, "");
        return is_resume;
    }


    public void setResumeTelephone(String resume_telephone) { //设置密码
        if (resume_telephone != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(RESUME_TELEPHONE, resume_telephone);
        }
    }

    public String getResumeTelephone() {
        String is_resume = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(RESUME_TELEPHONE, "");
        return is_resume;
    }


    public void setPwd(String pwd) { //设置密码
        if (pwd != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_PWD, pwd);
        }
    }

    public String getPwd() {
        String pwd = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_PWD, "");
        return pwd;
    }


    public String getWxinfoId() {
        String wxid = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(WXINFO_ID, "");
        return wxid;
    }

    public void setWxinfoId(String wxid) { //设置密码
        if (wxid != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(WXINFO_ID, wxid);
        }
    }

    public void setWifiLimited(boolean isWifiLimited) { //设置是否只有wifi可以播放
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).writeBoolean(WIFI_LIMITED, isWifiLimited);
    }

    public boolean isWifiLimited() { //是否只有wifi可以播放
        boolean isWifiLimited = Prefs.with(GlobalApp.getAppContext(), APP_FILE).readBoolean(WIFI_LIMITED, false);
        return isWifiLimited;
    }


    public void setRememberPwd(boolean isRememberPwd) { //记住密码
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).writeBoolean(REMEMBER_PWD, isRememberPwd);
    }

    public boolean isRememberPwd() { //是否记住密码
        boolean isRememberPwd = Prefs.with(GlobalApp.getAppContext(), APP_FILE).readBoolean(REMEMBER_PWD, false);
        return isRememberPwd;
    }

    public void setLoginModel(int loginModel) { //设置登陆方式的值
        if (loginModel != 0) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).writeInt(LOGIN_MODEL, loginModel);
        }
    }

    public int getLoginModel() { //得到登陆方式的值
        int loginModel = Prefs.with(GlobalApp.getAppContext(), APP_FILE).readInt(LOGIN_MODEL, 0);
        return loginModel;
    }

    public void setToken(String token) { //设置token值
        if (token != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(APP_TOKEN, token);
        }
    }

    public String getToken() { //得到token值
        String token = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(APP_TOKEN, "");
        return token;
    }

    public void setRole(String role) { //设置角色
        if (role != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(APP_ROLE, role);
        }
    }

    public String getRole() { //得到角色  游客身份默认是主播身份
        String role = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(APP_ROLE, "0");
        return role;
    }


    public void setVisitor(String visitor) { //设置是不是游客身份
        if (visitor != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(APP_VISITOR, visitor);
        }
    }

    public String getVisitor() { //得到角色  游客身份默认是主播身份
        String visitor = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(APP_VISITOR, "1");
        return visitor;
    }


    public void setUserId(long userId) { //设置userId
        if (userId != 0) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).writeLong(USER_ID, userId);
        }
    }

    public long getUserId() { //得到userId值
        long userId = Prefs.with(GlobalApp.getAppContext(), APP_FILE).readLong(USER_ID);
        return userId;
    }

    public void setUserName(String userName) { //设置userName
        if (userName != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_NAME, userName);
        }
    }

    public String getUserName() { //得到username值
        String userName = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_NAME);
        return userName;
    }


    public void setMobile(String mobile) { //设置手机号
        if (mobile != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_MOBILE, mobile);
        }
    }

    public String getMobile() { //得到手机号
        String mobile = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_MOBILE) + "";
        return mobile;
    }

    public void setAvatar(String avatar) { //设置头像
        if (avatar != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_AVATAR, avatar);
        }
    }

    public String getAvatar() { //得到头像
        String avatar = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_AVATAR);
        return avatar;
    }

    public void setNickname(String avatar) { //设置头像
        if (avatar != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(NICKNAME, avatar);
        }
    }

    public String getNickname() { //得到头像
        String avatar = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(NICKNAME);
        return avatar;
    }

    public void setSign(String sign) { //设置个人签名
        if (sign != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_SIGN, sign);
        }
    }

    public String getSign() { //得到个人签名
        String sign = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_SIGN);
        return sign;
    }

    public void setGender(String gender) { //设置用户性别
        if (gender != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_GENDER, gender);
        }
    }

    public String getBingphone() { //得到个人签名
        String sign = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(BINGPHONE);
        return sign;
    }

    public void setBingphone(String gender) { //设置用户性别
        if (gender != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(BINGPHONE, gender);
        }
    }


    public String getGender() { //得到用户性别
        String sign = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_GENDER);
        return sign;
    }

    public void setEnable(Integer enable) { //设置enable
        if (enable != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).writeInt(USER_ENABLE, enable);
        }
    }

    public Integer getEnable() { //得到enable
        Integer enable = Prefs.with(GlobalApp.getAppContext(), APP_FILE).readInt(USER_ENABLE);
        return enable;
    }

    public void setRoleId(String roleId) { //设置roleId
        if (roleId != null) {
            Prefs.with(GlobalApp.getAppContext(), APP_FILE).write(USER_ROLEID, roleId);
        }
    }

    public String getRoleId() { //得到enable
        String roleId = Prefs.with(GlobalApp.getAppContext(), APP_FILE).read(USER_ROLEID);
        return roleId;
    }

    public void clear() {
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).clear();
    }

    public void removeKey(String key) {
        Prefs.with(GlobalApp.getAppContext(), APP_FILE).remove(key);
    }
}
