package com.shawn.general;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.Pipe;

public class ImageOperationTest {

    public static void main(String[] args) {
        ImageParam imageParam = new ImageParam();
        imageParam.setHeight(100);
        imageParam.setWitdth(100);
        imageParam.setOperation(Operation.resize);
        boolean flag=processImage("/home/shawncao/Desktop/logos/apple_2.jpg","/home/shawncao/Desktop/apple-small.jpg", imageParam);
        System.out.println("finish"+ flag);
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

    public byte[] image(int height, int width, InputStream Stream) throws IOException, InterruptedException, IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage("");
        op.resize(width, height, ">");
        Pipe pipeIn = new Pipe(Stream, null);
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        Pipe pipeOut = new Pipe(null, fos);

        ConvertCmd convert = new ConvertCmd();
        convert.setInputProvider(pipeIn);
        convert.setOutputConsumer(pipeOut);
        convert.run(op);
        Stream.close();
        return fos.toByteArray();
    }

    static class ImageParam{
        int x = 0;
        int y = 0;
        int witdth;
        int height;
        Operation operation;

        public Operation getOperation() {
            return operation;
        }
        public void setOperation(Operation operation) {
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

enum Operation{
    cut,
    resize;
}
