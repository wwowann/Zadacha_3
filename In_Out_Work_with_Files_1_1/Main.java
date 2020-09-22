package In_Out_Work_with_Files_1_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        File dirGames = new File("Games");
        if (dirGames.mkdir()) stringBuilder.append("Директория " + dirGames + " создана\n");
        File dirGamesTemp = new File("Games\\temp");
        if (dirGamesTemp.mkdir()) stringBuilder.append("Директория " + dirGamesTemp + " создана\n");
        File temp = new File("Games\\temp\\temp.txt");
        try {
            if (temp.createNewFile()) stringBuilder.append("Файл " + temp + " создан\n");
        } catch (IOException ex) {
            stringBuilder.append("Файл " + temp + " уже создан\n");
        }
        File dirSaveGames = new File("Games\\savegames");
        if (dirSaveGames.mkdir()) stringBuilder.append("Директория " + dirSaveGames + " создана\n");

        File dirScr = new File("Games\\scr");
        if (dirScr.mkdir()) stringBuilder.append("Директория " + dirScr + " создана\n");
        File dirSrcMain = new File("Games\\scr\\main");
        if (dirSrcMain.mkdir()) stringBuilder.append("Директория " + dirSrcMain + " создана\n");

        File main = new File("Games\\scr\\main\\Main.java");
        try {
            if (main.createNewFile()) stringBuilder.append("Файл " + main + " создан\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        File utils = new File("Games\\scr\\main\\Utils.java");
        try {
            if (utils.createNewFile()) stringBuilder.append("Файл " + utils + " создан\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File dirSrcTest = new File("Games\\scr\\test");
        if (dirSrcTest.mkdir()) stringBuilder.append("Директория " + dirSrcTest + " создана\n");

        File dirRes = new File("Games\\res");
        if (dirRes.mkdir()) stringBuilder.append("Директория " + dirRes + " создана\n");
        File dirResDrawables = new File("Games\\res\\drawables");
        if (dirResDrawables.mkdir()) stringBuilder.append("Директория " + dirResDrawables + " создана\n");
        File dirResVectors = new File("Games\\res\\vectors");
        if (dirResVectors.mkdir()) stringBuilder.append("Директория " + dirResVectors + " создана\n");
        File dirResIcons = new File("Games\\res\\icons");
        if (dirResIcons.mkdir()) stringBuilder.append("Директория " + dirResIcons + " создана\n");

        try (FileWriter writer = new FileWriter(temp, false)) {
            // запись всего лога
            writer.write(String.valueOf(stringBuilder));
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
