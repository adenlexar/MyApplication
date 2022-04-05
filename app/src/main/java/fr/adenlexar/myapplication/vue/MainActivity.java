package fr.adenlexar.myapplication.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import fr.adenlexar.myapplication.R;
import fr.adenlexar.myapplication.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //construction des éléments graphiques en appelant le fichier xml associé
        init();
        this.controle = Controle.getInstance();
    }

    //controleur
    private Controle controle;

    //propriétés graphiques input
    private EditText txtNom;
    private RadioButton rdHomme;
    private EditText txtAge;
    private EditText txtTaille;
    private EditText txtPoids;

    //propriétés graphiques output
    private TextView txtImc;
    private TextView txtMeta;
    private TextView txtMetaObj;


    /**
     * Init des liens avec les objets graphiques
     */
    private void init(){
        this.txtNom = (EditText) findViewById(R.id.idNameInput); //récupération de l'objet graphique à l'aide de l'id
        this.rdHomme = (RadioButton) findViewById(R.id.idMaleRd);
        this.txtAge = (EditText) findViewById(R.id.idAgeInput);
        this.txtTaille = (EditText) findViewById(R.id.idHeightInput);
        this.txtPoids = (EditText) findViewById(R.id.idWeightInput);

        this.txtImc = (TextView) findViewById(R.id.idImcOutput);
        this.txtMeta = (TextView) findViewById(R.id.idMetaOutput);
        this.txtMetaObj = (TextView) findViewById(R.id.idMetaObjOutput);

        ecouteCalcul();
    }

    private void ecouteCalcul(){
        ((Button) findViewById(R.id.idCalcButton)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //@TODO compléter la méthode avec activité et objectif
                String nom = "";
                boolean sexe = true;
                int age = 0;
                int taille = 0;
                int poids = 0;

                //récup données saisies
                nom = txtNom.toString();
                sexe = rdHomme.isChecked();
                try{
                    age = Integer.parseInt(txtAge.toString());
                    taille = Integer.parseInt(txtTaille.toString());
                    poids = Integer.parseInt(txtPoids.toString());
                }catch(Exception e){};

                //controle des données saisies
                if(age == 0 || taille == 0 || poids == 0){
                    Toast.makeText(MainActivity.this,"saisie incorrecte",Toast.LENGTH_SHORT);
                }
                else{
                    afficheInfos(nom, sexe, age, taille, poids, 0,1);
                }
            }
        });
    }

    /**
     * Affiche les infos du profil créé
     * @param nom
     * @param sexe
     * @param age
     * @param taille
     * @param poids
     * @param activite
     * @param objectif
     */
    private void afficheInfos(String nom, boolean sexe, int age, int taille, int poids, int activite, int objectif){
        //Création profil et récup des infos
        this.controle.creerProfil(nom,sexe,age,taille,poids,activite,objectif);
        double imc = this.controle.getImc();
        int metabolisme_basal = this.controle.getMetabolisme_basal();
        int metabolisme_objectif = this.controle.getMetabolisme_objectif();

        //affichage
        this.txtImc.setText(String.format("%.0.1f",imc));
        this.txtMeta.setText(metabolisme_basal);
        this.txtMetaObj.setText(metabolisme_objectif);
    }

}