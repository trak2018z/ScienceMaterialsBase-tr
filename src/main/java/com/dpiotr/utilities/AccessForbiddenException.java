package com.dpiotr.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dpiotr on 20.11.17.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Brak dostępu")
public class AccessForbiddenException extends RuntimeException {
}
