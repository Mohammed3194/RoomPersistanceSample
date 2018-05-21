package mohammedzaheeruddin.roompersistancesample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;

import mohammedzaheeruddin.roompersistancesample.App;
import mohammedzaheeruddin.roompersistancesample.database.entity.Product;

/**
 * Created by mohammedzaheeruddin on 21/05/18
 */

@Database(entities = {Product.class}, version = 2)
@TypeConverters({DateTypeConverter.class})
public abstract class MyDatabase extends RoomDatabase {
    public abstract ProductDao productDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            //database.execSQL("ALTER TABLE product ADD COLUMN uid INTEGER");
            database.execSQL("ALTER TABLE product ADD COLUMN price INTEGER");

            // enable flag to force update products
            App.get().setForceUpdate(true);
        }
    };
}
