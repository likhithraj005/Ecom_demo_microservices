package com.likhith.microservices.usermservice.dto;

import lombok.Data;

@Data
public class UserRequest {
	private String name;
	private String email;
}