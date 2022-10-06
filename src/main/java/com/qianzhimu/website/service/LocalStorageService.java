package com.qianzhimu.website.service;

import com.qianzhimu.website.dto.LocalStorageDto;
import com.qianzhimu.website.query.LocalStorageQueryCriteria;
import com.qianzhimu.website.entity.LocalStorage;
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
