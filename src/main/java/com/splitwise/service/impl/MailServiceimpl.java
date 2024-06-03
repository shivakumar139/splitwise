package com.splitwise.service.impl;

import com.splitwise.entity.User;
import com.splitwise.service.MailService;
import com.sun.jdi.InternalException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceimpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}") private String sender;
    @Value("${spring.serverIP}")
    private String serverIP;


    @Async
    @Override
    public void sendMail(User user) {

        MimeMessage message = mailSender.createMimeMessage();
        // Wrap the MimeMessage object in a MimeMessageHelper object
        // It provides various methods to create the mime message
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(user.getEmail());
            helper.setFrom(sender);
            helper.setSubject("Account Verification");
            helper.setText(getMailTemplate(user.getVerificationCode()), true);
            mailSender.send(message);

        } catch (Exception e){
            throw new InternalException("Error in mail server");
        }


    }

    private String getMailTemplate(String verificationCode){
        String mailTemplate = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Please activate your account</title>\n" +
                "  <!--[if mso]><style type=\"text/css\">body, table, td, a { font-family: Arial, Helvetica, sans-serif !important; }</style><![endif]-->\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family: Helvetica, Arial, sans-serif; margin: 0px; padding: 0px; background-color: #ffffff;\">\n" +
                "  <table role=\"presentation\"\n" +
                "    style=\"width: 100%; border-collapse: collapse; border: 0px; border-spacing: 0px; font-family: Arial, Helvetica, sans-serif; background-color: rgb(239, 239, 239);\">\n" +
                "    <tbody>\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding: 1rem 2rem; vertical-align: top; width: 100%;\">\n" +
                "          <table role=\"presentation\" style=\"max-width: 600px; border-collapse: collapse; border: 0px; border-spacing: 0px; text-align: left;\">\n" +
                "            <tbody>\n" +
                "              <tr>\n" +
                "                <td style=\"padding: 40px 0px 0px;\">\n" +
                "                  <div style=\"text-align: left;\">\n" +
                "                    <div style=\"padding-bottom: 20px;\"><img src=\"https://i.ibb.co/Qbnj4mz/logo.png\" alt=\"Company\" style=\"width: 56px;\"></div>\n" +
                "                  </div>\n" +
                "                  <div style=\"padding: 20px; background-color: rgb(255, 255, 255);\">\n" +
                "                    <div style=\"color: rgb(0, 0, 0); text-align: left;\">\n" +
                "                      <h1 style=\"margin: 1rem 0\">Final step...</h1>\n" +
                "                      <p style=\"padding-bottom: 16px\">Follow this link to verify your email address.</p>\n" +
                "                      <p style=\"padding-bottom: 16px\"><a href=\"{{verificationLink}}\" target=\"_blank\"\n" +
                "                          style=\"padding: 12px 24px; border-radius: 4px; color: #FFF; background: #2B52F5;display: inline-block;margin: 0.5rem 0;\">Confirm\n" +
                "                          now</a></p>\n" +
                "                      <p style=\"padding-bottom: 16px\">If you didn’t ask to verify this address, you can ignore this email.</p>\n" +
                "                      <p style=\"padding-bottom: 16px\">Thanks,<br>The Splitwise team</p>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  \n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        String url = "http://" + serverIP + ":8080/api/v1/auth/verify?code=" + verificationCode;
        mailTemplate = mailTemplate.replace("{{verificationLink}}",url);
        return mailTemplate;
    }
}
