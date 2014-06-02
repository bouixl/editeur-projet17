/**
 * 
 * @author rougen
 *
 */
public enum Classes
{
	/**
	 * La classe bourrin 
	 */
	BARBARE(60,20,10,6,1,3, 5,2,3,2,1,1),
	/**
	 * Classe de degats par sorts � distance
	 */
	MAGISTER(45,35,2,1,11,6, 3,4,1,1,4,2),
	/**
	 * Classe  en melee axe sur les degats
	 */
	ASSASSIN(50,25,6,3,2,9, 4,3,2,2,1,2),
	/**
	 * Soigneur par les plantes
	 */
	DRUIDE(40,40,2,4,10,4, 2,4,1,2,4,2);

	
	/**
	 * Point de vie de base d'une classe
	 */
	private int pv;
	/**
	 * Poitn de vie de base d'une classe
	 */
	private int pm;
	/**
	 * Poitn de force de base d'une classe
	 */
	private int force;
	/**
	 * Point de constitution de base d'une classe
	 */
	private int constitution;
	/**
	 * Poitn d'intelligence de base d'une classe
	 */
	private int intelligence;
	/**
	 * Point de dexterite de base d'une classe
	 */
	private int dexterite;
	/**
	 * nombre de pv que gagne le personnage en montant de niveau
	 */
	private int pvParNiveau;
	/**
	 * nombre de point de mana que gagne le personnage en montant de niveau
	 */
	private int pmParNiveau;
	/**
	 * nombre de point de force que gagne le personnage en montant de niveau
	 */
	private int forceParNiveau;
	/**
	 * nombre de point de constitution que gagne le personnage en montant de niveau
	 */
	private int constitutionParNiveau;
	/**
	 * nombre de point d'intelligence que gagne le personnage en montant de niveau
	 */
	private int intelligenceParNiveau;
	/**
	 * nombre de point de dexterite que gagne le personnage en montant de niveau
	 */
	private int dexteriteParNiveau;
	
	/**
	 * @param pv point de vie de la classe	
	 * @param pm point de mana de la classe
	 * @param force point de force de la classe
	 * @param constitution point de constitution de la classe
	 * @param intelligence point d'intelligence de la classe
	 * @param dexterite point de dexterite de la classe 
	 * @param pvParNiveau point de vie gagner par niveau 
	 * @param pmParNiveau point de mana gagner par niveau
	 * @param forceParNiveau force gagner par niveau
	 * @param constitutionParNiveau constitution gagner par niveau
	 * @param intelligenceParNiveau intelligence gagner par niveau
	 * @param dexteriteParNiveau dexterite gagner par niveau
	 */
	private Classes(int pv, int pm, int force, int constitution, int intelligence, int dexterite, int pvParNiveau,
			int pmParNiveau, int forceParNiveau, int constitutionParNiveau, int intelligenceParNiveau,
			int dexteriteParNiveau)
	{
		this.pv = pv;
		this.pm = pm;
		this.force = force;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.dexterite = dexterite;
		this.pvParNiveau = pvParNiveau;
		this.pmParNiveau = pmParNiveau;
		this.forceParNiveau = forceParNiveau;
		this.constitutionParNiveau = constitutionParNiveau;
		this.intelligenceParNiveau = intelligenceParNiveau;
		this.dexteriteParNiveau = dexteriteParNiveau;
	}


	/**
	 * @return the pv
	 */
	public int obtenirPv()
	{
		return this.pv;
	}

	/**
	 * @return the pm
	 */
	public int obtenirPm()
	{
		return this.pm;
	}

	/**
	 * @return the force
	 */
	public int obtenirForce()
	{
		return this.force;
	}

	/**
	 * @return the constitution
	 */
	public int obtenirConstitution()
	{
		return this.constitution;
	}

	/**
	 * @return the intelligence
	 */
	public int obtenirIntelligence()
	{
		return this.intelligence;
	}

	/**
	 * @return the dexterite
	 */
	public int obtenirDexterite()
	{
		return this.dexterite;
	}

	/**
	 * 
	 * @return gain vie par niveau
	 */
	public int obtenirPvParNiveau() {
		return this.pvParNiveau;
	}

	/**
	 * 
	 * @return gain mana par niveau
	 */
	public int obtenirPmParNiveau() {
		return this.pmParNiveau;
	}

	/**
	 * 
	 * @return gain force par niveau
	 */
	public int obtenirForceParNiveau() {
		return this.forceParNiveau;
	}

	/**
	 * 
	 * @return gain constitution par niveau
	 */
	public int obtenirConstitutionParNiveau() {
		return this.constitutionParNiveau;
	}

	/**
	 * 
	 * @return gain intelligence par niveau
	 */
	public int obtenirIntelligenceParNiveau() {
		return this.intelligenceParNiveau;
	}

	/**
	 * 
	 * @return gain dexterite par niveau
	 */
	public int obtenirDexteriteParNiveau() {
		return this.dexteriteParNiveau;
	}
	
}
