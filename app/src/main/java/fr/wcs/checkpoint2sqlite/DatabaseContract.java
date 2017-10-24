package fr.wcs.checkpoint2sqlite;

import android.provider.BaseColumns;

/**
 * Created by bastienwcs on 24/10/17.
 */

public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {}

    /* Inner class that defines the table contents */
    public static class CarEntry implements BaseColumns {
        public static final String TABLE_NAME = "Car";
        public static final String COLUMN_NAME_ID = "car_id";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PLATE = "plate";
        public static final String COLUMN_NAME_KM = "km";
    }
    public static class RoadEntry implements BaseColumns {
        public static final String TABLE_NAME = "Road";
        public static final String COLUMN_NAME_ID = "road_id";
        public static final String COLUMN_NAME_NAME = "nom";
    }
}

