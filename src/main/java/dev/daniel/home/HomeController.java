package dev.daniel.home;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomeController {

    @GetMapping("")
    public String index() {
        return "Hello, Daniel!";
    }

    @GetMapping("passing-param")
    public String passingParams(@RequestParam String msg) {
        return "Params: " + msg;
    }

    @GetMapping("params")
    public String getParams(@RequestParam(name = "id") int paramName, String country) {
        return new String(paramName + ", " + country);
    }
    

}
