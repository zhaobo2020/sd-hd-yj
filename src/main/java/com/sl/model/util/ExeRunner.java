package com.sl.model.util;

import lombok.extern.slf4j.Slf4j;
import java.io.*;

@Slf4j
public class ExeRunner {



    public static String runExeWithGB2312(String exePath) throws IOException, InterruptedException {
        // 检查路径有效性（与之前一致）
        File exeFile = new File(exePath);
        if (!exeFile.exists() || !exeFile.isFile()) {
            throw new IllegalArgumentException("exe路径无效或文件不存在");
        }
        String exeDir = exeFile.getParent();
        File workingDirectory = new File(exeDir);

        // 创建ProcessBuilder并配置编码
        ProcessBuilder processBuilder = new ProcessBuilder(exePath);
        processBuilder.directory(workingDirectory);
        processBuilder.redirectErrorStream(true);  // 合并错误流与输出流

        // 启动进程并手动读取流（关键修改）
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "GB2312")  // 指定GB2312编码
        );
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);  // 直接输出到控制台
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("exe执行失败，退出码: " + exitCode);
        }
        return exeDir;
    }

    /**
     * 调用指定路径的 exe 程序。
     * @param exePath exe 程序的路径（可能包含中文字符）。
     * @return exe 所在目录的路径（字符串格式）。
     * @throws IOException          如果路径无效或执行失败。
     * @throws InterruptedException 如果等待进程完成时被中断。
     */
    public static String runExe(String exePath) throws IOException, InterruptedException {
        // 检查路径是否有效
        File exeFile = new File(exePath);
        if (!exeFile.exists() || !exeFile.isFile()) {
            throw new IllegalArgumentException("exe 路径无效或文件不存在");
        }
        // 获取 exe 所在的目录
        String exeDir = exeFile.getParent();
        if (exeDir == null) {
            throw new IllegalArgumentException("无法确定 exe 的所在目录");
        }
        // 设置运行目录
        File workingDirectory = new File(exeDir);
        // 创建进程构建器
        ProcessBuilder processBuilder = new ProcessBuilder(exePath);
        processBuilder.directory(workingDirectory);
        // 启动进程
        Process process = processBuilder.start();
        // 等待进程完成
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("exe 执行失败，退出码: " + exitCode);
        }
        // 返回 exe 所在的目录
        return exeDir;
    }

}