package com.sl.plat.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: duan
 * @time: 2025-05-26 17:10
 */
@RestController
@RequestMapping("/plat/file")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@Tag(name = "文件", description = "提供文件上传、下载、浏览相关功能")
public class FileController {

    @Value("${file.dirPath:}")
    private String dirPath;

    @PostMapping("/upload")
    @Operation(summary = "上传")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest().body("上传文件不能为空");
            }
            // 构建存储路径
            String originalFileName = file.getOriginalFilename();
            String fileExtension = FileUtil.extName(originalFileName);
            String saveFileName = generateUniqueFileName(fileExtension);
            String relativePath = DateUtil.today().replace("-", "/");
            File saveDir = FileUtil.file(dirPath, relativePath);
            FileUtil.mkdir(saveDir);

            // 保存文件
            File saveFile = FileUtil.file(saveDir, saveFileName);
            file.transferTo(saveFile);

            // 返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("originalFileName", originalFileName);
            result.put("saveFileName", saveFileName);
            result.put("relativePath", relativePath);
            result.put("fileSize", FileUtil.readableFileSize(saveFile.length()));
            result.put("accessUrl", relativePath + "/" + saveFileName);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败：" + e.getMessage());
        }
    }

    // 文件下载接口
    @GetMapping("/download")
    @Operation(summary = "下载")
    public void downloadFile(@RequestParam String path, HttpServletResponse response) {
        File file = FileUtil.file(dirPath, path);
        if (!FileUtil.exist(file)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            // 设置响应头
            String fileName = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            response.setContentType(ContentType.OCTET_STREAM.getValue());
            response.setContentLengthLong(file.length());
            IoUtil.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // 文件预览接口
    @GetMapping("/preview")
    @Operation(summary = "预览")
    public ResponseEntity<byte[]> previewFile(@RequestParam String path, HttpServletResponse response) {
        File file = FileUtil.file(dirPath, path);
        if (!FileUtil.exist(file)) {
            return ResponseEntity.notFound().build();
        }
        try {
            String fileSuffix = path.substring(path.lastIndexOf("."));
            ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
            builder.contentLength(file.length());
            if (fileSuffix.toLowerCase().matches(".png|.jpg|.jpeg|.bmp|.gif|.icon")) {
                builder.cacheControl(CacheControl.noCache()).contentType(MediaType.IMAGE_PNG);
            } else if (fileSuffix.toLowerCase().matches(
                    ".flv|.swf|.mkv|.avi|.rm|.rmvb|.mpeg|.mpg|.ogg|.ogv|.mov|.wmv|.mp4|.webm|.wav|.mid|.mp3|.aac")) {
                builder.header("Content-Type", "video/mp4; charset=UTF-8");
            }
            return builder.body(readFileToByteArray(file));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private byte[] readFileToByteArray(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            log.error("error", e);
        }
        return buffer;
    }

    // 生成唯一文件名
    private String generateUniqueFileName(String extension) {
        return IdUtil.fastSimpleUUID() + (StrUtil.isNotBlank(extension) ? "." + extension : "");
    }

    // 根据文件名获取内容类型
    private String getContentType(String fileName) {
        String extension = FileUtil.extName(fileName).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "pdf":
                return "application/pdf";
            case "txt":
                return "text/plain";
            case "html":
            case "htm":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "json":
                return "application/json";
            case "xml":
                return "application/xml";
            case "mp4":
                return "video/mp4";
            case "mp3":
                return "audio/mpeg";
            default:
                return "application/octet-stream";
        }
    }

}
