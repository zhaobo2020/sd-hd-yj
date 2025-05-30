package com.sl.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class FileUtil {

    /**
     * 递归删除指定目录下的所有文件和子目录，最后删除空目录本身
     * @param directoryPath 要删除的目录路径（绝对路径或相对路径）
     * @return 删除成功返回 true，失败返回 false
     */
    public static boolean deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        // 检查目录是否存在且是有效目录
        if (!directory.exists()) {
            log.warn("目录不存在: {}", directoryPath);
            return false;
        }
        if (!directory.isDirectory()) {
            log.error("路径不是目录: {}", directoryPath);
            return false;
        }

        log.info("开始删除目录: {}", directoryPath);

        // 递归删除目录下的所有文件和子目录
        File[] files = directory.listFiles();
        if (files == null) { // 处理权限问题导致的无法列举文件
            log.error("无法访问目录内容（可能缺少权限）: {}", directoryPath);
            return false;
        }

        boolean allDeleted = true;
        for (File file : files) {
            if (file.isDirectory()) {
                // 递归删除子目录
                boolean subDirDeleted = deleteDirectory(file.getAbsolutePath());
                if (!subDirDeleted) {
                    log.error("子目录删除失败: {}", file.getAbsolutePath());
                    allDeleted = false;
                } else {
                    log.debug("子目录已删除: {}", file.getAbsolutePath());
                }
            } else {
                // 删除普通文件
                if (file.delete()) {
                    log.debug("文件已删除: {}", file.getAbsolutePath());
                } else {
                    log.error("文件删除失败: {}", file.getAbsolutePath());
                    allDeleted = false;
                }
            }
        }

        // 所有内容删除后，删除当前空目录
        if (allDeleted && directory.delete()) {
            log.info("目录及内容全部删除成功: {}", directoryPath);
            return true;
        }

        log.error("目录删除失败（可能有残留文件）: {}", directoryPath);
        return false;
    }
    /**
     * 扫描指定目录下的指定后缀的文件，将其复制到目标目录，并返回扫描到的文件名称列表。
     *
     * @param sourceDir 源目录路径
     * @param targetDir 目标目录路径
     * @param suffix    文件后缀，例如 ".txt"
     * @return 扫描到的文件名称列表
     * @throws IOException 如果在扫描或复制过程中出现 I/O 错误
     */
    public static List<String> scanAndCopyFiles(String sourceDir, String targetDir, String suffix) throws IOException {
        // 存储找到的符合后缀的文件名称
        List<String> fileNames = new ArrayList<>();
        // 存储找到的符合后缀的文件路径
        List<Path> matchingFiles = new ArrayList<>();

        // 遍历源目录及其子目录
        Files.walkFileTree(Paths.get(sourceDir), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                // 检查文件是否以指定后缀结尾
                if (file.toString().endsWith(suffix)) {
                    matchingFiles.add(file);
                    fileNames.add(file.getFileName().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });

        // 创建目标目录（如果不存在）
        Path targetPath = Paths.get(targetDir);
        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
        }

        // 复制找到的文件到目标目录
        for (Path sourceFile : matchingFiles) {
            Path targetFile = targetPath.resolve(sourceFile.getFileName());
            Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
        }

        return fileNames;
    }


    /**
     * 删除指定目录下指定后缀的文件
     * @param directoryPath 目标目录路径
     * @param suffix 要删除的文件后缀（不区分大小写，支持带或不带点）
     * @return 实际删除的文件数量
     */
    public static int deleteFilesBySuffix(String directoryPath, String suffix) {
        Path dir = Paths.get(directoryPath);
        AtomicInteger deletedCount = new AtomicInteger(0);
        final String normalizedSuffix = normalizeSuffix(suffix);

        // 验证目录有效性
        if (!Files.isDirectory(dir)) {
            log.info("错误：指定的路径不是目录 - " + directoryPath);
            return 0;
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            // 遍历目录下的文件
            for (Path path : stream) {
                if (Files.isRegularFile(path) && isTargetFile(path, normalizedSuffix)) {
                    try {
                        Files.delete(path);
                        log.info("[成功] 已删除文件: " + path.getFileName());
                        deletedCount.incrementAndGet();
                    } catch (IOException e) {
                        log.error("[失败] 删除文件失败: " + path.getFileName() + " - " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            log.error("目录访问错误: " + e.getMessage());
        }

        log.info("操作完成，共删除 " + deletedCount.get() + "个文件\n");
        return deletedCount.get();
    }






    // 标准化文件后缀格式（确保以.开头且小写）
    private static String normalizeSuffix(String suffix) {
        if (suffix == null || suffix.isEmpty()) return "";
        String clean = suffix.toLowerCase().trim();
        return clean.startsWith(".") ? clean : "." + clean;
    }

    // 判断文件是否符合后缀要求
    private static boolean isTargetFile(Path path, String normalizedSuffix) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.endsWith(normalizedSuffix);
    }

    /**
     * 简化版文件复制方法（仅处理绝对路径）
     * @param sourceFiles 绝对路径文件列表
     * @param targetDir   绝对路径目标目录
     * @throws IOException 当发生I/O错误时抛出
     */
    public static void copyFiles(List<String> sourceFiles, String targetDir) throws IOException {
        log.info("【文件复制任务启动】");
        log.info("目标目录: {}", targetDir);
        log.info("待复制文件数量: {}", sourceFiles.size());

        // 创建目标目录（如果不存在）
        Path targetPath = Paths.get(targetDir).toAbsolutePath().normalize();
        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
            log.info("目录创建成功: {}", targetPath);
        }

        int successCount = 0;
        for (String source : sourceFiles) {
            Path sourcePath = Paths.get(source).toAbsolutePath().normalize();
            Path targetFile = targetPath.resolve(sourcePath.getFileName());

            try {
                Files.copy(
                        sourcePath,
                        targetFile,
                        StandardCopyOption.REPLACE_EXISTING
                );
                successCount++;
                log.info("✅ 复制成功: {} → {}", source, targetFile);
            } catch (IOException e) {
                log.error("❌ 复制失败: {} → {}", source, targetFile, e);
            }
        }

        log.info("【任务完成】");
        log.info("总文件数: {}", sourceFiles.size());
        log.info("成功复制: {} 个文件", successCount);
        log.info("失败文件: {} 个", sourceFiles.size() - successCount);
    }

    /**
     * 扫描指定目录下所有具有特定扩展名的文件，并返回文件名的列表。
     *
     * @param directoryPath 要扫描的目录路径
     * @param extension     文件扩展名（不带点，例如 "nc"）
     * @return 包含符合条件的文件名的列表
     */
    public static List<String> scanFilesByExtension(String directoryPath, String extension) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(directoryPath);

        // 检查目录是否存在且为目录
        if (!directory.exists()) {
            log.error("目录不存在: {}", directoryPath);
            return fileList;
        }
        if (!directory.isDirectory()) {
            log.error("指定的路径不是一个目录: {}", directoryPath);
            return fileList;
        }

        // 调用递归扫描方法
        scanFiles(directory, extension, fileList);

        log.info("扫描完成，找到 {} 个 .{} 文件", fileList.size(), extension);
        return fileList;
    }
    /**
     * 扫描目录下指定后缀的文件
     * @param targetDir   目标目录路径
     * @param extension   文件后缀（如".nc"）
     * @param recursive   是否递归子目录
     * @return 相对目标目录的文件路径列表
     */
    public static List<String> scanFiles(String targetDir,
                                         String extension,
                                         boolean recursive) {
        List<String> result = new ArrayList<>();
        File rootDir = new File(targetDir);

        // 校验目录有效性
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            return result;
        }

        // 统一转换为标准Path对象
        Path basePath = Paths.get(rootDir.getAbsolutePath()).normalize();

        // 开始扫描
        scanDirectory(rootDir, extension, recursive, result, basePath);
        return result;
    }

    /**
     * 递归扫描核心逻辑
     */
    private static void scanDirectory(File currentDir,
                                      String extension,
                                      boolean recursive,
                                      List<String> result,
                                      Path basePath) {
        File[] files = currentDir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                // 匹配后缀
                if (file.getName().endsWith(extension)) {
                    // 转换为相对路径
                    Path relativePath = basePath.relativize(
                            Paths.get(file.getAbsolutePath()).normalize()
                    );
                    result.add(relativePath.toString());
                }
            } else if (recursive && file.isDirectory()) {
                // 递归处理子目录
                scanDirectory(file, extension, true, result, basePath);
            }
        }
    }

    /**
     * 递归扫描目录中的文件。
     *
     * @param directory   当前扫描的目录
     * @param extension   文件扩展名
     * @param fileList    存储符合条件的文件名列表
     */
    private static void scanFiles(File directory, String extension, List<String> fileList) {
        File[] files = directory.listFiles();
        if (files == null) {
            // 如果无法读取目录内容，记录警告并跳过
            log.warn("无法读取目录内容: {}", directory.getAbsolutePath());
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // 递归扫描子目录
                scanFiles(file, extension, fileList);
            } else if (file.isFile()) {
                // 检查文件扩展名是否匹配
                if (hasExtension(file.getName(), extension)) {
                    fileList.add(file.getName());
                    log.debug("找到文件: {}", file.getAbsolutePath());
                }
            }
        }
    }
    /**
     * 复制文件到指定路径，自动递归创建目标目录
     *
     * @param sourcePath 源文件路径（必须存在且可读）
     * @param targetPath 目标文件路径（自动创建父目录）
     * @throws RuntimeException 当发生IO异常或目录创建失败时抛出
     * @throws IllegalArgumentException 当目标路径没有父目录时（如根路径下的文件）
     *
     * <p>示例：
     * {@code PathFileUtils.copyFileWithCreateDir(Paths.get("data.txt"), Paths.get("backup/2024/data.txt"))}
     */
    public static void copyFileWithCreateDir(Path sourcePath, Path targetPath) {
        // 获取目标文件的父目录路径
        Path targetDir = targetPath.getParent();

        try {
            // 处理目标目录逻辑
            if (targetDir != null) {
                // 仅在目录不存在时创建（避免冗余检查）
                if (!Files.exists(targetDir)) {
                    Files.createDirectories(targetDir);
                    log.info("目录创建成功: " + targetDir.toAbsolutePath());
                }
            } else {
                // 处理无父目录的特殊情况（如 UNIX 根目录下的文件）
                if (targetPath.getNameCount() == 0) {
                    throw new IllegalArgumentException("无效的目标路径: " + targetPath);
                }
            }

            // 执行文件复制（覆盖模式）
            Files.copy(
                    sourcePath,
                    targetPath,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {
            // 统一异常处理
            String errorMsg = String.format("操作失败: %s → %s (%s)",
                    sourcePath.toAbsolutePath(),
                    targetPath.toAbsolutePath(),
                    e.getClass().getSimpleName() + ": " + e.getMessage()
            );
            log.error(errorMsg);
            throw new RuntimeException(errorMsg, e);
        }
    }
    /**
     * 递归复制源目录下的所有内容到目标目录
     *
     * @param sourceDir 源目录路径
     * @param targetDir 目标目录路径
     * @throws IOException 如果发生IO错误
     * @throws IllegalArgumentException 如果源路径不存在或不是目录
     */
    public static void copyDirectory(String sourceDir, String targetDir) throws IOException {
        Path sourcePath = Paths.get(sourceDir);
        Path targetPath = Paths.get(targetDir);

        // 验证源路径是否存在且为目录
        if (!Files.exists(sourcePath)) {
            throw new IllegalArgumentException("源目录不存在: " + sourceDir);
        }
        if (!Files.isDirectory(sourcePath)) {
            throw new IllegalArgumentException("源路径不是目录: " + sourceDir);
        }

        // 创建目标目录(如果不存在)
        Files.createDirectories(targetPath);

        // 递归复制目录内容
        Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // 计算目标子目录路径
                Path targetSubDir = targetPath.resolve(sourcePath.relativize(dir));
                // 创建目标子目录
                Files.createDirectories(targetSubDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // 计算目标文件路径
                Path targetFile = targetPath.resolve(sourcePath.relativize(file));
                // 复制文件，替换已存在文件
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * 检查文件名是否具有指定的扩展名。
     *
     * @param fileName    文件名
     * @param extension   扩展名（不带点）
     * @return 如果文件名以指定扩展名结尾，则返回true，否则返回false
     */
    private static boolean hasExtension(String fileName, String extension) {
        if (fileName == null || extension == null) {
            return false;
        }
        // 将扩展名转换为小写，以实现不区分大小写的比较
        String lowerCaseExtension = extension.toLowerCase();
        String lowerCaseFileName = fileName.toLowerCase();
        return lowerCaseFileName.endsWith("." + lowerCaseExtension);
    }

    // 示例主方法，用于测试
    public static void main(String[] args) throws IOException{
        String directoryPath = "E:\\baiduDown\\DAT"; // 替换为实际目录路径
        String extension = "dat"; // 替换为实际文件扩展名

        List<String> ncFiles = scanFiles(directoryPath, extension , true);
        List<String> nc = scanFiles(directoryPath, extension , false);
        nc.sort(Comparator.comparing(fileName -> fileName.substring(fileName.lastIndexOf("\\") + 1)));
        Collections.sort(ncFiles, new Comparator<String>() {
            @Override
            public int compare(String file1, String file2) {
                try {

                    return file1.compareTo(file2);
                } catch (NumberFormatException e) {
                    // 处理非数字格式的异常（根据需求可调整策略）
                    throw new IllegalArgumentException("文件名格式错误，应为数字.nc格式", e);
                }
            }
        });

        List<String> strings = ncFiles.stream().map(fileName -> directoryPath + "\\" + fileName).collect(Collectors.toList());

//        copyFiles(strings, "D:\\gisSource\\modelData\\gast\\1");
        System.out.println("ncFiles = " + ncFiles);
    }
}