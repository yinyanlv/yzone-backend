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

    public static void appendToFile(String filePath, String content) {

        try {

            FileWriter writer = new FileWriter(filePath, true);  // true，表示追加模式

            writer.write(content);

            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

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

    public static void createTempFile(String prefix, String suffix, String dirPath) {

        if (dirPath == null) {

            try {

                File.createTempFile(prefix, suffix);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            File file = new File(dirPath);

            if (!file.exists()) {

                createDir(dirPath);
            }

            try {

                File.createTempFile(prefix, suffix, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createDir(String dirPath) {

        File file = new File(dirPath);

        if (file.exists()) {
            return;
        }

        file.mkdirs();
    }

    public static void deleteFile(String filePath) {

        File file = new File(filePath);

        if (file.isFile() && file.exists()) {

            file.delete();
        }
    }

    public static void deleteDir(String dirPath) {

        File file = new File(dirPath);

        if (!file.exists() || !file.isDirectory()) {
            return;
        }

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                deleteFile(files[i].getAbsolutePath());
            } else {
                deleteDir(files[i].getAbsolutePath());
            }
        }

        file.delete();
    }

    // TODO
    public static void copyFile(String sourcePath, String targetPath) {

        try {

            File sourceFile = new File(sourcePath);
            File targetFile = new File(targetPath);
            if (!sourceFile.exists()) {
                return;
            }
            if (!targetFile.exists()) {
                // TODO
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

    // TODO
    public static void copyDir(String sourcePath, String targetPath) {

        try {
            File sourceFile = new File(sourcePath);
            File targetFile = new File(targetPath);

            if (!sourceFile.exists()) {
                return;
            }

            if (!targetFile.exists()) {
                // TODO
            }

            String[] files = sourceFile.list();
            File fileTemp = null;

            for (int i = 0; i < files.length; i++) {

                if (sourcePath.endsWith(File.separator)) {
                    fileTemp = new File(sourceFile + files[i]);
                } else {
                    fileTemp = new File(sourceFile + File.separator + files[i]);
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
                    copyDir(sourcePath + "/" + files[i], targetPath + "/" + files[i]);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String path = System.getProperty("user.dir") + "/pom.xml";

        FileHelper.appendToFile(path, "Hello \n");
        FileHelper.appendToFileByRandomAccess(path, "World \n");
        FileHelper.readFileByLine(path);
        FileHelper.copyDir(System.getProperty("user.dir"), "D:\\");
    }
}
