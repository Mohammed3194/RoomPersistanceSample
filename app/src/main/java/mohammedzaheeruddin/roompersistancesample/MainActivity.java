package mohammedzaheeruddin.roompersistancesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mohammedzaheeruddin.roompersistancesample.database.entity.Product;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // run the sentence in a new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Product> products = App.get().getDB().productDao().getAll();
                boolean force = App.get().isForceUpdate();
                if (force || products.isEmpty()) {
                    retrieveProducts();
                } else {
                    //update();
                    populateProducts(products);
                }
            }
        }).start();
    }

    private void update(){
        Log.d("debug"," Inside update method ====");
        Product product = new Product();
        product.setUid(0);
        product.setName("Zaheer");
        product.setImageUrl("https://picsum.photos/500/500?image=" + 1);
        product.setPrice(10000);

        App.get().getDB().productDao().update(product);
    }

    private void retrieveProducts() {
        List<Product> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setUid(i);
            product.setName(getString(R.string.name_format, String.valueOf(i)));
            product.setImageUrl("https://picsum.photos/500/500?image=" + i);
            product.setPrice(i == 0 ? 50 : i * 100);
            list.add(product);
        }

        // insert product list into database
        App.get().getDB().productDao().insertAll(list);

        // disable flag for force update
        App.get().setForceUpdate(false);

        populateProducts(list);
    }

    private void populateProducts(final List<Product> products) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new ProductAdapter(products));
            }
        });
    }

}
