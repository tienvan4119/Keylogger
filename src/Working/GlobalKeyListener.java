package Working;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GlobalKeyListener implements NativeKeyListener {
    public static String data = "";
    public static void main(String[] args) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        System.out.println("Press : " +NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
        String charac = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        if(charac.length() > 1)
        {
            if(charac.equals("Backspace")){
                data = (data.length() != 0) ? data.substring(0, data.length()-1) : "";
            }
            else if(charac.equals("Enter")){
                data += "\n";
            }else if(charac.equals("Tab")){
                data += "\t";
            }else if(charac.equals("Space")){
                data += " ";
            }
        }else
            data += NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        System.out.println(data);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
