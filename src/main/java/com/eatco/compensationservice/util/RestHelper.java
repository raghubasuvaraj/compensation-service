package com.eatco.compensationservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eatco.compensationservice.dto.RestResponse;

public class RestHelper {

	public static ResponseEntity<RestResponse> responseSuccess(String payloadKey, Object payload) {
		RestResponse restResponse = new RestResponse();
		restResponse.addPayload(payloadKey, payload);
		restResponse.setStatus(true);

		return new ResponseEntity<>(restResponse, HttpStatus.OK);
	}

	public static ResponseEntity<RestResponse> responseError(String msg, HttpStatus httpStatus) {
		RestResponse restResponse = new RestResponse();
		restResponse.addError(msg);
		restResponse.setStatus(false);

		return new ResponseEntity<>(restResponse, httpStatus);
	}

	public static ResponseEntity<RestResponse> responseMessage(String msg, HttpStatus httpStatus) {
		RestResponse restResponse = new RestResponse();
		restResponse.addMessage(msg);
		restResponse.setStatus(true);

		return new ResponseEntity<>(restResponse, httpStatus);
	}
}
