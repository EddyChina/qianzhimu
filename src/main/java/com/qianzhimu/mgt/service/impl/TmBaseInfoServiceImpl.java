package com.qianzhimu.mgt.service.impl;


import com.qianzhimu.api.utils.FileUtil;
import com.qianzhimu.api.utils.PageUtil;
import com.qianzhimu.api.utils.QueryHelp;
import com.qianzhimu.api.utils.ValidationUtil;
import com.qianzhimu.mgt.dto.TmBaseInfoDto;
import com.qianzhimu.mgt.entity.TmBaseInfo;
import com.qianzhimu.mgt.exception.EntityExistException;
import com.qianzhimu.mgt.mapstruct.TmBaseInfoMapper;
import com.qianzhimu.mgt.query.TmBaseInfoQueryCriteria;
import com.qianzhimu.mgt.repository.TmBaseInfoRepository;
import com.qianzhimu.mgt.service.TmBaseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TmBaseInfoServiceImpl implements TmBaseInfoService {

    private final TmBaseInfoRepository tmBaseInfoRepository;

    private final TmBaseInfoMapper tmBaseInfoMapper;

    @Override
    public Object queryAll(TmBaseInfoQueryCriteria criteria, Pageable pageable){
        Page<TmBaseInfo> page = tmBaseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tmBaseInfoMapper::toDto));
    }

    @Override
    public List<TmBaseInfoDto> queryAll(TmBaseInfoQueryCriteria criteria){
        return tmBaseInfoMapper.toDto(tmBaseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public TmBaseInfoDto findById(Long id) {
        TmBaseInfo tmBaseInfo = tmBaseInfoRepository.findById(id).orElseGet(TmBaseInfo::new);
        ValidationUtil.isNull(tmBaseInfo.getId(),"TmBaseInfo","id",id);
        return tmBaseInfoMapper.toDto(tmBaseInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TmBaseInfoDto create(TmBaseInfo resources) {
        if(tmBaseInfoRepository.findByRegId(resources.getRegId()) != null){
            throw new EntityExistException(TmBaseInfo.class,"reg_id",resources.getRegId());
        }
        return tmBaseInfoMapper.toDto(tmBaseInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TmBaseInfo resources) {
        TmBaseInfo tmBaseInfo = tmBaseInfoRepository.findById(resources.getId()).orElseGet(TmBaseInfo::new);
        ValidationUtil.isNull( tmBaseInfo.getId(),"TmBaseInfo","id",resources.getId());
        TmBaseInfo tmBaseInfo1 = null;
        tmBaseInfo1 = tmBaseInfoRepository.findByRegId(resources.getRegId());
        if(tmBaseInfo1 != null && !tmBaseInfo1.getId().equals(tmBaseInfo.getId())){
            throw new EntityExistException(TmBaseInfo.class,"reg_id",resources.getRegId());
        }
        tmBaseInfo.copy(resources);
        tmBaseInfoRepository.save(tmBaseInfo);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            tmBaseInfoRepository.deleteById(id);
        }
    }

    /**
     * @param tmId    商标ID
     * @param prev    更新前价格
     * @param current 更新后价格
     * @return
     */
    @Override
    public int updateTagPrice(Long tmId, Double prev, Double current) {
        return this.tmBaseInfoRepository.updateTagPrice(tmId, prev, current);
    }

    /**
     * @param tmId    商标ID
     * @param prev    更新前价格
     * @param current 更新后价格
     * @return
     */
    @Override
    public int updateFloorPrice(Long tmId, Double prev, Double current) {
        return this.tmBaseInfoRepository.updateFloorPrice(tmId, prev, current);
    }

    /**
     * @param tmIdSet 商标ID集合
     */
    @Override
    public void down(Set<Long> tmIdSet) {
        this.tmBaseInfoRepository.down(tmIdSet);
    }

    /**
     * @param tmIdSet 商标ID集合
     */
    @Override
    public void up(Set<Long> tmIdSet) {
        this.tmBaseInfoRepository.up(tmIdSet);
    }

    @Override
    public void download(List<TmBaseInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TmBaseInfoDto tmBaseInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("注册号", tmBaseInfo.getRegId());
            map.put("商标名称", tmBaseInfo.getName());
            map.put("商标分类", tmBaseInfo.getCategory());
            map.put("适用项目", tmBaseInfo.getDomain());
            map.put("中英文类型", tmBaseInfo.getContentType());
            map.put("注册日期", tmBaseInfo.getRegDate());
            map.put("有效日期", tmBaseInfo.getValidDate());
            map.put("注册人", tmBaseInfo.getRegUserName());
            map.put("注册人联系方式", tmBaseInfo.getRegUserContact());
            map.put("持有人", tmBaseInfo.getOwnerUserName());
            map.put("持有人联系方式", tmBaseInfo.getOwnerContact());
            map.put("持有人标量", tmBaseInfo.getOwnerHolderCnt());
            map.put("商标图片路径", tmBaseInfo.getPicPath());
            map.put("标价", tmBaseInfo.getTagPrice());
            map.put("底价", tmBaseInfo.getFloorPrice());
            map.put("商标长度", tmBaseInfo.getLengthRanger());
            map.put("上下架状态", tmBaseInfo.getState());
            map.put("创建人", tmBaseInfo.getCreateBy());
            map.put("修改人", tmBaseInfo.getUpdateBy());
            map.put("创建时间", tmBaseInfo.getCreateTime());
            map.put("修改时间", tmBaseInfo.getUpdateTime());
            map.put("类似群组", tmBaseInfo.getLikeGroup());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

 }
