package com.customer.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class FileUploadController {

    public static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/upload", method = GET)
    public String showUploadForm() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = POST)
    public String uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        long size = multipartFile.getSize();
        String contentType = multipartFile.getContentType();
        byte[] fileContents = multipartFile.getBytes();
        multipartFile.transferTo(new File("/tmp/" + filename));
        InputStream is = null;
        try {
            is = multipartFile.getInputStream();
        } finally {
            is.close();
        }
        LOG.trace("size=" + size);
        LOG.trace("contentType=" + contentType);
        LOG.trace("fileContents=" + new String(fileContents, "UTF-8"));

        return "redirect:/upload";
    }

}
