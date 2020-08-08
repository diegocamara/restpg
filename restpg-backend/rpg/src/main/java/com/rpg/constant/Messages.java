package com.rpg.constant;

public class Messages {

  public static final String NAME_VALIDATION_MESSAGE =
      new Message("name.validation.message", "name must be between 4 and 20 characters").toString();

  public static class Message {

    private String key;
    private String defaultValue;

    public Message() {}

    public Message(String key, String defaultValue) {
      this.key = key;
      this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
      return "{" + "key='" + key + '\'' + ", defaultValue='" + defaultValue + '\'' + '}';
    }
  }
}
