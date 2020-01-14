package com.example.techwork;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.techwork.Data.Contract;
import com.example.techwork.Data.DbHelper;

public class signup extends AppCompatActivity {


    private EditText ename;
    private EditText ecollege;
    private EditText epassword;
    private EditText repassword;
    private  EditText eemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ename= (EditText)findViewById(R.id.et_name);
        ecollege= (EditText)findViewById(R.id.et_clg);
        epassword=(EditText)findViewById(R.id.et_password);
        repassword=(EditText)findViewById(R.id.et_re_password);
        eemail=(EditText)findViewById(R.id.et_email);
    }
    public void register(View V)
    {
        String name = ename.getText().toString().trim();
        String college = ecollege.getText().toString().trim();
        String email = eemail.getText().toString().trim();
        String passwrd= epassword.getText().toString().trim();
        String passwrd2= repassword.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(college)||TextUtils.isEmpty(email))
        {

            Toast.makeText(this,"INVALID ENTRIES!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(checkmail(email))
        {
            return;
        }
        if(!passwrd.equals(passwrd2))
        {
            Toast.makeText(this,"Error Password mismatched!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwrd.length()<6)
        {
            Toast.makeText(this,"Password contains atleast 6 characters",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, name);
        values.put(Contract.Entry.COLUMN_EMAIL,email);
        values.put(Contract.Entry.COLUMN_COLLEGE,college);
        values.put(Contract.Entry.COLUMN_PASSWORD,passwrd);
        Uri newUri = getContentResolver().insert(Contract.Entry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(this," Error in registration!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "registration successful with name: "+name,
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    DbHelper dbHelper=new DbHelper(this);
    public boolean checkmail(String email)
    {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+ Contract.Entry.TABLE_NAME+" where "+Contract.Entry.COLUMN_EMAIL +"=?"
                ,new String[]{email});
        if(c.getCount()<=0)
        {
            return false;
        }
        else
        {
            Toast.makeText(this,"email is already registered!",Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
