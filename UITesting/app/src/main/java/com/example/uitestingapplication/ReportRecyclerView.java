package com.example.uitestingapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.uitestingapplication.R;
import com.example.uitestingapplication.ReportUIActivity;
import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Report;

import java.util.List;

public class ReportRecyclerView extends RecyclerView.Adapter<ReportRecyclerView.ReportViewHolder> {
    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Report report);
    }
    private Report report;
    private List<Report> reportList;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private OnDeleteClickListener onDeleteClickListener;

    public ReportRecyclerView(Context context, OnDeleteClickListener listener){

        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(
                R.layout.report_items_layout,
                parent,
                false
        );
        return new ReportViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        if (reportList != null) {
            Report note = reportList.get(position);
            holder.setData(note, position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.report_filename.setText(R.string.no_report);
        }
    }

    public void deleteReport(Report report)
    {
        reportList.remove(report);
    }
    @Override
    public int getItemCount() {
        if (reportList != null)
            return reportList.size();
        else return 0;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder{
         private TextView report_filename;
            private TextView report_description;
            private ImageView image;
         private int mPosition;
         Button delete;

        public ReportViewHolder(View itemView) {
            super(itemView);
            report_filename = itemView.findViewById(R.id.filename_set);
            report_description = itemView.findViewById(R.id.description_set);
            image = itemView.findViewById(R.id.set_image);
            delete 	 = itemView.findViewById(R.id.btn_delete);
        }

        public void setData(Report report, int position) {
            mPosition = position;
            report_filename.setText(report.getFileName());
            report_description.setText(report.getDescription());
            image.setImageBitmap(ReportUIActivity.convertByteArrayToImage(report.getImage()));
        }

        public void setListeners() {

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(reportList.get(mPosition));
                    }
                }
            });
        }
    }
}
