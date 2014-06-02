import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Equipe
{
	private Position position;
	private Direction direction;
	private BufferedImage apparence;
	
	public Equipe()
	{
		this.position = new Position(0,0);
		this.direction = Direction.BAS;
		this.apparence = null;
		try {
			this.apparence = ImageIO.read(new File("textures/personnages/equipe.png"));
		} catch (IOException e) {
		}
	}
	
	public Position obtenirPosition()
	{
		return this.position;
	}

	public void deplacer(Direction direction)
	{
		this.direction = direction;
		this.position = this.position.ajouterOffset(direction);
	}
	public void changerDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	public Direction obtenirDirection()
	{
		return this.direction;
	}
	
	public BufferedImage obtenirApparence()
	{
		BufferedImage img_equipe_decoupe = new BufferedImage(apparence.getWidth(null), apparence.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D bGr = img_equipe_decoupe.createGraphics();
	    bGr.drawImage(apparence, 0, 0, null);
	    bGr.dispose();

		return img_equipe_decoupe.getSubimage(0,this.direction.obtenirIndexTexture()*Application.HAUTEUR_TILE,Application.LARGEUR_TILE, Application.HAUTEUR_TILE);
	}

	public void forcerPosition(Position position) {
		this.position = position;
	}
	
	
}

