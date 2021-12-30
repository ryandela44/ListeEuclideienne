package com.company;

import java.util.Iterator;
//Classe Eucliduenne
public class ListeEuclidienne<E> {
    //initialization des variables
    int size = 0;
    boolean isVide = true;
    Chainon tete = null;
    Chainon dernier = null;

    //direction par default
    Direction direction = Direction.HORAIRE;
    Chainon previousTete = null;

    public enum Direction {
        HORAIRE,
        ANTIHORAIRE
    }

    //constructeurs
    public ListeEuclidienne() {

    }

    public ListeEuclidienne(int n, E element) {
        for (int i = 0; i < n; i++) {
            inserer(element);
        }
    }

    //classe chainon avec deux directions ainsi que la valeur du chainon
    private class Chainon {
        E element;
        Chainon previous;
        Chainon next;
    }

    //classe iterateur
    private class Iter {
        public Iter() {
        }

        public boolean hasNext() {
            return estVide();
        }

        //retourne la valeur de l'element actuelle et avance au prochain element
        public E next() {
            E element = tete.element;
            avancer();
            return element;
        }
    }

    public Iterator<E> iterator() {
        return null;
    }

    //retourne la valuer de l'element actuelle
    public E lire() {
        return tete.element;
    }

    //retoune la taille de la liste
    public int taille() {
        return size;
    }

    //retourne la direction de liste
    public Direction getDirection() {
        return direction;
    }

    //retoune un bool indiquant si la liste est vide
    public boolean estVide() {
        if (size != 0)
            isVide = false;
        return isVide;
    }

    //ecrit une valeur dans la tete de la liste
    public void ecrire(E element) {
        tete.element = element;
    }

    //insere un element dans la liste
    public void inserer(E element) {
        //inserer si la liste est vide
        if (tete == null) {
            Chainon nouveauChainon = new Chainon();
            nouveauChainon.element = element;
            nouveauChainon.next = nouveauChainon.previous = nouveauChainon;
            tete = dernier = nouveauChainon;
            size++;
            return;
        }

        //si la liste n'est pas vide creer un nouveau chainon avac la valuer de element passe
        Chainon nouveauChainon = new Chainon();
        nouveauChainon.element = element;

        //inserer en direction horaire
        if (direction == Direction.HORAIRE) {
            previousTete = nouveauChainon.next = tete;
            tete.previous = nouveauChainon;
            nouveauChainon.previous = dernier;
            dernier.next = nouveauChainon;
        }
        //inserer en direction antihoraire
        else {
            nouveauChainon.next = dernier;
            dernier.previous = nouveauChainon;
            tete.next = nouveauChainon;
            previousTete = nouveauChainon.previous = tete;
        }
        //mettre a jour la taille et la tete de la liste.
        size++;
        tete = nouveauChainon;
    }

    //avancer la tete de la liste dans la direction courante d'une unite
    public void avancer() {
        tete = previousTete;
    }

    //avancer la tete de la liste du nombre d'unites passee
    public void avancer(int n) {
        if (n > 0) {
            tete = previousTete;
            if (n > 1) {
                for (int i = 0; i < n - 1; i++) {
                    //avancer en direction antihoraire
                    if (direction == Direction.ANTIHORAIRE)
                        tete = previousTete.previous;
                    //avancer en direction horaire
                    else
                        tete = previousTete.next;
                }
            }
        }
    }

    //inverser la direction de la liste
    public void inverser() {
        if (direction == Direction.HORAIRE)
            direction = Direction.ANTIHORAIRE;
        else if (direction == Direction.ANTIHORAIRE)
            direction = Direction.HORAIRE;
    }

    //supprime et retourne la tete actuelle;
    public E supprimer() {
        //nouvelle tete en direction horaire
        if (direction == Direction.HORAIRE) {
            avancer();
            dernier.next = previousTete;
            previousTete.previous = dernier;
            tete.next = previousTete.next;
            tete.previous = dernier;
        } else {
            //nouvelle tete en direction antihoraire
            avancer();
            previousTete.next = dernier;
            dernier.previous = previousTete;
            tete.previous = previousTete.previous;
            tete.next = dernier;
        }
        return tete.element;
    }

    //insere une liste a la liste courante
    public void insererTout(ListeEuclidienne<E> liste) {
        if (tete == null) {
            this.tete = liste.tete;
            size = liste.size;
            return;
        }

        //liste antihoraire inseree dans liste horaire
        if (this.direction == Direction.HORAIRE && liste.direction == Direction.ANTIHORAIRE) {
            this.dernier.next = liste.tete;
            this.dernier.next.previous = this.dernier;
            this.tete.previous = liste.dernier;
            this.tete.previous.next = this.tete;
            dernier = liste.dernier;
            size += liste.size;
        }
        //liste horaire inseree dans liste horaire
            else if (this.direction == Direction.HORAIRE && liste.direction == Direction.HORAIRE) {
            this.dernier.next = liste.dernier;
            this.dernier.next.previous = this.dernier;
            this.tete.previous = liste.tete;
            this.tete.previous.next = this.tete;
            dernier = liste.tete;
            size += liste.size;
        }
            //lsite anithoraire inseree dans liste antihoraire
            else if (this.direction == Direction.ANTIHORAIRE && liste.direction == Direction.ANTIHORAIRE) {
            this.tete.previous = liste.tete;
            this.tete.previous.next = this.tete;
            this.dernier.next = liste.dernier;
            this.dernier.next.previous = this.dernier;
            dernier = liste.tete;
            size += liste.size;
        }
            //liste horaire insere dans liste horaire
            else if (this.direction == Direction.ANTIHORAIRE && liste.direction == Direction.HORAIRE) {
            this.tete.previous = liste.dernier;
            this.tete.previous.next = this.tete;
            this.dernier.next = liste.tete;
            this.dernier.next.previous = this.dernier;
            dernier = liste.dernier;
            size += liste.size;
        }

    }
}
