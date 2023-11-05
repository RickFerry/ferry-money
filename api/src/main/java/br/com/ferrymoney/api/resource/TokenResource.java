package br.com.ferrymoney.api.resource;

import br.com.ferrymoney.api.config.property.AppProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
    private final AppProperties appProperties;

    public TokenResource(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<Void> revoke(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(appProperties.getSeguranca().getEnableHttps());
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }
}
