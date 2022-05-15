package com.littlecow.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class File implements Serializable {
    private Integer id;

    private  String path = "static/files";

    private  String fileName;

    private  String fileSuffix;

    private Date uploadTime;

    public String getFileNameWithExt(){
        return fileName+"."+fileSuffix;
    }
}
