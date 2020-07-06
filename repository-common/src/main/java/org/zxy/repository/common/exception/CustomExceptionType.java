package org.zxy.repository.common.exception;

public enum CustomExceptionType {
    /**
     * 异常枚举
     */
    FORBIDDEN_ERROR(403,"权限不足"),
    USER_INPUT_ERROR(400,"用户输入异常"),
    SYSTEM_ERROR (500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    /**
     * 异常类型中文描述
     */
    private String typeDesc;

    /**
     * code
     */
    private int code;

    public String getTypeDesc() {
        return typeDesc;
    }

    public int getCode() {
        return code;
    }
}