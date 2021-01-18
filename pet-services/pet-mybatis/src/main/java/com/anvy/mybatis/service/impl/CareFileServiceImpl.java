package com.anvy.mybatis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.anvy.dto.ResultVo;
import com.anvy.config.ServerConfig;
import com.anvy.mybatis.entity.CareFile;
import com.anvy.mybatis.mapper.CareFileMapper;
import com.anvy.mybatis.service.ICareFileService;
import com.anvy.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Anvy Lao
 * @since 2020-05-17
 */
@Service("careFileService")
public class CareFileServiceImpl extends ServiceImpl<CareFileMapper, CareFile> implements ICareFileService {

    @Value("${file_upload_path}")
    private String fileUploadPath;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private CareFileMapper careFileMapper;

    @Autowired
    private RedisUtil redis;

    @Override
    public ResultVo upload(MultipartFile file) {
        try {
            long millis = System.currentTimeMillis();
            String fileName = millis+file.getOriginalFilename();

            File newFile = new File(fileUploadPath+File.separator+fileName);
            if(!newFile.getParentFile().exists()){
                newFile.mkdirs();
            }
            file.transferTo(newFile);
            CareFile careFile = new CareFile();
            String url = serverConfig.getUrl();
            String path = url+ "/com/anvy/petcare/file/" +fileName;
            careFile.setFilepath(path);
            long fileId = redis.idGenerator("CARE_FILE");
            careFile.setId(fileId);
            careFile.setFilename(fileName);
            int insert = careFileMapper.insert(careFile);
            if(insert == 1){
                JSONObject object = new JSONObject();
                object.put("src",path);
                object.put("fileId",fileId);
                return ResultVo.success().data(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVo.error().message("上传失败！");
        }
        return null;
    }

    @Override
    public String download(String fileId) {
        CareFile careFile = careFileMapper.selectById(fileId);
        String filepath = careFile.getFilepath();
        return filepath;
    }
}
