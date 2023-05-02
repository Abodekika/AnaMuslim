package com.example.anamuslim.utils;

import android.os.Environment;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class Constants {
    public static final String ACTION_APPLICATION_STARTED = "com.nanosoft.holy.quran.ACTION_APPLICATION_STARTED";
    public static final String ACTION_DAILY_READING_REMINDER = "com.nanosoft.holy.quran.ACTION_DAILY_READING_REMINDER";
    public static final String ACTION_KAHF_REMINDER = "com.nanosoft.holy.quran.ACTION_KAHF_REMINDER";
    public static final String ARABIC_FONT_FILE_NAME = "fonts/arabic.ttf";
    public static final String AUDIO_DOWNLOAD_EXTENSION = ".mp3";
    public static final String AUDIO_EXTENSION = ".audio";
    public static final String AUDIO_MANAGER_PAGE_EN_FORMATER = "page %d";
    public static final String AUDIO_MANAGER_PAGE_FORMATER = "صفحة %d";
    public static final String AUDIO_PAGE_FILE_FORMAT = "Page%03d.mp3";
    public static final String AUTO_BOOKMARK_EXTENSION = ".auto_bookmark";
    public static final int AVERAGE_AYAH_SIZE = 1;
    public static final int AVERAGE_FILE_SIZE = 10;
    public static final int AVERAGE_MB_SIZE = 25;
    public static final int AVERAGE_PAGES_SIZE = 80;
    public static final int AVERAGE_PAGE_SIZE = 2;
    public static final int AVERAGE_SURAH_SIZE = 30;
    public static final int AYAH = 0;
    public static final int AYAH_BOOKMARK_COLOR = 872085604;
    public static final int AYAH_BOOKMARK_COLOR_NIGHT_MODE = 855967643;
    public static final int AYAH_COLOR = -1728053248;
    public static final String AYAH_FORMATER = "'%s (%d)' [سورة %s]";
    public static final int AYAH_REPEAT_INFINITY = -1;
    public static final int AYAH_REPEAT_MAX = 5;
    public static final int AYAH_REPEAT_MIN = 1;
    public static final int AYAH_SELECTION_COLOR = 864533225;
    public static final int AYAH_SELECTION_COLOR_NIGHT_MODE = 871263487;
    public static final String BASMALA_FILE_NAME = "/001001.audio";
    public static final String BOOKMARKS_SEPARATOR = "-";
    public static final String BOOKMARK_EXTENSION = ".bookmark";
    public static final int BORDER_WIDTH = 50;
    public static final int BORDER_WIDTH_HD = 50;
    public static final int CONNECTION_TIMEOUT = 1000;
    public static final String DEFAULT_LEFT_QURAN_PAGE = "default_left_page.png";
    public static final String DEFAULT_RIGHT_QURAN_PAGE = "default_right_page.png";
    public static final boolean DEVELOPER_MODE = false;
    public static final String DEV_AUTO_BOOKMARK_EXTENSION = ".dev_auto_bookmark";
    public static final String EXTRA_AYAH = "com.nanosoft.holy.quran.EXTRA_AYAH";
    public static final String EXTRA_PAGE = "com.nanosoft.holy.quran.EXTRA_PAGE";
    public static final String EXTRA_SURAH = "com.nanosoft.holy.quran.EXTRA_SURAH";
    public static final int FONT_INC_IN_SP = 2;
    public static final int HALF_HEIGHT = 15;
    public static final int HALF_HEIGHT_HD = 20;
    public static final int HALF_WIDTH = 15;
    public static final int HALF_WIDTH_HD = 20;
    public static final int HD_PAGES_INDEX = 1;
    public static final String INFINITY_SYMBOL = "∞";
    public static final int KAHF_SURAH_NUMBER = 17;
    public static final int KAHF_SURAH_PAGE_NUMBER = 292;
    public static final int KHATM_PAGES_NUMBER = 2;
    public static final String KHATM_QURAN_PAGE_ONE = "khatm_1.png";
    public static final String KHATM_QURAN_PAGE_TWO = "khatm_2.png";
    public static final String LANGUAGE_AR = "ar";
    public static final String LANGUAGE_EN = "en";
    public static final String MARKER_TYPE_JSON_TAG = "marker_type";
    public static final int MAX_PAGE = 603;
    public static final int MAX_ZOOM_LEVEL = 3;
    public static final int MIN_PAGE = 0;
    public static final int MIN_ZOOM_LEVEL = -3;
    public static final int MOVING_AYAH_COLOR = -1728031488;
    public static final String NEED_FOREGROUND_KEY = "com.nanosoft.holy.quran.NEED_FOREGROUND_KEY";
    public static final int NORMAL_PAGES_INDEX = 0;
    public static final int NOTIFICATION_ID_DAILY_READ_REMINDER = 11;
    public static final int NOTIFICATION_ID_KAHF_REMINDER = 10;
    public static final String NUMBER_AYAHS_IN_SURAH_JSON_TAG = "number_ayahs_in_surah";
    public static final String NUMBER_IN_SURAH_JSON_TAG = "number_in_surah";
    public static final String PAGES_EXTENSION = ".img";
    public static final int PAGES_NUMBER = 604;
    public static final String PAGE_NUMBER_JSON_TAG = "page_number";
    public static final int PAGE_START = 2;
    public static final int PAGE_START_COLOR = -1725875770;
    public static final char PARTS_WORD_SYMBOL = 64568;
    public static final String PART_CONFIG_FILE = "quran_part.json";
    public static final String PART_NUMBER_JSON_TAG = "part_number";
    public static final String PLACE_DECENT_JSON_TAG = "place_decent";
    public static final String QURAN_TAFSIR_URL = "/tafsir/";
    public static final String READERS_CONFIG_FILE = "readers.json";
    public static final String READER_FOLDER_NAME_JSON_TAG = "folder_name";
    public static final String READER_IS_AVAIABLE_JSON_TAG = "available";
    public static final String READER_IS_BASMALA_ENABLED_JSON_TAG = "enable_basmala";
    public static final String READER_IS_CONTINUOUS_JSON_TAG = "continuous";
    public static final String READER_NAME_EN_JSON_TAG = "reader_en";
    public static final String READER_NAME_JSON_TAG = "reader";
    public static final String READER_URL_JSON_TAG = "link";
    public static final int READ_TIMEOUT = 60000;
    public static final String SURAH_CONFIG_FILE = "quran_surah.json";
    public static final String SURAH_DISPLAY_NAME_JSON_TAG = "display_name";
    public static final String SURAH_END_PAGE_JSON_TAG = "surah_end_page";
    public static final String SURAH_FONT_FILE_NAME = "fonts/surah_font.ttf";
    public static final String SURAH_NAME_EN_JSON_TAG = "surah_name_en";
    public static final String SURAH_NAME_JSON_TAG = "surah_name";
    public static final String SURAH_NUMBER_IN_PAGE_JSON_TAG = "surah_number_in_page";
    public static final String SURAH_NUMBER_JSON_TAG = "surah_number";
    public static final int SURAH_START = 1;
    public static final int SURAH_START_COLOR = -1718026240;
    public static final String SURAH_START_PAGE_JSON_TAG = "surah_start_page";
    public static final String SURAH_WORD_SYMBOL = "005c";
    public static final String TAFSIR_LEFT_QURAN_PAGE = "tafsir_left_page.png";
    public static final String TAFSIR_MUYASSAR_DATABASE_NAME = "quran.muyassar.db";
    public static final String TAFSIR_PAGE_TITLE_EN_FORMATER = "\t\t(page %s)";
    public static final String TAFSIR_PAGE_TITLE_FORMATER = "\t\t(صفحة %s)";
    public static final String TAFSIR_RIGHT_QURAN_PAGE = "tafsir_right_page.png";
    public static final String TAG = "holy_quran";
    public static final String X_JSON_TAG = "x";
    public static final String Y_JSON_TAG = "y";
    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().toString();
    public static final String APP_DIR = SDCARD_DIR + "/holyquran/";
    public static final String AUDIO_DIR = APP_DIR + "audio/";
    public static final String BOOKMARK_DIR = APP_DIR + "bookmarks/";
    public static final String FILES_DIR = APP_DIR + "files/";
    public static final String PAGES_DIR = APP_DIR + "pages/";
    public static final String PAGES_HD_DIR = APP_DIR + "pages_hd/";
    public static final String[] PAGES_TYPE_DIR = {PAGES_DIR, PAGES_HD_DIR};
    public static final String UNHANDLED_EXCEPTION_FILE = APP_DIR + "exception";
    public static final String IMAGE_TMP_LIST_FILE = FILES_DIR + "image_list.tmp";
    public static final String IMAGE_LIST_FILE = FILES_DIR + "image_list.obj";
    public static final String AYAH_LOC_IN_PAGES_CONFIG_FILE_NAME = "ayah_loc_in_pages.json";
    public static final String AYAH_LOC_IN_PAGES_CONFIG_FILE = FILES_DIR + AYAH_LOC_IN_PAGES_CONFIG_FILE_NAME;
    public static final String QURAN_PAGES_URL = "/pages/";
    public static final String QURAN_PAGES_HD_URL = "/pages_hd/";
    public static final String[] QURAN_PAGES_TYPE_URL = {QURAN_PAGES_URL, QURAN_PAGES_HD_URL};
    public static final String[] QURAN_DOMAIN_URLS = {"http://nanosoft-group.com/holyquran", "http://nanosoft-group.com/holyquran"};
    public static final int[] HALF_WIDTH_TYPE = {15, 20};
    public static final int[] HALF_HEIGHT_TYPE = {15, 20};
    public static final int[] BORDER_WIDTH_TYPE = {50, 50};
    public static final int QURAN_PAGE_WIDTH = 466;
    public static final int QURAN_PAGE_HD_WIDTH = 642;
    public static final int[] QURAN_PAGE_WIDTH_TYPE = {QURAN_PAGE_WIDTH, QURAN_PAGE_HD_WIDTH};
    public static final int QURAN_PAGE_HEIGHT = 672;
    public static final int QURAN_PAGE_HD_HEIGHT = 917;
    public static final int[] QURAN_PAGE_HEIGHT_TYPE = {QURAN_PAGE_HEIGHT, QURAN_PAGE_HD_HEIGHT};
    public static final ArrayList<Character> PARTS_LIST_SYMBOL = new ArrayList<>();

    static {
        PARTS_LIST_SYMBOL.add((char) 64569);
        PARTS_LIST_SYMBOL.add((char) 64570);
        PARTS_LIST_SYMBOL.add((char) 64571);
        PARTS_LIST_SYMBOL.add((char) 64572);
        PARTS_LIST_SYMBOL.add((char) 64573);
        PARTS_LIST_SYMBOL.add((char) 64574);
        PARTS_LIST_SYMBOL.add((char) 64575);
        PARTS_LIST_SYMBOL.add((char) 64576);
        PARTS_LIST_SYMBOL.add((char) 64577);
        PARTS_LIST_SYMBOL.add((char) 64578);
        PARTS_LIST_SYMBOL.add((char) 64579);
        PARTS_LIST_SYMBOL.add((char) 64580);
        PARTS_LIST_SYMBOL.add((char) 64581);
        PARTS_LIST_SYMBOL.add((char) 64582);
        PARTS_LIST_SYMBOL.add((char) 64583);
        PARTS_LIST_SYMBOL.add((char) 64584);
        PARTS_LIST_SYMBOL.add((char) 64585);
        PARTS_LIST_SYMBOL.add((char) 64586);
        PARTS_LIST_SYMBOL.add((char) 64587);
        PARTS_LIST_SYMBOL.add((char) 64588);
        PARTS_LIST_SYMBOL.add((char) 64589);
        PARTS_LIST_SYMBOL.add((char) 64590);
        PARTS_LIST_SYMBOL.add((char) 64591);
        PARTS_LIST_SYMBOL.add((char) 64592);
        PARTS_LIST_SYMBOL.add((char) 64593);
        PARTS_LIST_SYMBOL.add((char) 64594);
        PARTS_LIST_SYMBOL.add((char) 64595);
        PARTS_LIST_SYMBOL.add((char) 64596);
        PARTS_LIST_SYMBOL.add((char) 64597);
        PARTS_LIST_SYMBOL.add((char) 64598);
    }
}