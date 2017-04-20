package ru.kpfu.itis.pita.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/14/17 10:47 AM
 */
@Controller
@RequestMapping(path = "/admin")
public class MainAdminController {

    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public MainAdminController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @GetMapping(path = "/")
    public String doGet(ModelMap map) {
        //get all request handler methods that Spring could find
        List<String> patterns = handlerMapping.getHandlerMethods().entrySet().stream()
                .filter(entry -> BaseAdminController.class.isAssignableFrom(entry.getValue().getBeanType()) &&
                                    entry.getValue().getMethod().getName().equals("listAll"))
                .map(Map.Entry::getKey)
                .flatMap(info -> info.getPatternsCondition().getPatterns().stream())
                .collect(Collectors.toList());

        map.put("urls", patterns);

        return "admin_main";
    }
}
