package com.anvy.petcare.modules.controller;


import com.anvy.dto.ResultVo;
import com.anvy.mybatis.service.ICareFileService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anvy Lao
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/file")
public class CareFileController {
    private Logger log = LoggerFactory.getLogger(CareFileController.class);

    @Autowired
    @Qualifier("careFileService")
    private ICareFileService careFileService;

    @ApiOperation(value = "图片上传",notes = "图片上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResultVo upload(MultipartFile file) {
        ResultVo resultVo = careFileService.upload(file);
        return resultVo;
    }

    @ApiOperation(value = "图片回显",notes = "图片回显")
    @RequestMapping(value = "/download/{fileId}",method = RequestMethod.POST)
    @ResponseBody
    public String download (@PathVariable("fileId")String fileId, HttpServletResponse response) throws IOException {
        String download = careFileService.download(fileId);
        return download;
    }

    @ApiOperation(value = "图片回显",notes = "图片回显")
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody List<String> ids, @RequestParam String panoRegionId)  {
        return null;
    }
}
