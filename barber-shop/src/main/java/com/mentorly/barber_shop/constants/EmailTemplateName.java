/**
 * @author Starling Diaz on 10/5/2024.
 * @Github https://github.com/NSTLRD
 * @Website https://mentorly.blog/
 * @Academy https://www.mentor-ly.com/
 * @version barber-shop 1.0
 * @since 10/5/2024.
 */

package com.mentorly.barber_shop.constants;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account"),
    RESET_PASSWORD("reset-password");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}