package com.madridonyou.micro.domain.dynamoTables;

public enum MoyUsers {

	USER_ID("userId"),
	PASSWORD("password"),
	USERNAME("username"),
	NAME("name"),
	EMAIL("email"),
	TABLENAME("moy_users");
	
	private final String text;

	
	private MoyUsers(final String text) {
		this.text = text;
	}

	public String getField() {
        return text;
    }
}
