package application;

public class Yemek {

	private String ismi;
	private int fiyat;

	public Yemek(String ismi, int fiyat) {
		this.ismi = ismi;
		this.fiyat = fiyat;
	}
	

	public String getIsmi() {
		return ismi;
	}

	public void setIsmi(String ismi) {
		this.ismi = ismi;
	}

	public int getFiyat() {
		return fiyat;
	}

	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}

	@Override
	public String toString() {
		return getIsmi() + ": Fiyatı:" + getFiyat();
	}

}
