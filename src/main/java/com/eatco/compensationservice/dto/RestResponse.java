package com.eatco.compensationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestResponse {

	private static final ObjectMapper mapper = new ObjectMapper();
	private Boolean status;
	private Long userId;
	private List<Message> messages;
	private List<Message> errors;
 	private Map<String,Object> payload;
	
	public String convert() throws JsonProcessingException{
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		return mapper.writeValueAsString(this);
	}

	public RestResponse() {
		this.status = true;
		this.userId = null;
		this.messages = new ArrayList<>();
		this.errors = new ArrayList<>();
		this.payload = new HashMap<>();
	}
	
	public void addError(String msg) {
		errors.add(new Message(msg));
	}
	
	public void addMessage(String msg) {
		messages.add(new Message(msg));
	}
	
	public void addPayload(String key,Object value) {
		this.payload.put(key, value);
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public List<Message> getErrors() {
		return errors;
	}

	public void setErrors(List<Message> errors) {
		this.errors = errors;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
