package com.qianzhimu.mgt.exception;

import com.qianzhimu.mgt.utils.StringUtils;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }

}
