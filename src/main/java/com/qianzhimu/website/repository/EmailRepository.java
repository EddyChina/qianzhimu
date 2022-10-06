package com.qianzhimu.website.repository;

import com.qianzhimu.website.entity.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
