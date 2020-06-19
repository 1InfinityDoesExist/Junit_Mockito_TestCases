package in.patel.javafeature.drastita.exception;

import lombok.Getter;

@Getter
public enum Error {


  EMAIL_ALREADY_EXIST("email_already_exist"),

  CONTACT_DOES_NOT_EXIST("contact_does_not_exit");


  private String message;

  Error(String message) {
    this.message = message;
  }

}
