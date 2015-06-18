package com.toucha.analytics.DailyStatistics;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMailUtil {
	public static void sendEmail(Statistics stat, Map<Integer, RewardStatistics> rewardStat, int companyId) {
		final String username = "support@rfidce.cn";
        final String password = "pC@m*2rJcSrS)kksMkQb";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.rfidce.cn");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("support@rfidce.cn"));
            
            String emailRecipientStr = AppConfig.EmailRecipient;
            String[] emailRecipients = emailRecipientStr.split(",");
            for (String recipient:emailRecipients) {
            	message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            }
            
            String emailCCStr = AppConfig.EmailCC;
            String[] emailCCs = emailCCStr.split(",");
            for (String cc:emailCCs) {
            	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
            
            String companyName = "";
            switch (companyId) {
            case AppConfig.WaterCompanyId:
            	companyName = "Hengda Water";
            	break;
            case AppConfig.EnergyDrinkCompanyId:
            	companyName = "Hengda Energy Drink";
            	break;
            }
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            String subject = String.format(AppConfig.EmailSubject, companyName, simpleDateFormat.format(cal.getTime()));
            message.setSubject(subject);
            
            int phoneTopupTimes = rewardStat.get(new Integer(1)).getClaimTimes();
            BigDecimal phoneTopupAmount = rewardStat.get(new Integer(1)).getClaimAmount();
            int unionPayTimes = rewardStat.get(new Integer(4)).getClaimTimes();
            BigDecimal unionPayAmount = rewardStat.get(new Integer(4)).getClaimAmount();
            int totalTimes = phoneTopupTimes + unionPayTimes;
            BigDecimal totalAmount = phoneTopupAmount.add(unionPayAmount);
            
            String content = String.format(AppConfig.EmailContent, stat.getNewUserCnt(),
            		stat.getOldUserScanCnt(),stat.getTotalUserCnt(),stat.getTotalScannedTagsCnt(),
            		stat.getUniqueScannedTagsCnt(),stat.getEffectiveScannedTagsCnt(),
            		stat.getHistoryEffectiveScannedTagsCnt(),stat.getActiveUserCnt(),
            		stat.getHistoryActiveUserCnt(), phoneTopupTimes, phoneTopupAmount,
            		unionPayTimes, unionPayAmount, totalTimes, totalAmount);
            message.setText(content);
            
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
