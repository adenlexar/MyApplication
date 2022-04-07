package fr.adenlexar.myapplication.modele;

/**
 * @author aleco
 */
public class Profil {

    //données entrées
    private String nom;
    private boolean sexe; //true : homme, false : femme
    private int age;
    private int taille; //(cm)
    private int poids; //(kg)
    private int activite; //sédentaire : 0, legerement actif : 1, actif : 2, très actif : 3, extremement actif : 4
    private int objectif; //maigrir : -1, maintient : 0, grossir : 1

    //données calculées
    private double imc;
    private int metabolisme_basal;
    private int metabolisme_maintient; //nombre de calories à ingérer pour maintenir son poids
    private int metabolisme_objectif; //nombre de calories à ingérer pour remplir l'objectif

    public Profil(String nom, boolean sexe, int age, int taille, int poids, int activite, int objectif) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.activite = activite;
        this.objectif = objectif;
        calculIMC();
        calculMeta();
        calculMetaM();
        calculMetaO();
    }

    private void calculIMC(){
        this.imc = ((double)this.poids)/Math.pow(((double)this.taille/100),2); // = P/T^2 avec T en m
    }

    private void calculMeta(){//on utilise la formule de Black et al. (1996) qui est actuellement la référence WIKIPEDIA
        if(this.sexe){ //si c'est un homme
            this.metabolisme_basal = (int) (259
                    *(Math.pow((double)this.poids, 0.48)
                    *Math.pow( ((double)this.taille/100), 0.50)
                    *Math.pow((double)this.age, -0.13)));
        }
        else{ //si c'est une femme
            this.metabolisme_basal = (int) (230
                    *(Math.pow((double)this.poids, 0.48)
                    *Math.pow( ((double)this.taille/100), 0.50)
                    *Math.pow((double)this.age, -0.13)));
        }
    }

    private void calculMetaM(){ //calcul metabolisme maintient
        double facteur = 1.2;
        if(this.activite == 1) facteur = 1.375;
        else if(this.activite == 2) facteur = 1.55;
        else if(this.activite == 3) facteur = 1.725;
        else if(this.activite == 4) facteur = 1.9;

        this.metabolisme_maintient = (int) (this.metabolisme_basal * facteur);
    }

    private void calculMetaO(){
        this.metabolisme_objectif = this.metabolisme_maintient;
        if(this.objectif > 0){
            this.metabolisme_objectif *= 1.1;
        }
        else if(this.objectif < 0){
            this.metabolisme_objectif *= 0.9;
        }
    }

    public String getNom() {
        return nom;
    }

    public boolean isSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }

    public int getTaille() {
        return taille;
    }

    public int getPoids() {
        return poids;
    }

    public int getActivite() {
        return activite;
    }

    public int getObjectif() {
        return objectif;
    }

    public double getImc() {
        return imc;
    }

    public int getMetabolisme_basal() {
        return metabolisme_basal;
    }

    public int getMetabolisme_maintient() {
        return metabolisme_maintient;
    }

    public int getMetabolisme_objectif() {
        return metabolisme_objectif;
    }
}
