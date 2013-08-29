package com.pythonbyte.BorderWaitWatcher.helpers;

import android.util.Log;
import com.pythonbyte.BorderWaitWatcher.BorderWaitWatcherConfiguration;

public class ExceptionHelpers {
    public static void printStackTrace( Exception e ) {
        if ( BorderWaitWatcherConfiguration.PRINT_STACKTRACES ) {
            e.printStackTrace();
        }
    }

    public static void logError( String className, String message ) {
        Log.e(className, message);
    }

    public static void logError( String className, Exception e ) {
        Log.e( className, Log.getStackTraceString( e ) );
    }
}
