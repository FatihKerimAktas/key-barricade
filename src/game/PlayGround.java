package game;

import gameobject.Barricade;
import gameobject.GameKey;
import gameobject.Wall;
import player.Player;
import gameobject.GameObject;
import menu.LevelSelect;
import static menu.LevelSelect.levelNumber;
import tile.EndTile;
import tile.Tile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import menu.MainMenu;

public class PlayGround extends JComponent {

    public Player player;
    public List<GameObject> gameObject = new ArrayList<>();         // gameObjects are in 1 list now
    public Tile[][] tiles;                                          // Tiles are stored in a 2D Array

    public PlayGround() {
        setFocusable(true);
        addKeyListener(new SelectionListener());
        setupGame();
        setPreferredSize(new Dimension( tiles.length * Tile.ICON_WIDTH + Tile.BOARD_X_OFFSET * 2,
                tiles.length * Tile.ICON_HEIGHT + Tile.BOARD_Y_OFFSET * 2));
    }

    public void setupGame() {

        String fileName = LevelSelect.levelchoice + ".txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // If the file unreachable or the game is 'broke' it will not directly store it into this.tiles
            Tile[][] tiles = null; // Array to store the level into.
            int x = 0;  // The current x position.
            int y = 0;  // The current y position.
            int size = 0; // The size of the playfield (grid).

            String line = null;
            while ((line = bufferedReader.readLine()) != null) { // Read each line of the file.
                String[] split = line.split("\\."); // Split the line by '.'.
                if (y == 0) { // If this is the first line
                    size = split.length; // Set the size based on how many splits there are
                    tiles = new Tile[size][size]; // Create the tiles based on the saved size
                } else if (size != split.length) { // If this isn't the first line and the size doesn't equal the split length.
                    throw new IOException("Invalid level format."); // Throw an error.
                }

                x = 0; // Reset x position to 0.
                for (String s : split) { // Loop through each split.
                    switch (s) { // Switch on each split.
                        // TILE, ENDTILE, WALL
                        case "TILE": // It's a TILE.
                            tiles[x][y] = new Tile(x, y); // Create a Tile and store it into the current x,y position in tiles.
                            break;
                        case "ENDTILE": // It's an EndTile.
                            tiles[x][y] = new EndTile(x, y); // Create an EndTile and store it into the current x,y position in tiles.
                            break;
                        case "WALL": { // It's a Wall.
                            Tile tile = new Tile(x, y); // Create a Tile
                            tile.setGameObject(new Wall()); // Set the gameObject to a Wall.
                            tiles[x][y] = tile; // Store the tile into the current x,y position in tiles
                            break;
                        }
                        case "PLAYER": { // It's a Player.
                            // set player at the position ? Yep, however, you would still need to create a tile at the
                            Tile tile = new Tile(x, y); // Create a Tile
                            player = new Player(this, tile.x, tile.y);
                            tiles[x][y] = tile;
                            break;
                        }

                        // KEY#, BARRICADE#
                        default: // Isn't any of the above.
                            if (s.startsWith("KEY")) { // It starts with "KEY".
                                //       s = "KEY100"
                                //            ^^^
                                // s.substring(3); // Returns "100";
                                int gameKeyNumber = Integer.parseInt(s.substring(3));  // Parse the String into an int
                                Tile tile = new Tile(x, y); // Create a Tile
                                tile.setGameObject(new GameKey(gameKeyNumber)); // Set the gameObject to GameKey with the keyNumber from the parsed String
                                tiles[x][y] = tile; // Store it in the current x,y position in tiles
                            } else if (s.startsWith("BARRICADE")) { // "BARRICADE",,,
                                int barricadeNumber = Integer.parseInt(s.substring(9)); // Future use.
                                Tile tile = new Tile(x, y); // Create a Tile...
                                tile.setGameObject(new Barricade(barricadeNumber)); // Set the gameObject to Barricade with the barricadeNumber from the parsed String
                                tiles[x][y] = tile; // Store it in the current x,y position in tiles
                            } else {
                                // Throw an error if one or more of the cases don't match.
                                throw new IOException("Invalid level format.");
                            }
                            break;
                    }
                    x++; // Increment the x position.
                }
                y++; // Increment the y position.
            }
            this.tiles = tiles; // Level loaded, set the loaded tiles to this.tiles.
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    @Override
    public void paintComponent(Graphics g) {

        // Draw all tiles
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                tile1.draw(g);
            }
        }

