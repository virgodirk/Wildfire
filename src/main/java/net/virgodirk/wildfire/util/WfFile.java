package net.virgodirk.wildfire.util;

import net.virgodirk.wildfire.util.exception.WfFileException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * File Utils
 *
 * @author 李晓勇 on 2017年8月26日 上午8:57:14
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfFile {
    
    /**
     * 目录创建失败提示信息
     */
    private static final String CREATE_DIR_FAILED = "未能创建目录：";
    
    /**
     * 文件创建失败提示信息
     */
    private static final String CREATE_FILE_FAILED = "未能创建文件：";
    
    /**
     * 未找到文件提示信息
     */
    private static final String NO_FOUND_FILE = "未找到文件：";
    
    /**
     * 父目录为空提示信息
     */
    private static final String PARENT_PATH_IS_NULL = "父目录为空";
    
    
    /**
     * 获取Web项目的classes目录路径
     * @return classes目录路径，结尾包含 {@code "/"}
     */
    public static String getClassPath() {
        try {
            final URI uri = Thread.currentThread().getContextClassLoader().getResource("").toURI();
            return Paths.get(uri).toString() + File.separator;
        } catch (URISyntaxException excpt) {
            return "";
        }
    }
    
    /**
     * 获取当前路径的父路径，没有父路径返回 {@code ""}
     * @param path 当前路径，如：C:\logs、C:\logs\log.txt
     * @return 当前路径的父路径，如：C:\、C:\logs
     */
    public static String getParentPath(final String path) {
        if (path == null || path.trim().length() <= 0) {
            return "";
        }
        final Path parentPath = Paths.get(path).getParent();
        return parentPath == null ? "" : parentPath.toString();
    }

    /**
     * 从文件路径中获取文件名称
     * @param filePath 文件路径，如：C:\logs\log.txt
     * @return 文件名称，如：log.txt
     */
    public static String getFileNameByPath(final String filePath) {
        if (filePath == null || filePath.trim().length() <= 0) {
            return "";
        }
        final Path path = Paths.get(filePath).getFileName();
        return path == null ? "" : path.toString();
    }
    
    /**
     * 获取文件扩展名
     * @param fileName 文件名称或含有文件名称的路径，如：log.txt、C:\logs\log.txt
     * @return 文件扩展名，如：txt、jpg、bmp等，没有扩展名返回 {@code ""}
     */
    public static String getExtensionName(final String fileName) {
        if (fileName == null || fileName.trim().length() <= 0 || fileName.endsWith(".")) {
            return "";
        }
        final int lastIndex = fileName.lastIndexOf('.');
        return lastIndex < 0 ? "" : fileName.substring(lastIndex + 1);
    }
    
    
    /**
     * 检查文件或目录是否存在
     * @param path 文件路径或目录，如：C:\logs、C:\logs\log.txt
     * @return {@code true} 存在<br>
     *         {@code false} 不存在
     */
    public static boolean exists(final String path) {
        if (path == null || path.trim().length() <= 0) {
            return false;
        }
        return Files.exists(Paths.get(path));
    }
    
    /**
     * 检查文件或目录是否不存在
     * @param path 文件路径或目录，如：C:\logs、C:\logs\log.txt
     * @return {@code true} 不存在<br>
     *         {@code false} 存在
     */
    public static boolean notExists(final String path) {
        if (path == null || path.trim().length() <= 0) {
            return false;
        }
        return Files.notExists(Paths.get(path));
    }
    
    /**
     * 检查当前路径是否是目录
     * @param path 当前路径，如：C:\logs、C:\logs\
     * @return {@code true} 是目录<br>
     *         {@code false} 不是目录或目录不存在
     */
    public static boolean isDirectory(final String path) {
        if (path == null || path.trim().length() <= 0) {
            return false;
        }
        return Files.isDirectory(Paths.get(path));
    }
    
    
    /**
     * 创建新目录
     * @param newDir 待创建目录，如：C:\logs\info
     * @return 创建成功后的目录路径 {@link Path}
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static Path createDir(final String newDir) throws WfFileException {
        if (newDir == null || newDir.trim().length() <= 0) {
            throw new WfFileException("待创建目录为空");
        }

        // 检查待创建目录是否已存在
        final Path dir = Paths.get(newDir);
        if (Files.exists(dir)) {
            return dir;
        }
        
        // 创建目录
        try {
            return Files.createDirectories(dir);
        } catch (IOException excpt) {
            throw new WfFileException(CREATE_DIR_FAILED + newDir, excpt);
        }
    }
    
    /**
     * 创建新文件
     * @param newFile 待创建文件路径，如：C:\logs\log.txt
     * @return 创建成功后的文件路径 {@link Path}
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static Path createFile(final String newFile) throws WfFileException {
        if (newFile == null || newFile.trim().length() <= 0) {
            throw new WfFileException("待创建文件路径为空");
        }
        
        // 检查文件是否已存在
        final Path path = Paths.get(newFile);
        if (Files.exists(path)) {
            return path;
        }
        
        // 检查文件所在目录是否存在，不存在则创建
        final Path dir = Paths.get(newFile).getParent();
        if (dir == null) {
            throw new WfFileException(newFile + PARENT_PATH_IS_NULL);
        }
        if (Files.notExists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException excpt) {
                throw new WfFileException(CREATE_DIR_FAILED + dir, excpt);
            }
        }
        
        // 创建文件
        try {
            return Files.createFile(path);
        } catch (IOException excpt) {
            throw new WfFileException(CREATE_FILE_FAILED + newFile, excpt);
        }
    }
    
    
    /**
     * 读取文件为 {@code byte[]}
     * <p>注意：如果文件很大，可能会超出内存空间，使用前要做评估</p>
     * @param filePath 待读取文件路径，如：C:\logs\log.txt
     * @return {@code byte[]} 类型文件数据
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static byte[] readToBytes(final String filePath) throws WfFileException {
        if (filePath == null || filePath.trim().length() <= 0) {
            throw new WfFileException("文件路径为空");
        }
        
        // 检查文件是否存在
        final Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            throw new WfFileException(NO_FOUND_FILE + filePath);
        }
        
        // 读取文件
        try {
            return Files.readAllBytes(path);
        } catch (IOException excpt) {
            throw new WfFileException("读取文件：" + filePath + " 错误", excpt);
        }
    }
    
    /**
     * 读取文件为Base64字符串
     * <p>注意：如果文件很大，可能会超出内存空间，使用前要做评估</p>
     * @param filePath 待读取文件路径，如：C:\logs\log.txt
     * @return Base64编码后的文件数据
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static String readToBase64(final String filePath) throws WfFileException {
        return WfBase64.encodeBytes(WfFile.readToBytes(filePath));
    }
    
    /**
     * 将 {@code byte[]} 数据写入文件
     * <p>目录或文件不存在时会自动创建</p>
     * @param bytes 待写入 {@code byte[]} 数据
     * @param filePath 待写入文件路径，如：C:\logs\log.txt
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static void write(final byte[] bytes, final String filePath) throws WfFileException {
        writeCheck(bytes, filePath);
        
        // 检查目标目录是否存在，不存在则创建
        final Path dir = Paths.get(filePath).getParent();
        if (dir == null) {
            throw new WfFileException(filePath + PARENT_PATH_IS_NULL);
        }
        if (Files.notExists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException expt) {
                throw new WfFileException(CREATE_DIR_FAILED + filePath, expt);
            }
        }
        
        // 检查文件是否存在，不存在则创建
        final Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException expt) {
                throw new WfFileException(CREATE_FILE_FAILED + filePath, expt);
            }
        }
        
        // 写入文件
        try {
            Files.write(path, bytes, StandardOpenOption.APPEND);
        } catch (IOException excpt) {
            throw new WfFileException("向文件：" + filePath + " 中写入数据错误", excpt);
        }
    }
    
    /**
     * 将 {@code InputStream} 写入文件
     * <p>目录或文件不存在时会自动创建</p>
     * @param input 待写入 {@code InputStream} 数据
     * @param filePath 待写入文件路径，如：C:\logs\log.txt
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static void write(final InputStream input, final String filePath) throws WfFileException {
        final String path = WfFile.getParentPath(filePath);
        if (WfFile.notExists(path)) {
            WfFile.createDir(path);
        }
        
        int readBytes;
        final byte[] buff = new byte[1024];
        try (final FileOutputStream output = new FileOutputStream(filePath)) {
            while ((readBytes = input.read(buff, 0, 1024)) > 0) {
                output.write(buff, 0, readBytes);
            }
        } catch (IOException excpt) {
            throw new WfFileException("向文件：" + filePath + " 中写入数据错误", excpt);
        }
    }
    
    
    /**
     * 复制文件
     * @param source 原文件路径，如：C:\logs\src.txt
     * @param target 目标文件路径，如：C:\logs\dest.txt
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static void copyFile(final String source, final String target) throws WfFileException {
        if (source == null || source.trim().length() <= 0 || target == null || target .trim().length() <= 0) {
            throw new WfFileException("原文件路径或目标文件路径为空");
        }
        
        // 检查原文件是否存在
        final Path sourcePath = Paths.get(source);
        if (Files.notExists(sourcePath)) {
            throw new WfFileException(NO_FOUND_FILE + source);
        }

        // 检查目标目录是否存在，不存在则创建
        final Path targetDir = Paths.get(target).getParent();
        if (targetDir == null) {
            throw new WfFileException(target + PARENT_PATH_IS_NULL);
        }
        if (Files.notExists(targetDir)) {
            try {
                Files.createDirectories(targetDir);
            } catch (IOException excpt) {
                throw new WfFileException(CREATE_DIR_FAILED + targetDir, excpt);
            }
        }
        
        // 复制文件
        try {
            Files.copy(sourcePath, Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException excpt) {
            throw new WfFileException(String.format("复制文件 %s 到 %s 错误", source, target), excpt);
        }
    }

    /**
     * 复制原目录下所有子目录和文件到目标目录
     * @param source 原目录路径，如：C:\logs\log
     * @param target 目标目录路径，如：C:、logs\copyLog
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static void copyDir(final String source, final String target) throws WfFileException {
        if (source == null || source.trim().length() <= 0 || target == null || target .trim().length() <= 0) {
            throw new WfFileException("原目录或目标目录为空");
        }
        
        // 检查原目录是否存在
        final Path sourcePath = Paths.get(source);
        if (Files.notExists(sourcePath)) {
            throw new WfFileException("未找到目录：" + source);
        }

        // 检查目标目录是否存在，不存在则创建
        final Path targetPath = Paths.get(target);
        if (Files.notExists(targetPath)) {
            try {
                Files.createDirectories(targetPath);
            } catch (IOException e) {
                throw new WfFileException(CREATE_DIR_FAILED + target);
            }
        }
        
        // 复制目录
        try {
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                
                /**
                 * 创建目录
                 */
                @Override
                public FileVisitResult preVisitDirectory(final Path dir, 
                        final BasicFileAttributes attrs) throws IOException {
                    // 在目标目录中创建dir对应的子目录
                    Path subDir;
                    if (dir.compareTo(sourcePath) == 0) {
                        subDir = targetPath;
                    } else {
                        subDir = targetPath.resolve(dir.subpath(sourcePath.getNameCount(), dir.getNameCount()));
                    }
                    Files.createDirectories(subDir);
                    return super.preVisitDirectory(dir, attrs);
                }
                
                /**
                 * 复制文件
                 */
                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, targetPath.resolve(file.subpath(sourcePath.getNameCount(), file.getNameCount())), 
                            StandardCopyOption.REPLACE_EXISTING);
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException excpt) {
            throw new WfFileException(String.format("复制目录 %s 至 %s 错误", source, target), excpt);
        }
    }
    
    /**
     * 删除文件
     * @param filePath 待删除文件路径，如：C:\logs\log.txt
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static void deleteFile(final String filePath) throws WfFileException {
        if (filePath == null || filePath.trim().length() <= 0) {
            throw new WfFileException("文件路径为空");
        }
        
        final Path path = Paths.get(filePath);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            throw new WfFileException(NO_FOUND_FILE + filePath);
        }
        
        try {
            Files.delete(path);
        } catch (IOException excpt) {
            throw new WfFileException("删除文件：" + filePath + " 错误", excpt);
        }
    }
    
    /**
     * 删除目录及其所有子目录和文件
     * @param dir 待删除目录，如：C:\longs\info
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static void deleteDir(final String dir) throws WfFileException {
        if (dir == null || dir.trim().length() <= 0) {
            throw new WfFileException("目录为空");
        }
        
        // 检查目录是否存在
        final Path dirPath = Paths.get(dir);
        if (Files.notExists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new WfFileException("未找到目录：" + dir);
        }
        
        // 遍历删除
        try {
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
                
                /**
                 * 删除目录
                 */
                @Override
                public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) throws IOException {
                    Files.delete(dir);
                    return super.postVisitDirectory(dir, exc);
                }
                
                /**
                 * 删除文件
                 */
                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException excpt) {
            throw new WfFileException("删除目录：" + dir + " 错误", excpt);
        }
    }
    
    /**
     * 将原文件移动至目标文件同时删除原文件
     * @param source 原文件路径，如：C:\logs\log.txt
     * @param target 目标文件路径，如：C:\logs\newLog.txt
     * @return 目标文件路径
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static Path moveFile(final String source, final String target) throws WfFileException {
        if (source == null || source.trim().length() <= 0 || target == null || target .trim().length() <= 0) {
            throw new WfFileException("原文件路径或目标文件路径为空");
        }
        
        // 检查原文件是否存在
        final Path sourcePath = Paths.get(source);
        if (Files.notExists(sourcePath)) {
            throw new WfFileException(NO_FOUND_FILE + source);
        }
        
        // 检查目标目录是否存在
        final Path targetDir = Paths.get(target).getParent();
        if (targetDir == null) {
            throw new WfFileException(target + PARENT_PATH_IS_NULL);
        }
        if (Files.notExists(targetDir)) {
            try {
                Files.createDirectories(targetDir);
            } catch (IOException excpt) {
                throw new WfFileException(CREATE_DIR_FAILED + targetDir, excpt);
            }
        }
        
        // 移动文件
        try {
            return Files.move(sourcePath, Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException excpt) {
            throw new WfFileException(String.format("移动文件 %s 至 %s 错误", source, target), excpt);
        }
    }
    
    /**
     * 将原目录下所有子目录和文件移动到目标目录下同时删除原目录
     * @param source 原目录，如：C:\logs\old
     * @param target 目标目录，如：C:\logs\new
     * @return 目标目录 {@link Path}
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static Path moveDir(final String source, final String target) throws WfFileException {
        if (source == null || source.trim().length() <= 0 || target == null || target.trim().length() <= 0) {
            throw new WfFileException("原目录或目标目录为空");
        }
        
        // 检查原目录是否存在
        final Path sourcePath = Paths.get(source);
        if (Files.notExists(sourcePath)) {
            throw new WfFileException("未找到目录：" + source);
        }
        
        // 检查目标目录是否存在
        final Path targetDir = Paths.get(target);
        if (Files.notExists(targetDir)) {
            try {
                Files.createDirectories(targetDir);
            } catch (IOException excpt) {
                throw new WfFileException(CREATE_DIR_FAILED + target, excpt);
            }
        }
        
        // 移动目录
        try {
            return Files.move(sourcePath, Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException excpt) {
            throw new WfFileException(String.format("移动目录 %s 至 %s 错误", source, target), excpt);
        }
    }
    
    /**
     * 重命名文件或目录
     * @param oldName 旧名称（包含路径），如：C:\logs\oldLog.txt
     * @param newName 新名称（包含路径），如：C:\logs\newLog.txt
     * @param isOverwrite 是否覆盖已存在的同名文件或文件夹，{@code true} 为覆盖
     * @return {@code true} 重命名成功<br>
     *         {@code false} 重命名失败
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static boolean rename(final String oldName, 
            final String newName, final boolean isOverwrite) throws WfFileException {
        if (oldName == null || oldName.trim().length() <= 0 || newName == null || newName.trim().equals("")) {
            throw new WfFileException("文件或目录名称为空");
        }

        // 检查旧文件或目录是否存在
        if (!Files.exists(Paths.get(oldName))) {
            throw new WfFileException("未找到文件或目录：" + oldName);
        }

        // 覆盖同名文件或目录
        final Path newPath = Paths.get(newName);
        if (Files.exists(newPath) && isOverwrite) {
            if (Files.isDirectory(newPath)) {
                deleteDir(newName);
            } else {
                deleteFile(newName);
            }
        }
        return new File(oldName).renameTo(new File(newName));
    }
    
    
    /**
     * 获取文件大小（字节）
     * @param filePath 文件路径，如：C:\logs\log.txt
     * @return 返回文件大小（字节）<br>
     *         返回 {@code -1} 表示文件不存在
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static long getSize(final String filePath) throws WfFileException {
        if (filePath == null || filePath.trim().length() <= 0) {
            return -1L;
        }
        
        final Path path = Paths.get(filePath);
        if (!Files.exists(path) || Files.isDirectory(path)) {
            return -1L;
        }
        
        try {
            return Files.size(path);
        } catch (IOException excpt) {
            throw new WfFileException("获取文件：" + filePath + " 大小错误", excpt);
        }
    }
    
    /**
     * 获取文件最后修改时间
     * @param filePath 文件路径，如：C:\logs\log.txt
     * @return 文件最后修改时间 {@link LocalDateTime}
     * @throws WfFileException {@link WfFileException} 异常
     */
    public static LocalDateTime getLastModifiedTime(final String filePath) throws WfFileException {
        if (filePath == null || filePath.trim().length() <= 0) {
            return LocalDateTime.now(ZoneId.systemDefault());
        }
        
        final Path path = Paths.get(filePath);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            return LocalDateTime.now(ZoneId.systemDefault());
        }
        
        try {
            final FileTime fileTime = Files.getLastModifiedTime(path);
            return LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
        } catch (IOException excpt) {
            throw new WfFileException("获取文件：" + filePath + " 最后修改时间错误", excpt);
        }
    }
    
    
    /**
     * 写文件参数检查
     * @param bytes 待写入 {@code byte[]} 数据
     * @param filePath 待写入文件路径，如：C:\logs\log.txt
     * @throws WfFileException {@link WfFileException} 异常
     */
    private static void writeCheck(final byte[] bytes, final String filePath) throws WfFileException {
        if (bytes == null) {
            throw new WfFileException("待写入数据为空");
        }
        if (filePath == null || filePath.trim().length() <= 0) {
            throw new WfFileException("待写入文件路径为空");
        }
    }
}