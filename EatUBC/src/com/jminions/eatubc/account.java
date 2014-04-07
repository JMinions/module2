package com.jminions.eatubc;


public class account {
		private String username;
		private String password;
	public account(String user, String pass){
		this.username = user;
		this.password = pass;

	}



	public void setuser(String user){
		this.username = user;
	}

	public void setpass(String pass){
		this.password = pass;
	}

	public String getpass(){
		return this.password;
	}

	public String getuser(){
		return this.username;
	}

	public account test(){
		return this;
	}

}