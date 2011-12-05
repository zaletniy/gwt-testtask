package com.example.test.task.client;

import java.util.Date;

public interface NonStringMessages extends com.google.gwt.i18n.client.Messages {
	  @DefaultMessage("{0,date,medium}")
	  @Key("dateFormat")
	  String dateFormat(Date arg0);
	  
	  @AlternateMessage({
	       "FEMALE", "{0} likes her widgets.",
	       "MALE", "{0} likes his widgets.",
	   })
	   String roles(@Select String roleName);

}
