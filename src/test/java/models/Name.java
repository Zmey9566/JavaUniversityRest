package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name{

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	public String getFirstname(){
		return firstname;
	}

	public String getLastname(){
		return lastname;
	}
}