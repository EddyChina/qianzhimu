package com.qianzhimu.mgt.exception;

import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.api.utils.StringUtils;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private Response response;

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
