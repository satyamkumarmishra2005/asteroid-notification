package com.satyam.notificationservice.service;

import com.satyam.asteroidalerting.event.AsteroidCollisionEvent;
import com.satyam.notificationservice.entity.Notification;
import com.satyam.notificationservice.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
public class NotificationService {


    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "asteroid-alert", groupId = "notification-service")
    public void alertEvent(AsteroidCollisionEvent notificationEvent){
        log.info("Received order event: {}",notificationEvent);

        // Create entity for notification
        final Notification notification = Notification.builder()
                .asteroidName(notificationEvent.getAsteroidName())
                .closeApproachDate(LocalDate.parse(notificationEvent.getCloseApproachDate()))
                .missDistanceKilometers(new BigDecimal(notificationEvent.getMissDistanceKilometers()))
                .estimatedDiameterAvgMeters(notificationEvent.getEstimatedDiameterAvgMeters())
                .emailSent(false)
                .build();

        // Save notification
        final Notification savedNotification = notificationRepository.saveAndFlush(notification);
        log.info("Saved notification: {}", savedNotification);


    }
    @Scheduled(fixedRate = 10000)
    public void sendAlertingEmail(){
        log.info("Triggering scheduled job to send email alerts");
        emailService.sendAsteroidAlertEmail();
    }

}

