package com.weeds.client.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 参数类
 *
 * @author weeds
 */
@Data
public class Param implements Serializable {
    private static final long serialVersionUID = -196763889169448474L;
    /**
     * 参数名
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
}
