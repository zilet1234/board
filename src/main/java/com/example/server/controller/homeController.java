package com.example.server.controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
public class homeController {

    @CrossOrigin("*")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "HELLO WORLD !!";
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String get(@RequestBody String body) throws Exception {
        String filename = body.replace("=", "").split("&&")[0];
        File file = new File(filename);

        if(!file.isFile()) {
            return "error1";
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            stringBuffer.append(line);
        }
        return stringBuffer.toString();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String set(@RequestBody String body) throws Exception {
        String filename = body.replace("=", "").split("&&")[0];
        String content = body.replace("=", "").split("&&")[1];

        File file = new File(filename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.newLine();
        bw.flush();

        return "success";
    }

}
