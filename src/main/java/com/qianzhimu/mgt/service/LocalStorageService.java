package com.qianzhimu.mgt.service;

import com.qianzhimu.mgt.dto.LocalStorageDto;
import com.qianzhimu.mgt.query.LocalStorageQueryCriteria;
import com.qianzhimu.mgt.entity.LocalStorage;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface LocalStorageService {

    Object queryAll(LocalStorageQueryCriteria criteria, Pageable pageable);

    List<LocalStorageDto> queryAll(LocalStorageQueryCriteria criteria);

    LocalStorageDto findById(Long id);

    void create(String name, MultipartFile file);

    void update(LocalStorage resources);

    void deleteAll(Long[] ids);

    void download(List<LocalStorageDto> queryAll, HttpServletResponse response) throws IOException;

}
