package com.qianzhimu.ows.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.qianzhimu.api.entity.TradeMarker;
import com.qianzhimu.api.repository.TradeMarkerRepository;
import com.qianzhimu.api.utils.FileUtil;
import com.qianzhimu.api.utils.SecurityUtils;
import com.qianzhimu.mgt.service.TradeMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeMarkServiceImpl implements TradeMarkService {
    private final TradeMarkerRepository tradeMarkerRepository;
    /**
     * @param file 服务器上的临时文件
     */
    @Override
    public void upload(File file) {

        try(ExcelReader excelReader = ExcelUtil.getReader(file)){
            List<TradeMarker> tmList = new ArrayList<>();
            String userName = SecurityUtils.getCurrentUsername();
            // 解析文件，读取出商标注册号和价格
            List<List<Object>> rowList = excelReader.read(1);
            for (List<Object> objects : rowList) {
                String regId = objects.get(0).toString();
                double price = Double.parseDouble(objects.get(1).toString()) ;

                TradeMarker tm = new TradeMarker();

                tm.setRegId(regId);
                tm.setTagPrice(price);
                tm.setCreateBy(userName);

                tmList.add(tm);
            }

            List<TradeMarker> newTmList = this.requestForMoreInfo(tmList);

            // fixme 保存
//            tradeMarkerRepository.saveAll(newTmList);
        } finally {
            // 删除临时文件
            FileUtil.del(file);
        }
    }

    /**
     * 请求数据服务获取更多商标信息
     * @param tmList 只包含注册号的商标集合
     * @return 填充了更多信息的商标集合
     */
    private List<TradeMarker> requestForMoreInfo(List<TradeMarker> tmList) {
        //fixme 这里需要请求商标服务或者更多的商标信息
        return tmList.stream().peek(tradeMarker -> {
            tradeMarker.setName("");
            tradeMarker.setRegDate(new Date());
        }).collect(Collectors.toList());

    }
}
