package com.cts.digitalbook.digitalbookbookservice.clients;

import feign.Response;

public interface FeignHttpExceptionHandler {
	Exception handle(Response response);
}