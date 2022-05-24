package com.example.filemanager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FileViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_fileName, tv_fileSize;
    public CardView container;
    public ImageView img_file;

    public FileViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_fileName = itemView.findViewById(R.id.tv_fileName);
        tv_fileSize  = itemView.findViewById(R.id.tv_fileSize);
        container = itemView.findViewById(R.id.container);
        img_file = itemView.findViewById(R.id.img_fileType);
    }
}
