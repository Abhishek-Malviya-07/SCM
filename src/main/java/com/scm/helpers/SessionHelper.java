package com.scm.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importing the Logger and LoggerFactory for logging purposes.
import org.springframework.stereotype.Component; // Importing @Component annotation to make this class a Spring-managed bean.
import org.springframework.web.context.request.RequestContextHolder; // Importing RequestContextHolder to get the current request attributes.
import org.springframework.web.context.request.ServletRequestAttributes; // Importing ServletRequestAttributes to access the HTTP request and session.

import jakarta.servlet.http.HttpSession; // Importing HttpSession to work with the session object.

@Component // Indicates that this class is a Spring component and will be detected during component scanning.
public class SessionHelper {

    // Creating a logger instance to log messages for this class.
    private static final Logger logger = LoggerFactory.getLogger(SessionHelper.class);

    // Static method to remove the 'message' attribute from the session.
    public static void removeMessage() {
        try {
            // Obtain the current HTTP session from the request attributes.
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                                    .getRequestAttributes()).getRequest().getSession();
            
            // Remove the 'message' attribute from the session.
            session.removeAttribute("message");
        } catch (Exception e) {
            // Log any exceptions that occur during the process, useful for debugging and monitoring.
            logger.error("Error in SessionHelper: ", e);
        }
    }
}
