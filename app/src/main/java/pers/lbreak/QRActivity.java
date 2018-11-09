package pers.lbreak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import pers.lbreak.myutils.utils.QRCodeUtils;

public class QRActivity extends AppCompatActivity {
    ImageView qrcode1;
    ImageView qrcode2;
    ImageView qrcode3;
    ImageView qrcode4;
    ImageView qrcode5;
    ImageView qrcode6;
    QRCodeUtils code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        code=new QRCodeUtils(this);

        qrcode1 = (ImageView) findViewById(R.id.qrcode1);
        qrcode2 = (ImageView) findViewById(R.id.qrcode2);
        qrcode3 = (ImageView) findViewById(R.id.qrcode3);
        qrcode4 = (ImageView) findViewById(R.id.qrcode4);
        qrcode5 = (ImageView) findViewById(R.id.qrcode5);
        qrcode6 = (ImageView) findViewById(R.id.qrcode6);

        qrcode1.setImageBitmap(code.createQRCode("http://www.tmtpost.com/2536837.html"));
        qrcode2.setImageBitmap(code.createQRCodeWithLogo2("http://www.jianshu.com/users/4a4eb4feee62/latest_articles", 500, R.drawable.icon));
        qrcode3.setImageBitmap(code.createQRCodeWithLogo3("http://www.jianshu.com/users/4a4eb4feee62/latest_articles", 500,R.drawable.icon));
        qrcode4.setImageBitmap(code.createQRCodeWithLogo4("http://www.jianshu.com/users/4a4eb4feee62/latest_articles", 500, R.drawable.icon));
        qrcode5.setImageBitmap(code.createQRCodeWithLogo5("http://www.jianshu.com/users/4a4eb4feee62/latest_articles", 500,R.drawable.icon));
        qrcode6.setImageBitmap(code.createQRCodeWithLogo6("http://www.jianshu.com/users/4a4eb4feee62/latest_articles", 500, R.drawable.icon));
        qrcode6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               String text= code.readImg(qrcode6);
               if (text!=null){
                   Toast.makeText(getApplicationContext(),"二维码信息"+text, Toast.LENGTH_LONG).show();
               }
                return false;
            }
        });

    }


}
