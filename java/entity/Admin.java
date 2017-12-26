package entity;

public class Admin {
    private String adminid;

    private String password;

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid == null ? null : adminid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
	public Admin() {
		super();
	}

	public Admin(String adminid, String password) {
		super();
		this.adminid = adminid;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", password=" + password + "]";
	}
    
	
    
}