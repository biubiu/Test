package com.shawn.general;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.Pipe;

public class ImageOperationTest {

    public static void main(String[] args) throws IOException, InterruptedException, IM4JavaException {
        String path="/var/image/temp/65498/apple.jpg";
        File file = new File("/home/shawncao/image/small/65498/");

        if(!file.exists()){
            System.out.println(file.exists());
            System.out.println(file.mkdirs());;
        }
        //System.out.println(path.lastIndexOf("/"));;
        //String dir = path.substring(0,21);
        //System.out.println(dir);

       /* ImageParam imageParam = new ImageParam();
        imageParam.setHeight(100);
        imageParam.setWitdth(100);
        imageParam.setOperation(Operation.resize);
        testImage(imageParam);*/
        //boolean flag=processImage("/home/shawncao/Desktop/logos/apple_2.jpg","/home/shawncao/Desktop/apple-small.jpg", imageParam);
        //System.out.println("finish"+ flag);
    }
    private static Matcher matcher;
    private static final String IMAGE_FILE_NAME_PATTERN_STRING = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    private static Pattern pattern = Pattern.compile(IMAGE_FILE_NAME_PATTERN_STRING);

    public static boolean validateFileName(final String image) {
        matcher = pattern.matcher(image);
        return matcher.matches();
    }

    public static boolean processImage(String imagePath, String targetPath, ImageParam imageParam) {
        boolean flag = false;
        try {

            IMOperation op = new IMOperation();
            op.addImage(imagePath);
            /** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
            switch (imageParam.getOperation()) {
            case cut:
                 op.crop(imageParam.getWitdth(), imageParam.getHeight(),imageParam.getX(), imageParam.getY());
                break;
            case resize:
                op.resize(imageParam.getWitdth(), imageParam.getHeight());
                break;
            default:
                op.resize(imageParam.getWitdth(), imageParam.getHeight());
                break;
            }

            op.addImage(targetPath);
            ConvertCmd convert = new ConvertCmd(true);
            convert.run(op);
            flag = true;

        } catch (IOException e) {
            System.out.println("文件读取错误!");
            flag = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = false;
        } catch (IM4JavaException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


    public static byte[] image(ImageParam imageParam, InputStream in) throws IOException, InterruptedException, IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage("-");
        op.resize(imageParam.getWitdth(), imageParam.getHeight());
        op.addImage("-");
        Pipe pipeIn = new Pipe(in, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Pipe pipeOut = new Pipe(null, out);

        ConvertCmd convert = new ConvertCmd();
        convert.setInputProvider(pipeIn);
        convert.setOutputConsumer(pipeOut);
        convert.run(op);
        in.close();
        out.close();
        return out.toByteArray();
    }

    public static void testImage(ImageParam imageParam) throws IOException, InterruptedException, IM4JavaException{
        InputStream inputStream = new FileInputStream(new File("/home/shawncao/Desktop/logos/apple_2.jpg"));
        byte[] out = image(imageParam,inputStream);
        System.out.println("out" + out.length);
        FileOutputStream outputStream = new FileOutputStream(new File("/home/shawncao/temp/small_apple.jpg"));
        outputStream.write(out);
        outputStream.close();
    }

    static class ImageParam{
        int x = 0;
        int y = 0;
        int witdth;
        int height;
        Modify operation;

        public Modify getOperation() {
            return operation;
        }
        public void setOperation(Modify operation) {
            this.operation = operation;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public int getWitdth() {
            return witdth;
        }
        public void setWitdth(int witdth) {
            this.witdth = witdth;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
    }

}

enum Modify {
    cut,
    resize;
}
