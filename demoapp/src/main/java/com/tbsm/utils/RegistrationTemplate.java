package com.tbsm.utils;


/**
 * @author Yogesh Lokare
 *
 */
public class RegistrationTemplate {

	public static final String CLOSE_TAG = "</p>\n";
	public static final String LOGO = "http://35.182.236.2/application/img/print/Webp.net-compress-image.jpg";
	
	public String registerEmail(String firstName, String username, String password, String url) {
		return  "<html>"+
				"<body style=\"font-family: verdana;font-size: 12px;\">" + 
				"<title>Experience</title>\n" + 
				"<div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 600px;border: solid 1px rgb(220,220,220);\">    \n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 100px;border-bottom:  solid 1px  rgb(220,220,220);text-align: center\">\n" + 
				"        <img style=\"width: 200px;height: 75px;margin-top: 10px;\" src=\"http://35.182.236.2/application/img/print/logo.png\" />\n" + 
				"    </div>\n" + 
				"\n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 150px;border-bottom:  solid 1px  rgb(220,220,220);\">\n" + 
				"        <img src="+LOGO+" style=\"width: 500px;height: 150px;\">\n" + 
				"    </div>\n" + 
				"\n" + 
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
				"    </div>\n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 150px;border-bottom:  solid 1px  rgb(220,220,220);\">\n" + 
				"        <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">\n" + 
				"            <b>Our Vision</b>\n" + 
				"            <br/>\n" + 
				"            Our mission is to empower students and give young people the opportunity to discover themselves through new travel experiences. \n" + 
				"            What makes a transformative experience? This question drives our innovation strategy and everything we do. \n" + 
				"            We believe we can redefine student travel through our passionate culture, integrated student tour operator services, and care for our customers." + 
				"        </p>" + 
				"    </div>" + 
				"    <div style=\"margin: 0 auto;height: auto;min-height: 50px;border-bottom:  solid 0px;\">\n" + 
				"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">\n" + 
				"  	Best Regards,<br>\n" + 
				"  	The Experience Team</p>" + 
				"    </div>" + 
				"    <div style=\"background-color: #333333; height:90px;\">\n" + 
				"        <table style=\"width: 98%;\" align='center'>\n" + 
				"            <tr>\n" + 
				"                <td style=\"width: 45%\">\n" + 
				"                    <p style=\"line-height: 25px;font-family: verdana;font-size: 12px;color: white\">\n" + 
				"                        Experience America<br/>\n" + 
				"                        4556 University Way Ne Ste 200<br/>\n" + 
				"                        Seattle, WA 98105<br/>\n" + 
				CLOSE_TAG + 
				"                </td>\n" + 
				"                <td style=\"width: 10%\"></td>\n" + 
				"                <td >\n" + 
				"                    <p style=\"line-height: 25px;font-family: verdana;font-size: 12px;color: white\">\n" + 
				"                        www.experienceamerica.com<br/>\n" + 
				"                        hello@experienceamerica.com<br/>\n" + 
				"                        +1 (800) 410-6088\n" + 
				"                    </p>\n" + 
				"                </td>\n" + 
				"            </tr>\n" + 
				"        </table>\n" + 
				"    </div>\n" + 
				"</div>"+
				"    </body>" + 
				"</html>" + 
				"";
	}

	public String forgotEmail(String firstName, String forgotUrl) {
		return  "<html>"+
				"<body style=\"font-family: verdana;font-size: 12px;\">" + 
				"<title>Experience</title>\n" + 
				"<div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 250px;border: solid 1px rgb(220,220,220);\">    \n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 100px;border-bottom:  solid 1px  rgb(220,220,220);text-align: center\">\n" + 
				"        <img style=\"width: 200px;height: 75px;margin-top: 10px;\" src=\"http://35.182.236.2/application/img/print/logo.png\" />\n" + 
				"    </div>\n" + 
				"\n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 150px;border-bottom:  solid 1px  rgb(220,220,220);\">\n" + 
				"        <img src="+LOGO+"style=\"width: 500px;height: 150px;\">\n" + 
				"    </div>\n" + 
				"\n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 150px;border-bottom:  solid 0px;\">\n" + 
				"        <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">" + 
				"           Hello "+firstName+"," + 
				"        </p>\n" + 
				"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">" + 
				"    To reset your password, click the link below:"+
				"        </p>\n" + 
				"        <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\"><a>"+ 
				forgotUrl+"</a>"+
				"        </p>" + 
				"    </div>\n" + 
				"    <div style=\"margin: 0 auto;width: 500px;height: auto;min-height: 150px;border-bottom:  solid 1px  rgb(220,220,220);\">\n" + 
				"        <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">\n" + 
				"            <b>Our Vision</b>\n" + 
				"            <br/>\n" + 
				"            Our mission is to empower students and give young people the opportunity to discover themselves through new travel experiences. \n" + 
				"            What makes a transformative experience? This question drives our innovation strategy and everything we do. \n" + 
				"            We believe we can redefine student travel through our passionate culture, integrated student tour operator services, and care for our customers." + 
				"        </p>" + 
				"    </div>" + 
				"    <div style=\"margin: 0 auto;height: auto;min-height: 50px;border-bottom:  solid 0px;\">\n" + 
				"    <p style=\"padding-left: 10px;padding-right: 10px;line-height: 22px;\">\n" + 
				"  	Best Regards,<br>\n" + 
				"  	The Experience Team</p>" + 
				"    </div>" + 
				"    <div style=\"background-color: #333333; height:90px;\">\n" + 
				"        <table style=\"width: 98%;\" align='center'>\n" + 
				"            <tr>\n" + 
				"                <td style=\"width: 45%\">\n" + 
				"                    <p style=\"line-height: 25px;font-family: verdana;font-size: 12px;color: white\">\n" + 
				"                        Experience America<br/>\n" + 
				"                        4556 University Way Ne Ste 200<br/>\n" + 
				"                        Seattle, WA 98105<br/>\n" + 
				"                    </p>\n" + 
				"                </td>\n" + 
				"                <td style=\"width: 10%\"></td>\n" + 
				"                <td >\n" + 
				"                    <p style=\"line-height: 25px;font-family: verdana;font-size: 12px;color: white\">\n" + 
				"                        www.experienceamerica.com<br/>\n" + 
				"                        hello@experienceamerica.com<br/>\n" + 
				"                        +1 (800) 410-6088\n" + 
				"                    </p>\n" + 
				"                </td>\n" + 
				"            </tr>\n" + 
				"        </table>\n" + 
				"    </div>\n" + 
				"</div>"+
				"    </body>" + 
				"</html>" + 
				"";
	}

}
