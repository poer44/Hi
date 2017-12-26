package entity;

import com.github.pagehelper.PageInfo;

public class JsonResult<T> {
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state = 0;
	private String message = "";
	private T data;
	private PageInfo<T> datas;
	
	
	public JsonResult(String message) {
		this.message = message;
	}

	public JsonResult(int state, String message) {
		this.state = state;
		this.message = message;
	}
	
	public JsonResult(T data) {
		this.data = data;
	}

	public JsonResult(String message, T data) {
		this.message = message;
		this.data = data;
	}

	public JsonResult(PageInfo<T> datas) {
		this.datas = datas;
	}

	public JsonResult(String message, PageInfo<T> datas) {
		this.message = message;
		this.datas = datas;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public PageInfo<T> getDatas() {
		return datas;
	}

	public void setDatas(PageInfo<T> datas) {
		this.datas = datas;
	}
	
	
	
	
}
