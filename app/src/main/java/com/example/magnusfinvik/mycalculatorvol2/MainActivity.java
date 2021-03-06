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
import android.widget.Toast;

import org.matheclipse.parser.client.eval.DoubleEvaluator;

public class MainActivity extends AppCompatActivity {

    private static final java.lang.String CALCULATOR_STATE = "Calculator";
    private static final java.lang.String EXPRESSION_STATE = "Expression";
    private static final java.lang.String RESULT_STATE = "Result";
    private int typeOfCalculator = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        restoreCalculatorState(savedInstanceState);
    }

    private void restoreCalculatorState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            typeOfCalculator = savedInstanceState.getInt(CALCULATOR_STATE);
            switch (typeOfCalculator){
                case 0:
                    setContentView(R.layout.content_main);
                    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
                    break;
                case 1:
                    setContentView(R.layout.scientific_main);
                    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
                    break;
            }
            ((TextView)findViewById(R.id.resultView)).setText(savedInstanceState.getCharSequence(EXPRESSION_STATE));
            ((TextView)findViewById(R.id.result)).setText(savedInstanceState.getCharSequence(RESULT_STATE));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putCharSequence(EXPRESSION_STATE, ((TextView) findViewById(R.id.resultView)).getText().toString());
        outState.putCharSequence(RESULT_STATE, ((TextView) findViewById(R.id.result)).getText().toString());
        outState.putInt(CALCULATOR_STATE, typeOfCalculator);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_end:
                this.finish();
                return true;
            case R.id.action_changeCalc:
                changeCalculatorView();
                return true;
            case R.id.action_info:
                showInfoDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void changeCalculatorView() {
        if(typeOfCalculator == 0){
            setContentView(R.layout.scientific_main);
            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            typeOfCalculator = 1;
        }else{
            setContentView(R.layout.content_main);
            Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            typeOfCalculator = 0;
        }
    }

    private void showInfoDialog() {
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
    }

    public void btnClick(View view) {
        TextView resultView = (TextView)findViewById(R.id.resultView);
        TextView result = (TextView)findViewById(R.id.result);
        DoubleEvaluator mathEngine = new DoubleEvaluator();
        switch(view.getId()){
            case R.id.btnEquals:
                calculationOfEquation(resultView, result, mathEngine);
                break;
            case R.id.btnClear: resultView.setText("");break;
            case R.id.btnRemoveOne:
                if(resultView.length() > 0)
                    resultView.setText(resultView.getText().toString().substring(0, resultView.length()-1));
                break;
            case R.id.btnUseAnswer: resultView.append(result.getText());break;
            case R.id.btnBracketRight: resultView.append("]");break;
            case R.id.btnDivide: resultView.append("/");break;
            case R.id.btnMultiply: resultView.append("*");break;
            case R.id.btnMinus: resultView.append("-");break;
            case R.id.btnPlus: resultView.append("+");break;
            case R.id.btnPercentage: resultView.append("/100");break;
            case R.id.btnSqrt: resultView.append("Sqrt[");break;
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
            case R.id.btnLog: resultView.append("Log[");break;
            case R.id.btnx2: resultView.append("^2");break;
            case R.id.btn10X: resultView.append("10^");break;
            case R.id.btnSin: resultView.append("Sin[");break;
            case R.id.btnSquaredX: resultView.append("^");break;
            case R.id.btnCos: resultView.append("Cos[");break;
            case R.id.btnPI: resultView.append("Pi");break;
            case R.id.btnTan: resultView.append("Tan[");break;
            case R.id.btnFaculty:
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.FacultyErrorMessage), Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }

    private void calculationOfEquation(TextView resultView, TextView result, DoubleEvaluator mathEngine) {
        try {
            double d = mathEngine.evaluate(resultView.getText().toString());
            result.setText(Double.toString(d));
            resultView.setText("");
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.EquationErrorMessage), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