        // Draw player
        player.draw(g);

    }

    class SelectionListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent event) {
            String key = KeyStroke.getKeyStrokeForEvent(event).toString();
            key = key.replace("pressed ", "");
            switch (key) {
                case "SPACE": {
                    Tile currentPos = getTile(player.getX(), player.getY());    // Get the tile where the player is standing on
                    // If the player is standing on a gameObject and the gameObject is a GameKey
                    if (currentPos.getGameObject() != null && currentPos.getGameObject() instanceof GameKey) {
                        // Get the current GameObject at the current position of the player and cast it to a GameKey.
                        GameKey gameKey = (GameKey) currentPos.getGameObject();
                        // Get the pin of the GameKey and set the pin in player.
                        player.setKeyPinPickedUp(gameKey.getGameKeyPin());
                        currentPos.setGameObject(null); // Remove the gameObject (GameKey)
                    }
                    break;
                }
                case "U":
                    Tile currentPos = null;

                    if (player.current == player.playerUp) {
                        currentPos = getTile(player.getX(), player.getY() - 1);
                    } else if (player.current == player.playerDown) {
                        currentPos = getTile(player.getX(), player.getY() + 1);
                    } else if (player.current == player.playerLeft) {
                        currentPos = getTile(player.getX() - 1, player.getY());
                    } else if (player.current == player.playerRight) {
                        currentPos = getTile(player.getX() + 1, player.getY());
                    }
                    if (currentPos.getGameObject() != null && currentPos.getGameObject() instanceof Barricade) {
                        Barricade barricade = (Barricade) currentPos.getGameObject();   // cast gameObject to Barricade
                        if (player.getKeyPinPickedUp() == barricade.getBarricadePin()) { // If the GamePin equals to the BarricadePin
                            currentPos.setGameObject(null); // Remove the gameObject (Barricade)
                        } else {
                            JFrame popUp = new JFrame();
                            popUp.setTitle("Key Barricade");
                            JLabel textlevel = new JLabel("Verkeerde Sleutel!");

                            popUp.add(textlevel);
                            textlevel.setLocation (100,100);
                            textlevel.setSize(100, 100);

                            popUp.pack();
                            popUp.setVisible(true);
                            popUp.setLocationRelativeTo(null);
                        }
                    }
                    break;
                case "UP":
                    player.moveUp();
                    break;
                case "DOWN":
                    player.moveDown();
                    break;
                case "LEFT":
                    player.moveLeft();
                    break;
                case "RIGHT":
                    player.moveRight();
                    break;
            }
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    public static void setupPlayGround() {
        JFrame frame = new JFrame();
        frame.setTitle("Key Barricade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JLabel textlevel = new JLabel(LevelSelect.levelchoice);
        JButton retry = new JButton("Restart");
        JButton mainMenu = new JButton("Main menu");
        JButton nextLevel = new JButton("Next level");
        JButton prevLevel = new JButton("Previous level");

        panel.add(textlevel);
        panel.add(retry);
        panel.add(mainMenu);
        panel.add(nextLevel);
        panel.add(prevLevel);

        retry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                PlayGround.setupPlayGround();
            }
        });

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MainMenu.create_mainmenu();
            }
        });

        nextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (levelNumber < 5 && levelNumber > 0) {
                    levelNumber = levelNumber + 1;
                    LevelSelect.levelchoice = "level" + levelNumber;
                    frame.setVisible(false);
                    PlayGround.setupPlayGround();
                }
            }
        });
        prevLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (levelNumber > 1) {
                    levelNumber = levelNumber - 1;
                    LevelSelect.levelchoice = "level" + levelNumber;
                    frame.setVisible(false);
                    PlayGround.setupPlayGround();
                }
            }
        });

        JComponent chart = new PlayGround();

        frame.add(panel, BorderLayout.NORTH);
        frame.add(chart, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        if(Player.levelwon < 1) {
            frame.setVisible(false);
        }

        chart.requestFocus();
    }
}
