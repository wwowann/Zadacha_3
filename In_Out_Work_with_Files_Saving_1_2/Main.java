package In_Out_Work_with_Files_Saving_1_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> path = new ArrayList<>();
        GameProgress game1 = new GameProgress(98, 37, 15, 125.32);
        String gameAddress = "Games\\savegames\\save1.dat";
        path.add(savegame(gameAddress, game1));
        GameProgress game2 = new GameProgress(54, 23, 21, 103.29);
        gameAddress = "Games\\savegames\\save2.dat";
        path.add(savegame(gameAddress, game2));
        GameProgress game3 = new GameProgress(23, 15, 45, 438.18);
        gameAddress = "Games\\savegames\\save3.dat";
        path.add(savegame(gameAddress, game3));
        String zipPath = "Games\\savegames\\save.zip";
        zipFiles(zipPath, path);

        File dir = new File("Games\\savegames");
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (!file.getName().equals("save.zip"))
                    file.delete();
            }
        }
    }


    public static String savegame(String strPath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(strPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println((ex.getMessage()));
        }
        return strPath;
    }

    public static void zipFiles(String zipPath, List<String> path) {
        try (ZipOutputStream zout = new ZipOutputStream(//создаем архив
                new FileOutputStream(zipPath))) {  // и указываем в какой файл будет производиться запись
            for (String s : path) {
                int i = s.lastIndexOf("save");
                String nameFile = s.substring(i);
                try (FileInputStream fis = new FileInputStream(s)) {//считываем файл, который хотим поместить в архив
                    ZipEntry entry = new ZipEntry(nameFile);//создаем объект, в котором будет храниться архивируемый файл
                    zout.putNextEntry((entry));//помещаем этот объект в архив zout
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);//считываем содержимое файла s
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
