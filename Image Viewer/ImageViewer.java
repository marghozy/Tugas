import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

/**
 * ImageViewer is the main class of the image viewer application.
 * It builds and displays the application GUI and initializes all other components.
 * 
 * @author Michael
 * @version 1.01
 */
public class ImageViewer {
    private static final String VERSION = "Version 1.01";
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    private JFrame frame;
    private ImagePanel imagePanel;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    private OFImage currentImage;

    /**
     * Create an ImageViewer and show it on screen.
     */
    public ImageViewer() {
        currentImage = null;
        makeFrame();
    }

    /**
     * Open a file chooser to select a new image file.
     */
    private void openFile() {
        int returnVal = fileChooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                currentImage = ImageFileManager.loadImage(selectedFile);
                imagePanel.setImage(currentImage);
                showFilename(selectedFile.getPath());
                showStatus("File loaded.");
                frame.pack();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame,
                        "The file was not in a recognized image file format.",
                        "Image Load Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Close the current image.
     */
    private void close() {
        currentImage = null;
        imagePanel.clearImage();
        showFilename(null);
    }

    /**
     * Quit the application.
     */
    private void quit() {
        System.exit(0);
    }

    /**
     * Make the picture darker.
     */
    private void makeDarker() {
        if (currentImage != null) {
            currentImage.darker();
            frame.repaint();
            showStatus("Applied: darker");
        } else {
            showStatus("No image loaded.");
        }
    }

    /**
     * Make the picture lighter.
     */
    private void makeLighter() {
        if (currentImage != null) {
            currentImage.lighter();
            frame.repaint();
            showStatus("Applied: lighter");
        } else {
            showStatus("No image loaded.");
        }
    }

    /**
     * Show "About" dialog.
     */
    private void showAbout() {
        JOptionPane.showMessageDialog(frame,
                "ImageViewer " + VERSION,
                "About ImageViewer",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display a filename on the appropriate label.
     */
    private void showFilename(String filename) {
        filenameLabel.setText(filename != null ? "File: " + filename : "No file selected");
    }

    /**
     * Display a status message in the status bar.
     */
    private void showStatus(String text) {
        statusLabel.setText(text);
    }

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame() {
        frame = new JFrame("Image Viewer");
        imagePanel = new ImagePanel();
        filenameLabel = new JLabel("No file selected");
        statusLabel = new JLabel(VERSION);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.add(filenameLabel, BorderLayout.NORTH);
        contentPane.add(imagePanel, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        makeMenuBar(frame);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Create the menu bar.
     */
    private void makeMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());
        fileMenu.add(openItem);

        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.addActionListener(e -> close());
        fileMenu.add(closeItem);

        fileMenu.addSeparator();

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> quit());
        fileMenu.add(quitItem);

        // Filter menu
        JMenu filterMenu = new JMenu("Filter");
        menuBar.add(filterMenu);

        JMenuItem darkerItem = new JMenuItem("Darker");
        darkerItem.addActionListener(e -> makeDarker());
        filterMenu.add(darkerItem);

        JMenuItem lighterItem = new JMenuItem("Lighter");
        lighterItem.addActionListener(e -> makeLighter());
        filterMenu.add(lighterItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        frame.setJMenuBar(menuBar);
    }

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        new ImageViewer();
    }
}
