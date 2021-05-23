package pers.wady.fxsip.channel.pjsip;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import pers.wady.fxsip.channel.pjsip.impl.IObserver;
import pers.wady.fxsip.common.Log;

public class PjsipManager implements MethodChannel.MethodCallHandler {
    private final ManagerHelper mManagerHelper = ManagerHelper.getInstance();
    private MethodChannel mMethodChannel;

    private PjsipManager() {
    }

    private static class Holder {
        private static final PjsipManager INSTANCE = new PjsipManager();
    }

    public static PjsipManager getInstance() {
        return PjsipManager.Holder.INSTANCE;
    }

    private final IObserver mObserver = new IObserver() {
        @Override
        public void notifyIncomingCall(ManagerHelper helper) {

        }

        @Override
        public void notifyCallState(ManagerHelper helper) {

        }

        @Override
        public void notifyCallMediaState(ManagerHelper helper) {

        }
    };

    private void handleMethodCall(String method) throws Throwable {
        switch (method) {
            case Constants.METHOD_INIT:
                mManagerHelper.init(mObserver);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        try {
            handleMethodCall(call.method);
        } catch (Throwable throwable) {
            Log.e(Constants.LOG_TAG_APP, "Error in the MethodCall:", throwable);
        }
    }

    public void createChannel(BinaryMessenger binaryMessenger) {
        mMethodChannel = new MethodChannel(binaryMessenger, Constants.CHANNEL);
        mMethodChannel.setMethodCallHandler(this);
    }
}
