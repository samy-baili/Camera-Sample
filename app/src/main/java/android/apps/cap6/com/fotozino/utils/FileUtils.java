package android.apps.cap6.com.fotozino.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static void byteArrayToFile(Context context, String path, byte[] array) throws IOException {
        File file = new File(context.getFilesDir(), path);

//        if (!file.exists())
//            file.createNewFile();

        FileOutputStream stream = new FileOutputStream(file);
        stream.write(array);
        stream.close();
    }
}
