package com.google.code.blogxy.demo.struts2.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;


/**
 * 处理文件上传的Action类
 * @author qiujy
 * @version 1.0
 */
public class FileuploadAction extends ActionSupport {
    private static final int BUFFER_SIZE = 16 * 1024;

    // 文件标题
    private String title;

    // 上传文件域对象
    private File upload;

    // 上传文件名
    private String uploadFileName;

    // 上传文件类型
    private String uploadContentType;

    // 保存文件的目录路径(通过依赖注入)
    private String savePath;

    //以下省略getter和setter......
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public File getUpload() {
        return upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    //自己封装的一个把源文件对象复制成目标文件对象
    private static void copy(File src, File dst) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(src),
                    BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst),
                    BUFFER_SIZE);

            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String execute() throws Exception {
        //根据服务器的文件保存地址和原文件名创建目录文件全路径
        String dstPath = ServletActionContext.getServletContext()
                                             .getRealPath(this.getSavePath())
            + "\\" + this.getUploadFileName();

        System.out.println("上传的文件的类型：" + this.getUploadContentType());

        File dstFile = new File(dstPath);

        copy(this.upload, dstFile);

        return SUCCESS;
    }
}
