package kmitl.afinal.nakarin58070064.wallsplash.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import kmitl.afinal.nakarin58070064.wallsplash.R;
import kmitl.afinal.nakarin58070064.wallsplash.fragment.MyCollectionFragment;
import kmitl.afinal.nakarin58070064.wallsplash.model.MyCollection;

public class MyWallActivity extends AppCompatActivity implements MyCollectionFragment.MyCollectionFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wall);

        initInstances(savedInstanceState);
    }

    private void initInstances(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.my_wallpapers));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, new MyCollectionFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCollectionClick(MyCollection collection) {
        Toast.makeText(this, collection.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
