package com.benkitoumiraouycoders.ecommerce.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseDto {
    private int errorCode;
    private String message;
    private String field;
    private Object value;
    private String reason;

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getField() {
        return this.field;
    }

    public Object getValue() {
        return this.value;
    }

    public String getReason() {
        return this.reason;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseDto)) {
            return false;
        } else {
            ResponseDto other = (ResponseDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getErrorCode() != other.getErrorCode()) {
                return false;
            } else {
                label61: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label61;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$field = this.getField();
                    Object other$field = other.getField();
                    if (this$field == null) {
                        if (other$field == null) {
                            break label54;
                        }
                    } else if (this$field.equals(other$field)) {
                        break label54;
                    }

                    return false;
                }

                Object this$value = this.getValue();
                Object other$value = other.getValue();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                Object this$reason = this.getReason();
                Object other$reason = other.getReason();
                if (this$reason == null) {
                    if (other$reason != null) {
                        return false;
                    }
                } else if (!this$reason.equals(other$reason)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseDto;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getErrorCode();
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $field = this.getField();
        result = result * 59 + ($field == null ? 43 : $field.hashCode());
        Object $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        Object $reason = this.getReason();
        result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getErrorCode();
        return "ResponseDto(errorCode=" + var10000 + ", message=" + this.getMessage() + ", field=" + this.getField() + ", value=" + this.getValue() + ", reason=" + this.getReason() + ")";
    }

    public ResponseDto() {
    }

    public ResponseDto(int errorCode, String message, String field, Object value, String reason) {
        this.errorCode = errorCode;
        this.message = message;
        this.field = field;
        this.value = value;
        this.reason = reason;
    }
}

