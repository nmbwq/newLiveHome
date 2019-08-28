package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * @author J
 */
public class SortModel implements Serializable {

    private String name;
    private String sortLetters;
    private boolean isChecked;
    private String iconUrl;
    private String id;
    private String register_guild_id;

    //1添加公会时公会名称布局  2 添加主播 布局
    private String is_paltfrom;
    private String table_flag;

    public String getTable_flag() {
        return table_flag;
    }

    public void setTable_flag(String table_flag) {
        this.table_flag = table_flag;
    }

    public String getIs_paltfrom() {
        return is_paltfrom;
    }

    public void setIs_paltfrom(String is_paltfrom) {
        this.is_paltfrom = is_paltfrom;
    }

    public String getRegister_guild_id() {
        return register_guild_id;
    }

    public void setRegister_guild_id(String register_guild_id) {
        this.register_guild_id = register_guild_id;
    }
    //	public SortModel(String name, String sortLetters, boolean isChecked,
//					 String iconUrl, int sex) {
//		super();
//		this.name = name;
//		this.sortLetters = sortLetters;
//		this.isChecked = isChecked;
//		this.iconUrl = iconUrl;
//		this.id = sex;
//	}

    public SortModel() {
        super();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
