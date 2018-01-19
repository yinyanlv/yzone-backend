package com.yzone.util.file;

import java.io.*;

public class FileHelper {

    /**
     * 已byte为单位读取文件，常用于读取二进制文件，如图片、声音、视频等文件
     *
     * @param filename
     */
    public static void readFileByByte(String filename) {

        File file = new File(filename);
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
     * @param filename
     * @param bytesLength
     */
    public static void readFileByBytes(String filename, int bytesLength) {

        File file = new File(filename);
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
     * @param filename
     */
    public static void readFileByChar(String filename) {

        File file = new File(filename);
        Reader reader = null;

        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int charTemp;

            while ((charTemp = reader.read()) != -1) {
                // windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行
                if ((char)charTemp != '\r') {
                    System.out.print((char)charTemp);
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
     * @param filename
     * @param charsLength
     */
    public static void readFileByChars(String filename, int charsLength) {

        File file = new File(filename);
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
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 以行为单位读取文件，常用于读取面向行的格式化文件
     *
     * @param filename
     */
    public static void readFileByLine(String filename) {

        File file = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String strTemp;

            while ((strTemp = reader.readLine()) != null) {

                System.out.println(strTemp);
            }

            reader.close();
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 随机起始位置读取文件内容
     *
     * @param filename
     */
    public static void readFileByRandomAccess(String filename) {

        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(filename, "r");

            System.out.println("文件字节数：" + file.length());

            int beginIndex = (int)Math.ceil(Math.random() * file.length());

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

    public static void main(String[] args) {

        String path = System.getProperty("user.dir") + "/pom.xml";

        FileHelper.readFileByByte(path);
        FileHelper.readFileByBytes(path, 100);
        FileHelper.readFileByChar(path);
        FileHelper.readFileByChars(path, 100);
        FileHelper.readFileByLine(path);
        FileHelper.readFileByRandomAccess(path);
    }
}
