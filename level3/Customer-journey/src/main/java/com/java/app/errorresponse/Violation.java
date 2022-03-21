
package com.java.app.errorresponse;

public class Violation {

  private final String fieldName;

  private final String message;

public String getFieldName() {
	return fieldName;
}

public String getMessage() {
	return message;
}

public Violation(String fieldName, String message) {
	super();
	this.fieldName = fieldName;
	this.message = message;
}
  
  

}