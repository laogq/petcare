package com.anvy.mybatis.service;

import com.anvy.dto.ResultVo;
import com.anvy.mybatis.entity.CareFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Anvy Lao
 * @since 2020-05-17
 */
public interface ICareFileService extends IService<CareFile> {

    ResultVo upload(MultipartFile file);

    String download(String fileId);
}
