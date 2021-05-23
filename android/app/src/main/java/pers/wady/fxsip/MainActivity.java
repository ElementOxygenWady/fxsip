package pers.wady.fxsip;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import pers.wady.fxsip.channel.pjsip.PjsipManager;

public class MainActivity extends FlutterActivity {
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        PjsipManager.getInstance().createChannel(flutterEngine.getDartExecutor().getBinaryMessenger());
    }
}
