package tacos.controller.file;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件上传，下载（不推荐这种方法），定时任务删除文件
 * @author yuanfu
 * @data 2021/5/15 11:43
 */
@Slf4j
@RestController
// @EnableScheduling
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload.url}")
    private String uploadFilePath;

    @Value("${file.download.url}")
    private String downloadFilePath;

    @Value("${file.delete.url}")
    private String deleteFilePath;

    @PostMapping("/upload")
    public String httpUpload(@RequestParam("files") MultipartFile files[]) {
        JSONObject object = new JSONObject();
        for(int i=0; i<files.length; i++) {
            String filename = files[i].getOriginalFilename();
            File dest = new File(uploadFilePath + '/' + filename);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try{
                files[i].transferTo(dest);
            } catch (IOException e) {
                log.error("files上传错误", e);
                object.put("failure", 2);
                object.put("result", "程序错误，请重新上传");
                return object.toString();
            }
        }
        object.put("success", 1);
        object.put("result", "文件上传成功");
        return object.toString();
    }

    @GetMapping("/download")
    public String fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        File file = new File(downloadFilePath + '/' + fileName);
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();;
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            ServletOutputStream os = response.getOutputStream();
            int i = 0;
            while((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error("文件下载错误", e);
            return "下载失败";
        }
        return "下载成功";
    }

    // @Scheduled(cron="30 * * * * ?")
    private void deleteFiles() {
        deleteFile(new File(deleteFilePath));
    }

    public void deleteFile(File file) {
        if(file == null || !file.exists()){
            log.info("暂无文件可以删除");
            return ;
        }
        // 该目录下所有子文件对象
        File[] files = file.listFiles();
        for(File f : files) {
            String name = f.getName();
            log.info("即将删除文件或文件夹：" + name);
            if(f.isDirectory()){
                deleteFile(f);
            } else {
                f.delete();
            }
        }
        // 删除空文件夹，for循环已经把上一层节点的目录清空
        file.delete();
    }
}
