package com.codefans.practicetask.httpserver;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.Map;

/**
 * @author: mpif
 * @date: 2018-06-06 10:05
 */
@RestController
@RequestMapping("/admin")
public class HttpRequestController {

    @RequestMapping(path = "/queryString", produces = "text/plain")
    public ResponseEntity<String> queryString() {
        String jsonResult = "{\"name\":\"zhangsan\",\"password\":\"123456\"}";
        ResponseEntity<String> entity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(jsonResult);
        return entity;
    }

    @PostMapping(path = "/addDomain")
    public ResponseEntity<String> addUser(@ModelAttribute RequestDomain body) {
//        String jsonResult = "{\"name\":\"zhangsan\",\"password\":\"123456\"}";
        String jsonResult = "I am Post method!!! post request content is:[" + body.toString() + "]";
        ResponseEntity<String> entity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(jsonResult);
        return entity;
    }

    @GetMapping(path = "/queryGetString")
    public ResponseEntity<String> queryGetString(@RequestParam(value = "username", required = true) String username,
                                                 @RequestParam(value = "password", required = true) String password) {
        String jsonResult = "welcome to get method!!! get request content is:[" + username + "," + password + "]";
        ResponseEntity<String> entity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(jsonResult);
        return entity;
    }

    @RequestMapping(path = "/set/{username}-{password}", method={RequestMethod.GET})
    public ResponseEntity<String> set(@PathVariable String username, @PathVariable String password) {
        String jsonResult = "path variable username:[" + username + "], password:[" + password + "]";
        ResponseEntity<String> entity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(jsonResult);
        return entity;
    }

}
