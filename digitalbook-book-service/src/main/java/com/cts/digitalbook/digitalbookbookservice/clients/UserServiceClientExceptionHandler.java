package com.cts.digitalbook.digitalbookbookservice.clients;

import org.apache.http.HttpStatus;
import org.springframework.cloud.openfeign.support.FeignUtils;
import org.springframework.stereotype.Component;

import feign.Response;

@Component
public class UserServiceClientExceptionHandler implements FeignHttpExceptionHandler {

	@Override
	public Exception handle(Response response) {
		// TODO Auto-generated method stub
		return null;
	}
	// @Override
//    public Exception handle(Response response) {
//        HttpStatus httpStatus = HttpStatus.resolve(response.status());
//        String body = FeignUtils.readBody(response.body());
//        if (HttpStatus.NOT_FOUND.equals(httpStatus)) {
//            return new UserNotFoundException(body);
//        }
//        return new RuntimeException(body);
//    }

}