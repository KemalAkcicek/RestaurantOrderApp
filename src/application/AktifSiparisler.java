package application;

public class AktifSiparisler {
	private String baslangic;
	private String anaYemek;
	private String tatli;
	private String icecek;

	public AktifSiparisler(String baslangic, String anaYemek, String tatli, String icecek) {
		super();
		this.baslangic = baslangic;
		this.anaYemek = anaYemek;
		this.tatli = tatli;
		this.icecek = icecek;
	}

	public String getBaslangic() {
		return baslangic;
	}

	public void setBaslangic(String baslangic) {
		this.baslangic = baslangic;
	}

	public String getAnaYemek() {
		return anaYemek;
	}

	public void setAnaYemek(String anaYemek) {
		this.anaYemek = anaYemek;
	}

	public String getTatli() {
		return tatli;
	}

	public void setTatli(String tatli) {
		this.tatli = tatli;
	}

	public String getIcecek() {
		return icecek;
	}

	public void setIcecek(String icecek) {
		this.icecek = icecek;
	}

	@Override
	public String toString() {
		return getBaslangic() + "," + getAnaYemek() + "," + getTatli() + "," + getIcecek();
	}

}
