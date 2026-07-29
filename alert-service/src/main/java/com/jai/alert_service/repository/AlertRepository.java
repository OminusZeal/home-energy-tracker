package com.jai.alert_service.repository;

import com.jai.alert_service.entitiy.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert,Long> {
}
