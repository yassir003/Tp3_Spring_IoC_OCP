package metier;

public interface IMetier {
    /**
     * Effectue un traitement métier en s'appuyant sur la source de données DAO.
     * @return le résultat du calcul métier
     */
    double calcul();
}
