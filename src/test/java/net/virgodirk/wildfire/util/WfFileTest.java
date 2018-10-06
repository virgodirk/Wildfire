package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * Test for WfFile
 *
 * @author 李晓勇 on 2017年8月26日 上午11:03:32
 * @version Version 3.0
 */
public class WfFileTest {

    @Test
    public void CommonOperations() { // 文件常用操作组合测试
        // 测试根目录C:\JUtilsTest\
        String rootPath = "C:\\JUtilsTest\\";

        // 删除测试目录和文件，初始化测试环境
        if (WfFile.exists(rootPath)) {
            WfFile.deleteDir(rootPath);
        }
        assertFalse(WfFile.exists(rootPath));

        // 创建目录 C:\JUtilsTest\SubA\
        String subAPath = rootPath + "SubA" + File.separator;
        assertNotNull(WfFile.createDir(subAPath));
        assertTrue(WfFile.exists(subAPath));

        // 创建文件C:\JUtilsTest\SubA/sub-a.txt
        String subAFile = subAPath + "sub-a.txt";
        assertNotNull(WfFile.createFile(subAFile));
        assertTrue(WfFile.exists(subAFile));

        // 重命名文件 C:\JUtilsTest\SubA/sub-a-new.txt
        String newSubAFile = subAPath + "sub-a-new.txt";
        assertTrue(WfFile.rename(subAFile, newSubAFile, true));
        assertTrue(WfFile.exists(newSubAFile));
        assertFalse(WfFile.exists(subAFile));

        // 拷贝文件夹 C:\JUtilsTest\SubB/sub-b.txt
        String subBPath = rootPath + "SubB" + File.separator;
        String subBFile = subBPath + "sub-b.txt";
        WfFile.copyDir(subAPath, subBPath);
        assertTrue(WfFile.exists(subBPath));
        // 将拷贝过来的sub-a-new.txt重命名为sub-b.txt
        assertTrue(WfFile.rename(subBPath + "sub-a-new.txt", subBFile, false));
        assertTrue(WfFile.exists(subBFile));

        // 拷贝文件 将sub-b.txt拷贝到SubA目录下并重命名为copy.txt
        String copyFile = subAPath + "copy.txt";
        WfFile.copyFile(subBFile, copyFile);
        assertTrue(WfFile.exists(copyFile));

        // 移动文件夹 将SubB移动到SubA下
        assertNotNull(WfFile.moveDir(subBPath, subAPath + "SubB" + File.separator));
        assertTrue(WfFile.exists(subAPath + "SubB" + File.separator));
        assertFalse(WfFile.exists(subBPath));

        // 移动文件 将copy.txt移动到C:\JUtilsTest\下
        assertNotNull(WfFile.moveFile(copyFile, rootPath + "copy.txt"));
        assertTrue(WfFile.exists(rootPath + "copy.txt"));
        assertFalse(WfFile.exists(copyFile));

        // 删除文件
        WfFile.deleteFile(rootPath + "copy.txt");
        assertFalse(WfFile.exists(rootPath + "copy.txt"));

        // 删除目录
        WfFile.deleteDir(rootPath);
        assertFalse(WfFile.exists(rootPath));
    }

    @Test
    public void readAndWrite() throws Exception { // 文件读写测试
        String text = "abc测试123";
        String filePath = "C:\\JUtilsTest\\test.txt";

        // 写入
        WfFile.write(text.getBytes("UTF-8"), filePath);
        assertTrue(WfFile.exists(filePath));

        // 读取（并非读取文件内容）
        byte[] data = WfFile.readToBytes(filePath);
        assertEquals(WfBase64.encodeBytes(data), WfFile.readToBase64(filePath));

        // 删除
        WfFile.deleteDir(filePath.substring(0, filePath.lastIndexOf(File.separator)));
    }

    @Test
    public void testGetParentPath() {
        String path = "E:\\Eclipse\\JUtilsLab\\JUtils\\Test.java";
        assertEquals("", WfFile.getParentPath(null));
        assertEquals("", WfFile.getParentPath(""));
        assertEquals("", WfFile.getParentPath(" "));
        assertEquals("E:\\Eclipse\\JUtilsLab\\JUtils", WfFile.getParentPath(path));
        assertEquals("E:\\Eclipse\\JUtilsLab\\JUtils", WfFile.getParentPath(path));
        assertEquals("C:\\", WfFile.getParentPath("C:/abc"));
    }

    @Test
    public void tetGetFileNameByPath() {
        String path = "E:\\Eclipse\\JUtilsLab\\JUtils\\Test.java";
        assertEquals("", WfFile.getFileNameByPath(null));
        assertEquals("", WfFile.getFileNameByPath(""));
        assertEquals("", WfFile.getFileNameByPath(" "));
        assertEquals("Eclipse", WfFile.getFileNameByPath("E:\\Eclipse"));
        assertEquals("Eclipse", WfFile.getFileNameByPath("E:\\Eclipse\\"));
        assertEquals("Eclipse", WfFile.getFileNameByPath("E:/Eclipse"));
        assertEquals("Eclipse", WfFile.getFileNameByPath("E:/Eclipse/"));
        assertEquals("Test.java", WfFile.getFileNameByPath(path));
    }

    @Test
    public void testGetExtensionName() {
        assertEquals("", WfFile.getExtensionName(null));
        assertEquals("", WfFile.getExtensionName(""));
        assertEquals("", WfFile.getExtensionName(" "));
        assertEquals("", WfFile.getExtensionName("."));
        assertEquals("", WfFile.getExtensionName("test."));
        assertEquals("", WfFile.getExtensionName("d:/Test/test"));
        assertEquals("txt", WfFile.getExtensionName(".txt"));
        assertEquals("txt", WfFile.getExtensionName("test.txt"));
        assertEquals("txt", WfFile.getExtensionName("d:/Test/test.txt"));
    }
    
    @Test
    public void testGetFileSize() {
        assertEquals(WfFile.getSize(null), -1);
        assertEquals(WfFile.getSize(""), -1);
        assertEquals(WfFile.getSize(" "), -1);
        assertEquals(WfFile.getSize("C:\\abcdefg"), -1);
        assertEquals(WfFile.getSize("C:\\Windows"), -1);
    }
}