package jp.co.formula.app.formulabooklibrary.logger;

import android.util.Log;

/**
 * ログ出力用ユーティリティクラス.
 * Created by @formula on 2016/06/18.
 */
public class FBL_Logger {
    protected static String TAG = "FormulaBookLibrary";
    protected static final boolean DEBUG = true;

    /**
     * ログ出力時のタグ設定.
     * @param aTag タグ.
     */
    public static void setTag(String aTag) {
        TAG = aTag;
    }

    /**
     * メソッド先頭ログ出力.
     * 呼び出し元メタ情報.
     */
    public static void enter() {
        if (DEBUG) {
            log(Log.INFO, "enter()");
        }
    }
    /**
     * メソッド先頭ログ出力.
     * 呼び出し元メタ情報.
     * @param aObj 付加メッセージ引数.
     */
    public static void enter(Object... aObj) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer();
            for (Object o : aObj) {
                sb.append(String.valueOf(o));
                sb.append("\n");
            }
            log(Log.INFO, "enter()\n" + sb.toString());
        }
    }
    /**
     * メソッド後尾ログ出力.
     * 呼び出し元メタ情報.
     */
    public static void leave() {
        if (DEBUG) {
            log(Log.INFO, "leave()");
        }
    }
    /**
     * メソッド後尾ログ出力.
     * 呼び出し元メタ情報.
     * @param aObj 付加メッセージ引数.
     */
    public static void leave(Object... aObj) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer();
            for (Object o : aObj) {
                sb.append(String.valueOf(o));
                sb.append("\n");
            }
            log(Log.INFO, "leave()\n" + sb.toString());
        }
    }

    /**
     * 強調ログ出力.
     * 呼び出し元メタ情報.
     */
    public static void a() {
        if (DEBUG) {
            log(Log.ASSERT);
        }
    }
    /**
     * 強調ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    public static void a(String aMsg) {
        if (DEBUG) {
            log(Log.ASSERT, aMsg);
        }
    }
    /**
     * 強調ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    public static void a(String aFormat, Object... aObj) {
        if (DEBUG) {
            log(Log.ASSERT, aFormat, aObj);
        }
    }

    /**
     * デバッグログ出力.
     * 呼び出し元メタ情報.
     */
    public static void d() {
        if (DEBUG) {
            log(Log.DEBUG);
        }
    }
    /**
     * デバッグログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    public static void d(String aMsg) {
        if (DEBUG) {
            log(Log.DEBUG, aMsg);
        }
    }
    /**
     * デバッグログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    public static void d(String aFormat, Object... aObj) {
        if (DEBUG) {
            log(Log.DEBUG, aFormat, aObj);
        }
    }

    /**
     * エラーログ出力.
     * 呼び出し元メタ情報.
     */
    public static void e() {
        if (DEBUG) {
            log(Log.ERROR);
        }
    }
    /**
     * エラーログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    public static void e(String aMsg) {
        if (DEBUG) {
            log(Log.ERROR, aMsg);
        }
    }
    /**
     * エラーログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    public static void e(String aFormat, Object... aObj) {
        if (DEBUG) {
            log(Log.ERROR, aFormat, aObj);
        }
    }

    /**
     * 情報ログ出力.
     * 呼び出し元メタ情報.
     */
    public static void i() {
        if (DEBUG) {
            log(Log.INFO);
        }
    }
    /**
     * 情報ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    public static void i(String aMsg) {
        if (DEBUG) {
            log(Log.ERROR, aMsg);
        }
    }
    /**
     * 情報ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    public static void i(String aFormat, Object... aObj) {
        if (DEBUG) {
            log(Log.ERROR, aFormat, aObj);
        }
    }

    /**
     * 詳細ログ出力.
     * 呼び出し元メタ情報.
     */
    public static void v() {
        if (DEBUG) {
            log(Log.VERBOSE);
        }
    }
    /**
     * 詳細ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    public static void v(String aMsg) {
        if (DEBUG) {
            log(Log.VERBOSE, aMsg);
        }
    }
    /**
     * 詳細ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    public static void v(String aFormat, Object... aObj) {
        if (DEBUG) {
            log(Log.VERBOSE, aFormat, aObj);
        }
    }

    /**
     * 警告ログ出力.
     * 呼び出し元メタ情報.
     */
    public static void w() {
        if (DEBUG) {
            log(Log.WARN);
        }
    }
    /**
     * 警告ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    public static void w(String aMsg) {
        if (DEBUG) {
            log(Log.WARN, aMsg);
        }
    }
    /**
     * 警告ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    public static void w(String aFormat, Object... aObj) {
        if (DEBUG) {
            log(Log.WARN, aFormat, aObj);
        }
    }

    /**
     * ログ出力.
     * 呼び出し元メタ情報.
     */
    private static void log(int aPriority) {
        if (DEBUG) {
            println(aPriority, getMetaInfoStr());
        }
    }
    /**
     * ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aMsg 付加メッセージ.
     */
    private static void log(int aPriority, String aMsg) {
        if (DEBUG) {
            if (aMsg != null) {
                println(aPriority, getMetaInfoStr() + "\n" + aMsg);
            } else {
                println(aPriority, getMetaInfoStr());
            }
        }
    }
    /**
     * ログ出力.
     * 呼び出し元メタ情報 + 付加メッセージ.
     * @param aFormat 付加メッセージフォーマット.
     * @param aObj 付加メッセージ引数.
     */
    private static void log(int aPriority, String aFormat, Object... aObj) {
        if (DEBUG) {
            try {
                String msg = String.format(aFormat, aObj);
                println(aPriority, getMetaInfoStr() + "\n" + msg);
            } catch (Exception e) {
                // nop.
            }
        }
    }

    /**
     * ログ出力.
     * @param aPriority ログレベル.
     * @param aMsg メッセージ.
     */
    private static void println(int aPriority, String aMsg) {
        if (DEBUG) {
            switch (aPriority) {
                case Log.ASSERT:
                case Log.DEBUG:
                case Log.ERROR:
                case Log.INFO:
                case Log.WARN:
                    Log.println(aPriority, TAG, aMsg);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * ログ呼び出し元のメタ情報を取得する.
     * @return 呼び出し元StackTraceElement.
     */
    private static StackTraceElement getMetaInfo() {
        // スタックトレースから情報を取得
        // 0: VM, 1: Thread, 2: Logger#getMetaInfo, 3: Logger#getMetaInfoStr, 4; Logger#log. 5: Logger#d など, 6: 呼び出し元.
        // Test.
//        for(StackTraceElement elem : Thread.currentThread().getStackTrace()) {
//            Log.println(Log.INFO, TAG, elem.toString());
//        }
        return Thread.currentThread().getStackTrace()[6];
    }

    /**
     * ログ呼び出し元のメタ情報を取得する.
     * @return 呼び出し元メタ情報String [ClassName]#[MethodName]([FileName]:[LineNumber]).
     */
    private static String getMetaInfoStr() {
        StackTraceElement trace = getMetaInfo();
        StringBuffer sb = new StringBuffer();
        int i = trace.getClassName().lastIndexOf(".");
        i = i>0?i+1:0;
        sb.append(trace.getClassName().substring(i));
        sb.append("#");
        sb.append(trace.getMethodName());
        sb.append(" (");
        sb.append(trace.getFileName());
        sb.append(":");
        sb.append(trace.getLineNumber());
        sb.append(")");
        return sb.toString();
    }
}
