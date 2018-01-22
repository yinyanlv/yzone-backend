package com.yzone.util.file;

import java.io.*;

public class FileHelper {

    /**
     * 已byte为单位读取文件，常用于读取二进制文件，如图片、声音、视频等文件
     *
     * @param filePath
     */
    public static void readFileByByte(String filePath) {

        File file = new File(filePath);
        InputStream in = null;

        try {

            in = new FileInputStream(file);
            int byteTemp;

            while ((byteTemp = in.read()) != -1) {

                System.out.write(byteTemp);
            }

            in.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 以自定义长度的byte[]为单位读取文件，常用于读取二进制文件，如图片、声音、视频等文件
     *
     * @param filePath
     * @param bytesLength
     */
    public static void readFileByBytes(String filePath, int bytesLength) {

        File file = new File(filePath);
        InputStream in = null;

        try {

            in = new FileInputStream(file);
            byte[] bytesTemp = new byte[bytesLength];
            int len;

            System.out.println("当前文件的字节数为：" + in.available());

            while ((len = in.read(bytesTemp)) != -1) {

                System.out.write(bytesTemp, 0, len);
            }

            in.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 以字符为单位读取文件，常用于读取文本类型的文件
     *
     * @param filePath
     */
    public static void readFileByChar(String filePath) {

        File file = new File(filePath);
        Reader reader = null;

        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int charTemp;

            while ((charTemp = reader.read()) != -1) {
                // windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行
                if ((char) charTemp != '\r') {
                    System.out.print((char) charTemp);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以自定义长度的char[]为单位读取文件，常用于读取文本类型的文件
     *
     * @param filePath
     * @param charsLength
     */
    public static void readFileByChars(String filePath, int charsLength) {

        File file = new File(filePath);
        Reader reader = null;

        try {
            reader = new InputStreamReader(new FileInputStream(file));
            char[] charsTemp = new char[charsLength];
            int len = 0;

            while ((len = reader.read(charsTemp)) != -1) {

                if (len == charsTemp.length && charsTemp[charsTemp.length - 1] != '\r') {
                    System.out.print(charsTemp);
                } else {
                    for (int i = 0; i < len; i++) {
                        if (charsTemp[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(charsTemp[i]);
                        }
                    }
                }
            }

            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 以行为单位读取文件，常用于读取面向行的格式化文件
     *
     * @param filePath
     */
    public static void readFileByLine(String filePath) {

        File file = new File(filePath);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String strTemp;

            while ((strTemp = reader.readLine()) != null) {

                System.out.println(strTemp);
            }

            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 随机起始位置读取文件内容
     *
     * @param filePath
     */
    public static void readFileByRandomAccess(String filePath) {

        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(filePath, "r");

            System.out.println("文件字节数：" + file.length());

            int beginIndex = (int) Math.ceil(Math.random() * file.length());

            file.seek(beginIndex);

            byte[] bytes = new byte[100];
            int len = 0;

            while ((len = file.read(bytes)) != -1) {

                System.out.write(bytes, 0, len);
            }

            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 在文件尾部追加内容
     *
     * @param filePath
     * @param content
     */
    public static void appendToFile(String filePath, String content) {

        try {

            FileWriter writer = new FileWriter(filePath, true);  // true，表示追加模式

            writer.write(content);

            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 在文件尾部追加内容，（RandomAccessFile实现）
     *
     * @param filePath
     * @param content
     */
    public static void appendToFileByRandomAccess(String filePath, String content) {

        try {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");

            int len = (int) file.length();
            file.seek(len);
            file.writeBytes(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @param filePath
     */
    public static void createFile(String filePath) {

        File file = new File(filePath);

        if (file.exists()) {
            return;
        }

        if (!file.getParentFile().exists()) {

            file.getParentFile().mkdirs();
        }

        try {
            file.createNewFile();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 创建临时文件
     *
     * @param prefix "test"
     * @param suffix ".txt"
     * @param dirPath
     */
    public static void createTempFile(String prefix, String suffix, String dirPath) {

        if (dirPath == null) {

            try {

                File.createTempFile(prefix, suffix);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            File dir = new File(dirPath);

            if (!dir.exists()) {

                createDir(dirPath);
            }

            try {

                File.createTempFile(prefix, suffix, dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件夹
     *
     * @param dirPath
     */
    public static void createDir(String dirPath) {

        File dir = new File(dirPath);

        if (dir.exists()) {
            return;
        }

        dir.mkdirs();
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {

        File file = new File(filePath);

        if (file.isFile() && file.exists()) {

            file.delete();
        }
    }

    /**
     * 删除文件夹
     *
     * @param dirPath
     */
    public static void deleteDir(String dirPath) {

        File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                deleteFile(files[i].getAbsolutePath());
            } else {
                deleteDir(files[i].getAbsolutePath());
            }
        }

        dir.delete();
    }

    /**
     * 复制文件
     *
     * @param sourcePath
     * @param targetPath
     */
    public static void copyFile(String sourcePath, String targetPath) {

        try {
            File sourceFile = new File(sourcePath);
            File targetFile = new File(targetPath);

            if (!sourceFile.exists()) {
                return;
            }

            if (!targetFile.exists()) {
                createFile(targetPath);
            }

            InputStream in = new FileInputStream(sourceFile);
            FileOutputStream out = new FileOutputStream(targetPath);
            byte[] bytesTemp = new byte[1024];
            int len = 0;
            while ((len = in.read(bytesTemp)) != -1) {
                out.write(bytesTemp, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 复制文件夹
     *
     * @param sourcePath
     * @param targetPath
     */
    public static void copyDir(String sourcePath, String targetPath) {

        try {
            File sourceDir = new File(sourcePath);
            File targetDir = new File(targetPath);

            if (!sourceDir.exists()) {
                return;
            }

            if (!targetDir.exists()) {

                createDir(targetPath);
            }

            String[] files = sourceDir.list();
            File fileTemp = null;

            for (int i = 0; i < files.length; i++) {

                if (sourcePath.endsWith(File.separator)) {
                    fileTemp = new File(sourcePath + files[i]);
                } else {
                    fileTemp = new File(sourcePath + File.separator + files[i]);
                }

                if (fileTemp.isFile()) {
                    FileInputStream in = new FileInputStream(fileTemp);
                    FileOutputStream out = new FileOutputStream(targetPath + "/" + fileTemp.getName().toString());

                    byte[] bytesTemp = new byte[1024];
                    int len;

                    while ((len = in.read(bytesTemp)) != -1) {
                        out.write(bytesTemp, 0, len);
                    }

                    out.flush();
                    in.close();
                    out.close();
                }

                if (fileTemp.isDirectory()) {
                    copyDir(sourcePath + File.separator + files[i], targetPath + File.separator + files[i]);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String path = System.getProperty("user.dir");

        FileHelper.copyDir(path + "/src/test", "d:/test");
        FileHelper.deleteDir("d:/test");
    }
}
