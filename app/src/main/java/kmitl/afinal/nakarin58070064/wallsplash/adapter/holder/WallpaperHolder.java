package kmitl.afinal.nakarin58070064.wallsplash.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import kmitl.afinal.nakarin58070064.wallsplash.R;

public class WallpaperHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public WallpaperHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
    }
}
