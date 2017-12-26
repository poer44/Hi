package entity;

public class Videocomment {
    private Integer commentid;

    private Integer videoid;

    private String userid;

    private String content;

    private String commenttime;

    public Videocomment(Integer videoid, String userid, String content,
			String commenttime) {
		super();
		this.videoid = videoid;
		this.userid = userid;
		this.content = content;
		this.commenttime = commenttime;
	}

	public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime == null ? null : commenttime.trim();
    }

	public Videocomment() {
		super();
	}

	@Override
	public String toString() {
		return "Videocomment [commentid=" + commentid + ", videoid=" + videoid
				+ ", userid=" + userid + ", content=" + content
				+ ", commenttime=" + commenttime + "]";
	}
    
    
}