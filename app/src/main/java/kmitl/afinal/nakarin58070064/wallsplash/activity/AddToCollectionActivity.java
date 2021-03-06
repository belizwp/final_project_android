package kmitl.afinal.nakarin58070064.wallsplash.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Date;

import kmitl.afinal.nakarin58070064.wallsplash.R;
import kmitl.afinal.nakarin58070064.wallsplash.database.DatabaseManager;
import kmitl.afinal.nakarin58070064.wallsplash.database.WallSplashDatabase;
import kmitl.afinal.nakarin58070064.wallsplash.fragment.MyCollectionFragment;
import kmitl.afinal.nakarin58070064.wallsplash.model.MyCollection;
import kmitl.afinal.nakarin58070064.wallsplash.model.MyPhoto;
import kmitl.afinal.nakarin58070064.wallsplash.model.Photo;
import kmitl.afinal.nakarin58070064.wallsplash.task.AddToCollectionTask;

public class AddToCollectionActivity extends AppCompatActivity implements
        MyCollectionFragment.MyCollectionFragmentListener {

    private WallSplashDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_collection);

        initInstances(savedInstanceState);
    }

    private void initInstances(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.add_to_collection));

        initDB();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_container, new MyCollectionFragment())
                    .commit();
        }
    }

    private void initDB() {
        database = DatabaseManager.getInstance().getDatabase();
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
    public void onCollectionClick(final MyCollection collection) {
        Photo photo = getIntent().getParcelableExtra(Photo.class.getSimpleName());

        if (photo != null) {
            MyPhoto myPhoto = new MyPhoto(photo);
            myPhoto.setCurrentCollection(collection.getId());
            myPhoto.setTimeCreate(new Date());

            new AddToCollectionTask(database, new AddToCollectionTask.OnPostAddListener() {
                @Override
                public void onPostAdd() {
                    showToast(getString(R.string.photo_added_to, collection.getTitle()));
                    finish();
                }
            }).execute(myPhoto);
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
