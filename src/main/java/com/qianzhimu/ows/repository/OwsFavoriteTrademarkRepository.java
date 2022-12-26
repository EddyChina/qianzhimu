package com.qianzhimu.ows.repository;

import com.qianzhimu.ows.entity.OwsFavoriteTrademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OwsFavoriteTrademarkRepository extends JpaRepository<OwsFavoriteTrademark, Long>, JpaSpecificationExecutor<OwsFavoriteTrademark> {

    boolean existsByAccountIdAndTmRegId(Long accountId, String tmRegId);

    void deleteByAccountIdAndTmRegId(Long accountId, String tmRegId);
}
