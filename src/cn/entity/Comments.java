package cn.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Comments {
    private Integer id;
    private Integer hotEventsId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date commentDate;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotEventsId() {
        return hotEventsId;
    }

    public void setHotEventsId(Integer hotEventsId) {
        this.hotEventsId = hotEventsId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
