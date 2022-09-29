package com.cts.training.digitalbooknotificationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.training.digitalbooknotificationservice.entities.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

}
