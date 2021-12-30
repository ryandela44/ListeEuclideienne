package com.company;

public class Main {

    public static void main(String[] args) {
//	ListeEuclidienne<Integer> list = new ListeEuclidienne<>(5,5);
//    System.out.println("List ");
    ListeEuclidienne<Integer> liste = new ListeEuclidienne<>();
    ListeEuclidienne<Integer> liste1 = new ListeEuclidienne<>();
   /* liste.inverser();
    liste.inserer(1);
    liste.inserer(2);*/
    liste1.inserer(3);
    liste1.inserer(4);
    //liste.lire();
    //liste.avancer(2);
//    System.out.println(liste.size);
    liste.insererTout(liste1);
    liste.supprimer();
    }
}
