package com.gestion.clientes.application.dto;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String code;
    private String message;
    private String details;

    private ErrorDetails(Builder builder) {
        this.timestamp = builder.timestamp;
        this.code = builder.code;
        this.message = builder.message;
        this.details = builder.details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public static class Builder {
        private Date timestamp;
        private String code;
        private String message;
        private String details;

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this);
        }
    }
}

