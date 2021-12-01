package com.app.cjportfoliomanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity4 extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ListView listView = (ListView) findViewById(R.id.pdf_list);
        ArrayList<String> myList = new ArrayList<>();
        File directory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(String.valueOf(directory));
        File list[] = file.listFiles();

        for( int i=0; i< list.length; i++)
        {
            myList.add( list[i].getName() );
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, myList);
        listView.setAdapter(adapter); //Set all the file in the list.

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //list is my listView
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                AlertDialog diaBox = AskOption(pos);
                diaBox.show();
                return true;
            }

            private AlertDialog AskOption(int pos) {
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(MainActivity4.this)
                        // set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
                        .setIcon(R.drawable.pdfsmallicon)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //your deleting code
                                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), myList.get(pos));
                                boolean deleted = file.delete();
                                dialog.dismiss();
                                finish();
                                startActivity(getIntent());
                            }

                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })
                        .create();

                return myQuittingDialogBox;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewPdf(myList.get(i));
            }

            private void viewPdf(String file) {
                File  pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), file);
                Uri path = FileProvider.getUriForFile(MainActivity4.this, BuildConfig.APPLICATION_ID + ".provider", pdfFile);
                Log.e("create pdf uri path==>", "" + path);

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "There is no any PDF Viewer",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

}