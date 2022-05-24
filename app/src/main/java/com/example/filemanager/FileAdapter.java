package com.example.filemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileViewHolder>{
    private Context context;
    private List<File> fileList;
    private OnFileSelectedListener listener;

    public FileAdapter(Context context, List<File> fileList, OnFileSelectedListener listener) {
        this.context = context;
        this.fileList = fileList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_fileName.setText(fileList.get(position).getName());
        holder.tv_fileName.setSelected(true);
        int items = 0;
        if(fileList.get(position).isDirectory()) {
            File[] files = fileList.get(position).listFiles();
            for (File singleFile: files) {
                if(!singleFile.isHidden()) {
                    items += 1;
                }
            }
            holder.tv_fileSize.setText(String.valueOf(items) + " Files");
        } else {
            holder.tv_fileSize.setText(Formatter.formatShortFileSize(context, fileList.get(position).length()));
        }

        String lowerFileName = fileList.get(position).getName().toLowerCase();
        if(lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".png")) {
            holder.img_file.setImageResource(R.drawable.ic_image);
        }
        else if( lowerFileName.endsWith(".pdf")) {
            holder.img_file.setImageResource(R.drawable.ic_pdf);
        }
        else if(lowerFileName.endsWith(".mp3") || lowerFileName.endsWith(".wav")) {
            holder.img_file.setImageResource(R.drawable.ic_music);
        }
        else if(lowerFileName.endsWith(".mp4")) {
            holder.img_file.setImageResource(R.drawable.ic_video);
        }
        else if(lowerFileName.endsWith(".apk")) {
            holder.img_file.setImageResource(R.drawable.ic_android);
        } else {
            holder.img_file.setImageResource(R.drawable.folder);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFileClicked(fileList.get(position));
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onFileLongClicked(fileList.get(position));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }
}
