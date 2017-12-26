package entity;

public class Video {
    private Integer videoid;

    private String videoname;

    private String author;

    private String imgurl;

    private String fileurl;

    private String filesize;

    private String format;

    private Integer play;

    private Integer collection;

    private String uptime;

    private Integer typeid;

    private String videolength;
    private String msg;
    

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Video(String videoname, String author, String imgurl,
			String fileurl, String filesize, String format, Integer play,
			Integer collection, String uptime, Integer typeid,
			String videolength) {
		super();
		this.videoname = videoname;
		this.author = author;
		this.imgurl = imgurl;
		this.fileurl = fileurl;
		this.filesize = filesize;
		this.format = format;
		this.play = play;
		this.collection = collection;
		this.uptime = uptime;
		this.typeid = typeid;
		this.videolength = videolength;
	}

	public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname == null ? null : videoname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
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

    public Integer getPlay() {
        return play;
    }

    public void setPlay(Integer play) {
        this.play = play;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime == null ? null : uptime.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getVideolength() {
        return videolength;
    }

    public void setVideolength(String videolength) {
        this.videolength = videolength == null ? null : videolength.trim();
    }

	public Video() {
		super();
	}

	@Override
	public String toString() {
		return "Video [videoid=" + videoid + ", videoname=" + videoname
				+ ", author=" + author + ", imgurl=" + imgurl + ", fileurl="
				+ fileurl + ", filesize=" + filesize + ", format=" + format
				+ ", play=" + play + ", collection=" + collection + ", uptime="
				+ uptime + ", typeid=" + typeid + ", videolength="
				+ videolength + "]";
	}
    
    
}