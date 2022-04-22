package gfx;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Debreczeni Mate
 *
 * A jatekban felhasznalt texturak tarolasaert es betolteseert felelo osztaly
 */
public class Assets {
	
	//CHARACTER TEXTURES
	//Hero
	public static BufferedImage[] heroRunDown;
	public static BufferedImage heroDown;
	
	public static BufferedImage heroRight;
	public static BufferedImage[] heroRunRight;
	public static BufferedImage heroLeft;
	public static BufferedImage[] heroRunLeft;
	
	public static BufferedImage heroUp;
	public static BufferedImage[] heroRunUp;
	
	//Enemy
	public static BufferedImage[] enemyRunDown;
	public static BufferedImage enemyDown;
	
	public static BufferedImage enemyRight;
	public static BufferedImage[] enemyRunRight;
	public static BufferedImage enemyLeft;
	public static BufferedImage[] enemyRunLeft;
	
	public static BufferedImage enemyUp;
	public static BufferedImage[] enemyRunUp;
	
	//WallTiles
	public static BufferedImage walltile;
	public static BufferedImage stonetile;
	public static BufferedImage topWallTile;
	public static BufferedImage bottomWallTile;
	public static BufferedImage leftWallTile;
	public static BufferedImage rightWallTile;
	public static BufferedImage topLeftWallCorner;
	public static BufferedImage topRightWallCorner;
	public static BufferedImage bottomLeftWallCorner;
	public static BufferedImage bottomRightWallCorner;
	//CobbleStoneTile
	public static BufferedImage cobbleStoneTile;
	//DoorTiles
	public static BufferedImage openDoorTileTop;
	public static BufferedImage openDoorTileBottom;
	public static BufferedImage openDoorTileLeft;
	public static BufferedImage openDoorTileRight;
	
	public static BufferedImage closedDoorTileTop;
	public static BufferedImage closedDoorTileBottom;
	public static BufferedImage closedDoorTileLeft;
	public static BufferedImage closedDoorTileRight;

	
	//Static Entities
	public static BufferedImage barrel;
	
	//Items
	public static BufferedImage healthPotion;
	public static BufferedImage strengthPotion;

	
	//Enemies
	public static BufferedImage enemy;
	
	//Bullets
	public static BufferedImage[] heroBullets;
	
	public static BufferedImage[] enemyBullets;

	
	public static BufferedImage enemyBulletUp;
	public static BufferedImage enemyBulletDown;
	public static BufferedImage enemyBulletLeft;
	public static BufferedImage enemyBulletRight;
	
	//Buttons
	public static BufferedImage[] playButton;
	public static BufferedImage[] leaderBoardButton;
	public static BufferedImage[] quitButton;
	public static BufferedImage[] backButton;
	
	//Other
	public static BufferedImage heart;
	public static BufferedImage background;

	private static final int TEXTUREWIDTH =32, TEXTUREHEIGHT = 32;
	
