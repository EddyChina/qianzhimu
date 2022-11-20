package com.qianzhimu.ows.service;

import com.qianzhimu.mgt.dto.UserDto;
import com.qianzhimu.mgt.entity.User;
import com.qianzhimu.mgt.query.UserQueryCriteria;
import com.qianzhimu.ows.dto.OwsAccountDTO;
import com.qianzhimu.ows.entity.OwsAccount;
import com.qianzhimu.ows.vo.OwsAccountRegisterVO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OwsAccountService {

    OwsAccount exists(String phone, String password);

    /**
     * 根据ID查询
     * @param id ID
     * @return /
     */
    OwsAccountDTO findById(long id);

    /**
     * 新增用户
     * @param resources /
     */
    OwsAccountDTO register(OwsAccountRegisterVO resources) throws Exception;


    /**
     * 根据手机号码查询
     * @param phone /
     * @return /
     */
    OwsAccountDTO findByPhone(String phone);

    /**
     * 修改密码
     * @param id 用户id
     * @param encryptPassword 密码
     */
    void updatePass(long id, String encryptPassword);

}

