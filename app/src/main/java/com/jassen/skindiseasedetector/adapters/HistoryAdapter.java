package com.jassen.skindiseasedetector.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jassen.skindiseasedetector.R;
import com.jassen.skindiseasedetector.models.History;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<History> {

    private int resourceLayout;
    private Context mContext;

    public HistoryAdapter(@NonNull Context context, int resource, List<History> listItem) {
        super(context, resource, listItem);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        final History item = getItem(position);

        assert item != null;
        byte[] decodedString = Base64.decode(item.getImageBase64(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        ImageView imageView = v.findViewById(R.id.history_item_imageView);
        imageView.setImageBitmap(decodedByte);
        TextView tv = v.findViewById(R.id.history_item_label_listView);
        tv.setText(item.getResult());
        return v;
    }
}
