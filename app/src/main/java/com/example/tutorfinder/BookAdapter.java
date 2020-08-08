package com.example.tutorfinder;

import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class BookAdapter extends FirestoreRecyclerAdapter<Book, BookAdapter.BookHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookAdapter(@NonNull FirestoreRecyclerOptions<Book> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final BookHolder holder, int position, @NonNull Book model) {
      holder.title.setText(model.getBook_title());
      holder.book_url.setText(model.getBook_url());
      final String url = model.getBook_url();
      final String title = model.getBook_title();

      holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download_file(holder.title.getContext(), title, ".pdf", DIRECTORY_DOWNLOADS, url);
            }
        });
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);
        return new BookHolder(view);
    }

    class BookHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView  imageView;
        TextView book_url;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            book_url = itemView.findViewById(R.id.book_url);
        }
    }

    public void Download_file(Context context, String filename,String fileextention, String destination , String url){
        DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Log.d("Download", " Downloading");
        request.setDestinationInExternalPublicDir(DIRECTORY_DOWNLOADS, filename+fileextention);
        request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
    }
}
