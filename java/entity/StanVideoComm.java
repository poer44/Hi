package entity;

public class StanVideoComm extends Videocomment{
	private String videoname;

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	@Override
	public String toString() {
		return "StanVideoComm [videoname=" + videoname + ", getCommentid()="
				+ getCommentid() + ", getVideoid()=" + getVideoid()
				+ ", getUserid()=" + getUserid() + ", getContent()="
				+ getContent() + ", getCommenttime()=" + getCommenttime()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
