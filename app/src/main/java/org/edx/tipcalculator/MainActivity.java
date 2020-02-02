package org.edx.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipTotal)
    TextView tvTipTotal;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotal;

    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float REGULAR_TIP_PERCENTAGE = 10;
    float DEFAULT_TIP_PERCENTAGE = 15;
    float EXCELLENT_TIP_PERCENTAGE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    /**
     * replace default values with numbers
     */
    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tvTipTotal.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotal.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));

    }

    @OnClick({R.id.ibExcellentService, R.id.ibGoodService, R.id.ibRegularService})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if(percentage == 0){
            percentage = DEFAULT_TIP_PERCENTAGE;
        }

        if(!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals(".")) {
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        } else {
            totalBillAmount = 0;
        }

        tipTotal = (totalBillAmount*percentage)/100;
        finalBillAmount = totalBillAmount + tipTotal;
    }
}
