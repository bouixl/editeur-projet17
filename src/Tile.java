import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Tile
{
	MUR_PIERRE("textures/tiles/mur_pierre.png"),			// 1
	SOL_PIERRE_1("textures/tiles/sol_pierre_1.png"),		// 2
	SOL_PIERRE_2("textures/tiles/sol_pierre_2.png"),		// 3
	SOL_BOIS("textures/tiles/sol_bois.png"),				// 4
	SOL_TROU("textures/tiles/sol_trou.png"),				// 5
	ROCHER("textures/tiles/rocher.png"),					// 1bis
	TABLE_CASSE("textures/tiles/table_casse.png"),			// 4bis
	TABLE("textures/tiles/table.png"),						// 2bis
	TABOURET_CASSE("textures/tiles/tabouret_casse.png"),	// 5bis
	TABOURET("textures/tiles/tabouret.png"),				// 3bis
	TAS_PIERRE("textures/tiles/tas_pierre.png"),			// 6bis
	TOMBE("textures/tiles/tombe.png"),						// 7bis
	OMBRE("textures/tiles/ombre.png"),						// --
	VIDE("textures/tiles/vide.png");						// 9
	
	private BufferedImage apparence;
	private final String url;
			
	private Tile(String url)
	{
		this.apparence = null;
		try {
			this.apparence = ImageIO.read(new File(url));
		} catch (IOException e) {
		}
		this.url = url;
	}
	
	public BufferedImage obtenirApparence()
	{
		return this.apparence;
	}
	public String obtenirUrl()
	{
		return this.url;
	}
	public boolean estBloquant()
	{
		return false;
	}
}
