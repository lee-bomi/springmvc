package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody   //@RestController가 아닌 @Controller사용시 return값을 본문에 콱 박히게하려면 @ResponseBody를 해당 메서드에 사용한다
    @RequestMapping("/request-param-v2")
    public String RequestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age
            ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String RequestParamV3(
            @RequestParam String username,  //요청파라미터와 변수명이 같다면 생략가능
            @RequestParam int age
            ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String RequestParamV4(String username,int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //이값이 무조건 들어와야돼! 여부
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String RequestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age         //요청파라미터가 없어도 에러안남(bad request 400에러)
            ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String RequestParamdefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") Integer age     //defaultValue를 설정한경우, 파라미터에 담긴값이 없는경우 표시된다
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String RequestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) {   //HelloData객체 생성, 요청파라미터가 모두들어가있음.
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloDate={}", helloData);
        return "ok";
    }
}
