package com.qianzhimu.ows.repository;

import com.qianzhimu.ows.entity.OwsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OwsAccountRepository extends JpaRepository<OwsAccount, Long>, JpaSpecificationExecutor<OwsAccount> {

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return /
     */
    OwsAccount findByPhone(String phone);

    OwsAccount findByPhoneAndPassword(String phone, String password);

    /**
     * 修改密码
     *
     * @param id              用户id
     * @param pass            修改后的密码
     */
    @Modifying
    @Query(value = "update ows_account set password = ?2 where id = ?1", nativeQuery = true)
    void updatePass(long id, String pass);


}

