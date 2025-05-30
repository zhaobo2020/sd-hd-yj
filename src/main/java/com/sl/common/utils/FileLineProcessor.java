package com.sl.common.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileLineProcessor {

    /**
     * 扫描文件并返回所有以指定前缀开头的行号（从0开始）
     *
     * @param filePath 文件路径
     * @param prefix   要匹配的行前缀
     * @param charset  文件编码
     * @return 匹配行的行号
     */
    public static Integer scanLinesWithPrefix(String filePath,
                                                    String prefix,
                                                    Charset charset) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath), charset);

//        默认没有
        Integer res = -1;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(prefix)) {
                res = i;
                // 找到符合条件的行后，立即中断循环
                break;
            }
        }
        return res;
    }

    // 替换方法（支持批量替换）
    public static void replaceLines(String filePath,
                                    List<Integer> lineNumbers,
                                    List<String> newContents,
                                    Charset charset) throws IOException {
        // 参数校验
        if (lineNumbers.size() != newContents.size()) {
            throw new IllegalArgumentException("行号数量与内容数量不匹配");
        }
        if (lineNumbers.isEmpty()) {
            return; // 无需处理
        }

        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>(Files.readAllLines(path, charset));

        // 倒序处理避免行号变化
        List<Integer> sortedLines = new ArrayList<>(lineNumbers);
        Collections.sort(sortedLines, Collections.reverseOrder());

        for (int i = 0; i < sortedLines.size(); i++) {
            int lineNum = sortedLines.get(i);
            String newContent = newContents.get(lineNumbers.indexOf(lineNum));

            if (lineNum < 0 || lineNum >= lines.size()) {
                throw new IllegalArgumentException("无效行号: " + (lineNum+1));
            }
            lines.set(lineNum, newContent);
        }

        Files.write(path, lines, charset);
    }


}