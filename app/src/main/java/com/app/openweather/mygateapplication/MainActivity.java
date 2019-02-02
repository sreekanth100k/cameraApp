package com.app.openweather.mygateapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mAddFabBtn;
    RecyclerView mDetailsListRv;
    DetailsListAdapter mDetailslistAdapterObj;
    int CAMERA_IMAGE_REQUEST    =   900;
    String imageName;

    ArrayList<String> detailsListItemArrayListObj     =   new ArrayList<>();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_IMAGE_REQUEST) {


            detailsListItemArrayListObj.add("item 1");
            detailsListItemArrayListObj.add("item 2");

            mDetailslistAdapterObj  =   new DetailsListAdapter(detailsListItemArrayListObj,getApplicationContext());

            mDetailsListRv.setAdapter(mDetailslistAdapterObj);

            mDetailsListRv.invalidate();




        } else {
            Toast.makeText(this, "Take Picture Failed or canceled",
                    Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mAddFabBtn      =   (FloatingActionButton) findViewById(R.id.fab);

        mDetailsListRv  =   (RecyclerView)findViewById(R.id.id_details_list_rv);



        mDetailsListRv.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetailsListRv.setLayoutManager(linearLayoutManager);


        mDetailslistAdapterObj  =   new DetailsListAdapter(detailsListItemArrayListObj,getApplicationContext());


        mAddFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                captureImage();

            }
        });

        mDetailsListRv.setAdapter(mDetailslistAdapterObj);

        mDetailsListRv.invalidate();


    }

    public void addItemToDb(){

    }


    public void addItemToList(){

    }

    public void captureImage() {


        // Creating folders for Image
        String imageFolderPath = Environment.getExternalStorageDirectory().toString()
                + "/myGate";
        File imagesFolder = new File(imageFolderPath);
        imagesFolder.mkdirs();

        // Generating file name
        imageName = new Date().toString() + ".png";



        // Creating image here
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
/*
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageFolderPath, imageName)));
*/

        Uri outputUri= Uri.fromFile(new File(imageFolderPath));


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, outputUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        }


        startActivityForResult(takePictureIntent,
                CAMERA_IMAGE_REQUEST);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
