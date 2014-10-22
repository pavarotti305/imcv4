package com.droid.imcv4;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String[] couleurs = new String[]{
            "#9590FF",
            "#FF0033",
            "#186FDF",
            "#00FF00",
            "#DEAA2C",
            "#FDBD96",
            "#91283B",
            "#ED0000",
            "#A91101",
            "#A10684",
            "#57D53B"};

    private static final String[] etats = new String[]{
            "Nous ne pouvons pas calculer votre IMC",
            "Votre état est critique il faudra vite vous faire soigner.",
            "Vous êtes maigre.",
            "Vous avez une corpulence normale.",
            "Vous êtes en surpoids.",
            "Vous êtes en obésité modérée.",
            "Vous êtes en obésité sevère.",
            "Vous êtes en obésité morbide.",
            "Vous êtes en obésité massive.",
            "Nous ne pouvons pas calculer votre IMC",
            "Sans oublier de manger 5 fruits et légumes par jour."};

    private EditText gPoids;
    private EditText gTaille;
    private RadioGroup gUnites;
    private CheckBox gMegaFonction;
    private TextView gIMC, gInterp;

    private OnClickListener CalcListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Stub de la méthode généré automatiquement
            double poids, taille, imc = 0;
            boolean flag = false;
            String error = "";

            if (gPoids.getText().toString().isEmpty()) {
                flag = true;
                error += "La case poids est vide remplissage de cette case obligatoire \n";
                gIMC.setText("La case poids est vide.");
                gInterp.setText(etats[9]);
                gInterp.setTextColor(Color.parseColor(couleurs[9]));

            }
            if (gTaille.getText().toString().isEmpty()) {
                flag = true;
                error += "La case taille est vide remplissage de cette case obligatoire \n";
                gIMC.setText("La case taille est vide.");
                gInterp.setText(etats[9]);
                gInterp.setTextColor(Color.parseColor(couleurs[9]));

            }

            if (gPoids.getText().toString().isEmpty() && (gTaille.getText().toString().isEmpty())) {
                flag = true;
                error += "Les cases poids et taille incomplets remplissage des cases obligatoire \n";
                gIMC.setText("Les cases poids et taille  sont vides.");
                gInterp.setText(etats[9]);
                gInterp.setTextColor(Color.parseColor(couleurs[9]));
                Toast.makeText(getApplicationContext(), error,
                        Toast.LENGTH_LONG).show();
            }

            if (flag) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            } else {
                flag = false;
                error = "";
                poids = Double.parseDouble(gPoids.getText().toString());
                taille = Double.parseDouble(gTaille.getText().toString());

                if (poids <= 0 || poids >= 630) {
                    flag = true;
                    error += "Poids incorrect";
                    Toast.makeText(getApplicationContext(), error,
                            Toast.LENGTH_LONG).show();
                }

                if (taille <= 0 || taille > 250) {
                    flag = true;
                    error += "Taille incorrect";
                    Toast.makeText(getApplicationContext(), error,
                            Toast.LENGTH_LONG).show();
                }


                if (flag) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                } else if (gUnites.getCheckedRadioButtonId() == R.id.rdoCenti) {
                    taille = taille / 100;
                    imc = poids / Math.pow(taille, 2);
                    imc = Math.round(imc * 100.0) / 100.0;
                    gIMC.setText("Votre IMC est " + String.valueOf(imc));
                }
                if (flag) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();

                } else if (gUnites.getCheckedRadioButtonId() == R.id.rdoMetres) {
                    imc = poids / Math.pow(taille, 2);
                    imc = Math.round(imc * 100.0) / 100.0;
                    gIMC.setText("Votre IMC est " + String.valueOf(imc));

                }

                if (imc <= 12) {
                    gIMC.setText("Vos données ne sont pas correctes.");
                    gInterp.setText(etats[0]);
                    gInterp.setTextColor(Color.parseColor(couleurs[0]));
                } else {
                    if (imc < 16.5) {
                        gInterp.setText(etats[1]);
                        gInterp.setTextColor(Color.parseColor(couleurs[1]));
                    } else {
                        if (imc < 18.5) {
                            gInterp.setText(etats[2]);
                            gInterp.setTextColor(Color.parseColor(couleurs[2]));
                        } else {
                            if (imc < 25) {
                                gInterp.setText(etats[3]);
                                gInterp.setTextColor(Color.parseColor(couleurs[3]));
                            } else {
                                if (imc < 30) {
                                    gInterp.setText(etats[4]);
                                    gInterp.setTextColor(Color.parseColor(couleurs[4]));
                                } else {
                                    if (imc < 35) {
                                        gInterp.setText(etats[5]);
                                        gInterp.setTextColor(Color.parseColor(couleurs[5]));
                                    } else {
                                        if (imc < 40) {
                                            gInterp.setText(etats[6]);
                                            gInterp.setTextColor(Color.parseColor(couleurs[6]));
                                        } else {
                                            if (imc < 50) {
                                                gInterp.setText(etats[7]);
                                                gInterp.setTextColor(Color.parseColor(couleurs[7]));
                                            } else {
                                                if (imc < 160) {
                                                    gInterp.setText(etats[8]);
                                                    gInterp.setTextColor(Color.parseColor(couleurs[8]));
                                                } else {
                                                    gIMC.setText("Vos données ne sont pas correctes");
                                                    gInterp.setText(etats[9]);
                                                    gInterp.setTextColor(Color.parseColor(couleurs[9]));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    };

    private OnClickListener AnnulerListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Stub de la méthode généré automatiquement
            gPoids.getText().clear();
            gTaille.getText().clear();

            gIMC.setText("");
            gInterp.setText("");

            gMegaFonction.setChecked(false);
            gUnites.check(R.id.rdoCenti);

            gPoids.requestFocus();
        }
    };

    private OnCheckedChangeListener checkListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            if (isChecked) {
                gIMC.setText("Vous êtes en parfaite santé continuez a faire du sport");
                gInterp.setText("Sans oublier de manger 5 fruits et légumes par jour");

            }
        }
    };


    private android.widget.RadioGroup.OnCheckedChangeListener RdoListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Stub de la méthode généré automatiquement
            gIMC.setText("");
            gInterp.setText("");
        }
    };

    private TextWatcher TxtWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Stub de la méthode généré automatiquement
            gIMC.setText("");
            gInterp.setText("");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Stub de la méthode généré automatiquement

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Stub de la méthode généré automatiquement

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gPoids = (EditText) findViewById(R.id.edtPoids);
        gTaille = (EditText) findViewById(R.id.edtTaille);

        gUnites = (RadioGroup) findViewById(R.id.rdoGrpUnite);
        gMegaFonction = (CheckBox) findViewById(R.id.chkFonction);

        Button gCalc = (Button) findViewById(R.id.btnCalc);
        Button gAnnuler = (Button) findViewById(R.id.btnRaz);

        gIMC = (TextView) findViewById(R.id.txtIMC);
        gInterp = (TextView) findViewById(R.id.txtInterp);

        gCalc.setOnClickListener(CalcListener);
        gAnnuler.setOnClickListener(AnnulerListener);
        gUnites.setOnCheckedChangeListener(RdoListener);
        gMegaFonction.setOnCheckedChangeListener(checkListener);
        gPoids.addTextChangedListener(TxtWatcher);
        gTaille.addTextChangedListener(TxtWatcher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_main, container,
                    false);
        }
    }

}
