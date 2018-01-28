package com.madridonyou.micro.domain.inputs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"username",
	"password",
	"mail"
})
public class RegisterInput {

	public RegisterInput() {}

	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("mail")
	private String mail;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("update")
	private boolean update;
	
	@JsonProperty("update")
	public boolean isUpdate() {
		return update;
	}

	@JsonProperty("update")
	public void setUpdate(boolean update) {
		this.update = update;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty("mail")
	public String getMail() {
		return mail;
	}

	@JsonProperty("mail")
	public void setMail(String mail) {
		this.mail = mail;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Iterator<String> getProperties() {

		List<String> props = new LinkedList<String>();
		props.add(this.username);
		props.add(this.password);
		props.add(this.mail);
		return props.iterator();
	}
}