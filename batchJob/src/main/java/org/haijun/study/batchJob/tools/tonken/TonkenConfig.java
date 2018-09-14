package org.haijun.study.batchJob.tools.tonken;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix="tonken")//Spring Boot 会自动将prefix="tonken"前缀为tonken的属性注入进来。
public class TonkenConfig {

    private String subJect;

    private List<String> servers;

}
