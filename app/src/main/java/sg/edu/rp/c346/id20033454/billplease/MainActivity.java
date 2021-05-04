package sg.edu.rp.c346.id20033454.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etPaid;
    EditText etPax;
    EditText etDiscount;
    EditText etTel;
    ToggleButton btnSvs;
    ToggleButton btnGst;
    Button btnSplit;
    Button btnReset;
    RadioGroup radioPayment;
    TextView totalPay;
    RadioButton radioButtonCash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPaid=findViewById(R.id.etPaid);
        etPax=findViewById(R.id.etPax);
        etDiscount=findViewById(R.id.etDiscount);
        etTel=findViewById(R.id.etTel);
        btnSvs=findViewById(R.id.btnSvs);
        btnGst=findViewById(R.id.btnGst);
        btnSplit=findViewById(R.id.btnSplit);
        btnReset=findViewById(R.id.btnReset);
        radioPayment=findViewById(R.id.radioPayment);
        totalPay=findViewById(R.id.totalPay);
        radioButtonCash=findViewById(R.id.radioButtonCash);


        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean errorcheck=false;
                boolean isdiscount=false;
                int pax = 0;
                double discount = 0.0;
                String output="";
                double svs=1.1;
                double gst=1.07;
                double totalbill=0.0;
                String payby="";

                //Amount
                if (etPaid.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Amount Error",
                            Toast.LENGTH_LONG).show();
                    etPaid.setError("Invalid Amount");
                    output+=String.format("\nPlease input a valid amount");
                    totalPay.setText(output);
                    totalPay.setTextColor(Color.RED);
                    errorcheck=true;
                }else{
                    totalbill = Double.parseDouble(etPaid.getText().toString());

                }

                //Pax
                if (etPax.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Pax Error",
                            Toast.LENGTH_LONG).show();
                    etPax.setError("Invalid Number");
                    output+=String.format("\nPlease input a valid no of pax");
                    totalPay.setText(output);
                    totalPay.setTextColor(Color.RED);
                    errorcheck=true;
                }else{
                    pax = Integer.parseInt(etPax.getText().toString());
                }

                //Discount
                if (!etDiscount.getText().toString().isEmpty()) {

                    double temp = Double.parseDouble(etDiscount.getText().toString());
                    if (temp == 0) {
                        discount = 1;
                    } else {
                        discount = temp / 100;
                    }
                    isdiscount = true;
                }

                //GST
                if(btnGst.isChecked()==true) {
                    totalbill=totalbill*gst;

                }

                //SVS
                if(btnSvs.isChecked()==true) {
                    totalbill = totalbill * svs;
                }

                //Discount
                if(isdiscount==true){
                   totalbill=totalbill*(1-discount);
                }

                //Payment
                String telno = etTel.getText().toString();
                int pay = radioPayment.getCheckedRadioButtonId();
                if(pay==R.id.radioButtonCash){
                    payby= "via cash";
                }else{
                    payby= "via PayNow to " + telno;
                }

                if (errorcheck==false){
                    output= String.format("Total Bill: $%.2f\nEach Pays: $%.2f %s",
                            totalbill, (totalbill/pax), payby );
                    totalPay.setText(output);
                }






            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPaid.setText("");
                etPax.setText("");
                etDiscount.setText("");
                etTel.setText("");
                radioPayment.clearCheck();
                radioButtonCash.setChecked(true);
                totalPay.setText("");
            }
        });





    }


}