
public enum Direction {
	HAUT("haut",3,-1,0),
	BAS("bas",0,1,0),
	GAUCHE("gauche",1,0,-1),
	DROITE("droite",2,0,1);
	
	private final String nom;
	private final int indexTexture;
	private final int offsetLigne;
	private final int offsetColonne;
	
	private Direction(String nom, int indexTexture, int offsetLigne, int offsetColonne)
	{
		this.nom = nom;
		this.indexTexture = indexTexture;
		this.offsetLigne = offsetLigne;
		this.offsetColonne = offsetColonne;
	}
	
	public String obtenirNom()
	{
		return this.nom;
	}
	
	public int obtenirIndexTexture()
	{
		return this.indexTexture;
	}
	
	public int obtenirOffsetColonne()
	{
		return this.offsetColonne;
	}
	
	public int obtenirOffsetLigne()
	{
		return this.offsetLigne;
	}
}
