package com.qianzhimu.ows.service.impl;

import com.qianzhimu.mgt.config.RsaProperties;
import com.qianzhimu.api.utils.RsaUtils;
import com.qianzhimu.ows.dto.OwsAccountDTO;
import com.qianzhimu.ows.entity.OwsAccount;
import com.qianzhimu.ows.mapstruct.OwsAccountMapper;
import com.qianzhimu.ows.repository.OwsAccountRepository;
import com.qianzhimu.ows.service.OwsAccountService;
import com.qianzhimu.ows.vo.OwsAccountRegisterVO;
import com.qiniu.util.Md5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwsAccountServiceImpl implements OwsAccountService {

    private final OwsAccountRepository accountRepository;
    private final OwsAccountMapper accountMapper;

    @Override
    public OwsAccount exists(String phone, String password) {
        OwsAccount byPhoneAndPassword = this.accountRepository.findByPhoneAndPassword(phone, password);
        return byPhoneAndPassword;
    }

    @Override
    public OwsAccountDTO findById(long id) {
        OwsAccount account = accountRepository.findById(id).orElseGet(OwsAccount::new);
        return accountMapper.toDto(account);
    }

    @Override
    public OwsAccount get(long id) {
        return this.accountRepository.getOne(id);
    }

    @Override
    public OwsAccountDTO register(OwsAccountRegisterVO resources) throws Exception{
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, resources.getPassword());
        // 验证用户名 密码
        String md5Pwd = Md5.md5((password+RsaProperties.md5Key).getBytes());

        OwsAccount account = new OwsAccount();

        account.setAccountType(resources.getAccountType());
        account.setPhone(resources.getPhone());
        account.setPassword(md5Pwd);

        return accountMapper.toDto(this.accountRepository.save(account));
    }

    @Override
    public OwsAccountDTO findByPhone(String phone) {
        return accountMapper.toDto(this.accountRepository.findByPhone(phone));
    }

    @Override
    @Transactional
    public void updatePass(long id, String encryptPassword) {
        this.accountRepository.updatePass(id, encryptPassword);
    }
}
