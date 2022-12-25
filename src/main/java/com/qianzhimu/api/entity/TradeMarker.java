package com.qianzhimu.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qianzhimu.api.constant.Constant;
import com.qianzhimu.api.utils.PinyinHelper;
import com.qianzhimu.api.utils.StringUtils;
import com.qianzhimu.mgt.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 商标基础信息
 */
@Entity
@Getter
@Setter
@Table(name = "tm_base_info")
public class TradeMarker extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String regId;

    @NotBlank
    private String name;

    @NotNull
    private Integer category;

    @NotBlank
    private String domain;

    @NotNull
    private Integer contentType;

    private int state;

    @NotNull
    @JsonIgnore
    private Date regDate;

    @NotNull
    @JsonIgnore
    private Date validDate;

    @JsonIgnore
    private String regUserName;

    @JsonIgnore
    private String regUserContact;

    @JsonIgnore
    private String ownerUserName;

    @JsonIgnore
    private String ownerContact;
    private Integer ownerHolderCnt;

    private String picPath;

    @JsonIgnore
    private Double tagPrice;

    @JsonIgnore
    private Double floorPrice;

    private Integer lengthRanger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeMarker that = (TradeMarker) o;

        return regId.equals(that.regId);
    }

    /**
     * 根据商标名称获取商标的类型： 中文/英文/中英文等
     * @return 三位的字符串，每一位的取值为0或1， 0代表未出现，1代表出现，最后取十进制
     *          第一位代表数字
     *          第二位代表中文
     *          第三位代表英文
 *          举例：
     *          000: 图片
     *          010: 中文
     *          011: 中文+英文
     *          111: 数字+中文+英文
     *          以此类推
     */
    public Integer getContentType() {
        String contentType = "";
        int length = 0;

        if (StringUtils.trimToEmpty(name).length() == 0
                || "图形".equals(name)) {
            contentType = "000";
        } else {
            StringBuilder builder = new StringBuilder();

            // 提取字母
            String en = name.replaceAll(Constant.REG_CH, "").trim();

            // 提取数字
            String num = name.replaceAll(Constant.REG_NUM, "").trim();

            // 加上数字的个数
            length += num.length();

            // 提取中文
            String ch = name.replaceAll(Constant.REG_EN, "").trim();

            String hasNum = "0";
            // 含数字
            if (num.length() > 0) {
                hasNum = "1";
            }
            builder.append(hasNum);

            if (ch.length() > 0) {
                builder.append("1");
                length += ch.length();

                // 看字母是不是就是拼音
                String chPinyin = PinyinHelper.toPinyin(ch);

                // 其中的字母就是拼音
                if (chPinyin.equalsIgnoreCase(en)) {
                    builder.append("0");
                } else {
                    // 其中的字母不是拼音
                    builder.append("1");
                    length += en.length();
                }
            } else {
                builder.append("01");
                length += en.length();
            }

            contentType = builder.toString();
        }
        this.setLengthRanger(length);
        return Integer.parseInt(contentType, 2);
    }


    @Override
    public int hashCode() {
        return regId.hashCode();
    }
}
