package entity;

public class User {
    private String userid;

    private String password;

    private String username;

    private String imgurl;

    private String sex;

    private String birthday;

    private String address;

    private String emotion;

    private String email;

    private String sign;

    private String registertime;
    private String msg;
    

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion == null ? null : emotion.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime == null ? null : registertime.trim();
    }

	public User() {
		super();
	}

	public User(String userid, String password, String username,
			String birthday, String email) {
		super();
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.birthday = birthday;
		this.email = email;
	}
	
	public User(String userid, String password, String username,
			String birthday, String email,String imgurl) {
		super();
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.birthday = birthday;
		this.email = email;
		this.imgurl=imgurl;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", password=" + password
				+ ", username=" + username + ", imgurl=" + imgurl + ", sex="
				+ sex + ", birthday=" + birthday + ", address=" + address
				+ ", emotion=" + emotion + ", email=" + email + ", sign="
				+ sign + ", registertime=" + registertime + "]";
	}
    
    
}