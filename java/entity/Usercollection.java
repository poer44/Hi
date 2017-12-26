package entity;

public class Usercollection {
    private String userid;

    private String collection;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection == null ? null : collection.trim();
    }

	@Override
	public String toString() {
		return "Usercollection [userid=" + userid + ", collection="
				+ collection + "]";
	}

	public Usercollection() {
		super();
	}

	public Usercollection(String userid, String collection) {
		super();
		this.userid = userid;
		this.collection = collection;
	}
    
    
}