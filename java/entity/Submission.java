package entity;

public class Submission {
    private Integer submissionid;

    private String userid;

    private String videoname;

    private String imgurl;

    private String fileurl;

    private String filesize;

    private String format;

    private String submissiontime;

    private String state;

    private String videolength;

    private Integer typeid;

    public Integer getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(Integer submissionid) {
        this.submissionid = submissionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname == null ? null : videoname.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize == null ? null : filesize.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public String getSubmissiontime() {
        return submissiontime;
    }

    public void setSubmissiontime(String submissiontime) {
        this.submissiontime = submissiontime == null ? null : submissiontime.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getVideolength() {
        return videolength;
    }

    public void setVideolength(String videolength) {
        this.videolength = videolength == null ? null : videolength.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

	public Submission() {
		super();
	}

	public Submission(String userid, String videoname, String imgurl,
			String fileurl, String filesize, String format,
			String submissiontime, String state, String videolength,
			Integer typeid) {
		super();
		this.userid = userid;
		this.videoname = videoname;
		this.imgurl = imgurl;
		this.fileurl = fileurl;
		this.filesize = filesize;
		this.format = format;
		this.submissiontime = submissiontime;
		this.state = state;
		this.videolength = videolength;
		this.typeid = typeid;
	}
    
}