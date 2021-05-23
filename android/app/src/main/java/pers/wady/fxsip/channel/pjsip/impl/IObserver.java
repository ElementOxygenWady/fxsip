package pers.wady.fxsip.channel.pjsip.impl;

import pers.wady.fxsip.channel.pjsip.ManagerHelper;

public interface IObserver {
    void notifyIncomingCall(ManagerHelper helper);

    void notifyCallState(ManagerHelper helper);

    void notifyCallMediaState(ManagerHelper helper);
}
