package wang.relish.keyboard.sample;

import android.inputmethodservice.KeyboardView;

/**
 * @author Relish Wang
 * @since 2017/09/18
 */
public abstract class OnKeyboardActionAdapter implements KeyboardView.OnKeyboardActionListener {

    /**
     * （用于拓展）处理特殊的键盘按键响应
     *
     * @param primaryCode 主键值
     * @param keyCodes    所有键值
     */
    public void onKeyEvent(int primaryCode, int[] keyCodes) {
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
