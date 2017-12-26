package entity;

public class Barrage {
    private Integer barrageid;

    private Integer videoid;

    private String userid;

    private String content;

    private String videotime;

    private String barragetime;
    private String msg;

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getBarrageid() {
        return barrageid;
    }

    public void setBarrageid(Integer barrageid) {
        this.barrageid = barrageid;
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

    public String getVideotime() {
        return videotime;
    }

    public void setVideotime(String videotime) {
        this.videotime = videotime == null ? null : videotime.trim();
    }

    public String getBarragetime() {
        return barragetime;
    }

    public void setBarragetime(String barragetime) {
        this.barragetime = barragetime == null ? null : barragetime.trim();
    }

	public Barrage() {
		super();
	}

	public Barrage(Integer videoid, String userid, String content,
			String videotime, String barragetime) {
		super();
		this.videoid = videoid;
		this.userid = userid;
		this.content = content;
		this.videotime = videotime;
		this.barragetime = barragetime;
	}

	@Override
	public String toString() {
		return "Barrage [barrageid=" + barrageid + ", videoid=" + videoid
				+ ", userid=" + userid + ", content=" + content
				+ ", videotime=" + videotime + ", barragetime=" + barragetime
				+ ", msg=" + msg + "]";
	}
	
	
    
    
}