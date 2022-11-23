package com.qianzhimu.ows.repository;

import com.qianzhimu.ows.entity.OwsFavoriteTrademarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OwsFavoriteTrademarkerRepository extends JpaRepository<OwsFavoriteTrademarker, Long>, JpaSpecificationExecutor<OwsFavoriteTrademarker> {
}
