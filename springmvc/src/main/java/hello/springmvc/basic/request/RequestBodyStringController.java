package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void RequestBodyString (HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    //servlet을 다쓸필요도없는데!!귀찮다!!
    @PostMapping("/request-body-string-v2")
    public void RequestBodyStringV2 (InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    //stream도 안쓸꺼야!!!!!!! => http 메세지컨버터
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> RequestBodyStringV3 (HttpEntity<String> httpEntity) throws IOException {
        //Http메세지컨버터동작 = HttpEntity가 Httpbody에 있는걸 String으로 바꿔서 넣어줄께!
        //String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); 이코드 자동동작

        String body = httpEntity.getBody();
        log.info("messageBody={}", body);
        return new HttpEntity<>("ok");
    }

    //entity쓰는것도귀찮아!! => 어노테이션으로 대체하자!
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String RequestBodyStringV4 (@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        return "ok";
    }


}
