package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CommentBean extends BaseBeen {
    private String article_id;
    private String train_id;

    private String comment;
    private String nickname;
    private String reply_to_id;
    private String to_nickname;

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public CommentBean() {
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReply_to_id() {
        return reply_to_id;
    }

    public void setReply_to_id(String reply_to_id) {
        this.reply_to_id = reply_to_id;
    }

    public String getTo_nickname() {
        return to_nickname;
    }

    public void setTo_nickname(String to_nickname) {
        this.to_nickname = to_nickname;
    }
}
