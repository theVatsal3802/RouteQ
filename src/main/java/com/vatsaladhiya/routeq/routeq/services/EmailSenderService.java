package com.vatsaladhiya.routeq.routeq.services;


public interface EmailSenderService {
    public void sendEmail(String toEmail, String subject, String body);
    public void sendBulkEmail(String[] toEmails, String subject, String body);
}
