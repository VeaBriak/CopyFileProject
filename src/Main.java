import java.io.*;
/*Написать код, который будет копировать указанную папку с файлами с сохранением структуры в другую указанную папку.*/

public class Main
{
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;) {
            try {
                System.out.println("Введите путь к файлу или к папке, которую хотите скопировать:");
                File source = new File(reader.readLine().trim());
                System.out.println("Введите путь и название папки, куда хотите скопирьвать выбронный файл или папку:");
                File result = new File(reader.readLine().trim());
                copy(source, result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void copy(File source, File result) throws IOException {
        if (source.isDirectory()) {
            copyDirectiry(source, result);
        } else {
            copyFile(source, result);
        }
    }

    private static void copyDirectiry(File source, File result) throws IOException {
        if (!result.exists()) {
            result.mkdir();
        }
        for (String s : source.list()) {
            copy(new File(source, s), new File(result, s));
        }
    }

    private static void copyFile(File source, File result) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(result)
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
