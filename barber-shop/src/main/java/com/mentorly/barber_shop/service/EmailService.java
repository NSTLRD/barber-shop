/**
 * @author Starling Diaz on 10/5/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/5/2024.
 */

package com.mentorly.barber_shop.service;

import com.mentorly.barber_shop.constants.EmailTemplateName;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(String to, String username, EmailTemplateName emailTemplate, String ConfirmationUrl, String activationConde, String subject) throws MessagingException;
}