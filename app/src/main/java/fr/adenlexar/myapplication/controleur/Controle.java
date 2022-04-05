package fr.adenlexar.myapplication.controleur;

import fr.adenlexar.myapplication.modele.Profil;

public final class Controle {

    private static Controle instance = null;
    private Profil profil;

    /**
     * Constructeur privée
     */
    private Controle(){
        super();
    }

    /**
     * Création de l'instance du singleton controle
     * @return instance
     */
    public static final Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param nom
     * @param sexe true : homme, false : femme
     * @param age
     * @param taille (cm)
     * @param poids (kg)
     * @param activite sédentaire : 0, legerement actif : 1, actif : 2, très actif : 3, extremement actif : 4
     * @param objectif maigrir : -1, maintient : 0, grossir : 1
     */
    public void creerProfil(String nom, boolean sexe, int age, int taille, int poids, int activite, int objectif){
        this.profil = new Profil(nom, sexe, age, taille, poids, activite, objectif);
    }

    /**
     * récupération de l'imc de profil
     * @return imc
     */
    public double getImc(){
        return this.profil.getImc();
    }

    /**
     * récupération du métabolisme basal de profil
     * @return imc
     */
    public int getMetabolisme_basal() {
        return this.profil.getMetabolisme_basal();
    }

    /**
     * récupération du métabolisme de maintient de profil
     * @return imc
     */
    public int getMetabolisme_maintient() {
        return this.profil.getMetabolisme_maintient();
    }

    /**
     * récupération du métabolisme de l'objectif de profil
     * @return imc
     */
    public int getMetabolisme_objectif() {
        return this.profil.getMetabolisme_objectif();
    }
}
