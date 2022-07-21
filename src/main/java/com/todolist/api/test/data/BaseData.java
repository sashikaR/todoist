package com.todolist.api.test.data;

public class BaseData {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final String AUTHORIZATION = "Authorization";

    //Header Data - Enums
    public enum Content {

        CONTENT_JSON("application/json");

        private final String value;

        Content(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Accept {
        ACCEPT_JSON("application/json");

        private final String value;

        Accept(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
