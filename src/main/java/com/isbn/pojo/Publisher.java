package com.isbn.pojo;

public class Publisher {
	
	private Integer id;
	
	private String accountId;
	
	private String email;
	
	private String userName;
	
	private String password;
	
	private Boolean isAuth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", accountId=" + accountId + ", email="
				+ email + ", userName=" + userName + ", password=" + password
				+ ", isAuth=" + isAuth + "]";
	}

}
