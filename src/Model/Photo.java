package Model;

import javafx.scene.image.Image;

public class Photo {

		private String ImageNom;
		private String ImageUrl;
		
		public Photo (String nom, String url) {
			super();
			this.ImageNom = nom;
			this.ImageUrl = url;
			
		}

		public String getImageNom() {
			return ImageNom;
		}

		public void setImageNom(String imageNom) {
			ImageNom = imageNom;
		}

		public String getImageUrl() {
			return ImageUrl;
		}

		public void setImageUrl(String imageUrl) {
			ImageUrl = imageUrl;
		}

		@Override
		public String toString() {
			return this.ImageNom +";"+ this.ImageUrl;
		}
		
		
	}
