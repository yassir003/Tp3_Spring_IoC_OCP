import config.AppConfig;
import metier.IMetier;
import metier.MetierMultiple;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialisation avec activation du profil prod avant le refresh du conteneur
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(AppConfig.class);
        context.refresh();

        // 1. Récupération du bean métier principal (MetierImpl)
        IMetier metier = context.getBean(IMetier.class);
        double resultat = metier.calcul();
        System.out.println("Résultat final (MetierImpl) : " + resultat);

        // 2. Test de MetierMultiple (injection de List<IDao> et Map<String, IDao>)
        MetierMultiple metierMultiple = context.getBean(MetierMultiple.class);
        System.out.println("\n--- Démonstration de MetierMultiple ---");
        System.out.println("Nombre total de beans DAO injectés dans la List : " + metierMultiple.getDaos().size());
        System.out.println("Clés des beans DAO injectés dans la Map : " + metierMultiple.getDaoMap().keySet());
        System.out.println("Somme de toutes les valeurs des DAO : " + metierMultiple.calcul());

        // Fermeture du conteneur
        context.close();
    }
}
