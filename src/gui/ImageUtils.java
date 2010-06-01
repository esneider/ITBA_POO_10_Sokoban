package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Clase con métodos útiles para el manejo de imágenes.
 */
public class ImageUtils {

	/**
	 * Carga una imagen y retorna una instancia de la misma. Si hay algun problema al leer el archivo lanza una excepcion.
	 */
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {
			return ImageIO.read(new File(fileName));
		} else {
			return ImageIO.read(stream);
		}
	}

	/**
	 * Dada una imagen, la rota 90 grados en sentido horario tantas veces como se indique en el parametro times. Retorna una nueva imagen con la
	 * rotación, la imagen original queda intacta.
	 */
	public static Image rotateImage(Image image, int times) {
		BufferedImage aux = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		aux.getGraphics().drawImage(image, 0, 0, null);

		AffineTransform at = new AffineTransform();
		at.rotate(times * 90.0 * Math.PI / 180.0, image.getWidth(null) / 2.0, image.getHeight(null) / 2.0);

		BufferedImageOp bio = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		return bio.filter(aux, null);
	}

	/**
	 * Dada una imagen en escala de grises, la tiñe con el color indicado.
	 */
	public static Image dye(Image image, Color color) {
		BufferedImage result = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		result.getGraphics().drawImage(image, 0, 0, null);

		for (int x = 0; x < image.getWidth(null); x++) {
			for (int y = 0; y < image.getHeight(null); y++) {
				Color c = new Color(result.getRGB(x, y), true);

				if (c.getAlpha() != 0) {
					double r = c.getGreen() / 255.0;
					Color c2 = new Color((int) (r * color.getRed()), (int) (r * color.getGreen()), (int) (r * color.getBlue()));
					result.setRGB(x, y, c2.getRGB());
				}
			}
		}
		return result;

	}

	/**
	 * Aumenta el brillo de una imagen.
	 */
	public static Image increaseBrightness(Image image) {
		BufferedImage result = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		result.getGraphics().drawImage(image, 0, 0, null);

		for (int x = 0; x < image.getWidth(null); x++) {
			for (int y = 0; y < image.getHeight(null); y++) {
				Color c = new Color(result.getRGB(x, y));
				result.setRGB(x, y, new Color(verifyRange(c.getRed() + 100), verifyRange(c.getGreen() + 100),
						verifyRange(c.getBlue() + 100)).getRGB());
			}
		}
		return result;

	}

	private static int verifyRange(double value) {
		if (value < 0) {
			return 0;
		} else if (value > 255) {
			return 255;
		} else {
			return (int) value;
		}
	}
}