package com.minacloud.common.base;

import com.minacloud.common.utils.ValidatorUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public abstract class BaseEntity<PK> {
    /**
     * 主键 ID
     */
    @Id
    private PK id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 删除人
     */
    private String deleteBy;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;
    /**
     * 删除时间
     */
    private Date deletedAt;

    public void tryValidate(Class<?>... groups) {
        ValidatorUtils.tryValidate(this, groups);
    }

}
