package com.example.magnusfinvik.mycalculatorvol2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.matheclipse.parser.client.eval.DoubleEvaluator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_end:
                this.finish();
                return true;
            case R.id.action_changeCalc:
                //legg til opphenting av nytt view her
                return true;
            case R.id.action_info:

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.infoTitle));
                alertDialog.setMessage(getString(R.string.infoField));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            case R.id.action_settings:
                //insertSomethingHere
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void btnClick(View view) {
        TextView resultView = (TextView)findViewById(R.id.resultView);
        TextView result = (TextView)findViewById(R.id.result);
        DoubleEvaluator mathEngine = new DoubleEvaluator();
        switch(view.getId()){
            case R.id.btnEquals:
                double d = mathEngine.evaluate(resultView.getText().toString());
                result.setText(Double.toString(d));
                resultView.setText("");
                break;
            case R.id.btnClear: resultView.setText("");break;
            case R.id.btnRemoveOne:
                if(resultView.length() > 0)
                    resultView.setText(resultView.getText().toString().substring(0, resultView.length()-1));
                break;
            case R.id.btnUseAnswer: resultView.append(result.getText());break;
            case R.id.btnBracketLeft: resultView.append("(");break;
            case R.id.btnBracketRight: resultView.append(")");break;
            case R.id.btnDivide: resultView.append("/");break;
            case R.id.btnMultiply: resultView.append("*");break;
            case R.id.btnMinus: resultView.append("-");break;
            case R.id.btnPlus: resultView.append("+");break;
            case R.id.btnPercentage: resultView.append("%");break;
            case R.id.btnSqrt: resultView.append("sqrt(");break;
            case R.id.btnComma: resultView.append(".");break;
            case R.id.btnSeven: resultView.append("7");break;
            case R.id.btnEight: resultView.append("8");break;
            case R.id.btnNine: resultView.append("9");break;
            case R.id.btnFour: resultView.append("4");break;
            case R.id.btnFive: resultView.append("5");break;
            case R.id.btnSix: resultView.append("6");break;
            case R.id.btnOne: resultView.append("1");break;
            case R.id.btnTwo: resultView.append("2");break;
            case R.id.btnThree: resultView.append("3");break;
            case R.id.btnZero: resultView.append("0");break;
        }
    }
}
