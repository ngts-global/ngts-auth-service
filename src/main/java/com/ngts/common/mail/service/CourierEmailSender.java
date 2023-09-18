package com.ngts.common.mail.service;

import com.ngts.common.mail.config.MailConfig;
import com.ngts.common.mail.model.EMail;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.Courier;
import services.SendService;
import java.util.HashMap;

@Service
public class CourierEmailSender {

    @Autowired
    private MailConfig mailConfig;

    private final String msg_content="Dear {##NAME##},\n" +
            "\n" +
            "We cordially invite you to attend our seminar on Computer Systems. The seminar will be held on {##Date##} at {##Location##}. This seminar is designed to provide an overview on the different types of computer systems, and how they can be used to enhance your day-to-day operations. \n" +
            "\n" +
            "We will also be discussing the latest trends in computer systems and how they can benefit your business. We look forward to your participation in this insightful event. \n" +
            "\n" +
            "If you have any questions or queries regarding the seminar, please do not hesitate to contact us.\n" +
            "\n" +
            "Sincerely, \n" +
            "\n" +
            "{##OrganizationName##}";

    public void sendEmail(EMail mail) {

        Courier.init(mailConfig.courierId);

        SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
        SendRequestMessage sendRequestMessage = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", mail.getTo());
        sendRequestMessage.setTo(to);

        HashMap<String, String> content = new HashMap<String, String>();
        content.put("title", "Welcome to NGTS-SCM Mr/Mrs " + mail.getName());
        String msgBody = msg_content.replace("{##NAME##}", mail.getName())
                                    .replace("{##OrganizationName##}","NGTS SCM")
                                    .replace("{##Date##}","Today")
                                    .replace("{##Location##}","Chennai")
                                    .replace("{##OrganizationName##}", "NGTS It Solutions");
        content.put("body", msg_content.replace("{##NAME##}",msgBody));
        sendRequestMessage.setContent(content);


        sendEnhancedRequestBody.setMessage(sendRequestMessage);

        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}