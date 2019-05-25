package com.tbsm.utils;


/**
 * @author Yogesh Lokare
 *
 */
public class RegistrationTemplate {

	public static final String CLOSE_TAG = "</p>\n";

	public String registerEmail(String firstName, String username, String password, String url) {
		return  "<html>"+
				"<body style=\"font-family: verdana;font-size: 12px;\">" + 
				"<title>Tejovat Building Security Management</title>\n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 150px;border-bottom:  solid 0px;\">\n" + 
				"        <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">" + 
				"           Hello "+firstName+"," + 
				CLOSE_TAG + 
				"        <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">" + 
				"       User account created with below details:" + 
				"        </p>\n" + 
				"         <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">Username: "+username+" \n<br/>" + 
				"        Password:"+password+
				"\n\n"+
				"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">" + 
				"    Please login here:"+url+" " + 
				"        </p>\n" + 
				"\n\n"+
				"  	Best Regards,<br>\n" + 
				"  	The Tejovat Team</p>" + 
				"    </div>\n" + 
				"    </body>" + 
				"</html>" + 
				"";
	}

}
