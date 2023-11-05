package br.com.ferrymoney.api.config.property;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("api")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppProperties {
    String origemPermitida = "http://localhost:8080";
    Seguranca seguranca = new Seguranca();

    @Builder @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Seguranca {
        Boolean enableHttps;
    }
}
