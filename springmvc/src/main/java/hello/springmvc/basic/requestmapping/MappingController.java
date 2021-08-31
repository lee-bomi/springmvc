package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본요청, 요청url을 배열화 가능
     * http메서드 모두허용(method 작성안할경우)
     */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("hellobasic");
        return "ok";
    }

    @RequestMapping({"/hello-basic", "/hello-ok"})
    public String helloBasic2() {
        log.info("hellobasic2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }
}
