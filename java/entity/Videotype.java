package entity;

public class Videotype {
    private Integer typeid;

    private String typename;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

	public Videotype() {
		super();
	}

	@Override
	public String toString() {
		return "Videotype [typeid=" + typeid + ", typename=" + typename + "]";
	}
    
    
}