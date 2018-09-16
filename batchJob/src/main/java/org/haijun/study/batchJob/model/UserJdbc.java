package org.haijun.study.batchJob.model;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UserJdbc {

    private Long id;

    private String code;

    private String name;

    private String pwd;


}
