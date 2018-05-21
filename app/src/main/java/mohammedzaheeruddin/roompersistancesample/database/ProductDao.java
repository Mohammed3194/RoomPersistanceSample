package mohammedzaheeruddin.roompersistancesample.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mohammedzaheeruddin.roompersistancesample.database.entity.Product;

/**
 * Created by mohammedzaheeruddin on 21/05/18
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    Product findByName(String name);

    @Insert
    void insertAll(List<Product> products);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);



    // My reference quries . . .

    /*@Query("select * from user where id = :id")
    User loadUserById(int id);

    @Query("select * from user where name = :firstName and lastName = :lastName")
    List<User> findUserByNameAndLastName(String firstName, String lastName);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("delete from user where name like :badName OR lastName like :badName")
    int deleteUsersByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceUsers(User... users);

    @Delete
    void deleteUsers(User user1, User user2);

    @Query("SELECT * FROM User WHERE :age == :age") // TODO: Fix this!
    List<User> findUsersYoungerThan(int age);

    @Query("SELECT * FROM User WHERE age < :age")
    List<User> findUsersYoungerThanSolution(int age);

    @Query("DELETE FROM User")
    void deleteAll();*/
}
