package pers.wady.fxsip.channel.pjsip;

import org.pjsip.pjsua2.LogEntry;
import org.pjsip.pjsua2.LogWriter;

import pers.wady.fxsip.common.Log;

public class CustomLogWriter extends LogWriter {
    private int convertLevel(LogEntry entry) {
        switch (entry.getLevel()) {
            case 0: //Level 0 Display fatal error only.
            case 1: //Level 1 Display error messages and more severe verbosity level only.
                return Log.ERROR;
            case 2: //Level 2 Display Warning messages and more severe verbosity level only.
                return Log.WARN;
            case 3: //Level 3 Info verbosity (normally used by applications).
            case 4: //Level 4 Important PJSIP events.
                return Log.INFO;
            case 5: //Level 5 Detailed PJSIP events.
            case 6: //Level 6 Very detailed PJLIB events.
            default:
                return Log.DEBUG;
        }
    }

    @Override
    public void write(LogEntry entry) {
        Log.println(convertLevel(entry), Constants.LOG_TAG_SDK, entry.getMsg());
    }
}
