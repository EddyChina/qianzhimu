package com.qianzhimu.mgt.repository;

import com.qianzhimu.mgt.entity.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
