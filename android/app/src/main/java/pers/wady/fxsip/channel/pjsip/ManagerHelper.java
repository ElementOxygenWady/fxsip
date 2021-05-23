package pers.wady.fxsip.channel.pjsip;

import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.LogConfig;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.UaConfig;
import org.pjsip.pjsua2.pj_log_decoration;
import org.pjsip.pjsua2.pjsip_transport_type_e;
import org.pjsip.pjsua2.pjsua2;

import pers.wady.fxsip.channel.pjsip.impl.IObserver;
import pers.wady.fxsip.common.Log;

public class ManagerHelper extends pjsua2 {
    private static IObserver sObserver;
    private static final Endpoint sEndpoint = new Endpoint();
    private final EpConfig mEpConfig = new EpConfig();
    private final TransportConfig mTransportConfig = new TransportConfig();
    private boolean mInitialized;

    private ManagerHelper() {
        mInitialized = false;
    }

    private static class Holder {
        private static final ManagerHelper INSTANCE = new ManagerHelper();
    }

    public static ManagerHelper getInstance() {
        return Holder.INSTANCE;
    }

    public IObserver getObserver() {
        return sObserver;
    }

    public void init(IObserver observer) throws Throwable {
        if (mInitialized) {
            Log.w(Constants.LOG_TAG_APP, "The pjsip has been initialized!");
            return;
        }

        mInitialized = true;

        sObserver = observer;

        sEndpoint.libCreate();

        LogConfig mLogConfig = mEpConfig.getLogConfig();
        mLogConfig.setLevel(Constants.LOG_LEVEL);
        mLogConfig.setConsoleLevel(Constants.LOG_LEVEL);

        mLogConfig.setWriter(new CustomLogWriter());
        mLogConfig.setDecor(mLogConfig.getDecor() & (~(pj_log_decoration.PJ_LOG_HAS_CR | pj_log_decoration.PJ_LOG_HAS_NEWLINE)));

        UaConfig uaConfig = mEpConfig.getUaConfig();
        uaConfig.setUserAgent("Fxsip Android " + sEndpoint.libVersion().getFull());

        sEndpoint.libInit(mEpConfig);

        mTransportConfig.setPort(Constants.SIP_PORT);
        sEndpoint.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, mTransportConfig);
        sEndpoint.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TCP, mTransportConfig);

        sEndpoint.libStart();
    }
}
