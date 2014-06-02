import java.util.HashMap;

public class Partie
{
	public static String NOM_CARTE = "Blargh";
	public static int LARGEUR_CARTE = 20;
	public static int HAUTEUR_CARTE = 15;
	public static boolean COMBAT_ALEATOIRES = false;
	private Equipe equipe;
	private HashMap<String, Carte> ensemble_cartes;
	private Carte carte;
	private String etat;
	private IHM ihm;
	
	public Partie(IHM ihm)
	{
		this.equipe = new Equipe();
		this.ensemble_cartes = new HashMap<String, Carte> ();
		this.etat = "init";
		this.ihm = ihm;
		this.ihm.transmettrePartie(this);
	}

	public void lancerPartie()
	{
		creerCartes();
		this.carte = ensemble_cartes.get(NOM_CARTE);
		this.etat = "Carte";
		boucleJeu();
	}
	
	private void creerCartes() {
		int largeur = LARGEUR_CARTE;
		int hauteur = HAUTEUR_CARTE;
		this.ensemble_cartes.put(NOM_CARTE, new Carte(new Tile[2][largeur*hauteur],largeur,hauteur));
		
		Position position;
		for (int ligne = 0; ligne < hauteur; ligne++)
		{
			for (int colonne = 0; colonne < largeur; colonne++)
			{
				position = new Position(ligne, colonne);
				this.ensemble_cartes.get(NOM_CARTE).setCase(position,Tile.MUR_PIERRE,0);
				this.ensemble_cartes.get(NOM_CARTE).setCase(position,null,1);
			}
		}
	}

	public void boucleJeu()
	{
		while (etat != "Fin")
		{
			if (etat == "Carte")
			{
				this.ihm.afficherCarte();
			}
			else
			{
				System.out.println("Erreur, état imprévu.");
			}
			this.ihm.attendreReaction();
		}
	}
	
	public void changerEtat(String etat)
	{
		this.etat = etat;
	}
	
	public void changerCarte(String carte, Position position)
	{
		this.carte = ensemble_cartes.get(carte);
		this.equipe.forcerPosition(position);
	}

	public String obtenirEtat()
	{
		return this.etat;
	}
	
	public Carte obtenirCarte()
	{
		return this.carte;
	}
	
	public HashMap<String, Carte> obtenirEnsembleCartes()
	{
		return this.ensemble_cartes;
	}
	
	public Equipe obtenirEquipe()
	{
		return this.equipe;
	}
}
