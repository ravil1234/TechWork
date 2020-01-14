package com.example.techwork;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.techwork.Data.Contract;
import com.example.techwork.Data.DbHelper;
public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText passwrd;
    static String mail="";
    static int count=0;
    LinearLayout ll;
    DbHelper dbHelper=new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         email=(EditText)findViewById(R.id.login_email_edit);
         passwrd=(EditText)findViewById(R.id.login_password_edit);
         ll=findViewById(R.id.left_swipe);
        ll.setOnTouchListener(new OnSwipe(MainActivity.this)
        {
            public void onSwipeTop() {
                if(count==0) {
                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(i);
                }
            }
        });
    }
    public void signup(View v)
    {
        Intent i=new Intent(this,signup.class);
        startActivity(i);
    }
    public void login(View V)
    {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+ Contract.Entry.TABLE_NAME+" where "+Contract.Entry.COLUMN_EMAIL +"=? and "
                + Contract.Entry.COLUMN_PASSWORD +"=?",new String[]{email.getText().toString().trim(),passwrd.getText().toString().trim()});
        if(c.getCount()<=0)
        {
            Toast.makeText(this,"INVALID LOGIN!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            count=1;
            mail=email.getText().toString();
            Toast.makeText(this,"LOGGED IN!",Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(this,Dashboard.class);
            startActivity(intent);
        }
    }
}
