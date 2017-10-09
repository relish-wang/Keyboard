package wang.relish.keyboard.sample;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

/**
 * @author Relish Wang
 * @since 2017/10/09
 */
public class SimpleIME extends InputMethodService {
    KeyboardView kv;
    Keyboard keyboard;
    boolean caps;


    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(new OnKeyboardActionAdapter() {
            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                InputConnection ic = getCurrentInputConnection();
                playClick(primaryCode);
                switch (primaryCode) {
                    case Keyboard.KEYCODE_DELETE:
                        ic.deleteSurroundingText(1, 0);
                        break;
                    case Keyboard.KEYCODE_SHIFT:
                        caps = !caps;
                        keyboard.setShifted(caps);
                        kv.invalidateAllKeys();
                        break;
                    case Keyboard.KEYCODE_DONE:
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                        kv.closing();
                        break;
                    default:
                        char code = (char) primaryCode;
                        if (Character.isLetter(code) && caps) {
                            code = Character.toUpperCase(code);
                        }
                        ic.commitText(String.valueOf(code), 1);
                }
            }
        });
        return kv;
    }

    private void playClick(int keyCode) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
}
