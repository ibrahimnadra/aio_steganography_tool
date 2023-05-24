package imageInImageStego;

public class ImageHider {

	private ImageWriteReader imgWriteReader;
	private ImageSteganography steganographer;

	public ImageHider() {
		this.imgWriteReader = new ImageWriteReader();
		this.steganographer = new ImageSteganography();
	}

	public void hide(String canvasImageUrl, String secretImageUrl, String outputUrl) {
		this.imgWriteReader.writeImage(this.steganographer.encode(
				this.imgWriteReader.readImage(canvasImageUrl),
				this.imgWriteReader.readImage(secretImageUrl)),
				outputUrl);
		Messages.IMAGE_HIDDEN_SUCCESSFULLY.println();
	}

	public void reveal(String canvasImageUrl, String outputUrl) {
		this.imgWriteReader.writeImage(this.steganographer.decode(
				this.imgWriteReader.readImage(canvasImageUrl)),
				outputUrl);
		Messages.IMAGE_EXTRACTED_SUCCESSFULLY.println();
	}

}