	/**
	 * A statikus adattagkent tarolt texturak inicializalasaert felelo fuggveny.
	 */
	public static void init() {
		SpriteSheet characterSheet = new SpriteSheet(TextureLoader.LoadTexture("/textures/characterSheet.png"));
		SpriteSheet IGtextures = new SpriteSheet(TextureLoader.LoadTexture("/textures/IGtextures.png"));
		SpriteSheet UItextures = new SpriteSheet(TextureLoader.LoadTexture("/textures/UItextures.png"));


		//Hero
		heroRunDown = new BufferedImage[2];
		heroDown=characterSheet.crop(0, 0, TEXTUREWIDTH, 48);
		heroRunDown[0]= characterSheet.crop(TEXTUREWIDTH*1, 0, TEXTUREWIDTH, 48);
		heroRunDown[1]= characterSheet.crop(TEXTUREWIDTH*2, 0, TEXTUREWIDTH, 48);
		
		heroUp = characterSheet.crop(TEXTUREWIDTH*3, 0, TEXTUREWIDTH, 48);
		heroRunUp= new BufferedImage[2];
		heroRunUp[0]= characterSheet.crop(TEXTUREWIDTH*4, 0, TEXTUREWIDTH, 48);
		heroRunUp[1]= characterSheet.crop(TEXTUREWIDTH*5, 0, TEXTUREWIDTH, 48);
		
		heroRight = characterSheet.crop(TEXTUREWIDTH*6, 0, TEXTUREWIDTH, 48);
		heroRunRight= new BufferedImage[2];
		heroRunRight[0]= characterSheet.crop(TEXTUREWIDTH*7, 0, TEXTUREWIDTH, 48);
		heroRunRight[1]= characterSheet.crop(0, 48, TEXTUREWIDTH, 48);

		heroLeft = characterSheet.crop(TEXTUREWIDTH, 48, TEXTUREWIDTH, 48);
		heroRunLeft= new BufferedImage[2];
		heroRunLeft[0]= characterSheet.crop(TEXTUREWIDTH*2, 48, TEXTUREWIDTH, 48);
		heroRunLeft[1]= characterSheet.crop(TEXTUREWIDTH*3, 48, TEXTUREWIDTH, 48);
		

		
		//Enemy
		enemyRunDown = new BufferedImage[2];
		enemyRunDown[0]= characterSheet.crop(TEXTUREWIDTH*4, 48, TEXTUREWIDTH, 48);
		enemyRunDown[1]= characterSheet.crop(TEXTUREWIDTH*5, 48, TEXTUREWIDTH, 48);
		enemyDown=characterSheet.crop(TEXTUREWIDTH*6, 48, TEXTUREWIDTH, 48);
		
		enemyRight = characterSheet.crop(TEXTUREWIDTH*7, 48, TEXTUREWIDTH, 48);
		enemyRunRight= new BufferedImage[2];
		enemyRunRight[0]= characterSheet.crop(0, 48*2, TEXTUREWIDTH, 48);
		enemyRunRight[1]= characterSheet.crop(TEXTUREWIDTH, 48*2, TEXTUREWIDTH, 48);

		enemyLeft = characterSheet.crop(TEXTUREWIDTH*2, 48*2, TEXTUREWIDTH, 48);
		enemyRunLeft= new BufferedImage[2];
		enemyRunLeft[0]= characterSheet.crop(TEXTUREWIDTH*3, 48*2, TEXTUREWIDTH, 48);
		enemyRunLeft[1]= characterSheet.crop(TEXTUREWIDTH*4, 48*2, TEXTUREWIDTH, 48);
		
		enemyUp = characterSheet.crop(TEXTUREWIDTH*5, 48*2, TEXTUREWIDTH, 48);
		enemyRunUp= new BufferedImage[2];
		enemyRunUp[0]= characterSheet.crop(TEXTUREWIDTH*6, 48*2, TEXTUREWIDTH, 48);
		enemyRunUp[1]= characterSheet.crop(TEXTUREWIDTH*7, 48*2, TEXTUREWIDTH, 48);
		
		//OTHER
		heart =  IGtextures.crop(64, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		background = TextureLoader.LoadTexture("/textures/background.png");

		//TILES
		//Walltiles
		topWallTile = IGtextures.crop(96, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		bottomWallTile = IGtextures.crop(192, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		leftWallTile = IGtextures.crop(32, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		rightWallTile = IGtextures.crop(0, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		topLeftWallCorner = IGtextures.crop(192, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		topRightWallCorner = IGtextures.crop(224, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		bottomLeftWallCorner = IGtextures.crop(96, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		bottomRightWallCorner = IGtextures.crop(64, 32, TEXTUREWIDTH, TEXTUREHEIGHT);

		//CobbleStoneTile
		cobbleStoneTile = IGtextures.crop(128, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		
		//Doortiles
		closedDoorTileTop = IGtextures.crop(128, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		closedDoorTileBottom = IGtextures.crop(224, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		closedDoorTileLeft = IGtextures.crop(96, 64, TEXTUREWIDTH, TEXTUREHEIGHT);
		closedDoorTileRight = IGtextures.crop(32, 64, TEXTUREWIDTH, TEXTUREHEIGHT);

		openDoorTileTop = IGtextures.crop(160, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		openDoorTileBottom = IGtextures.crop(0, 64, TEXTUREWIDTH, TEXTUREHEIGHT);
		openDoorTileLeft = IGtextures.crop(128, 64, TEXTUREWIDTH, TEXTUREHEIGHT);
		openDoorTileRight = IGtextures.crop(64, 64, TEXTUREWIDTH, TEXTUREHEIGHT);

		
		//STATIC ENTITIES
		//Barrel
		barrel = IGtextures.crop(160, 32, TEXTUREWIDTH, TEXTUREHEIGHT);
		
		//ITEMS
		//Healthpotion
		healthPotion = IGtextures.crop(0, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
		//StrengthPotion
		strengthPotion= IGtextures.crop(32, 0, TEXTUREWIDTH, TEXTUREHEIGHT);
						
		//BULLETS
		heroBullets = new BufferedImage[4];
		heroBullets[0]= IGtextures.crop(160, 64, TEXTUREWIDTH, TEXTUREHEIGHT);
		heroBullets[1]= IGtextures.crop(192, 64, TEXTUREWIDTH, TEXTUREHEIGHT);
		heroBullets[2]= IGtextures.crop(224, 64, TEXTUREWIDTH, TEXTUREHEIGHT);
		heroBullets[3]= IGtextures.crop(0, 96, TEXTUREWIDTH, TEXTUREHEIGHT);

		enemyBullets = new BufferedImage[4];
		enemyBullets[0]=IGtextures.crop(32, 96, TEXTUREWIDTH, TEXTUREHEIGHT);
		enemyBullets[1]=IGtextures.crop(64, 96, TEXTUREWIDTH, TEXTUREHEIGHT);
		enemyBullets[2]= IGtextures.crop(96, 96, TEXTUREWIDTH, TEXTUREHEIGHT);
		enemyBullets[3]= IGtextures.crop(128, 96, TEXTUREWIDTH, TEXTUREHEIGHT);

		//BUTTONS
		playButton = new BufferedImage[2];
		playButton[0]= UItextures.crop(0, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
		playButton[1]= UItextures.crop(32, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);

		leaderBoardButton= new BufferedImage[2];
		leaderBoardButton[0]= UItextures.crop(128, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
		leaderBoardButton[1]= UItextures.crop(160, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
		
		quitButton= new BufferedImage[2];
		quitButton[0]= UItextures.crop(192, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
		quitButton[1]= UItextures.crop(224, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
		
		backButton= new BufferedImage[2];
		backButton[0]= UItextures.crop(64, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
		backButton[1]= UItextures.crop(96, 0, TEXTUREWIDTH, TEXTUREHEIGHT/2);
	}
}
