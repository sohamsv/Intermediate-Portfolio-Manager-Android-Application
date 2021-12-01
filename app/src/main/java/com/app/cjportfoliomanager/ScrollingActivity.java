package com.app.cjportfoliomanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import android.os.Environment;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ScrollingActivity extends AppCompatActivity {
    String[] Months = {"Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    EditText PersonName, EmailAddress, Phone, Address, AadharNumber, PanNo, Bname, Bbranch, AccHoldr, AccNo, upi,brokerage, periodofagr, AmountRecieved, Amountrecinwords, accopeningchar, lockingperiod, clientage,  daterec, startingdate, endingdate, Payback, IFSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        PersonName = findViewById(R.id.PersonName);
        EmailAddress = findViewById(R.id.EmailAddress);
        Phone = findViewById(R.id.Phone);
        Address = findViewById(R.id.Address);
        AadharNumber = findViewById(R.id.AadharNumber);
        PanNo = findViewById(R.id.PanNo);
        Bname = findViewById(R.id.Bname);
        Bbranch = findViewById(R.id.Bbranch);
        AccHoldr = findViewById(R.id.AccHoldr);
        AccNo = findViewById(R.id.AccNo);
        upi = findViewById(R.id.upi);
        brokerage = findViewById(R.id.brokerage);
        periodofagr = findViewById(R.id.periodofagr);
        AmountRecieved = findViewById(R.id.AmountRecieved);
        Amountrecinwords = findViewById(R.id.Amountrecinwords);
        accopeningchar = findViewById(R.id.accopeningchar);
        lockingperiod = findViewById(R.id.lockingperiod);
        clientage = findViewById(R.id.clientage);
        daterec = findViewById(R.id.daterec);
        startingdate = findViewById(R.id.startingdate);
        endingdate = findViewById(R.id.endingdate);
        Payback = findViewById(R.id.Payback);
        IFSC = findViewById(R.id.IFSC);



        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if(PersonName.getText().toString().length()==0 ||
                        EmailAddress.getText().toString().length()==0 ||
                        Phone.getText().toString().length()==0 ||
                        Address.getText().toString().length()==0 ||
                        AadharNumber.getText().toString().length()==0 ||
                        PanNo.getText().toString().length()==0 ||
                        Bname.getText().toString().length()==0 ||
                        Bbranch.getText().toString().length()==0 ||
                        AccHoldr.getText().toString().length()==0 ||
                        AccNo.getText().toString().length()==0 ||
                        upi.getText().toString().length()==0 ||
                        brokerage.getText().toString().length()==0 ||
                        periodofagr.getText().toString().length()==0 ||
                        AmountRecieved.getText().toString().length()==0 ||
                        Amountrecinwords.getText().toString().length()==0 ||
                        accopeningchar.getText().toString().length()==0 ||
                        lockingperiod.getText().toString().length()==0 ||
                        clientage.getText().toString().length()==0 ||
                        Payback.getText().toString().length()==0 ||
                        IFSC.getText().toString().length()==0 ||
                        daterec.getText().toString().length()==0 ||
                        daterec.getText().toString().length()<8 ||
                        daterec.getText().toString().length()>10 ||
                        !daterec.getText().toString().contains("/") ||
                        startingdate.getText().toString().length()==0 ||
                        startingdate.getText().toString().length()<8 ||
                        startingdate.getText().toString().length()>10 ||
                        !startingdate.getText().toString().contains("/") ||
                        endingdate.getText().toString().length()==0 ||
                        endingdate.getText().toString().length()<8 ||
                        endingdate.getText().toString().length()>10 ||
                        !endingdate.getText().toString().contains("/"))

                {

                    Toast.makeText(ScrollingActivity.this,"Please Enter All Information Correctly",Toast.LENGTH_LONG).show();

                }
                else {

                    String[] arrOfDaterec = daterec.getText().toString().split("/", 0);
                    String[] arrOfStartDate = startingdate.getText().toString().split("/", 0);
                    String[] arrOfEndDate = endingdate.getText().toString().split("/", 0);


                    String BrokerName = "Chankesh Jain";
                    String BrokerAdd = "B-406, Suresh Patil Chawl, Balaji Mandir Rd, Dombivli (E), Dist Thane";

                    InputStream src = getResources().openRawResource(R.raw.pdf_fillable);
                    String dst = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                            + "/client_" + PersonName.getText().toString() + ".pdf";


                    try {
                        PdfReader reader = new PdfReader(src);
                        PdfStamper stamper = new PdfStamper(reader,
                                new FileOutputStream(dst));
                        AcroFields form = stamper.getAcroFields();

                        form.setField("BrokerName", BrokerName);
                        form.setField("BrokerAdd", BrokerAdd);
                        form.setField("BrokerAge", brokerage.getText().toString());
                        form.setField("ClientName", PersonName.getText().toString());
                        form.setField("ClientAdd", Address.getText().toString());
                        form.setField("ClientAge", clientage.getText().toString());
                        form.setField("PeriodOfAgr", periodofagr.getText().toString());
                        form.setField("AmountRec", AmountRecieved.getText().toString());
                        form.setField("AmtInWords", Amountrecinwords.getText().toString());
                        form.setField("DayRec", arrOfDaterec[0]);
                        form.setField("MonthRec", Months[Integer.parseInt(arrOfDaterec[1]) - 1]);
                        form.setField("YearRec", arrOfDaterec[2]);
                        form.setField("AocInWords", accopeningchar.getText().toString());
                        form.setField("MonthlyPayInWords", Payback.getText().toString());
                        form.setField("StartingDay", arrOfStartDate[0]);
                        form.setField("StartingMonth", Months[Integer.parseInt(arrOfStartDate[1]) - 1]);
                        form.setField("EndingDayMonth", arrOfEndDate[0] + "th " + Months[Integer.parseInt(arrOfEndDate[1]) - 1]);
                        form.setField("EndingYear", arrOfEndDate[2]);
                        form.setField("LockingPeriod", lockingperiod.getText().toString());
                        form.setField("AccName", AccHoldr.getText().toString());
                        form.setField("Bname", Bname.getText().toString());
                        form.setField("AccNo", AccNo.getText().toString());
                        form.setField("IFSC", IFSC.getText().toString());
                        form.setField("Bbranch", Bbranch.getText().toString());
                        form.setField("Email", EmailAddress.getText().toString());
                        form.setField("MobileNo", Phone.getText().toString());
                        form.setField("Aadhaar", AadharNumber.getText().toString());
                        form.setField("PanNo", PanNo.getText().toString());
                        form.setField("BrokerPhoto","Chankesh Jain");
                        form.setField("ClientPhoto", PersonName.getText().toString());
                        form.setField("ClientSignature", PersonName.getText().toString() + "'s Sign");


                        stamper.close();
                        reader.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }


            }

        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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